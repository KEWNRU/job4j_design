create table schoolboy(
id serial primary key,
name varchar(255)
);

create table lesson(
id serial primary key,
name varchar(255)
);

create table scholboy_lesson(
id serial primary key,
schoolboy_id int references lesson(id),
lesson_id int references schoolboy(id)
);