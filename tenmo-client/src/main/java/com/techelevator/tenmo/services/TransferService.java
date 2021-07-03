package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private String baseUrl;
    private AuthenticatedUser currentUser;
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService(String url) {
        this.baseUrl = url;
    }

    //create transfer
    public ResponseEntity<Transfer> createTransfer(String token, Integer accountId, Integer destinationAccount, String amount) {
        Transfer transfer = new Transfer(null, 2, 2, accountId, destinationAccount, Double.parseDouble(amount));
        HttpEntity<Transfer> entity = makeEntity(token, transfer);
        try {
            return restTemplate.exchange(baseUrl + "transfers", HttpMethod.POST, entity, Transfer.class);
        } catch (RestClientResponseException ex) {
            System.err.println("Error code: " + ex.getRawStatusCode() + " " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    //list transfers by user
    public Transfer[] listTransfersByUser(String token, Integer userId) {
        Transfer[] transfers = null;
        HttpEntity<Transfer> entity = makeEntity(token);
        try {
            transfers = restTemplate.exchange(baseUrl + "transfers/user/" + userId, HttpMethod.GET, entity, Transfer[].class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println(ex.getRawStatusCode() + " " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            System.err.println(ex.getMessage());
        }
        return transfers;
    }

    //get transfer by transfer id
    public Transfer getTransferById(String token, Integer transferId) {
        Transfer transfer = null;
        HttpEntity<Transfer> entity = makeEntity(token);
        try {
            transfer = restTemplate.exchange(baseUrl + "transfers/" + transferId, HttpMethod.GET, entity, Transfer.class).getBody();
        } catch (RestClientResponseException ex) {
            System.err.println("Could not retrieve the transfer ID. Returning to main menu.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return transfer;
    }

    //helper methods
    private HttpEntity<Transfer> makeEntity(String token, Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }

    private HttpEntity<Transfer> makeEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(headers);
        return entity;
    }

}
