DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM users;
DELETE FROM voices;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password)
VALUES ('User1', 'password');

INSERT INTO users (name, password)
VALUES ('User2', 'password');

INSERT INTO users (name, password)
VALUES ('admin', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_ADMIN', 100002);

INSERT INTO restaurants (name) VALUES
  ('Минутка'),
  ('Для всей семьи'),
  ('Самый лучший ресторан'),
  ('The Best');

INSERT INTO dishes (restaurant_id, date, name, price) VALUES
  (100003, '2016-11-30', 'Борщ', '15'),
  (100003, '2016-11-30', 'Суп', '15'),
  (100004, '2016-11-30', 'Котлеты', '15'),
  (100004, '2016-11-30', 'Шницель', '15'),
  (100005, '2016-11-30', 'Пицца', '15'),
  (100005, '2016-11-30', 'Сосиски', '15'),
  (100006, '2016-11-30', 'Просто еда', '15'),
  (100006, '2016-11-30', 'Колбаса', '15'),
  (100006, '2016-11-30', 'Вареники', '15');

INSERT INTO voices (restaurant_id, date, user_id) VALUES
  (100003, '2016-11-30', 100000),
  (100003, '2016-11-30', 100001),
  (100004, '2016-11-30', 100002);