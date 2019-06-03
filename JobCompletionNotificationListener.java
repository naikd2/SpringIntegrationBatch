package com.dhruvit;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;


public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	public JobCompletionNotificationListener() {
		System.err.println("JobCompletionNotificationListener :: init");
		//
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.err.println(jobExecution.toString());
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.err.println("!!! JOB FINISHED! Time to verify the results");
		}
	}
}