package com.dhruvit.model;

import java.util.StringJoiner;

public class Review {

	// 2 letter country code of the marketplace where the review was written.
	String marketplace;

	// Random identifier that can be used to aggregate reviews written by a single author.
	String	customer_id;

	// The unique com.sun.xml.internal.bind.v2.model.core.ID of the review.
	String review_id;

	// The unique Product ID the review pertains to. In the multilingual dataset the reviews
	// for the same product in different countries can be grouped by the same product_id.
	String product_id;

	// Random identifier that can be used to aggregate reviews for the same product.
	String product_parent;

	// Title of the product.
	String product_title;

	// Broad product category that can be used to group reviews
	// (also used to group the dataset into coherent parts).
	String product_category;

	// The 1-5 star rating of the review.
	String star_rating;

	// Number of helpful votes.
	String helpful_votes;

	// Number of total votes the review received.
	String total_votes;

	// Review was written as part of the Vine program.
	String vine;

	// The review is on a verified purchase.
	String verified_purchase;

	// The title of the review.
	String review_headline;

	// The review text.
	String review_body;

	// The date the review was written.
	String review_date;

	public Review() {

	}

	public String getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(String marketplace) {
		this.marketplace = marketplace;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getReview_id() {
		return review_id;
	}

	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_parent() {
		return product_parent;
	}

	public void setProduct_parent(String product_parent) {
		this.product_parent = product_parent;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getStar_rating() {
		return star_rating;
	}

	public void setStar_rating(String star_rating) {
		this.star_rating = star_rating;
	}

	public String getHelpful_votes() {
		return helpful_votes;
	}

	public void setHelpful_votes(String helpful_votes) {
		this.helpful_votes = helpful_votes;
	}

	public String getTotal_votes() {
		return total_votes;
	}

	public void setTotal_votes(String total_votes) {
		this.total_votes = total_votes;
	}

	public String getVine() {
		return vine;
	}

	public void setVine(String vine) {
		this.vine = vine;
	}

	public String getVerified_purchase() {
		return verified_purchase;
	}

	public void setVerified_purchase(String verified_purchase) {
		this.verified_purchase = verified_purchase;
	}

	public String getReview_headline() {
		return review_headline;
	}

	public void setReview_headline(String review_headline) {
		this.review_headline = review_headline;
	}

	public String getReview_body() {
		return review_body;
	}

	public void setReview_body(String review_body) {
		this.review_body = review_body;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Review.class.getSimpleName() + "[", "]")
				.add("marketplace='" + marketplace + "'")
				.add("customer_id='" + customer_id + "'")
				.add("review_id='" + review_id + "'")
				.add("product_id='" + product_id + "'")
				.add("product_parent='" + product_parent + "'")
				.add("product_title='" + product_title + "'")
				.add("product_category='" + product_category + "'")
				.add("star_rating='" + star_rating + "'")
				.add("helpful_votes='" + helpful_votes + "'")
				.add("total_votes='" + total_votes + "'")
				.add("vine='" + vine + "'")
				.add("verified_purchase='" + verified_purchase + "'")
				.add("review_headline='" + review_headline + "'")
				.add("review_body='" + review_body + "'")
				.add("review_date='" + review_date + "'")
				.toString();
	}
}
