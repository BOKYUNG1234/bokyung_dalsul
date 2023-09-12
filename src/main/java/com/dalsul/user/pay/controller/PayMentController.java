package com.dalsul.user.pay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dalsul.user.pay.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class PayMentController {
	
	@Autowired
	PaymentService paymentService;

	private IamportClient api;
	
	public PayMentController(){		
		//토큰 발급
		this.api = new IamportClient("REST API Key","REST API Secret");
	}
		
	@ResponseBody
	@RequestMapping(value="/verifyiamport/{imp_uid}", method=RequestMethod.POST)
	public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session
			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException{	
		
			return api.paymentByImpUid(imp_uid);
	}
	
	//결제 완료 시 DB에 결제 완료 처리 - 02.15
	//관리자 페이지 코드 추가 - 02.19
	@RequestMapping(value="/paySuccess", method=RequestMethod.POST)
	public void paySuccess(String amount,String ID) {
		int tmp = Integer.parseInt(amount);
		int months = tmp/15000; //개월 수로 치환 -> 기간 갱신을 위함
		Map<String, Object> map = new HashMap<>();
		map.put("ID", ID);
		map.put("months", months);

		if(paymentService.paidCheck(ID) == "Y") {
			paymentService.rePaid(map);
		}
		else {
			paymentService.paid(map); //첫 결제시 : map에 ID, 개월 수 넣고 DB갱신
		}
		
		paymentService.paidUpdate(months);//관리자페이지 일 결제 조회를 위해 추가 - 02.19		
	}	
}