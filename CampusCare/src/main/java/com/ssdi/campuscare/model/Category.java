package com.ssdi.campuscare.model;

public class Category implements ICategory {

	private int categoryId;
	private String categoryName;

	
	public Category() {

	}

	public Category(String categoryName) {
        this.categoryId = -1;
		this.categoryName = categoryName;
	}

	public Category(int categoryId, String categoryName) {

		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
