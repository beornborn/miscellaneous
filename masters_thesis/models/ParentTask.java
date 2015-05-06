package models;

import java.util.Arrays;

import service.TaskProcess;

public class ParentTask extends Task {

	public ChildTask[] childTasks;
	public ExternalFactors ext;
	public double extraTime;
	public double difficulty;

	static String[] names = { "solving", "childs", "testing", "refactoring" };
	static String[] namesChi = { "solving", "development", "testing",
			"refactoring" };

	public void calculateParentTime(TaskProcess taskProcess) {
		this.solving=taskProcess.getCalculatedArrTime(this.solving, this);
		this.testing=taskProcess.getCalculatedArrTime(this.testing, this);
		this.refactoring=taskProcess.getCalculatedArrTime(this.refactoring, this);
		for (int i = 0; i < this.childTasks.length; i++) {
			this.childTasks[i].calculateChildTime(taskProcess);
		}
		this.calculateWholeParentTime();
		this.time *= taskProcess.calculateExtraTime(this);
	}

	public void calculateWholeParentTime() {
		Arrays.fill(this.partTime, 0);
		this.partTime[0]= TaskProcess.getOneOfPartTime(this.solving);
		for (int i = 0; i < this.childTasks.length; i++) {
			this.partTime[1] += this.childTasks[i].time;
		}
		this.partTime[2]= TaskProcess.getOneOfPartTime(this.testing);
		this.partTime[3]= TaskProcess.getOneOfPartTime(this.refactoring);
		this.time = TaskProcess.getSumArray(this.partTime);
	}

	public void printTask() {
		p("difficulty: " + this.difficulty);
		p("");
		p("External Factors");
		p("  client understanding: " + this.ext.clientUnd);
		p("  code difficulty: " + this.ext.codeUnd);
		p("  code organization: " + this.ext.codeOrg);
		p("  coordination: " + this.ext.coord);
		p("  external changes: " + this.ext.extChange);
		p("  force majeure: " + this.ext.fm);
		p("  intellect & experience: " + this.ext.IE);
		p("  labor conditions: " + this.ext.laborCond);
		p("  motivation: " + this.ext.motivation);
		p("  synergy: " + this.ext.synergy);
		p("  tools: " + this.ext.tools);
		p("");

		p("Time distribution");
		p("whole time: " + this.time);
		for (int i = 0; i < this.partTime.length; i++) {
			p(names[i] + ": " + this.partTime[i]);
			if (names[i].equals("childs")) {
				for (int ii = 0; ii < this.childTasks.length; ii++) {
					p("   child" + (ii + 1));
					for (int iii = 0; iii < this.childTasks[ii].partTime.length; iii++) {
						p("      " + namesChi[iii] + ": "
								+ this.childTasks[ii].partTime[iii]);
					}
				}
			}
		}

	}

	public static void p(Object o) {
		System.out.println(o);
	}
}
