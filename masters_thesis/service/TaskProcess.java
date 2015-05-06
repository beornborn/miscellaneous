package service;

import java.util.Random;

import models.Iteration;
import models.ParentTask;

public class TaskProcess {
	double measure = 10;
	public ParentTask task;

	public TaskProcess(ParentTask taskOriginal) {
		task = taskOriginal;
	}

	public void calculateTask() {
		task.calculateParentTime(this);
	}

	public double calculateExtraTime(ParentTask task) {
		double clientUndMul = getRandBetween2(
				0.9 * (measure - task.ext.clientUnd) * 0.02,
				1.1 * (measure - task.ext.clientUnd) * 0.02);
		double extChangeMul = getRandBetween2(
				0.9 * (measure - task.ext.extChange) * 0.01,
				1.1 * (measure - task.ext.extChange) * 0.01);
		double fmdMul = getRandBetween2(0.9 * (measure - task.ext.fm) * 0.01,
				1.1 * (measure - task.ext.fm) * 0.01);
		double coordMul = getRandBetween2(
				0.9 * (measure - task.ext.coord) * 0.02,
				1.1 * (measure - task.ext.coord) * 0.02);
		double res = 1 + clientUndMul + extChangeMul + fmdMul + coordMul;
		return res;
	}

	public double getSuccess(ParentTask task) {
		double res = getIESuccess(task) * getMultipleFactorsSuccess(task);
//		res = 1;
		return res;
	}

	public double getTimeMul(ParentTask task) {
		double res = getIETime(task) * getMultipleFactorsTime(task)
				* getRandBetween2(0.9, 1.1);
//		res = 1;
		return res;
	}

	public double getMultipleFactorsTime(ParentTask task) {
		double sum = task.ext.codeUndWeight + task.ext.codeOrgWeight
				+ task.ext.laborCondWeight + task.ext.motivationWeight
				+ task.ext.toolsWeight;
		double codeDiffWeightSl = (1-(task.ext.codeUnd / measure))
				* (task.ext.codeUndWeight / sum);
		double codeOrgWeightSl = (1-(task.ext.codeOrg / measure))
				* (task.ext.codeOrgWeight / sum);
		double laborCondWeightSl = (1-(task.ext.laborCond / measure))
				* (task.ext.laborCondWeight / sum);
		double motivationWeightSl = (1-(task.ext.motivation / measure))
				* (task.ext.motivationWeight / sum);
		double toolsWeightSl = (1-(task.ext.tools / measure))
				* (task.ext.toolsWeight / sum);
		double res = 1+0.4*(codeDiffWeightSl + codeOrgWeightSl
				+ laborCondWeightSl + motivationWeightSl + toolsWeightSl);
		Random rnd = new Random();
		double rndMul = 0.6 + 0.7 * Math.abs(rnd.nextDouble());
//		System.out.println(res);
		res *= rndMul;
		return res;
	}
	
	

	public double getMultipleFactorsSuccess(ParentTask task) {
		double measure = 10;
		double sum = task.ext.codeOrgWeight + task.ext.laborCondWeight
				+ task.ext.motivationWeight;
		double codeOrgWeightSl = (task.ext.codeOrg / measure)
				* (task.ext.codeOrgWeight / sum);

		double laborCondWeightSl = (task.ext.laborCond / measure)
				* (task.ext.laborCondWeight / sum);

		double motivationWeightSl = task.ext.motivation < 5 ? (task.ext.motivation / measure)
				* (task.ext.motivationWeight / sum)
				: task.ext.motivationWeight / sum;
		double res = 0.4+0.4*(codeOrgWeightSl + laborCondWeightSl + motivationWeightSl);
		Random rnd = new Random();
		double rndMul = 0.9 + 0.2 * Math.abs(rnd.nextDouble());
		res *= rndMul;
		return res;
	}

	public double getIETime(ParentTask task) {
		double diff = task.ext.IE - task.difficulty;
		double effPercent = getEffPercent(task);
		double ieMult = 0;
		if (diff < 0) {
			ieMult = 1 + getRandBetween2(0, (1 - effPercent))*1.3;
		} else {
			ieMult = getRandBetween2(0, (1 - effPercent) * 0.3) + 1;
		}
		return ieMult;
	}

	public double getIESuccess(ParentTask task) {
		double diff = task.ext.IE - task.difficulty;
		double effPercent = getEffPercent(task);
		double ieMult = 0;
		if (diff < 0) {
			ieMult = getRandBetween2((task.ext.synergy / measure) * 0.6
					+ (0.8 - (task.ext.synergy / measure) * 0.6)
					* effPercent, 0.8);
		} else {
			ieMult = 0.8 + getRandBetween2(effPercent * 0.2, 0.2);
		}
		return ieMult;
	}

	public double getEffPercent(ParentTask task) {
		double diff = task.ext.IE - task.difficulty;
		double res;
		if (diff < 0) {
			res = 1 - Math.abs(diff / 2);
		} else {
			res = diff / task.ext.IE;
		}
		return -0.43 + 1.29 * (Math.sqrt(1 + res) / 3);
	}

	public double getRandBetween2(double left, double right) {
		Random rnd = new Random();
		double res = left + (Math.abs(rnd.nextDouble()) * 100000)
				% (right - left);
		return res;
	}

	public Iteration[] getCalculatedArrTime(Iteration[] temp, ParentTask task) {
		for (int i = 0; i < temp.length; i++) {
			temp[i].time = getCalculatedTime(temp[i].time, task);
		}
		return temp;
	}

	public double getCalculatedTime(double temp, ParentTask task) {
		Random rnd = new Random();
		double res = temp;
		res *= this.getTimeMul(task);
		int count =1;
		while (rnd.nextDouble() > this.getSuccess(task)) {
			res += temp * this.getTimeMul(task);
			count++;
			if (count==4) break;
		}
		return res;
	}

	public static double getSumArray(double[] arr) {
		double res = 0;
		for (int i = 0; i < arr.length; i++) {
			res += arr[i];
		}
		return res;
	}

	public static double getOneOfPartTime(Iteration[] temp) {
		double res = 0;
		for (int i = 0; i < temp.length; i++) {
			res += temp[i].time;
		}
		return res;
	}

	public static void main(String[] args) {
		double sum1 = 0;
		double sum2 = 0;
		double a = Math.sqrt(5);
		for (double i = 0; i < 10; i += 1) {
			System.out.println(-0.43 + 1.29 * (Math.sqrt(1 + i) / 3));
			if (-0.43 + 1.29 * (Math.sqrt(1 + i) / 3) < 0.5) {
				sum1++;
			} else
				sum2++;

		}
		System.out.println(sum1);
		System.out.println(sum2);
	}
}








