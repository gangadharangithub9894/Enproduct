package com.endproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.endproduct.entity.EndProduct;
import com.endproduct.service.EndProductService;

@RestController
@RequestMapping("/endprod")
public class EndProductController {
	@Autowired
	RestTemplate rt;
	@Autowired
	EndProductService eps;
	
	@GetMapping("/getUpperString")
	public String getUpperString() {
		String url="http://localhost:8080/prt/getString";
		ResponseEntity<String> r=rt.exchange(url,HttpMethod.GET,null,String.class);
		String s=r.getBody();
		return s.toUpperCase();
	}
     
	@GetMapping("/getAllEndProduct")
	public List<EndProduct> getAllEndProduct(){
		String url1="http://localhost:8080/prt/getAllProd";
		String url2="http://localhost:8082/gst/getTaxByHsN/";
		
		ResponseEntity<List<EndProduct>> r1=rt.exchange(url1,HttpMethod.GET,null,new ParameterizedTypeReference<List<EndProduct>>() {});
		List<EndProduct> ep=r1.getBody();
		ep.forEach(x->{
			int hsn=x.getHsN();
			ResponseEntity<Integer> r2=rt.exchange(url2+hsn,HttpMethod.GET,null,Integer.class);
			Integer tax=r2.getBody();
			x.setPrice(x.getPrice()+x.getPrice()*tax/100);
			
		});
		return ep;
	}
	
	

	@PostMapping("/setAllEndProduct")
	public String setAllEndProd() {
		List<EndProduct> p=getAllEndProduct();
		return eps.setAllEndProd(p);
	}
	
	
	
}
