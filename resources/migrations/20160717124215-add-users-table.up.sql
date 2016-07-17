CREATE TABLE users
(id MEDIUMINT NOT NULL auto_increment,
 first_name VARCHAR(30),
 last_name VARCHAR(30),
 email VARCHAR(30),
 admin BOOLEAN,
 last_login TIME,
 is_active BOOLEAN,
 pass VARCHAR(300),
 PRIMARY KEY (id)
);
