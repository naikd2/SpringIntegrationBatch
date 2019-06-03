package com.dhruvit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@IntegrationComponentScan
public class DynamoloaderApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(DynamoloaderApplication.class, args);
		System.out.println("Exit Application");
		System.in.read();
		ctx.close();
	}

/**
	// tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<Review> reader() {
		return new FlatFileItemReaderBuilder<Review>()
				.name("processor")
				.lineTokenizer(new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB) {{
					setNames("marketplace", "customer_id", "review_id", "product_id", "product_parent", "product_title",
							"product_category", "star_rating", "helpful_votes", "total_votes",
							"vine", "verified_purchase", "review_headline", "review_body", "review_date");
				}}).fieldSetMapper(new BeanWrapperFieldSetMapper<Review>() {{
					setTargetType(Review.class);
				}})
				.build();
	}


	@Bean
	public Processor processor() {
		return new Processor();
	}

	@Bean
	public DynamoDbWriter writer() {
		return new DynamoDbWriter();
	}

	@Bean
	public JobCompletionNotificationListener listener() {
		return new JobCompletionNotificationListener();
	}

	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob")
				.listener(listener())
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Review, Review>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}


**/
}
