package com.endproduct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endproduct.dao.EndProductDao;
import com.endproduct.entity.EndProduct;

@Service
public class EndProductService {
	@Autowired
	EndProductDao epo;

	public String setAllEndProd(List<EndProduct> p) {
		return epo.setAllEndProd(p);
		
	}

}
