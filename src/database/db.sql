GRANT ALL PRIVILEGES ON DATABASE federation to federation_user;

CREATE TABLE federation(
                           id int PRIMARY KEY,
                           name VARCHAR(50)
);

CREATE TYPE status_enum as ENUM ('CONFIRMED', 'ON HOLD', 'REMOVE');

CREATE TABLE collectivities(
                               id int primary key ,
                               name varchar(50) ,
                               city varchar(50) ,
                               creation_date date,
                               speciality varchar(50),
                               status status_enum
);

CREATE TABLE mandat(
                       id int primary key ,
                       start_date date,
                       end_date date,
                       year int,
                       id_collectivity int REFERENCES collectivities(id)
);


CREATE TYPE gender_enum as ENUM ('M', 'F');

CREATE TABLE member(
                       id int primary key,
                       name varchar(50),
                       gender gender_enum,
                       activity varchar(50),
                       phone_number float,
                       email varchar(50),
                       joining_date date,
                       active boolean

);

CREATE TABLE position(
                         id int primary key,
                         description varchar(50)
);

CREATE TABLE membership(
                           id int primary key,
                           id_mandat int REFERENCES mandat(id),
                           id_collectivity int REFERENCES collectivities(id),
                           id_member int REFERENCES member(id),
                           id_position int REFERENCES position(id)
);

CREATE TABLE bureau(
                       id int primary key,
                       title varchar(50),
                       start_date date,
                       end_date date,
                       id_federation int REFERENCES federation(id)
);

CREATE TABLE bureau_membership(
                                  id int primary key,
                                  id_mandat int REFERENCES mandat(id),
                                  id_collectivity int REFERENCES collectivities(id),
                                  id_member int REFERENCES member(id),
                                  id_position int REFERENCES position(id)
);

CREATE TABLE activity(
                         id int primary key ,
                         id_collectivity int REFERENCES collectivities(id),
                         activity_date date,
                         title varchar(50),
                         id_federatiion int REFERENCES federation(id)
);

CREATE type presence_enum as ENUM ('PRESENT', 'MISSING', 'APOLOGIZED');

CREATE TABLE attendance(
                           id int primary key,
                           id_membership int REFERENCES membership(id),
                           presence presence_enum,
                           id_activity int REFERENCES activity(id)
);

CREATE type payment_type_enum as ENUM ('BANK', 'CASH', 'MOBILE_MONEY');
CREATE type payment_frequency_enum as ENUM ('MONTHLY', 'YEARLY');

CREATE TABLE contribution(
                             id int primary key,
                             payment_type payment_type_enum,
                             id_membership int REFERENCES membership(id),
                             payment_date date,
                             payment_frequency payment_frequency_enum,
                             id_collectivity int REFERENCES collectivities(id)
);

CREATE type reason_enum as ENUM ('CONTRIBUTION', 'JOINING_FEES');

CREATE TABLE payment(
                        id int primary key ,
                        payment_type payment_type_enum,
                        reason reason_enum,
                        id_member int REFERENCES member(id)
);

CREATE type service_enum as ENUM ('ORANGE MONEY', 'MVOLA', 'AIRTEL MONEY');

CREATE TABLE mobile_money_account(
                                     id int primary key,
                                     user_name varchar(50),
                                     phone_number float,
                                     service_name service_enum,
                                     id_collectivity int REFERENCES collectivities(id)
);

CREATE type bank_name_enum as ENUM('BRED', 'BMOI', 'MCB', 'BOA', 'BGFI', 'AFG', 'ACCES BANQUE', 'BAOBAB', 'SIPEM');

CREATE TABLE bank_account(
                             id int primary key,
                             holder_name varchar(50),
                             bank_name bank_name_enum,
                             code float,
                             id_collectivity int REFERENCES collectivities(id)
);
