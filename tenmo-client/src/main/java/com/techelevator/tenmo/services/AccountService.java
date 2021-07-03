package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
    public Double getBalance(String token, Integer userId) {
        Double balance = null;
        HttpEntity<Account> entity = makeEntity(token);
        try {
            balance = restTemplate.exchange(baseUrl + "accounts/" + userId, HttpMethod.GET, entity, Double.class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the balance. Returning to main menu.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return balance;
    }

    //get username
    public String getUsername(String token, Integer accountId) {
        String username = null;
        HttpEntity<Account> entity = makeEntity(token);
        try {
            username = restTemplate.exchange(baseUrl + "accounts/retrieve_username/" + accountId, HttpMethod.GET, entity, String.class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the username. Returning to main menu.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return username;
    }

    //get account id
    public Integer getAccountId(String token, Integer userId) {
        Integer accountId = null;
        HttpEntity<Account> entity = makeEntity(token);
        try {
            accountId = restTemplate.exchange(baseUrl + "accounts/retrieve_account_id/" + userId, HttpMethod.GET, entity, Integer.class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the user ID. Returning to main menu.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return accountId;
    }

    //helper method
    private HttpEntity<Account> makeEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> entity = new HttpEntity<>(headers);
        return entity;
    }

}