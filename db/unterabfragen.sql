CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers values  (1, 'Ivan', 'Popov', 26, 'Russia');
insert into customers values  (2, 'Vladimir', 'Popov', 25, 'Russia');
insert into customers values  (2, 'Natalia', 'Popova', 24, 'Russia');

select * from customers c
where c.age = (select min(age)from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders values (1, 12, 1);
insert into orders values (2, 2, 2);
insert into orders values (3, 10, 3);

select * from customers c
where c.id not in (select orders.id from orders);