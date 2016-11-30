DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS voices;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  password   VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_unique_idx ON users (id, name);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name          TEXT NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique ON restaurants(id);

CREATE TABLE dishes (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER NOT NULL,
  date     TIMESTAMP NOT NULL,
  name          TEXT NOT NULL,
  price         FLOAT NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE INDEX dishes_unique_datetime ON dishes(date);

CREATE TABLE voices (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id       INTEGER NOT NULL,
  date          TIMESTAMP NOT NULL,
  user_id       INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE INDEX voices_unique_datetime ON restaurants(id);