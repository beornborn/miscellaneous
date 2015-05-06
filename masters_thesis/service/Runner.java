package service;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import models.ParentTask;
import service.ExcelService;
import service.StatTask;
import service.TaskBuilder;




public class Runner {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws InvalidFormatException 
	 */
	public static void main(String[] args) throws BiffException, IOException, RowsExceededException, WriteException, InvalidFormatException {
//		Workbook workbook = Workbook.getWorkbook(new File("e.xls"));
//		Sheet sheet = workbook.getSheet(0);
//		Cell a1 = sheet.getCell(0,0); 
//		String stringa1 = a1.getContents(); 
//		p(stringa1);
//		p("");
		
		StatTask st = new StatTask();
		StatTask st2 = new StatTask();
		TaskBuilder tb = new TaskBuilder();
		
		
		tb.getExtFromExcel();
	//	TaskService.printTask(task);
		int count=0;
		double sum=0;
		int it = 10000;
		double[] forv=new double[it];
		double[] forv2=new double[it];
	for (int i=0;i<it;i++){
		ParentTask task = tb.build(10);
		TaskProcess p = new TaskProcess(task);
		//task.printTask();
		forv[i]=p.task.time;
		p.calculateTask();
		//task.printTask();
		sum+=p.task.time;
		forv2[i]=p.task.time;
	//p(p.task.time);
	}
	st.calculateStat(forv);
	st2.calculateStat(forv2);
	p("variance before: "+ st.dispersion);
	p("min before: "+ st.min);
	p("max before: "+ st.max);
	p("standart deviation: "+ st.standartDeviation);
	p("");
	p("variance after: "+ st2.dispersion);
	p("min after: "+ st2.min);
	p("max after: "+ st2.max);
	p("standart deviation: "+ st2.standartDeviation);
	ExcelService.writeDataForDistribution(st2.bordersActual, st2.distribution);
//	p(count);
//	p(TaskProcess.count);
	p(sum/it);
//		double count = 0;
//		for (double i = 0; i<999;i++){
//			Random rnd = new Random();
//			double number = rnd.nextGaussian();
//			while (Math.abs(number)>2){
//				number = rnd.nextGaussian();
//			}
//			
//			p(2.5*(2+number));	
			
			//p(Math.abs(getGaussian(0, 1)));
			
			//p(Math.abs(rnd.nextInt() % 30));
		//}
		
		
	}
	
	public static void p(Object o){
		System.out.println(o);
	}
	

}
