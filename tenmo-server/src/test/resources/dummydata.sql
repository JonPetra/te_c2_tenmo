TRUNCATE users, accounts, transfers CASCADE;

INSERT INTO users (user_id, username, password_hash) 
VALUES (1091, 'testuser1', 'password1'),
       (1092, 'testuser2', 'password1'),
       (1093, 'testuser3', 'password1'),
       (1094, 'testuser4', 'password1'),
       (1095, 'testuser5', 'password1'),
       (1096, 'testuser6', 'password1');

INSERT INTO accounts (account_id, user_id, balance)
VALUES (2091, 1091, 1000.00),
       (2092, 1092, 1000.00),
       (2093, 1093, 1000.00),
       (2094, 1094, 1000.00),
       (2095, 1095, 1000.00),
       (2096, 1096, 1000.00);

INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (3091, 2, 2, 2091, 2092, 5.00),
       (3092, 2, 2, 2092, 2091, 50.99),
       (3093, 2, 2, 2093, 2092, 500.06),
       (3094, 2, 2, 2094, 2091, 100.00),
       (3095, 2, 2, 2095, 2094, 95.00),
       (3096, 2, 2, 2096, 2093, 5000.00);

