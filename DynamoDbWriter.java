package com.dhruvit.service;

import com.dhruvit.model.Review;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.PostConstruct;
import java.util.List;

public class DynamoDbWriter implements ItemWriter<Review> {

	@PostConstruct
	public void init() {
		System.err.println("DynamoDbWriter :: init");
	}

	@Override
	public void write(List<? extends Review> items) throws Exception {
		System.err.println("Writing to DynamoDb");
	}
}
