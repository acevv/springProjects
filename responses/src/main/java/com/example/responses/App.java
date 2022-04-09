package com.example.responses;

import org.springframework.scheduling.annotation.Scheduled;

public class App{

	 public static void main(String[] args) {
		
		 System.out.println("Start na metod");
		 JobThread thread = new JobThread();
		 
		 if (thread.getState().toString() == "NEW") {
			 thread.start();
		 }
		 System.out.println("Kraj na metod");
	}
	 
	 
	 @Scheduled(cron = "*/2 * 0 * 8 *")
	 public void sched() {
		 System.out.println("Scheduliran metod vo tochno vreme;");
	 }
	

	
	
}
