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

INSERT INTO collectivity_structure (id,collectivity_id, position, member_id) VALUES
                                                                                 ('struct5','col-3', 'PRESIDENT', 'C3-M1'),
                                                                                 ('struct6','col-3', 'VICE_PRESIDENT', 'C3-M2'),
                                                                                 ('struct7','col-3', 'SECRETARY', 'C3-M3'),
                                                                                 ('struct8','col-3', 'TREASURER', 'C3-M4');


INSERT INTO member_referees (member_id, referee_id) VALUES
                                                        ('C3-M1', 'C1-M1'),
                                                        ('C3-M1', 'C1-M2');

INSERT INTO financial_account (id, type) VALUES
                                             ('C1-A-CASH', 'CASH'),
                                             ('C1-A-MOBILE-1', 'MOBILE_BANKING'),
                                             ('C2-A-CASH', 'CASH'),
                                             ('C2-A-MOBILE-1', 'MOBILE_BANKING'),
                                             ('C3-A-CASH', 'CASH');

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

INSERT INTO membership_fee (id, eligible_from, frequency, amount, label, status, collectivity_id)
VALUES ('cot-3', '2026-01-01', 'ANNUALLY', 50000.00, 'Cotisation annuelle', 'ACTIVE', 'col-3'),
 ('cot-2', '2026-01-01', 'ANNUALLY', 100000.00, 'Cotisation annuelle', 'ACTIVE', 'col-2'),
 ('cot-1', '2026-01-01', 'ANNUALLY', 100000.00, 'Cotisation annuelle', 'ACTIVE', 'col-1');

INSERT INTO financial_account (collectivity_id, account_id, account_type, initial_amount, holder, phone_number)
VALUES
    ('col-1', 'C1-A-CASH', 'CASH', 0, NULL, NULL),
    ('col-1', 'C1-A-MOBILE-1', 'ORANGE_MONEY', 0, 'Mpanorina', '0370489612');

INSERT INTO member_payment (id,collectivity_id, member_id, amount, account_credited_id, payment_mode, creation_date)
VALUES
    ('MP-C1-001','col-1', 'C1-M1', 100000, 'C1-A-CASH', 'CASH', '2026-01-01'),
    ('MP-C1-002','col-1', 'C1-M2', 100000, 'C1-A-CASH', 'CASH', '2026-01-01'),
    ('MP-C1-003','col-1', 'C1-M3', 100000, 'C1-A-CASH', 'CASH', '2026-01-01'),
    ('MP-C1-004','col-1', 'C1-M4', 100000, 'C1-A-CASH', 'CASH', '2026-01-01'),
    ('MP-C1-005','col-1', 'C1-M5', 100000, 'C1-A-CASH', 'CASH', '2026-01-01'),
    ('MP-C1-006','col-1', 'C1-M6', 100000, 'C1-A-CASH', 'CASH', '2026-01-01'),
    ('MP-C1-007','col-1', 'C1-M7', 60000,  'C1-A-CASH', 'CASH', '2026-01-01'),
    ('MP-C1-008','col-1', 'C1-M8', 90000,  'C1-A-CASH', 'CASH', '2026-01-01');

INSERT INTO collectivity_transaction (
    id,
    collectivity_id,
    creation_date,
    amount,
    payment_mode,
    account_credited_id,
    member_debited_id
) VALUES
      ('TRX-C1-001', 'col-1', DATE '2026-01-01', 100000.00, 'CASH', 'C1-A-CASH', 'C1-M1'),
      ('TRX-C1-002', 'col-1', DATE '2026-01-01', 100000.00, 'CASH', 'C1-A-CASH', 'C1-M2'),
      ('TRX-C1-003', 'col-1', DATE '2026-01-01', 100000.00, 'CASH', 'C1-A-CASH', 'C1-M3'),
      ('TRX-C1-004', 'col-1', DATE '2026-01-01', 100000.00, 'CASH', 'C1-A-CASH', 'C1-M4'),
      ('TRX-C1-005', 'col-1', DATE '2026-01-01', 100000.00, 'CASH', 'C1-A-CASH', 'C1-M5'),
      ('TRX-C1-006', 'col-1', DATE '2026-01-01', 100000.00, 'CASH', 'C1-A-CASH', 'C1-M6'),
      ('TRX-C1-007', 'col-1', DATE '2026-01-01',  60000.00, 'CASH', 'C1-A-CASH', 'C1-M7'),
      ('TRX-C1-008', 'col-1', DATE '2026-01-01',  90000.00, 'CASH', 'C1-A-CASH', 'C1-M8');

INSERT INTO member_payment (
    id,
    collectivity_id,
    member_id,
    amount,
    account_credited_id,
    payment_mode,
    creation_date
) VALUES
      ('MP-C2-001','col-2','C2-M1',  60000.00, 'C2-A-CASH',     'CASH',   DATE '2026-01-01'),
      ('MP-C2-002','col-2','C2-M2',  90000.00, 'C2-A-CASH',     'CASH',   DATE '2026-01-01'),
      ('MP-C2-003','col-2','C2-M3', 100000.00, 'C2-A-CASH',     'CASH',   DATE '2026-01-01'),
      ('MP-C2-004','col-2','C2-M4', 100000.00, 'C2-A-CASH',     'CASH',   DATE '2026-01-01'),
      ('MP-C2-005','col-2','C2-M5', 100000.00, 'C2-A-CASH',     'CASH',   DATE '2026-01-01'),
      ('MP-C2-006','col-2','C2-M6', 100000.00, 'C2-A-CASH',     'CASH',   DATE '2026-01-01'),
      ('MP-C2-007','col-2','C2-M7',  40000.00, 'C2-A-MOBILE-1', 'MOBILE_BANKING', DATE '2026-01-01'),
      ('MP-C2-008','col-2','C2-M8',  60000.00, 'C2-A-MOBILE-1', 'MOBILE_BANKING', DATE '2026-01-01');

INSERT INTO collectivity_transaction (
    id,
    collectivity_id,
    creation_date,
    amount,
    payment_mode,
    account_credited_id,
    member_debited_id
) VALUES
      ('TRX-C2-001','col-2', DATE '2026-01-01',  60000.00, 'CASH',   'C2-A-CASH',     'C2-M1'),
      ('TRX-C2-002','col-2', DATE '2026-01-01',  90000.00, 'CASH',   'C2-A-CASH',     'C2-M2'),
      ('TRX-C2-003','col-2', DATE '2026-01-01', 100000.00, 'CASH',   'C2-A-CASH',     'C2-M3'),
      ('TRX-C2-004','col-2', DATE '2026-01-01', 100000.00, 'CASH',   'C2-A-CASH',     'C2-M4'),
      ('TRX-C2-005','col-2', DATE '2026-01-01', 100000.00, 'CASH',   'C2-A-CASH',     'C2-M5'),
      ('TRX-C2-006','col-2', DATE '2026-01-01', 100000.00, 'CASH',   'C2-A-CASH',     'C2-M6'),
      ('TRX-C2-007','col-2', DATE '2026-01-01',  40000.00, 'MOBILE_BANKING', 'C2-A-MOBILE-1', 'C2-M7'),
      ('TRX-C2-008','col-2', DATE '2026-01-01',  60000.00, 'MOBILE_BANKING', 'C2-A-MOBILE-1', 'C2-M8');