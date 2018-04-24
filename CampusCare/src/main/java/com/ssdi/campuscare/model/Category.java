package com.ssdi.campuscare.model;

public class Category implements ICategory {

	private int categoryId;
	private String categoryName;

	
	public Category() {
        this.categoryId = -1;
		this.categoryName = null;
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

	@Override
	public boolean equals(Object rhs) {
		Category rhs_actual = (Category) rhs;
		System.out.println("In Category::equals");
		if(rhs_actual.categoryId == (this.categoryId)  && 
		   rhs_actual.categoryName.equals( this.categoryName))  
		{
			System.out.println("In Category::equals : inside if statement when true");	
			return true;
		}
		else
		{
			System.out.println("In Category::equals : inside if statement when false");
			return false;
		}
			
	}
}
