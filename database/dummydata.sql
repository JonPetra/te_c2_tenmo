TRUNCATE users, accounts, transfers CASCADE;

INSERT INTO users (user_id, username, password_hash) 
VALUES (1001, 'testuser', 'password1'),
       (1002, 'testuser1', 'password1'),
       (1003, 'testuser2', 'password1'),
       (1004, 'testuser3', 'password1'),
       (1005, 'testuser4', 'password1'),
       (1006, 'testuser5', 'password1');

INSERT INTO accounts (account_id, user_id, balance)
VALUES (2001, 1001, 1000.00), 
       (2002, 1002, 1000.00),
       (2003, 1003, 1000.00), 
       (2004, 1004, 1000.00), 
       (2005, 1005, 1000.00), 
       (2006, 1006, 1000.00); 

INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (3001, 2, 2, 2001, 2002, 5.00),
       (3002, 2, 2, 2002, 2001, 50.99),
       (3003, 2, 2, 2003, 2002, 500.06),
       (3004, 2, 2, 2004, 2001, 100.00),
       (3005, 2, 2, 2005, 2004, 95.00),
       (3006, 2, 2, 2006, 2003, 52.00);

