package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AccountService {


    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public AccountService(String url) {
        this.baseUrl = url;
    }

    //get balance
    public Double getBalance(Integer userId) {
        Double balance = null;
        try {
            balance = restTemplate.getForObject(baseUrl + "accounts/" + userId, Double.class);
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the auctions. Is the server running?");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return balance;
    }


}