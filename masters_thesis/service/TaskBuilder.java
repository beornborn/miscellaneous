package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import models.ChildTask;
import models.ExternalFactors;
import models.Iteration;
import models.ParentTask;
import models.Task;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class TaskBuilder {
	int numberIter = 3;
	ExternalFactors exf;
	double[] mockup = { 15.0, 40.0, 8.0, 7.0 };
	Task task;

	public ParentTask build(int hours) throws BiffException, IOException {
		int rand;
		ParentTask parTask = new ParentTask();

		parTask.time = hours;
		parTask.partTime = recalculatePartTime(parTask.time, mockup);
		parTask.difficulty = genGauss10();
		if (exf == null) {
			parTask.ext = getExtFromExcel();
			exf = parTask.ext;
		} else {
			parTask.ext = exf;
		}
		//parTask.ext=genExt();
		while (parTask.difficulty - 2 > parTask.ext.IE) {
			parTask.difficulty = genGauss10();
		}

		Random rnd = new Random();
		rand = 2 + rnd.nextInt(numberIter);
		parTask.solving = new Iteration[rand];
		for (int i = 0; i < rand; i++) {
			parTask.solving[i] = buildIteration(parTask.partTime[0] / rand);
		}

		rand = 2 + rnd.nextInt(numberIter);
		parTask.testing = new Iteration[rand];
		for (int i = 0; i < rand; i++) {
			parTask.testing[i] = buildIteration(parTask.partTime[2] / rand);
		}

		rand = 2 + rnd.nextInt(numberIter);
		parTask.refactoring = new Iteration[rand];
		for (int i = 0; i < rand; i++) {
			parTask.refactoring[i] = buildIteration(parTask.partTime[3] / rand);
		}

		rand = 2 + rnd.nextInt(numberIter);
		parTask.childTasks = new ChildTask[rand];
		for (int i = 0; i < rand; i++) {
			parTask.childTasks[i] = build(parTask.partTime[1] / rand, parTask);
		}
		return parTask;
	}

	public ChildTask build(double f, ParentTask partask) {
		int rand;
		ChildTask chiTask = new ChildTask();
		chiTask.parentTask = partask;
		chiTask.time = f;

		chiTask.partTime = recalculatePartTime(chiTask.time, mockup);
		Random rnd = new Random();
		rand = 2 + rnd.nextInt(numberIter);
		chiTask.solving = new Iteration[rand];
		for (int i = 0; i < rand; i++) {
			chiTask.solving[i] = buildIteration(chiTask.partTime[0] / rand);
		}

		rand = 2 + rnd.nextInt(numberIter);
		chiTask.development = new Iteration[rand];
		for (int i = 0; i < rand; i++) {
			chiTask.development[i] = buildIteration(chiTask.partTime[1] / rand);
		}

		rand = 2 + rnd.nextInt(numberIter);
		chiTask.testing = new Iteration[rand];
		for (int i = 0; i < rand; i++) {
			chiTask.testing[i] = buildIteration(chiTask.partTime[2] / rand);
		}

		rand = 2 + rnd.nextInt(numberIter);
		chiTask.refactoring = new Iteration[rand];
		for (int i = 0; i < rand; i++) {
			chiTask.refactoring[i] = buildIteration(chiTask.partTime[3] / rand);
		}

		return chiTask;
	}

	public Iteration buildIteration(double time) {
		Iteration iter = new Iteration();
		iter.time = time;
		return iter;
	}

	public double[] recalculatePartTime(double time, double[] percIn) {
		double[] perc = percIn.clone();
		double rest = 100;
		Random rnd = new Random();
		double[] tempRands = new double[perc.length];
		double sumTempRands = 0;
		double sumRes = 0;
		for (int i = 0; i < perc.length; i++) {
			rest -= perc[i];
		}
		for (int i = 0; i < perc.length; i++) {
			tempRands[i] = (double) Math.abs(rnd.nextInt() % 30);
			sumTempRands += tempRands[i];
		}
		for (int i = 0; i < perc.length; i++) {
			perc[i] += tempRands[i] * rest / sumTempRands;
			sumRes += perc[i];
		}
		perc[1] += 100 - sumRes;

		for (int i = 0; i < perc.length; i++) {
			perc[i] *= time / 100;
		}
		return perc;

	}

	public double genGauss10() {
		Random rnd = new Random();
		double number = rnd.nextGaussian();
		while (Math.abs(number) > 2) {
			number = rnd.nextGaussian();
		}
		return 2.5 * (2 + number);
	}

	public ExternalFactors genExt() {
		ExternalFactors ext = new ExternalFactors();
		ext.IE = genGauss10();
		ext.motivation = genGauss10();
		ext.codeOrg = genGauss10();
		ext.codeUnd = genGauss10();
		ext.tools = genGauss10();
		ext.laborCond = genGauss10();
		ext.synergy = genGauss10();
		ext.fm = genGauss10();
		ext.clientUnd = genGauss10();
		ext.extChange = genGauss10();
		ext.coord = genGauss10();
		ext.motivationWeight = genGauss10();
		ext.codeOrgWeight = genGauss10();
		ext.codeUndWeight = genGauss10();
		ext.toolsWeight = genGauss10();
		ext.laborCondWeight = genGauss10();
		return ext;
	}

	public ExternalFactors getExtFromExcel() throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(new File("data.xls"));
		Sheet sheet = workbook.getSheet(1);
		ExternalFactors ext = new ExternalFactors();
		ext.IE = getDoubleFromCell(1, 1, sheet);
		ext.motivation = getDoubleFromCell(1, 2, sheet);
		ext.codeOrg = getDoubleFromCell(1, 3, sheet);
		ext.codeUnd = getDoubleFromCell(1, 4, sheet);
		ext.tools = getDoubleFromCell(1, 5, sheet);
		ext.laborCond = getDoubleFromCell(1, 6, sheet);
		ext.synergy = getDoubleFromCell(1, 7, sheet);
		ext.fm = getDoubleFromCell(1, 8, sheet);
		ext.clientUnd = getDoubleFromCell(1, 9, sheet);
		ext.extChange = getDoubleFromCell(1, 10, sheet);
		ext.coord = getDoubleFromCell(1, 11, sheet);
		ext.motivationWeight = getDoubleFromCell(2, 2, sheet);
		ext.codeOrgWeight = getDoubleFromCell(2, 3, sheet);
		ext.codeUndWeight = getDoubleFromCell(2, 4, sheet);
		ext.toolsWeight = getDoubleFromCell(2, 5, sheet);
		ext.laborCondWeight = getDoubleFromCell(2, 6, sheet);
		workbook.close();
		return ext;
	}
	
	public ExternalFactors getExtAll5() throws BiffException, IOException {
		ExternalFactors ext = new ExternalFactors();
		ext.IE = 5;
		ext.motivation = 5;
		ext.codeOrg = 5;
		ext.codeUnd = 5;
		ext.tools = 5;
		ext.laborCond = 5;
		ext.synergy = 5;
		ext.fm = 5;
		ext.clientUnd = 5;
		ext.extChange = 5;
		ext.coord = 5;
		ext.motivationWeight = 5;
		ext.codeOrgWeight = 5;
		ext.codeUndWeight = 5;
		ext.toolsWeight = 5;
		ext.laborCondWeight = 5;
		return ext;
	}


	public double getDoubleFromCell(int x, int y, Sheet sheet) {
		Cell a1 = sheet.getCell(x, y);
		String stringa1 = a1.getContents();
		return Double.parseDouble(stringa1.replace(",", "."));
	}

}
