package com.ssdi.campuscare.service;

import java.util.List;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssdi.campuscare.dao.*;
import com.ssdi.campuscare.model.*;

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
	public List<Request> getProviderRequest(int provider_id) 
	{
		return requestDao.getProviderRequest(provider_id);
	}

	@Override
	public List<Request> getConsumerRequest(int consumer_id) 
	{
		return requestDao.getConsumerRequest(consumer_id);
	}

	@Override
	public Request createRequest(Request request) 
	{
		return requestDao.createRequest(request);
	}

	@Override
	public List<Request> cancelRequest(int consumer_id, int provider_id, int category_id) 
	{
		return requestDao.cancelRequest(consumer_id, provider_id, category_id);
	}

}
