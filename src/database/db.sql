

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
)

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

