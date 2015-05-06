package models;

import java.util.Random;

import service.TaskProcess;

public class ChildTask extends Task {

	Random rnd = new Random();
	public ParentTask parentTask;
	public Iteration[] development;

	public void calculateChildTime(TaskProcess taskProcess) {
		this.solving=taskProcess.getCalculatedArrTime(this.solving, this.parentTask);
		this.development=taskProcess.getCalculatedArrTime(this.development, this.parentTask);
		this.testing=taskProcess.getCalculatedArrTime(this.testing, this.parentTask);
		this.refactoring=taskProcess.getCalculatedArrTime(this.refactoring, this.parentTask);
		this.calculateWholeChildTime();
	}

	public void calculateWholeChildTime() {
		this.partTime[0]= TaskProcess.getOneOfPartTime(this.solving);
		this.partTime[1]= TaskProcess.getOneOfPartTime(this.development);
		this.partTime[2]= TaskProcess.getOneOfPartTime(this.testing);
		this.partTime[3]= TaskProcess.getOneOfPartTime(this.refactoring);
		this.time = TaskProcess.getSumArray(this.partTime);
	}

}
