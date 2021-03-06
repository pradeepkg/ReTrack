//drop table USER_LOGIN if exists;
drop table USER_ROLES if exists;
drop table USERS if exists;
drop table DOCUMENTS if exists;

///////////////////////////////////////////////////////////////////////////////////////////////////
create table USERS (userName varchar(255) not null, password varchar(255) not null, enabled int not null, firstName varchar(255), lastName varchar(255), userStatus varchar(10), primary key (userName));

insert into USERS (userName, password, enabled, firstName, lastName, userStatus) values ('pgkadam', 'password', 1, 'Pradeep', 'Kadambar', 'VERIFIED');
insert into USERS (userName, password, enabled, firstName, lastName, userStatus) values ('ppatila', 'password', 1, 'Pandu', 'Patila', 'UNVERIFIED');
insert into USERS (userName, password, enabled, firstName, lastName, userStatus) values ('tpanamb', 'password', 0, 'Tenku', 'Panambur', 'LOCKED');

///////////////////////////////////////////////////////////////////////////////////////////////////
//create table USER_LOGIN(userName varchar(255) not null, password varchar(255) not null, enabled int not null,  constraint fk_login_userName foreign key  (userName) references USERS (userName));

//insert into USER_LOGIN (userName, password, enabled) values ('pgkadam', 'password', 1);
//insert into USER_LOGIN (userName, password, enabled) values ('ppatila', 'password', 1);
//insert into USER_LOGIN (userName, password, enabled) values ('tpanamb', 'password', 0);

///////////////////////////////////////////////////////////////////////////////////////////////////
create table USER_ROLES (roleID integer generated by default as identity (start with 1), userName varchar(255) not null, authority varchar(255) not null,primary key (roleID), constraint fk_role_userName foreign key  (userName) references USERS (userName));

insert into USER_ROLES (roleID, userName, authority) values (1, 'pgkadam', 'ROLE_USER');
insert into USER_ROLES (roleID, userName, authority) values (2, 'pgkadam', 'ROLE_ADMIN');
insert into USER_ROLES (roleID, userName, authority) values (3, 'ppatila', 'ROLE_USER');

///////////////////////////////////////////////////////////////////////////////////////////////////
create table Documents (documentID integer generated by default as identity (start with 1), data blob, hash varchar(255), hashingAlorithm varchar(255), name varchar(255), fileName varchar(255), size integer not null, mimeType varchar(255), primary key (documentID));

create table Entries (entryID integer generated by default as identity (start with 1), description varchar(255), endDate timestamp, name varchar(255), startDate timestamp, primary key (entryID));
create table ENTRY_DOCS (entryID integer not null, documentID integer not null, constraint fk_entry_documentID foreign key  (documentID) references DOCUMENTS (documentID));

