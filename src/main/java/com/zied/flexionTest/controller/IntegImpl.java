package com.zied.flexionTest.controller;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IntegImpl implements Integration {

    @Autowired
    private RestTemplate restTemplate;

    private static String url="http://sandbox.flexionmobile.com/javachallenge/rest/zied";

    @Override
    @PostMapping(value = "/buyItem/{itemId}", consumes = "application/json", produces = "application/json")
    public Purchase buy(@PathVariable("itemId") String s) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(s,headers);
        Purchase result =  restTemplate.exchange(url+"/buy/"+s, HttpMethod.POST, entity, Purchase.class).getBody();
        return result;
    }

    @Override
    @GetMapping(value = "/purchases/all")
    public List<Purchase> getPurchases() {
        Purchase [] purchases = restTemplate.getForObject(url+"/all",Purchase[].class);
        return Arrays.asList(purchases);
    }

    @Override
    @PostMapping(value = "/consume", consumes = "application/json", produces = "application/json")
    public void consume(Purchase purchase) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Purchase> entity = new HttpEntity<Purchase>(purchase,headers);
        restTemplate.exchange(url+"/consume/"+entity.getBody().getId(), HttpMethod.POST, entity, Purchase.class);
    }
}
