package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
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
    public Integer findIdByUsername(String username) {
        Integer foundId = null;
        try {
            foundId = restTemplate.getForObject(baseUrl + "users/id/" + username, Integer.class);
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the ID. Is the server running?");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return foundId;
    }

    //find all users
    public User[] findAll() {
        User[] users = null;
        try {
            users = restTemplate.getForObject(baseUrl + "users", User[].class);
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve users. Is the server running?");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return users;
    }

    //find by username
    public User findByUsername(String username) {
        User user = null;
        try {
            user = restTemplate.getForObject(baseUrl + "users/" + username, User.class);
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the user. Is the server running?");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return user;
    }

}
