create table faceit(
id serial primary key,
	name varchar(255),
	ELO int,
    Rank float);

update faceit set name = 'Popov';
delete from faceit;