CREATE TABLE users(
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL UNIQUE,
  status SMALLINT NOT NULL DEFAULT 1,
  password VARCHAR(64) NOT NULL,
  created_at TIMESTAMP
);

CREATE TABLE user_likes(
       id INT PRIMARY KEY AUTO_INCREMENT,
       user_id_from INT,
       user_id_to INT,
       created_at TIMESTAMP
);

CREATE TABLE user_friends(
         id INT PRIMARY KEY AUTO_INCREMENT,
         user_id_from INT,
         user_id_to INT,
         created_at TIMESTAMP
);

CREATE TABLE roles(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(32)
);

create table authorities
(
id INT PRIMARY KEY AUTO_INCREMENT,
username  varchar(50) not null unique
references users (username),
authority varchar(50) not null
);

create unique index ix_auth_username
    on authorities (username, authority);