package service;

import java.io.IOException;
import java.lang.reflect.Field;

import jxl.read.biff.BiffException;
import models.ExternalFactors;
import models.ParentTask;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;

public class StatTask {
	public double dispersion;
	public double min;
	public double max;
	public double standartDeviation;
	public int[] distribution = new int[20];
	public double[] borders = { 0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4,
			0.45, 0.5, 0.55, 0.6, 0.65, 0.7, 0.75, 0.8, 0.85, 0.9, 0.95, 1 };
	public double[] bordersActual = new double[borders.length];

	public String[] factors = { "ум и опыт", "мотивация", "организация кода",
			"понятность кода",
			"удобство и производительность ПО, компьютеров и инфраструктуры",
			"условия труда", "синергия", "стабильность внешних модулей",
			"отсутствие форс-мажоров", "взаимопонимание с клиентом",
			"слаженность действий команды" };
	public double IE;
	public double motivation;
	public double codeOrg;
	public double codeUnd;
	public double tools;
	public double laborCond;
	public double synergy;
	public double fm;
	public double clientUnd;
	public double extChange;
	public double coord;
	public String[] realFactors = { "IE", "motivation", "codeOrg", "codeUnd",
			"tools", "laborCond", "synergy", "extChange", "fm", "clientUnd",
			"coord" };

	double[] minfactors = new double[factors.length];
	double[] maxfactors = new double[factors.length];

	public void calculateStat(double[] values) {
		Variance var = new Variance();
		this.dispersion = var.evaluate(values);
		Min var2 = new Min();
		this.min = var2.evaluate(values);
		Max var3 = new Max();
		this.max = var3.evaluate(values);
		StandardDeviation sd = new StandardDeviation();
		this.standartDeviation = sd.evaluate(values);
		this.getDistribution(values);
		this.calculateBordersActual();
	}

	public void calculateBordersActual() {
		for (int i = 0; i < borders.length; i++) {
			bordersActual[i] = min + borders[i] * (max - min);
		}
	}

	public void getMinMax() throws BiffException, IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		double aaa=0;
		String[] bbb = new String[11];
		double[] ccc = new double[11];
		int it = 3000;
		TaskBuilder tb = new TaskBuilder();
		double res1 = 0;
		double res2 = 0;
		double sum = 0;
		double[] forv = new double[it];
		for (int i = 0; i < realFactors.length; i++) {
			tb.exf = tb.getExtAll5();
			Class<?> cl = tb.exf.getClass();
			Field field = cl.getField(realFactors[i]);
			
			field.setDouble(tb.exf, 0.01);
			
			for (int ii = 0; ii < it; ii++) {
				ParentTask task = tb.build(10);
				TaskProcess p = new TaskProcess(task);
				p.calculateTask();
				forv[ii] = p.task.time;
			}
			sum = TaskProcess.getSumArray(forv);
			res1=sum/it;
			
			field.setDouble(tb.exf, 9.99);
			for (int ii = 0; ii < it; ii++) {
				ParentTask task = tb.build(10);
				TaskProcess p = new TaskProcess(task);
				p.calculateTask();
				forv[ii] = p.task.time;
			}
			sum = TaskProcess.getSumArray(forv);
			res2=sum/it;
			p(factors[i]+": "+(res1-res2));
			aaa+=(res1-res2);
			ccc[i]=(res1-res2);
			bbb[i]=factors[i]+" diff: ";
			
		}
		p("");
		p(aaa);
		for (int g=0;g<ccc.length;g++){
			p(bbb[g]+ccc[g]/aaa);
		}

	}

	public void getDistribution(double[] val) {
		for (int i = 0; i < val.length; i++) {
			for (int ii = 0; ii < borders.length; ii++) {
				if (val[i] <= min + borders[ii] * (max - min)) {
					distribution[ii]++;
					break;
				}
			}
		}
	}
	
	public static void p(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) throws BiffException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException{
		StatTask s = new StatTask();
		s.getMinMax();
	}
}
