package com.example.responses;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobThread extends Thread{
	
	public void run() {

		int i = 0;
		while (true) {
			System.out.println(this.getName() + ": New Thread is running..." + i++);
			try {
				// Wait for one sec so it doesn't print too fast
				Thread.sleep(1000);
				
				
				this.metod();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Async
	public void metod() {
		System.out.println("Asinchron metod");
	}

}
