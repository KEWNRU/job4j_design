create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Bear', 11100, '1111-01-08');
insert into fauna(name, avg_age, discovery_date)
values ('Fish', 1352, null);
insert into fauna(name, avg_age, discovery_date)
values ('Lion', 21000, '987-12-12');
insert into fauna(name, avg_age, discovery_date)
values ('Cat', 7100, '1000-12-25');
select * from fauna where name = 'Fish';
select * from fauna where avg_age >= 11000 and avg_age <= 21000;
select * from fauna where discovery_date < '01.01.1950';
select * from fauna where discovery_date is null;