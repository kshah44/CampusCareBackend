package com.ssdi.campuscare.model;

public class Request implements IRequest {
	private int requestId;
	private int consumerId;
	private int providerId;
	private int categoryId;
	private String status;

	public Request() {
		this.requestId = -1;
		this.consumerId = -1;
		this.providerId = -1;
		this.categoryId = -1;
		this.status = null;

	}

	public Request(int consumerID, int providerID, int categoryID, String status) {
		this.requestId = -1;
		this.consumerId = consumerID;
		this.providerId = providerID;
		this.categoryId = categoryID;
		this.status = status;
	}

	public Request(int requestId, int consumerID, int providerID, int categoryID, String status) {
		this.requestId = requestId;
		this.consumerId = consumerID;
		this.providerId = providerID;
		this.categoryId = categoryID;
		this.status = status;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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
		if (rhs_actual.consumerId == this.consumerId && rhs_actual.providerId == this.providerId
				&& rhs_actual.categoryId == this.categoryId) {
			return true;
		} else {
			return false;
		}

	}
}
