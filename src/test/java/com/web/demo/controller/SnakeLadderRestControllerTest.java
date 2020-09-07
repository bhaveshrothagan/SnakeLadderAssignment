package com.web.demo.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.web.demo.dto.PlayerInfo;

@SpringBootTest
public class SnakeLadderRestControllerTest {

	@Test
	public void getPlayerInfo() throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		
	/*	HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON.toString());
		HttpEntity<PlayerInfo> requestEntity = new HttpEntity<>(playerInfo, headers);*/
		
		Map<String, String> map = new HashMap<>();
		map.put("code", "1");
		
		// when
		ResponseEntity<PlayerInfo[]> responseEntity = 
				restTemplate.exchange("http://localhost:8081/v1/api/{code}", HttpMethod.GET, null, PlayerInfo[].class, map);
		
		
		// then
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}
}
