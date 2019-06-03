package com.dhruvit;

import com.dhruvit.model.FileMessageToJobRequest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
public class IntegrationConfiguration {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private Job sampleJob;

	protected DirectChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow sampleFlow() {
		// @formatter:off
		return IntegrationFlows
				.from(fileDropSource(),
						c -> c.poller(Pollers.fixedDelay(1000)))
				.channel(inputChannel())
				.handle(fileMessageToJobRequest())
				.handle(jobLaunchingGateway())
				.handle(jobExecution -> {
					System.out.println(jobExecution.getPayload());
				})
				.handle(archive())
				.get();
		// @formatter:on
	}

	@Bean
	public MessageHandler archive() {
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("tmp/out"));
		handler.setFileExistsMode(FileExistsMode.REPLACE);
		handler.setExpectReply(false);
		return handler;
	}

	@Bean
	public MessageSource<File> fileDropSource() {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File("tmp/in"));
		source.setFilter(new SimplePatternFileListFilter("*.txt"));
		source.setUseWatchService(true);
		source.setWatchEvents(FileReadingMessageSource.WatchEventType.CREATE);
		return source;
	}

	@Bean
	FileMessageToJobRequest fileMessageToJobRequest() {
		FileMessageToJobRequest transformer = new FileMessageToJobRequest();
		transformer.setFileParameterName("filename");
		transformer.setJob(sampleJob);
		return transformer;
	}

	@Bean
	public JobLaunchingGateway jobLaunchingGateway() {
		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setJobRepository(jobRepository);
		simpleJobLauncher.setTaskExecutor(new SyncTaskExecutor());
		JobLaunchingGateway jobLaunchingGateway = new JobLaunchingGateway(simpleJobLauncher);
		return jobLaunchingGateway;
	}
}
