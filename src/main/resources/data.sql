insert into user_details(id,birth_date,name) values(1001, current_date(), 'Tushar');
insert into user_details(id,birth_date,name) values(1002, current_date(), 'Ravi');
insert into user_details(id,birth_date,name) values(1003, current_date(), 'Satish');

insert into post(id, description, user_id) values(2001, 'I want to learn Microservices', 1001);
insert into post(id, description, user_id) values(2002, 'I want to learn AWS', 1001);
insert into post(id, description, user_id) values(2003, 'I want to learn Devops', 1002);
insert into post(id, description, user_id) values(2004, 'I want to learn AWS', 1003);

--
--Hibernate: drop table if exists post cascade 
--Hibernate: drop table if exists user_details cascade 
--Hibernate: drop sequence if exists post_seq
--Hibernate: drop sequence if exists user_details_seq
--Hibernate: create sequence post_seq start with 1 increment by 50
--Hibernate: create sequence user_details_seq start with 1 increment by 50
--Hibernate: create table post (id integer not null, user_id integer, description varchar(255), primary key (id))
--Hibernate: create table user_details (birth_date date, id integer not null, name varchar(255), primary key (id))
--Hibernate: alter table if exists post add constraint FKa3biitl48c71riii9uyelpdhb foreign key (user_id) references user_details
--

