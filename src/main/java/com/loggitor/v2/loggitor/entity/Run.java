package com.loggitor.v2.loggitor.entity;
import java.io.IOException;
import java.util.Map;

public class Run {

	public static void main(String[] args)
	{
		
		int count = args.length;
		if(count != 2)
		{
			System.out.println("You must enter one input file name and one output file name.");
			System.exit(1);
		}
		
		
		ReadLog log = new ReadLog(args[0]);
		
		try {
			log.processFile();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage() + " -> error occurred while processing the file");
		}
		
		Map<String, DefectByApp> defects = log.getDefects();
		
		WriteReport writer = new WriteReport(args[1],defects);
		
		if(writer.isEmpty())
		{
			System.out.println("There is no defect to print.");
			System.exit(1);
		}
		
		
		try {
			writer.printLog();
		} 
		catch (IOException e) {
			System.out.println(e.getMessage() + " -> error occurred while printing to the file");
		}
		
		
		
		
		
	}
}
