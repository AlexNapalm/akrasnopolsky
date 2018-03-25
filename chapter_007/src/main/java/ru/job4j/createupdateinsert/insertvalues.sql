insert into roles(id, title) values
(1, 'admin'),
(2, 'user'),
(3, 'analyst'),
(4, 'guest'),
(5, 'manager')
ON CONFLICT (id) DO NOTHING;

insert into rules(id, title) values
(1, 'read'),
(2, 'readwrite'),
(3, 'nodelete'),
(4, 'fullaccess')
ON CONFLICT (id) DO NOTHING;

insert into user_rights(role_id, rule_id) values
(1, 4),
(2, 1), 
(3, 2),
(3, 3),
(5, 2)
ON CONFLICT (role_id) DO NOTHING;

insert into states(id, title) values
(1, 'New'), 
(2, 'In work'),
(3, 'Paused'),
(4, 'Completed'),
(5, 'Closed')
ON CONFLICT (id) DO NOTHING;

insert into categories(title) values
('Desktop'), 
('Printing'),
('Avaya'),
('Software'),
('LAN/WAN');

insert into users(role_id, login, password, create_date) values
(1, 'akrasnopolsky', 'password', '1999-01-08 04:05:06'),
(2, 'aivanov', 'password', '1999-01-08 04:05:06'),
(2, 'spetrov', 'password', '1999-01-08 04:05:06'),
(2, 'msidorov', 'password', '1999-01-08 04:05:06'),
(5, 'dbuchenkov', 'password', '1999-01-08 04:05:06'),
(4, 'agorlov', 'password', '1999-01-08 04:05:06');