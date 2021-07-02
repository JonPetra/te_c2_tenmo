package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService(String url) {
        this.baseUrl = url;
    }

//    //create transfer
//    public Transfer createTransfer(String destinationAccount, String amount) {
//        Transfer transfer = makeTransfer(destinationAccount, amount);
//        HttpEntity<Transfer> entity = makeEntity(transfer);
//        try {
//            return restTemplate.postForObject(baseUrl + "transfers", entity, Transfer.class);
//        } catch (RestClientResponseException ex) {
//            System.err.println(ex.getRawStatusCode() + " " + ex.getStatusText());
//        } catch (ResourceAccessException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return null;
//    }

    //list transfers by user
    public Transfer[] listTransfersByUser(Integer userId) {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.getForObject(baseUrl + "transfers/user/" + userId, Transfer[].class);
        } catch (RestClientResponseException ex) {
            System.err.println(ex.getRawStatusCode() + " " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            System.err.println(ex.getMessage());
        }
        return transfers;
    }

    //get transfer by transfer id
    public Transfer getTransferById(Integer transferId) {
        Transfer transfer = null;
        try {
            transfer = restTemplate.getForObject(baseUrl + "transfers/" + transferId, Transfer.class);
        } catch (RestClientResponseException ex) {
            System.err.println("The transfer was not found. Please try again.");
        } catch (ResourceAccessException ex) {
            System.err.println("A network error occurred.");
        }
        return transfer;
    }

    //helper methods
    private HttpEntity<Transfer> makeEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }

//    private Transfer makeTransfer(String destinationAccount, String amount) {
//        return new Transfer(2, 2, 2002, Integer.parseInt(destinationAccount), Double.parseDouble(amount));
//    }
//    //still need to remove hard-coded values

}
