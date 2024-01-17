create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('iphone 15', 150.999);
insert into devices(name, price) values ('samsung s23', 114.999);
insert into devices(name, price) values ('Motorola g60s', 24.999);

insert into people (name) values ('Вова'), ('Ваня'), ('Наташа');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (3,3);

select avg(price) from devices;

select p.name, avg(dev.price)
from people as p
join devices_people dp on p.id= dp.people_id
join devices as dev on dp.device_id = dev.id
group by p.name;

select p.name, avg(dev.price)
from people as p
join devices_people dp on p.id= dp.people_id
join devices as dev on dp.device_id = dev.id
group by p.name
having avg(dev.price) > 5.000;