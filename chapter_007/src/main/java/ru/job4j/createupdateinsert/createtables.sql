create table if not exists roles(
	id serial primary key,
	title character varying (2000)
);

create table if not exists rules(
	id serial primary key,
	title character varying (2000)
);

create table if not exists user_rights(
	role_id integer references roles(id),
	rule_id integer references rules(id)
);

create table if not exists users(
	id serial primary key,
	role_id integer references roles(id),
	login character varying (2000),
	password character varying (2000),
	create_date timestamp
);

create table if not exists categories(
	id serial primary key,
	title character varying(2000)
);

create table if not exists states(
	id serial primary key,
	title character varying(2000)
);

create table if not exists items(
	id serial primary key,
	state_id integer references states(id),
	category_id integer references categories(id),
	title character varying (2000),
	description text,
	update_date timestamp,
	user_id integer references users(id)
);

create table if not exists attachs(
	id serial primary key,
	upload_date timestamp,
	item_id integer references items(id)
);

create table if not exists comments(
	id serial primary key,
	item_id integer references items(id),
	create_date timestamp
);