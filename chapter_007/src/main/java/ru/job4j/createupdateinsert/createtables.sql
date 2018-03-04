--создаем таблицу ролей
create table roles(
	id serial primary key,
	title character varying (2000)
);

--создаем таблицу правил
create table rules(
	id serial primary key,
	title character varying (2000)
);

--создаем таблицу сопоставления роли и прав
create table user_rights(
	role_id integer references roles(id),
	rule_id integer references rules(id)
);

--создаем таблицу юзеров
create table users(
	id serial primary key,
	role_id integer references roles(id),
	login character varying (2000),
	password character varying (2000),
	create_date timestamp
);

--создаем таблицу категорий заявки
create table categories(
	id serial primary key,
	title character varying(2000)
);

--создаем таблицу статусов заявки
create table states(
	id serial primary key,
	title character varying(2000)
);

--создаем таблицу заявок
create table items(
	id serial primary key,
	state_id integer references states(id),
	category_id integer references categories(id),
	title character varying (2000),
	description text,
	update_date timestamp,
	user_id integer references users(id)
);

--создаем таблицу вложений к заявкам
create table attachs(
	id serial primary key,
	upload_date timestamp,
	item_id integer references items(id)
);

--создаем таблицу комментариев к заявкам
create table comments(
	id serial primary key,
	item_id integer references items(id),
	create_date timestamp
);