create table classroom(
id serial primary key, 
number int
); 

create table schoolboy(
id serial primary key,
name varchar(255),
classroom_id int references classroom(id)
);