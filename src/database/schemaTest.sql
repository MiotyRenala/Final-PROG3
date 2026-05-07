INSERT INTO collectivity (id, name, number, location)
VALUES
    ('col-1', 'Mpanorina', 1, 'Ambatondrazaka'),
    ('col-2', 'Dobo voalohany', 2, 'Ambatondrazaka'),
    ('col-3', 'Tantely mamy', 3, 'Brickaville');

INSERT INTO member (id, last_name, first_name, birth_date, gender, address, profession, phone_number, email, occupation, collectivity_id)
VALUES
    ('C2-M1', 'Nom membre 1', 'Prénom membre 1', '1980-02-01', 'MALE', 'Lot II V M Ambato', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'SENIOR', 'col-2'),
    ('C2-M2', 'Nom membre 2', 'Prénom membre 2', '1982-03-05', 'MALE', 'Lot II F Ambato', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'SENIOR', 'col-2'),
    ('C2-M3', 'Nom membre 3', 'Prénom membre 3', '1992-03-10', 'MALE', 'Lot II J Ambato', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SENIOR', 'col-2'),
    ('C2-M4', 'Nom membre 4', 'Prénom membre 4', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'SENIOR', 'col-2'),
    ('C2-M5', 'Nom membre 5', 'Prénom membre 5', '1999-08-21', 'MALE', 'Lot UV 80 Ambato', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'PRESIDENT', 'col-2'),
    ('C2-M6', 'Nom membre 6', 'Prénom membre 6', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'VICE_PRESIDENT', 'col-2'),
    ('C2-M7', 'Nom membre 7', 'Prénom membre 7', '1998-01-31', 'MALE', 'Lot UV 7 Ambato', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'SECRETARY', 'col-2'),
    ('C2-M8', 'Nom membre 8', 'Prénom membre 6', '1975-08-20', 'MALE', 'Lot UV 8 Ambato', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'TREASURER', 'col-2');

INSERT INTO member (id, last_name, first_name, birth_date, gender, address, profession, phone_number, email, occupation, collectivity_id)
VALUES
    ('C1-M1', 'Nom membre 1', 'Prénom membre 1', '1980-02-01', 'MALE', 'Lot II V Ambato', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'PRESIDENT', 'col-1'),
    ('C1-M2', 'Nom membre 2', 'Prénom membre 2', '1982-03-05', 'MALE', 'Lot II F Ambato', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'VICE_PRESIDENT', 'col-1'),
    ('C1-M3', 'Nom membre 3', 'Prénom membre 3', '1992-03-10', 'MALE', 'Lot II J Ambato', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SECRETARY', 'col-1'),
    ('C1-M4', 'Nom membre 4', 'Prénom membre 4', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'TREASURER', 'col-1'),
    ('C1-M5', 'Nom membre 5', 'Prénom membre 5', '1999-08-21', 'MALE', 'Lot UV 80 Ambato', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'SENIOR', 'col-1'),
    ('C1-M6', 'Nom membre 6', 'Prénom membre 6', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'SENIOR', 'col-1'),
    ('C1-M7', 'Nom membre 7', 'Prénom membre 7', '1998-01-31', 'MALE', 'Lot UV 7 Ambato', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'SENIOR', 'col-1'),
    ('C1-M8', 'Nom membre 8', 'Prénom membre 6', '1975-08-20', 'MALE', 'Lot UV 8 Ambato', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'SENIOR', 'col-1')

INSERT INTO member (id, last_name, first_name, birth_date, gender, address, profession, phone_number, email, occupation, collectivity_id)
VALUES
    ('C3-M1', 'Nom membre 9', 'Prénom membre 9', '1988-01-02', 'MALE', 'Lot 33 J Antsirabe', 'Apiculteur', '034034567', 'member.9@fed-agri.mg', 'PRESIDENT', 'col-3'),
    ('C3-M2', 'Nom membre 10', 'Prénom membre 10', '1982-03-05', 'MALE', 'Lot 2 J Antsirabe', 'Agriculteur', '0338634567', 'member.10@fed-agri.mg', 'VICE_PRESIDENT', 'col-3'),
    ('C3-M3', 'Nom membre 11', 'Prénom membre 11', '1992-03-12', 'MALE', 'Lot 8 KM Antsirabe', 'Collecteur', '0338234567', 'member.11@fed-agri.mg', 'SECRETARY', 'col-3'),
    ('C3-M4', 'Nom membre 12', 'Prénom membre 12', '1988-05-10', 'FEMALE', 'Lot A K 50 Antsirabe', 'Distributeur', '0382334567', 'member.12@fed-agri.mg', 'TREASURER', 'col-3'),
    ('C3-M5', 'Nom membre 13', 'Prénom membre 13', '1999-08-11', 'MALE', 'Lot UV 80 Antsirabe', 'Apiculteur', '0373365567', 'member.13@fed-agri.mg', 'SENIOR', 'col-3'),
    ('C3-M6', 'Nom membre 14', 'Prénom membre 14', '1998-08-09', 'FEMALE', 'Lot UV 6 Antsirabe', 'Apiculteur', '0378234567', 'member.14@fed-agri.mg', 'SENIOR', 'col-3'),
    ('C3-M7', 'Nom membre 15', 'Prénom membre 15', '1998-01-13', 'MALE', 'Lot UV 7 Antsirabe', 'Apiculteur', '0374914567', 'member.15@fed-agri.mg', 'SENIOR', 'col-3'),
    ('C3-M8', 'Nom membre 16', 'Prénom membre 16', '1975-08-02', 'MALE', 'Lot UV 8 Antsirabe', 'Apiculteur', '0370634567', 'member.16@fed-agri.mg', 'SENIOR', 'col-3');

INSERT INTO financial_account (id, type,collectivity_id) VALUES
                                                             ('C1-A-CASH', 'CASH','col-1'),
                                                             ('C1-A-MOBILE-1', 'MOBILE_MONEY','col-1'),
                                                             ('C2-A-CASH', 'CASH','col-2'),
                                                             ('C2-A-MOBILE-1', 'MOBILE_MONEY','col-2'),
                                                             ('C3-A-CASH', 'CASH','col-3');

INSERT INTO cash_account (id, amount) VALUES
                                          ('C1-A-CASH', 0.00),
                                          ('C2-A-CASH', 0.00),
                                          ('C3-A-CASH', 0.00);

INSERT INTO mobile_banking_account (
    id,
    holder_name,
    mobile_banking_service,
    mobile_number,
    amount
) VALUES
      ('C1-A-MOBILE-1', 'Mpanorina', 'ORANGE_MONEY', 370489612, 0.00),
      ('C2-A-MOBILE-1', 'Dobo voalohany', 'ORANGE_MONEY', 320489612, 0.00);

INSERT INTO financial_account (account_id, account_type, collectivity_id)
VALUES
    ('C3-A-BANK-1', 'BANK', 'col-3'),
    ('C3-A-BANK-2', 'BANK', 'col-3');

INSERT INTO bank_account (id, holder_name, bank_name, bank_code, bank_branch_code, bank_account_code, bank_account_key, amount)
VALUES
    ('C3-A-BANK-1', 'Koto', 'BMOI', 00004, 00001, 1234567890, 12, 0),
    ('C3-A-BANK-2', 'Naivo', 'BRED', 00008, 00003, 4567890123, 58, 0);

INSERT INTO financial_account (account_id, account_type, collectivity_id)
VALUES ('C3-A-MOBILE-1', 'MOBILE_MONEY', 'col-3');

INSERT INTO mobile_banking_account (id, holder_name, mobile_banking_service, mobile_number, amount)
VALUES ('C3-A-MOBILE-1', 'Kolo', 'MVOLA', 0341889612, 0);

-
INSERT INTO membership_fee (id, eligible_from, frequency, amount, label, status, collectivity_id)
VALUES
    ('cot-1', '2026-01-01', 'ANNUALLY', 200000.00, 'Cotisation annuelle', 'ACTIVE', 'col-1'),
    ('cot-2', '2026-04-30', 'PUNCTUALLY', 20000.00, 'Famangiana', 'ACTIVE', 'col-1'),
    ('cot-3', '2026-01-01', 'ANNUALLY', 200000.00, 'Cotisation annuelle', 'ACTIVE', 'col-2'),
    ('cot-4', '2025-01-01', 'ANNUALLY', 100000.00, 'Cotisation 2025', 'INACTIVE', 'col-3'),
    ('cot-5', '2026-04-01', 'MONTHLY', 25000.00, 'Cotisation mensuelle', 'ACTIVE', 'col-3');

--collectivity 1
INSERT INTO member_payment (id, collectivity_id, member_id, amount, payment_mode, account_credited_id, creation_date)
VALUES
    ('PAY-001', 'col-1', 'C1-M1', 200000.00, 'CASH', 'C1-A-CASH', '2026-01-01'),
    ('PAY-002', 'col-1', 'C1-M2', 200000.00, 'CASH', 'C1-A-CASH', '2026-01-01'),
    ('PAY-003', 'col-1', 'C1-M3', 200000.00, 'MOBILE_MONEY', 'C1-A-MOBILE-1', '2026-01-01'),
    ('PAY-004', 'col-1', 'C1-M4', 200000.00, 'MOBILE_MONEY', 'C1-A-MOBILE-1', '2026-01-01'),
    ('PAY-005', 'col-1', 'C1-M5', 150000.00, 'MOBILE_MONEY', 'C1-A-MOBILE-1', '2026-01-01'),
    ('PAY-006', 'col-1', 'C1-M6', 100000.00, 'CASH', 'C1-A-CASH', '2026-05-01'),
    ('PAY-007', 'col-1', 'C1-M7', 60000.00, 'CASH', 'C1-A-CASH', '2026-05-01'),
    ('PAY-008', 'col-1', 'C1-M8', 90000.00, 'CASH', 'C1-A-CASH', '2026-05-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES
    ('TXN-001', 'col-1', '2026-01-01', 200000.00, 'CASH', 'C1-A-CASH', 'C1-M1'),
    ('TXN-002', 'col-1', '2026-01-01', 200000.00, 'CASH', 'C1-A-CASH', 'C1-M2'),
    ('TXN-003', 'col-1', '2026-01-01', 200000.00, 'MOBILE_MONEY', 'C1-A-MOBILE-1', 'C1-M3'),
    ('TXN-004', 'col-1', '2026-01-01', 200000.00, 'MOBILE_MONEY', 'C1-A-MOBILE-1', 'C1-M4'),
    ('TXN-005', 'col-1', '2026-01-01', 150000.00, 'MOBILE_MONEY', 'C1-A-MOBILE-1', 'C1-M5'),
    ('TXN-006', 'col-1', '2026-05-01', 100000.00, 'CASH', 'C1-A-CASH', 'C1-M6'),
    ('TXN-007', 'col-1', '2026-05-01', 60000.00, 'CASH', 'C1-A-CASH', 'C1-M7'),
    ('TXN-008', 'col-1', '2026-05-01', 90000.00, 'CASH', 'C1-A-CASH', 'C1-M8');

--collectivity 2
INSERT INTO member_payment (id, collectivity_id, member_id,  amount, payment_mode, account_credited_id, creation_date)
VALUES
    ('PAY2-001', 'col-2', 'C2-M1', 120000.00, 'CASH', 'C2-A-CASH', '2026-01-01'),
    ('PAY2-002', 'col-2', 'C2-M2', 180000.00, 'CASH', 'C2-A-CASH', '2026-01-01'),
    ('PAY2-003', 'col-2', 'C2-M3', 200000.00, 'CASH', 'C2-A-CASH', '2026-01-01'),
    ('PAY2-004', 'col-2', 'C2-M4', 200000.00, 'CASH', 'C2-A-CASH', '2026-01-01'),
    ('PAY2-005', 'col-2', 'C2-M5', 200000.00, 'CASH', 'C2-A-CASH', '2026-01-01'),
    ('PAY2-006', 'col-2', 'C2-M6', 200000.00, 'CASH', 'C2-A-CASH', '2026-01-01'),
    ('PAY2-007', 'col-2', 'C2-M7', 80000.00, 'MOBILE_MONEY', 'C2-A-MOBILE-1', '2026-01-01'),
    ('PAY2-008', 'col-2', 'C2-M8', 120000.00, 'MOBILE_MONEY', 'C2-A-MOBILE-1', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES
    ('TXN2-001', 'col-2', '2026-01-01', 120000.00, 'CASH', 'C2-A-CASH', 'C2-M1'),
    ('TXN2-002', 'col-2', '2026-01-01', 180000.00, 'CASH', 'C2-A-CASH', 'C2-M2'),
    ('TXN2-003', 'col-2', '2026-01-01', 200000.00, 'CASH', 'C2-A-CASH', 'C2-M3'),
    ('TXN2-004', 'col-2', '2026-01-01', 200000.00, 'CASH', 'C2-A-CASH', 'C2-M4'),
    ('TXN2-005', 'col-2', '2026-01-01', 200000.00, 'CASH', 'C2-A-CASH', 'C2-M5'),
    ('TXN2-006', 'col-2', '2026-01-01', 200000.00, 'CASH', 'C2-A-CASH', 'C2-M6'),
    ('TXN2-007', 'col-2', '2026-01-01', 80000.00, 'MOBILE_MONEY', 'C2-A-MOBILE-1', 'C2-M7'),
    ('TXN2-008', 'col-2', '2026-01-01', 120000.00, 'MOBILE_MONEY', 'C2-A-MOBILE-1', 'C2-M8');

--collectivity 3
INSERT INTO member_payment (id, collectivity_id, member_id, membership_fee_id, amount, payment_mode, account_credited_id, creation_date)
VALUES
    ('PAY3-001', 'col-3', 'C3-M1', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-1', '2026-04-01'),
    ('PAY3-002', 'col-3', 'C3-M2', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-1', '2026-04-01'),
    ('PAY3-003', 'col-3', 'C3-M3', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-1', '2026-04-01'),
    ('PAY3-004', 'col-3', 'C3-M4', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-1', '2026-04-01'),
    ('PAY3-005', 'col-3', 'C3-M5', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-2', '2026-04-01'),
    ('PAY3-006', 'col-3', 'C3-M6', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-2', '2026-04-01'),
    ('PAY3-007', 'col-3', 'C3-M7', 'cot-5', 25000.00, 'CASH', 'C3-A-CASH', '2026-04-01'),
    ('PAY3-008', 'col-3', 'C3-M8', 'cot-5', 25000.00, 'CASH', 'C3-A-CASH', '2026-04-01'),
    ('PAY3-009', 'col-3', 'C3-M1', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-1', '2026-05-01'),
    ('PAY3-010', 'col-3', 'C3-M2', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-1', '2026-05-01'),
    ('PAY3-011', 'col-3', 'C3-M3', 'cot-5', 15000.00, 'BANK', 'C3-A-MOBILE-1', '2026-05-01'),
    ('PAY3-012', 'col-3', 'C3-M4', 'cot-5', 15000.00, 'BANK', 'C3-A-MOBILE-1', '2026-05-01'),
    ('PAY3-013', 'col-3', 'C3-M5', 'cot-5', 20000.00, 'BANK', 'C3-A-BANK-2', '2026-05-01'),
    ('PAY3-014', 'col-3', 'C3-M6', 'cot-5', 25000.00, 'BANK', 'C3-A-BANK-2', '2026-05-01'),
    ('PAY3-015', 'col-3', 'C3-M7', 'cot-5', 5000.00, 'CASH', 'C3-A-CASH', '2026-05-01'),
    ('PAY3-016', 'col-3', 'C3-M8', 'cot-5', 5000.00, 'CASH', 'C3-A-CASH', '2026-05-01');
3. Insertion des transactions (collectivity_transaction)
sql
INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES
    ('TXN3-001', 'col-3', '2026-04-01', 25000.00, 'BANK', 'C3-A-BANK-1', 'C3-M1'),
    ('TXN3-002', 'col-3', '2026-04-01', 25000.00, 'BANK', 'C3-A-BANK-1', 'C3-M2'),
    ('TXN3-003', 'col-3', '2026-04-01', 25000.00, 'BANK', 'C3-A-BANK-1', 'C3-M3'),
    ('TXN3-004', 'col-3', '2026-04-01', 25000.00, 'BANK', 'C3-A-BANK-1', 'C3-M4'),
    ('TXN3-005', 'col-3', '2026-04-01', 25000.00, 'BANK', 'C3-A-BANK-2', 'C3-M5'),
    ('TXN3-006', 'col-3', '2026-04-01', 25000.00, 'BANK', 'C3-A-BANK-2', 'C3-M6'),
    ('TXN3-007', 'col-3', '2026-04-01', 25000.00, 'CASH', 'C3-A-CASH', 'C3-M7'),
    ('TXN3-008', 'col-3', '2026-04-01', 25000.00, 'CASH', 'C3-A-CASH', 'C3-M8'),
    ('TXN3-009', 'col-3', '2026-05-01', 25000.00, 'BANK', 'C3-A-BANK-1', 'C3-M1'),
    ('TXN3-010', 'col-3', '2026-05-01', 25000.00, 'BANK', 'C3-A-BANK-1', 'C3-M2'),
    ('TXN3-011', 'col-3', '2026-05-01', 15000.00, 'BANK', 'C3-A-MOBILE-1', 'C3-M3'),
    ('TXN3-012', 'col-3', '2026-05-01', 15000.00, 'BANK', 'C3-A-MOBILE-1', 'C3-M4'),
    ('TXN3-013', 'col-3', '2026-05-01', 20000.00, 'BANK', 'C3-A-BANK-2', 'C3-M5'),
    ('TXN3-014', 'col-3', '2026-05-01', 25000.00, 'BANK', 'C3-A-BANK-2', 'C3-M6'),
    ('TXN3-015', 'col-3', '2026-05-01', 5000.00, 'CASH', 'C3-A-CASH', 'C3-M7'),
    ('TXN3-016', 'col-3', '2026-05-01', 5000.00, 'CASH', 'C3-A-CASH', 'C3-M8');

INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, collectivity_id, membership_date, active)
VALUES
    ('C1-M13', 'Rakotoarisoa', 'Toky', '1995-03-12', 'MALE', 'Lot II J 123 Antananarivo', 'Étudiant', 0321234567, 'toky.rakoto@email.com', 'JUNIOR', 'col-1', '2026-04-01', true),
    ('C1-M14', 'Razafindrakoto', 'Miora', '1998-07-25', 'FEMALE', 'Lot 45 Bis Ambohimanarina', 'Chômeur', 0332345678, 'miora.razafy@email.com', 'JUNIOR', 'col-1', '2026-04-01', true),
    ('C1-M15', 'Andriamanantena', 'Nantenaina', '2000-11-18', 'MALE', 'Ampasampito Toamasina', 'Apprenti', 0343456789, 'nantena.andriana@email.com', 'JUNIOR', 'col-1', '2026-05-01', true),
    ('C1-M16', 'Ramanantsoa', 'Hery', '1993-09-08', 'MALE', '67 Ha Mahamasina', 'Ouvrier', 0354567890, 'hery.ramanantsoa@email.com', 'JUNIOR', 'col-1', '2026-06-01', true);

-- Créer la table de liaison many-to-many
CREATE TABLE member_referee (
                                member_id VARCHAR(50) REFERENCES member(id),
                                referee_id VARCHAR(50) REFERENCES member(id),
                                PRIMARY KEY (member_id, referee_id)
);

-- Insérer les relations pour col-1
INSERT INTO member_referee (member_id, referee_id) VALUES
                                                       ('C1-M13', 'C1-M1'),
                                                       ('C1-M13', 'C1-M2'),
                                                       ('C1-M14', 'C1-M1'),
                                                       ('C1-M14', 'C1-M2'),
                                                       ('C1-M15', 'C1-M1'),
                                                       ('C1-M15', 'C1-M2'),
                                                       ('C1-M16', 'C1-M1'),
                                                       ('C1-M16', 'C1-M2');

-- 1. Insertion des nouveaux membres
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, collectivity_id, membership_date, active)
VALUES
    ('C2-M12', 'Randrianasolo', 'Faneva', '1996-08-14', 'MALE', 'Lot 123 Andraharo Antananarivo', 'Technicien', 0329876543, 'faneva.randria@email.com', 'JUNIOR', 'col-2', '2026-03-01', true),
    ('C2-M13', 'Ravelonirina', 'Soa', '1999-12-04', 'FEMALE', '67 C Itaosy', 'Secrétaire', 0330987654, 'soa.ravelo@email.com', 'JUNIOR', 'col-2', '2026-03-01', true),
    ('C2-M14', 'Rakotondramasy', 'Tahina', '2001-02-28', 'MALE', 'Ambatobe', 'Étudiant', 0341098765, 'tahina.rakoto@email.com', 'JUNIOR', 'col-2', '2026-03-01', true);

-- 2. Insertion des relations référents (si table member_referee existe)
INSERT INTO member_referee (member_id, referee_id) VALUES
                                                       ('C2-M12', 'C1-M1'),
                                                       ('C2-M12', 'C1-M2'),
                                                       ('C2-M13', 'C1-M1'),
                                                       ('C2-M13', 'C1-M2'),
                                                       ('C2-M14', 'C1-M1'),
                                                       ('C2-M14', 'C1-M2');

-- 1. Insertion des nouveaux membres
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, collectivity_id, membership_date, active)
VALUES
    ('C3-M9', 'Rakotomalala', 'Ny Aina', '1994-05-20', 'FEMALE', 'Lot IV 51 Andoharanofotsy', 'Comptable', 0324567890, 'nyaina.rakoto@email.com', 'JUNIOR', 'col-3', '2026-01-01', true),
    ('C3-M10', 'Randriamampionona', 'Mamy', '1997-09-12', 'MALE', 'Tsiadana Mahajanga', 'Chauffeur', 0335678901, 'mamy.randria@email.com', 'JUNIOR', 'col-3', '2026-02-01', true),
    ('C3-M11', 'Raharimanana', 'Tendry', '2000-11-05', 'MALE', '67 Ha Antsiranana', 'Plombier', 0346789012, 'tendry.rahari@email.com', 'JUNIOR', 'col-3', '2026-02-01', true),
    ('C3-M12', 'Andriatsilavina', 'Lova', '1992-07-18', 'FEMALE', 'Ankatso Antananarivo', 'Enseignante', 0357890123, 'lova.andriatsi@email.com', 'JUNIOR', 'col-3', '2026-03-01', true),
    ('C3-M13', 'Ramanakoto', 'Haja', '1998-03-25', 'MALE', 'Ambalavao Fianarantsoa', 'Vendeur', 0368901234, 'haja.ramanakoto@email.com', 'JUNIOR', 'col-3', '2026-03-01', true),
    ('C3-M14', 'Razafimandimby', 'Narindra', '1995-10-30', 'FEMALE', 'Manakara Atsimo', 'Infirmière', 0379012345, 'narindra.razafy@email.com', 'JUNIOR', 'col-3', '2026-03-01', true);

-- 2. Insertion des relations référents
INSERT INTO member_referee (member_id, referee_id) VALUES
                                                       ('C3-M9', 'C3-M1'),
                                                       ('C3-M9', 'C3-M2'),
                                                       ('C3-M10', 'C3-M1'),
                                                       ('C3-M10', 'C3-M2'),
                                                       ('C3-M11', 'C3-M1'),
                                                       ('C3-M11', 'C3-M2'),
                                                       ('C3-M12', 'C3-M1'),
                                                       ('C3-M12', 'C3-M2'),
                                                       ('C3-M13', 'C3-M1'),
                                                       ('C3-M13', 'C3-M2'),
                                                       ('C3-M14', 'C3-M1'),
                                                       ('C3-M14', 'C3-M2');
