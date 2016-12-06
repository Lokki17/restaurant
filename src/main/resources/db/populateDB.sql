DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM users;
DELETE FROM voices;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password)
VALUES ('User1', '$2a$10$2P4bcB/yUPSs1iGVMgU3Zek.gV.aJYeGSOk9R6DxxIxQcEwU/OFAm');

INSERT INTO users (name, password)
VALUES ('User2', '$2a$10$o8KAMZeKLH3sDbdqyuga5ua0rnW0E6Xxfls9HhzxdCI3Sgs70Clua');

INSERT INTO users (name, password)
VALUES ('admin', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100001),
  ('ROLE_ADMIN', 100002);

INSERT INTO restaurants (name) VALUES
  ('Минутка'),
  ('Для всей семьи'),
  ('Самый лучший ресторан'),
  ('The Best');

INSERT INTO dishes (restaurant_id, date, name, price) VALUES
  (100003, '2016-12-06', 'Борщ', '15'),
  (100003, '2016-12-06', 'Суп', '15'),
  (100004, '2016-12-06', 'Котлеты', '15'),
  (100004, '2016-12-06', 'Шницель', '15'),
  (100005, '2016-12-06', 'Пицца', '15'),
  (100005, '2016-12-06', 'Сосиски', '15'),
  (100006, '2016-12-06', 'Просто еда', '15'),
  (100006, '2016-12-06', 'Колбаса', '15'),
  (100006, '2016-12-06', 'Вареники', '15');

INSERT INTO voices (restaurant_id, date, user_id) VALUES
  (100003, '2016-12-06', 100002),
  (100003, '2016-12-06', 100001),
  (100004, '2016-12-06', 100000);