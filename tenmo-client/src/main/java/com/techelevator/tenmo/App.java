package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.view.ConsoleService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
    private static final String LOGIN_MENU_OPTION_LOGIN = "Log in";
    private static final String[] LOGIN_MENU_OPTIONS = {LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT};
    private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
    private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
    private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
    private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
    private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
    private static final String MAIN_MENU_OPTION_LOGIN = "Log in as different user";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT};

    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;
    private AccountService accountService;
    private TransferService transferService;
    private UserService userService;

    public DecimalFormat df = new DecimalFormat("###.00");

    public static void main(String[] args) {
        App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL), new AccountService(API_BASE_URL), new TransferService(API_BASE_URL), new UserService(API_BASE_URL));
        app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService, AccountService accountService, TransferService transferService, UserService userService) {
        this.console = console;
        this.authenticationService = authenticationService;
        this.accountService = accountService;
        this.transferService = transferService;
        this.userService = userService;
    }

    public void run() {
        System.out.println(" _________                                     ");
        System.out.println("|  _   _  |                                    ");
        System.out.println("|_/ | | \\_|.---.  _ .--.   _ .--..--.   .--.   ");
        System.out.println("    | |   / /__\\\\[ `.-. | [ `.-. .-. |/ .'`\\ \\ ");
        System.out.println("   _| |_  | \\__., | | | |  | | | | | || \\__. | ");
        System.out.println("  |_____|  '.__.'[___||__][___||__||__]'.__.'  ");

        registerAndLogin();
        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            String choice = (String) console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
                viewCurrentBalance();
            } else if (MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
                viewTransferHistory();
            } else if (MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
                viewPendingRequests();
            } else if (MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
                sendBucks();
            } else if (MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
                requestBucks();
            } else if (MAIN_MENU_OPTION_LOGIN.equals(choice)) {
                login();
            } else {
                // the only other option on the main menu is to exit
                exitProgram();
            }
        }
    }

    private void viewCurrentBalance() {
        Double balance = accountService.getBalance(currentUser.getToken(), currentUser.getUser().getId());
        System.out.println("Your current account balance is: $" + df.format(balance));
    }

    private void viewTransferHistory() {
        Transfer[] transfers = transferService.listTransfersByUser(currentUser.getToken(), currentUser.getUser().getId());
        if (transfers != null) {
            System.out.println("--------------------------------------------");
            System.out.println("Transfers");
            System.out.println("ID    From/To                         Amount");
            System.out.println("--------------------------------------------");
            for (Transfer transfer : transfers) {
                System.out.printf("%-6d", transfer.getTransferId());
                if (transfer.getAccountTo().equals(accountService.getAccountId(currentUser.getToken(), currentUser.getUser().getId()))) {
                    System.out.printf("%-26s", "From:   " + accountService.getUsername(currentUser.getToken(), transfer.getAccountFrom()));
                } else if (transfer.getAccountFrom().equals(accountService.getAccountId(currentUser.getToken(), currentUser.getUser().getId()))) {
                    System.out.printf("%-26s", "To:     " + accountService.getUsername(currentUser.getToken(), transfer.getAccountTo()));
                }
                System.out.printf("%12s", "$" + df.format(transfer.getAmount()));
                System.out.println();

            }
            System.out.println("--------------------------------------------");
            String transferId = console.getUserInput("Please enter transfer ID to view details (0 to cancel)");
            if (transferId.equals("0")) {
            } else {
                try {
                    Integer.parseInt(transferId);
                } catch (NumberFormatException e) {
                    System.out.println("Non-numeric ID. Please enter the Transfer ID from the ID column. Returning to main menu");
                    return;
                }
                Transfer transfer = transferService.getTransferById(currentUser.getToken(), Integer.parseInt(transferId));
                if (transfer.getTransferId() == null) {
                    System.out.println("Invalid transfer ID. Returning to main menu.");
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("Transfer Details");
                    System.out.println("--------------------------------------------");
                    System.out.println(" Id: " + transfer.getTransferId());
                    System.out.println(" From: " + accountService.getUsername(currentUser.getToken(), transfer.getAccountFrom()));
                    System.out.println(" To: " + accountService.getUsername(currentUser.getToken(), transfer.getAccountTo()));
                    System.out.println(" Type: " + transfer.getTransferType());
                    System.out.println(" Status: " + transfer.getTransferStatus());
                    System.out.println(" Amount: $" + df.format(transfer.getAmount()));
                    System.out.println("--------------------------------------------");
                }
            }
        }
    }

    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    private void sendBucks() {
        User[] users = userService.findAll(currentUser.getToken());
        List<Integer> userIds = new ArrayList<>();
        if (users != null) {
            System.out.println("--------------------------------------------");
            System.out.println("Users");
            System.out.println("ID		    Name");
            System.out.println("--------------------------------------------");
            for (User user : users) {
                System.out.println(user.userToString());
                userIds.add(user.getId());
            }
            System.out.println("--------------------------------------------");
            String destinationAccount = console.getUserInput("Enter ID of user you are sending to (0 to cancel)");
            if (destinationAccount.equals("0")) {
                return;
            } try {
                Integer.parseInt(destinationAccount);
            } catch (NumberFormatException e) {
                System.out.println("Non-numeric ID. Please enter the Transfer ID from the ID column. Returning to main menu");
                return;
            } if (!userIds.contains(Integer.parseInt(destinationAccount))) {
                System.out.println("Invalid ID. Returning to main menu.");
            } else if (Integer.parseInt(destinationAccount) == (currentUser.getUser().getId())) {
                System.out.println("Cannot transfer to yourself. Returning to main menu.");
            } else {
                String amount = console.getUserInput("Enter amount");
                try {
                    Double.parseDouble(amount);
                } catch (NumberFormatException e) {
                    System.out.println("Not a number. Returning to main menu.");
                    return;
                }
                if (amount.equals("") || Double.parseDouble(amount) <= 0) {
                    System.out.println();
                    System.out.println("Invalid amount. Returning to main menu.");
                } else if (Double.parseDouble(amount) > accountService.getBalance(currentUser.getToken(), currentUser.getUser().getId())) {
                    System.out.println();
                    System.out.println("Insufficient funds. Returning to main menu.");
                } else {
                    transferService.createTransfer(currentUser.getToken(), accountService.getAccountId(currentUser.getToken(), currentUser.getUser().getId()), accountService.getAccountId(currentUser.getToken(), Integer.parseInt(destinationAccount)), amount);
                    System.out.println("Transfer successful.");
                }
            }
        }
    }

    private void requestBucks() {
        // TODO Auto-generated method stub

    }

    private void exitProgram() {
        System.exit(0);
    }

    private void registerAndLogin() {
        while (!isAuthenticated()) {
            String choice = (String) console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
            if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
                login();
            } else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
                register();
            } else {
                // the only other option on the login menu is to exit
                exitProgram();
            }
        }
    }

    private boolean isAuthenticated() {
        return currentUser != null;
    }

    private void register() {
        System.out.println("Please register a new user account");
        boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
                authenticationService.register(credentials);
                isRegistered = true;
                System.out.println("Registration successful. You can now login.");
            } catch (AuthenticationServiceException e) {
                System.out.println("REGISTRATION ERROR: " + e.getMessage());
                System.out.println("Please attempt to register again.");
            }
        }
    }

    private void login() {
        System.out.println("Please log in");
        currentUser = null;
        while (currentUser == null) //will keep looping until user is logged in
        {
            UserCredentials credentials = collectUserCredentials();
            try {
                currentUser = authenticationService.login(credentials);
            } catch (AuthenticationServiceException e) {
                System.out.println("LOGIN ERROR: " + e.getMessage());
                System.out.println("Please attempt to login again.");
            }
        }
    }

    private UserCredentials collectUserCredentials() {
        String username = console.getUserInput("Username");
        String password = console.getUserInput("Password");
        return new UserCredentials(username, password);
    }
}
