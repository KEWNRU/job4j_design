create table militaryID(
id serial primary key, 
serial varchar(255), 
number int
);

create table people(
id serial primary key,
name varchar(255)
);

create table militaryID_people(
id serial primary key, 
militaryID_id int references militaryID(id) unique, 
people_id int references people(id) unique 
);
