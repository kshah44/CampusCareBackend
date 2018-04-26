package com.ssdi.campuscare.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssdi.campuscare.dao.IRequestDao;
import com.ssdi.campuscare.model.Request;

@Service
public class RequestService implements IRequestService
{
	@Autowired
	IRequestDao requestDao;

	@Override
	public List<Request> getAllRequests() 
	{
		return requestDao.getAllRequests();
	}

	@Override
	public JSONArray getProviderRequest(int provider_id) 
	{
		return requestDao.getProviderRequest(provider_id);
	}

	@Override
	public JSONArray getConsumerRequest(int consumer_id) 
	{
		return requestDao.getConsumerRequest(consumer_id);
	}

	@Override
	public Request createRequest(Request request) 
	{
		return requestDao.createRequest(request);
	}

	@Override
	public JSONArray cancelRequest(int consumer_id, int provider_id, int category_id) 
	{
		return requestDao.cancelRequest(consumer_id, provider_id, category_id);
	}

	@Override
	public JSONArray ProviderUpdateRequestStatus(int request_id, int provider_id, String status) 
	{
		return requestDao.ProviderUpdateRequestStatus(request_id, provider_id, status);
	}

	/*
	@Override
	public JSONArray ProviderRejectRequest(int request_id, int provider_id) 
	{
		return requestDao.ProviderRejectRequest(request_id, provider_id);
	}

	@Override
	public JSONArray ProviderCompleteRequest(int request_id, int provider_id) 
	{
		return requestDao.ProviderCompleteRequest(request_id, provider_id);
	}
    */
	
}
