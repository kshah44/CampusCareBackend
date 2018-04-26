package com.ssdi.campuscare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ssdi.campuscare.service.RequestService;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.model.Request;
import org.json.JSONObject;

@CrossOrigin(origins = "*")
@RestController
public class RequestController 
{

	@Autowired
	private RequestService requestService;

	@RequestMapping("/requests")
	public List<Request> getAllRequests() {
		return requestService.getAllRequests();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/providerrequests")
	public List<Request> getProviderRequests(@RequestBody Provider provider) {
		return requestService.getProviderRequest(provider.getProviderId());

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/consumerrequests")
	public List<Request> getConsumerRequests(@RequestBody Consumer consumer) {
		return requestService.getConsumerRequest(consumer.getConsumerId());

	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequest")
	public Request createRequest(@RequestBody Request request) {
		return requestService.createRequest(request);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelrequest")
	public List<Request> cancelRequest(@RequestBody String obj) {
		JSONObject json = new JSONObject(obj);
		return requestService.cancelRequest((Integer.parseInt((String) json.get("consumerId"))),
				(Integer.parseInt((String) json.get("providerId"))),
				Integer.parseInt((String) json.get("categoryId")));
	}
}
