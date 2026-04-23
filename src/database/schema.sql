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
                       referee_id varchar(50) references  member (id),
                       collectivity_id VARCHAR(50) REFERENCES collectivity (id)
);

ALTER TABLE member add column collectivity_id VARCHAR(50) REFERENCES collectivity (id);
create table collectivity_structure
(
    collectivity_id VARCHAR(50) REFERENCES collectivity (id),
    role            VARCHAR(10) CHECK (role IN ('PRESIDENT', 'VICE_PRESIDENT', 'TREASURER', 'SECRETARY')),
    member_id       VARCHAR(50) REFERENCES member (id)
);


CREATE TABLE Collectivity(
                             id varchar(50) primary key ,
                             location varchar(50),
                             federation_approval boolean,
                             creation_date date
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

Drop table collectivity_structure;
ALTER TABLE member ADD COLUMN membership_date DATE;
ALTER TABLE member ADD COLUMN active boolean;

