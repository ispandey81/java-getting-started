package com.indra.rest.demo;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	   @LocalServerPort
	    private int port;
	    TestRestTemplate restTemplate = new TestRestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    String queryParam = "?values=400,0,-9,54";
	
	   @Test
	    public void testGetSortedListAndSumContentType() throws ClientProtocolException, IOException {
		   HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort("/sortAndSum"+queryParam), HttpMethod.GET, entity, String.class);
	        boolean actual = response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON);
	        Assert.assertTrue("The content type should be JSON", actual);
	    }
	   
	   @Test
	    public void testGetSortedListAndSumResponse() throws ClientProtocolException, IOException {
		   HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort("/sortAndSum"+queryParam), HttpMethod.GET, entity, String.class);
	        String expected = "{\"sortedList\":[-9,0,54,400],\"sum\":445}";
	        boolean actual = response.getBody().equals(expected);
	        Assert.assertTrue("The response body should have the sorted list and the sum of integers", actual);
	    }
	   
	   @Test
	    public void testGetSortedListAndSumError() throws ClientProtocolException, IOException {
		   HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort(""), HttpMethod.GET, entity, String.class);
	        boolean actual = response.getStatusCode().equals(HttpStatus.NOT_FOUND);
	        Assert.assertTrue("The user should see an HTTP error code of 404", actual);
	    }
	    
	    private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }

}
