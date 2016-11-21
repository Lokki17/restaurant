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
VALUES ('Admin', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name) VALUES
  ('Минутка'),
  ('Для всей семьи'),
  ('Самый лучший ресторан'),
  ('The Best');

INSERT INTO dishes (restaurant_id, date_time, name, price) VALUES
  (100003, '2015-05-30', 'Борщ', '15'),
  (100003, '2015-05-31', 'Суп', '15'),
  (100004, '2015-05-30', 'Котлеты', '15'),
  (100004, '2015-05-31', 'Шницель', '15'),
  (100005, '2015-05-30', 'Пицца', '15'),
  (100005, '2015-05-31', 'Сосиски', '15'),
  (100006, '2015-05-30', 'Просто еда', '15'),
  (100006, '2015-05-31', 'Колбаса', '15'),
  (100006, '2015-05-30', 'Вареники', '15');

INSERT INTO voices (restaurant_id, date_time, user_id) VALUES
  (100003, '2015-05-30 10:00', 100000),
  (100003, '2015-05-30 10:00', 100001),
  (100004, '2015-05-30 10:00', 100001);