

CREATE type gender_enum as ENUM('MALE', 'FEMALE');
CREATE type occupation_enum as ENUM('JUNIOR', 'SENIOR', 'SECRETARY', 'TREASURER', 'VICE_PRESIDENT', 'PRESIDENT');
CREATE TABLE member(
                       id varchar(50) primary key,
                       first_name varchar(50) not null,
                       last_name varchar(50),
                       birth_date timestamp,
                       gender gender_enum,
                       address varchar(50),
                       profession varchar(10),
                       phone_number int,
                       email varchar(50),
                       occupation occupation_enum,
    referee_id varchar(50) references  member (id)

);
create table collectivity_structure(
    id integer primary key,
    president varchar(50) references member (id),
    vice_president varchar(50) references member (id),
    secretary varchar(50) references member (id),
    treasurer varchar(50) references member (id)
);


CREATE TABLE Collectivity(
                             id varchar(50) primary key ,
                             location varchar(50),
    structure_id integer references collectivity_structure (id),
    member_id varchar(50) references member (id)
);

ALTER TABLE collectivity ADD COLUMN dues_amount DOUBLE;

CREATE TABLE membership_fee (
                                id VARCHAR(50) PRIMARY KEY,
                                eligible_from DATE NOT NULL,
                                frequency VARCHAR(20) NOT NULL,
                                amount DECIMAL(10,2) NOT NULL,
                                label VARCHAR(255),
                                status VARCHAR(20) NOT NULL,
                                collectivity_id VARCHAR(50) NOT NULL
);

ALTER table Collectivity DROP column structure_id;
ALTER table Collectivity DROP column member_id;
ALTER table Collectivity ADD COLUMN federation_approval boolean;
ALTER table Collectivity ADD COLUMN creation_date date;


ALTER table member drop column referee_id;
ALTER table member add column collectivity_id varchar(50) references Collectivity (id);
ALTER table member add column membership_date date;
ALTER table member add column active boolean default true;

create type payment_mode_enum as enum ('CASH','MOBILE_BANKING','BANK_TRANSFER');
Create table collectivity_transaction(
    id varchar(50) PRIMARY KEY ,
    collectivity_id varchar(50) references Collectivity (id) not null,
    creation_date date not null,
    amount numeric(12,2) not null,
    payment_mode payment_mode_enum not null,
    account_credited_id varchar(50) not null,
    member_debited_id varchar(50) references member(id)

);

create type bank_enum as enum('BRED' ,'MCB','BMOI','BOA','BGFI','AFG','ACCES_BAQUE','BAOBAB','SIPEM');
create table bank_account(
  id varchar(50) primary key references financial_account(account_id),
  holder_name varchar(50) not null,
  bank_name bank_enum not null,
  bank_code int not null,
    bank_branch_code int,
    bank_account_code int,
    bank_account_key int,
    amount numeric(12,2) not null check ( amount > 0 )

);

create type mobile_banking_service_enum as enum ('AIRTEL_MONEY', 'MVOLA','ORANGE_MONEY')
create table mobile_banking_account(
    id varchar(50) primary key references financial_account(account_id) ,
    holder_name varchar(50) not null,
    mobile_banking_service mobile_banking_service_enum,
    mobile_number int,
    amount numeric(12,2) check ( amount >0 )
);

create table cash_account(
    id varchar(50) primary key references financial_account(account_id),
    amount numeric(12,2) check ( amount>0 )
);

create type account_type_enum as enum ('CASH','BANK','MOBILE_MONEY')
create table financial_account(
    account_id varchar(50) primary key ,
    account_type account_type_enum
);