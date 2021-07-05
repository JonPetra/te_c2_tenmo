package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class UserService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public UserService(String url) {
        this.baseUrl = url;
    }

    //find id by username
    public Integer findIdByUsername(String token, String username) {
        Integer foundId = null;
        HttpEntity<User> entity = makeEntity(token);
        try {
            foundId = restTemplate.exchange(baseUrl + "users/id/" + username, HttpMethod.GET, entity, Integer.class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the user ID. Returning to main menu.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return foundId;
    }

    //find all users
    public User[] findAll(String token) {
        User[] users = null;
        HttpEntity<User> entity = makeEntity(token);
        try {
            users = restTemplate.exchange(baseUrl + "users", HttpMethod.GET, entity, User[].class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve users. Returning to main menu.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return users;
    }

    //find by username
    public User findByUsername(String token, String username) {
        User user = null;
        HttpEntity<User> entity = makeEntity(token);
        try {
            user = restTemplate.exchange(baseUrl + "users/" + username, HttpMethod.GET, entity, User.class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the user. Returning to main menu.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return user;
    }

    //helper method
    private HttpEntity<User> makeEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return entity;
    }

}
