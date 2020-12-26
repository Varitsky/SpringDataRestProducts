drop table if exists students cascade;
drop table if exists products cascade;
create table products (id bigserial primary key, title varchar(255), price int);
insert into products (title, price) values
('Ganymede', 14819000),
('Callisto', 10759000),
('Io', 8931900),
('Europa', 4800000),
('Himalia', 420),
('Amalthea', 208),
('Elara', 87),
('Thebe', 43),
('Pasiphae', 30),
('Carme', 13),
('Sinope', 8),
('Lysithea', 6),
('Metis', 4),
('Ananke', 3),
('Leda', 1),
('Adrastea', 1),
('Callirhoe', 1),
('Themisto', 1),
('Praxidike', 1),
('Kalyke', 1),
('Megaclite', 1),
('Locaste', 1),
('Taygete', 1),
('Dia', 1);