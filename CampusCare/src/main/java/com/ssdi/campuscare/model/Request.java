package com.ssdi.campuscare.model;

public class Request implements IRequest 
{
	int consumerId, providerId, categoryId;
	String status;

	public Request()
	{
		//all null field for testing purposes
	}
	public Request(int consumerID, int providerID, int categoryID, String status)
	{
		this.consumerId = consumerID;
		this.providerId = providerID;
		this.categoryId = categoryID;
		this.status  = status;
	}
	public int getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object rhs) {
		Request rhs_actual = (Request) rhs;
		if(rhs_actual.consumerId == this.consumerId &&
				rhs_actual.providerId == this.providerId &&
				rhs_actual.categoryId == this.categoryId)
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}
}
