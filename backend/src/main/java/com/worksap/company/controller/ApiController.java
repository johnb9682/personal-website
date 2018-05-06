package com.worksap.company.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ApiController {

    private final RestTemplate restClient;

    @Value("http://13.250.21.111:8080/jeefw/question")
    private String jeefwBaseUrl;
    
    @Value("")
    private String productSearchUrl;

    @Value("productUrl")
    private String productUrl;

    @Value("productAuthUrl")
    private String productAuthUrl;

    @Value("clientId:connecty001")
    private String clientId;

    @Value("clientSecret:5pe274ON66+D4Z2x5rCs66a345C24oq85J2M6aiP66Wv7I+25ryP4YO24Zm44rKf")
    private String clientSecret;

    @RequestMapping("/test")
    public Object index(
            @RequestParam("store-id") String storeId,
            @RequestParam("site-id") String siteId,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "fetch-size", required = false) Integer fetchSize,
            @RequestParam("query") String query,
            @RequestParam(value = "min-price", required = false) Double minPrice,
            @RequestParam(value = "max-price", required = false) Double maxPrice,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "sort", required = false) String sort
            ) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append(productSearchUrl + "/api/v1/productsearch/stores/"+storeId+"/sites/"+siteId+"?offset="+ offset +"&fetch-size="+ fetchSize +"&query=" + query);
        if (minPrice != null) {
            sb.append("&min-price=" + minPrice);
        }
        if (maxPrice != null) {
            sb.append("&max-price=" + maxPrice);
        }
        if (category != null) {
            sb.append("&category=" + category);
        }
        if (sort != null) {
            sb.append("&sort=" + sort);
        }

        return restClient.exchange(
                sb.toString(),
                HttpMethod.GET,
                createDefaultEntity(null, accessToken(), storeId, siteId),
                String.class);
    }
    
    @RequestMapping("/ask")
    public Object ask(
            @RequestParam(name = "text", required = false, defaultValue = "") String text
            ) throws Exception {

        String url = jeefwBaseUrl + "/ask"; 
       
        //add file
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();

        //add array
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url); 
        
        builder.queryParam("text", text); 

        //another staff
        String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(params, headers);
        
        ResponseEntity<String> responseEntity = restClient.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Object create(
            @RequestParam("answer") String answer,
            @RequestParam("question") String question,
            @RequestParam(name = "user_name", required = false, defaultValue = "Conan") String author
            ) throws Exception {

        String url = jeefwBaseUrl + "/create"; 
       
        //add file
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();

        //add array
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url); 
         
        builder.queryParam("answer", answer);
        builder.queryParam("question", question);
        builder.queryParam("user_name", author);

        //another staff
        String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(params, headers);
        
        ResponseEntity<String> responseEntity = restClient.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createPost(
            @RequestParam("answer") String answer,
            @RequestParam("question") String question,
            @RequestParam(name = "user_name", required = false, defaultValue = "Conan") String author
            ) throws Exception {

        String url = jeefwBaseUrl + "/create"; 
       
        //add file
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();

        //add array
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url); 
         
        builder.queryParam("answer", answer);
        builder.queryParam("question", question);
        builder.queryParam("user_name", author);

        //another staff
        String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(params, headers);
        
        ResponseEntity<String> responseEntity = restClient.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }
    
    
    @RequestMapping("/delete")
    public Object delete(
            @RequestParam("answerId") String answerId
            ) throws Exception {

        String url = jeefwBaseUrl + "/update"; 
        
        JSONObject  action = new JSONObject();
        action.put("name", "delete");
        action.put("type", "button");
        action.put("value", answerId);
        
        JSONArray actions = new JSONArray();
        actions.put(action);
        
        JSONObject  payload = new JSONObject();
        payload.put("callback_id", "update");
        payload.put("type", "interactive_message");
        payload.put("actions", actions);
       
        //add file
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();

        //add array
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url); 
         
        builder.queryParam("payload", payload);

        //another staff
        String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(params, headers);
        
        ResponseEntity<String> responseEntity = restClient.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @RequestMapping("/categories/")
    public Object categories(
            @RequestParam("store-id") String storeId
            ) throws Exception {
//        return restClient.getForEntity(
//                productUrl + "/ec-product/v1/products/categories/stores/" + storeId,
//                String.class);
    	return null;
    }

    private HttpHeaders createDefaultHeaders(String accessToken, String storeId, String siteId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("client-id", clientId);
        headers.add("access-token", accessToken);
        headers.add("x-wap-ec-store", storeId);
        headers.add("x-wap-ec-site", siteId);
        return headers;
    }

    private HttpHeaders createAuthHeaders(){
        HttpHeaders headers = new HttpHeaders();
//        headers.add("client-id", clientId);
//        headers.add("client-secret", clientSecret);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.add("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        return headers;
    }

    private HttpEntity<?> createDefaultEntity(Object body, String accessToken, String storeId, String siteId) {
        return new HttpEntity<>(body, createDefaultHeaders(accessToken, storeId, siteId));
    }

    private HttpEntity<?> createAuthEntity(Object body) {
        return new HttpEntity<>(body, createAuthHeaders());
    }

    private String accessToken() throws Exception {
        ResponseEntity<Object> authResult
                = restClient.exchange(productAuthUrl, HttpMethod.POST, createAuthEntity(null), Object.class);

        String accessToken = new GsonJsonParser().parseMap(authResult.getBody().toString()).get("accessToken").toString();

        return accessToken;
    }

}
