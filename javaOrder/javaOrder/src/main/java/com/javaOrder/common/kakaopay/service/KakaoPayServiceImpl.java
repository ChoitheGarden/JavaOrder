package com.javaOrder.common.kakaopay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.javaOrder.common.kakaopay.model.ApproveRequestVO;
import com.javaOrder.common.kakaopay.model.KakaoPayReadyRequestVO;
import com.javaOrder.common.kakaopay.model.KakaoPayReadyResponseVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaoPayServiceImpl implements KakaoPayService {
	@Value("${kakaopayJavaOrder.kakaopay-secret-key}")
	private String kakaoSecretKey;
	
	@Value("${kakaopayJavaOrder.cid}")
	private String cid;
	
	@Value("${kakaopayJavaOrder.host}")
	private String hostURL;
	
	private String tid;
	
	public KakaoPayReadyResponseVO ready(String agent, String openType) {
		// Request Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "DEV_SECRET_KEY "+kakaoSecretKey);
		headers.setContentType(MediaType.APPLICATION_JSON);
		log.info(hostURL);
		// Request param
		KakaoPayReadyRequestVO readyRequest = KakaoPayReadyRequestVO.builder()
				.cid(cid)
				.partnerOrderId("1")
				.partnerUserId("1")
				.itemName("상품명")
				.quantity(1)
				.totalAmount(1100)
				.taxFreeAmount(0)
				.vatAmount(100)
				.approvalUrl(hostURL+"/approve/"+agent+"/"+openType)
				.cancelUrl(hostURL+"/cancel/"+agent+"/"+openType)
				.failUrl(hostURL+"/fail/"+agent+"/"+openType)
				.build();
		
		// Send request
		HttpEntity<KakaoPayReadyRequestVO> entityMap = new HttpEntity<>(readyRequest, headers);
		ResponseEntity<KakaoPayReadyResponseVO> response = new RestTemplate().postForEntity(
					"https://open-api.kakaopay.com/online/v1/payment/ready",
					entityMap,
					KakaoPayReadyResponseVO.class
		);
		KakaoPayReadyResponseVO readyResponse = response.getBody();
		
		// 주문번호와 TID 매핑해서 저장
		// Mapping TID with partner_order_id than save it to use for approval request.
		this.tid = readyResponse.getTid();
		return readyResponse;
	}
	
	
	public String approve(String pgToken) {
        // ready할 때 저장해놓은 TID로 승인 요청
        // Call “Execute approved payment” API by pg_token, TID mapping to the current payment transaction and other parameters.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaoSecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Request param
        ApproveRequestVO approveRequest = ApproveRequestVO.builder()
                .cid(cid)
                .tid(tid)
                .partnerOrderId("1")
                .partnerUserId("1")
                .pgToken(pgToken)
                .build();

        // Send Request
        HttpEntity<ApproveRequestVO> entityMap = new HttpEntity<>(approveRequest, headers);
        try {
            ResponseEntity<String> response = new RestTemplate().postForEntity(
                    "https://open-api.kakaopay.com/online/v1/payment/approve",
                    entityMap,
                    String.class
            );

            // 승인 결과를 저장한다.
            // save the result of approval
            String approveResponse = response.getBody();
            return approveResponse;
        } catch (HttpStatusCodeException ex) {
            return ex.getResponseBodyAsString();
        }
    }
	
}
