package com.dhruvit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

	@Override
	public void setDataSource(DataSource dataSource) {
		//This BatchConfigurer ignores any DataSource
	}

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	Step sampleStep() {
		return stepBuilderFactory.get("sampleStep")//
				.<String, String>chunk(5) //
				.reader(itemReader(null)) //
				.writer(i -> i.stream().forEach(j -> System.out.println(j))) //
				.build();
	}

	@Bean
	Job sampleJob() {
		Job job = jobBuilderFactory.get("sampleJob") //
				.incrementer(new RunIdIncrementer()) //
				.start(sampleStep()) //
				.build();
		return job;
	}


	@Bean
	@StepScope
	FlatFileItemReader<String> itemReader(@Value("#{jobParameters[filename]}") String resource) {
		FlatFileItemReader<String> reader = new FlatFileItemReader<String>();
		final FileSystemResource fileResource = new FileSystemResource(resource);
		reader.setResource(fileResource);
		reader.setLineMapper(new PassThroughLineMapper());
		return reader;
	}


}