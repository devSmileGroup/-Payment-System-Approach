package com.smilestore.service;

import static com.liqpay.LiqPayUtil.sha1;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.liqpay.LiqPay;
import com.liqpay.LiqPayUtil;
import com.smilestore.model.Order;;

@Service
public class PayService {
	
	@Value("${public_key}")
	private String publicKey;
	
	@Value("${private_key}")
	private String privateKey;
	
	public void payRes(String data, String signature) throws Exception {
		if (!isAuthenticateRequest(data, signature)) {
			throw new Exception("Not authenticated signature");
		}		
	}
	
	public String pay(Order order) {
		HashMap<String, String> params = new HashMap<String, String>();
		
		LiqPay liqPay = new LiqPay(publicKey, privateKey);
		
		params.put("action", "pay");
		params.put("amount", "" + order.getTotalPrice());
		params.put("currency", "USD");
		params.put("description", "Test case"); 
		params.put("order_id", "" + order.getId());
		params.put("version", "3"); 
		params.put("sandbox", "1");
		
		return liqPay.cnb_form(params);
	}
	
	private Boolean isAuthenticateRequest(String data, String signature) {
		String serverSignature = LiqPayUtil.base64_encode(sha1(privateKey + data + privateKey));
		
		return serverSignature.equals(signature);
	}
	
}
