package com.ssdi.campuscare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ssdi.campuscare.service.RequestService;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.model.Request;

import org.json.JSONArray;
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
	public String getProviderRequests(@RequestBody Provider provider) {
		return requestService.getProviderRequest(provider.getProviderId()).toString();

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/consumerrequests")
	public String getConsumerRequests(@RequestBody Consumer consumer) {
		return requestService.getConsumerRequest(consumer.getConsumerId()).toString();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/createRequest")
	public Request createRequest(@RequestBody Request request) {
		return requestService.createRequest(request);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelrequest")
	public String cancelRequest(@RequestBody String obj) {
		JSONObject json = new JSONObject(obj);
		return requestService.cancelRequest((Integer.parseInt((String) json.get("consumerId"))),
				(Integer.parseInt((String) json.get("providerId"))),
				Integer.parseInt((String) json.get("categoryId"))).toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/acceptrequest")
	public String ProviderAcceptRequest(@RequestBody String obj) {
		JSONObject json = new JSONObject(obj);
		return requestService.ProviderAcceptRequest(Integer.parseInt((String) json.get("requestId")),
				Integer.parseInt((String) json.get("providerId"))).toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/rejectrequest")
	public String ProviderRejectRequest(@RequestBody String obj) {
		JSONObject json = new JSONObject(obj);
		return requestService.ProviderRejectRequest(Integer.parseInt((String) json.get("requestId")),
				Integer.parseInt((String) json.get("providerId"))).toString();
	}

}
