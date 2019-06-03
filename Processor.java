package com.dhruvit.service;

import com.dhruvit.model.Review;
import org.springframework.batch.item.ItemProcessor;

import javax.annotation.PostConstruct;

public class Processor implements ItemProcessor<Review, Review> {

	@PostConstruct
	public void init() {
		System.err.println("Processor :: init");
	}

	@Override
	public Review process(Review row) throws Exception {
		System.err.println("Processing: " + row);
		return row;
	}
}
