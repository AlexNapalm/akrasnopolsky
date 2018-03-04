--добавляем роли
insert into roles(title) values
('admin'),
('user'),
('analyst'),
('guest'),
('manager');

--добавляем права
insert into rules(title) values
('read'),
('readwrite'),
('nodelete'),
('fullaccess');

--добавляем сопоставления роли и прав
insert into user_rights(role_id, rule_id) values
(1, 4), -- у админа фулл аксесс
(2, 1), -- у юзера только чтение
(3, 2), -- у аналитика чтение/изменение без удаления
(3, 3),
(5, 2); -- у менеджера чтение/изменение

--добавляем статусы заявок
insert into states(title) values
('New'), 
('In work'),
('Paused'),
('Completed'),
('Closed');

--добавляем категории заявок
insert into categories(title) values
('Desktop'), 
('Printing'),
('Avaya'),
('Software'),
('LAN/WAN');

--добавляем юзеров
insert into users(role_id, login, password, create_date) values
(1, 'akrasnopolsky', 'password', '1999-01-08 04:05:06'),  --админ
(2, 'aivanov', 'password', '1999-01-08 04:05:06'), --юзер
(2, 'spetrov', 'password', '1999-01-08 04:05:06'), --юзер
(2, 'msidorov', 'password', '1999-01-08 04:05:06'), --юзер
(5, 'dbuchenkov', 'password', '1999-01-08 04:05:06'), --менеджер
(4, 'agorlov', 'password', '1999-01-08 04:05:06'); --аналитик