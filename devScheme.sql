
-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
DROP TABLE IF EXISTS users ;

CREATE TABLE IF NOT EXISTS users (
  user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_password VARCHAR(100) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(70) NOT NULL DEFAULT '',
  active TINYINT(1) NOT NULL DEFAULT TRUE,
  creation_date DATE NOT NULL,
  last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id),
  UNIQUE INDEX email_UNIQUE (email ASC),
  INDEX idx_user_id (user_id ASC)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table roles
-- -----------------------------------------------------
DROP TABLE IF EXISTS roles ;

CREATE TABLE IF NOT EXISTS roles (
  role_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  role VARCHAR(45) NOT NULL,
  PRIMARY KEY (role_id),
  INDEX idx_role (role_id ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table user_roles
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_roles ;

CREATE TABLE IF NOT EXISTS user_roles (
  user_id INT UNSIGNED NOT NULL,
  role_id int unsigned NOT NULL,
  PRIMARY KEY (user_id, role_id),
  INDEX fk_user_roles_roles_idx (role_id ASC),
  CONSTRAINT fk_user_roles_roles
    FOREIGN KEY (role_id)
    REFERENCES roles (role_id),
  CONSTRAINT fk_user_roles_users
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table genres
-- -----------------------------------------------------
DROP TABLE IF EXISTS genres ;

CREATE TABLE IF NOT EXISTS genres (
 genre_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  genre VARCHAR(70) NOT NULL unique,
  PRIMARY KEY (genre_id))
ENGINE = InnoDB;





-- -----------------------------------------------------
-- Table exhibitions
-- -----------------------------------------------------
DROP TABLE IF EXISTS exhibitions ;

CREATE TABLE IF NOT EXISTS exhibitions (
  exhibition_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL UNIQUE,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  description TEXT NULL,
  announced_by INT UNSIGNED NOT NULL,
  PRIMARY KEY (exhibition_id),
  INDEX fk_exhibitions_users_idx (announced_by ASC),
  CONSTRAINT fk_exhibitions_users
    FOREIGN KEY (announced_by)
    REFERENCES users (user_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table news
-- -----------------------------------------------------
DROP TABLE IF EXISTS news ;

CREATE TABLE IF NOT EXISTS news (
  news_id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL unique,
  content TEXT not NULL,
  user_id INT UNSIGNED NOT NULL,
publish_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (news_id),
  INDEX fk_news_user_idx (user_id ASC),
  CONSTRAINT fk_news_user
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


DROP TABLE IF EXISTS paintings ;

CREATE TABLE IF NOT EXISTS paintings (
  painting_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  description TEXT NOT NULL,
  create_date DATE NOT NULL,
  filepath VARCHAR(255) NULL,
  user_id INT UNSIGNED NOT NULL,
  title VARCHAR(70) NOT NULL,
  PRIMARY KEY (painting_id),
  UNIQUE INDEX paint_path_UNIQUE (filepath ASC),
  INDEX idx_filepath (filepath ASC),
  INDEX fk_paintings_user_idx (user_id ASC),
  CONSTRAINT fk_paintings_user
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table paintings_exhibition
-- -----------------------------------------------------
DROP TABLE IF EXISTS paintings_exhibition ;

CREATE TABLE IF NOT EXISTS paintings_exhibition (
  painting_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  exhibition_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (painting_id, exhibition_id),
  INDEX idx_painting_id (painting_id ASC),
  INDEX fk_paintegs_exhibition_exhibition_idx (exhibition_id ASC),
  CONSTRAINT fk_paintings_exhibition_exhibitions
   FOREIGN KEY (exhibition_id)
    REFERENCES exhibitions (exhibition_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_paintings_exhibition_paintings
    FOREIGN KEY (painting_id)
    REFERENCES paintings (painting_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table paintegs_genres
-- -----------------------------------------------------
DROP TABLE IF EXISTS paintings_genres ;

CREATE TABLE IF NOT EXISTS paintings_genres (
  painting_id INT UNSIGNED,
  genre_id INT UNSIGNED,
  PRIMARY KEY (painting_id, genre_id),
  INDEX idx_painting_id (painting_id ASC),
  INDEX fk_paintegs_genres_genre_idx (genre_id ASC),
  CONSTRAINT fk_paintegs_genres_genre
   FOREIGN KEY (genre_id)
    REFERENCES genres (genre_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_paintegs_genres_paintings
    FOREIGN KEY (painting_id)
    REFERENCES paintings (painting_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Inserts
-- -----------------------------------------------------
Insert into users (first_name, last_name, user_password, email, creation_date) values ("John", "Moo", "$2a$10$/0LvFCNtEf4StzpxWEQaWOamEQdY43fgiFivrs2iNd3zy1/9VJFVa", "john@manager.com","2008-10-03");
Insert into users (first_name, last_name, user_password, email, creation_date) values ("Sara","Kendrick", "$2a$10$/0LvFCNtEf4StzpxWEQaWOamEQdY43fgiFivrs2iNd3zy1/9VJFVa", "sara@manager.com","2009-10-03");
Insert into users (first_name, last_name, user_password, email, creation_date) values ("Mike","Polo", "$2a$10$/0LvFCNtEf4StzpxWEQaWOamEQdY43fgiFivrs2iNd3zy1/9VJFVa", "mike@manager.com","2012-10-03");

insert into roles (role) values ("ROLE_USER");
insert into roles (role) values ("ROLE_MANAGER");
insert into roles (role) values ("ROLE_ADMIN");

Insert into user_roles(user_id, role_id) values ("1", "1");
Insert into user_roles(user_id, role_id) values ("1", "2");
Insert into user_roles(user_id, role_id) values ("2", "1");
Insert into user_roles(user_id, role_id) values ("2", "2");
Insert into user_roles(user_id, role_id) values ("3", "1");
Insert into user_roles(user_id, role_id) values ("3", "2");

Insert into exhibitions (title, description, announced_by, start_date, end_date) values ("Fantasy", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elementum commodo enim ac suscipit. Donec lacinia pretium massa sit amet fermentum. Vivamus finibus felis a lacinia egestas. Sed in feugiat odio, quis ultrices odio. Integer euismod velit interdum ligula consequat, tempus hendrerit est auctor. Nulla euismod mi sit amet lorem maximus accumsan eu at sapien. Pellentesque laoreet nisi sit amet lacus malesuada dictum eu sed nisi. Cras sagittis dui lectus, quis aliquet lorem feugiat in. Aenean tempus ipsum sem. ", 1,"2008-10-03","2008-12-03");
Insert into exhibitions (title, description, announced_by, start_date, end_date) values ("Quatro", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elementum commodo enim ac suscipit. Donec lacinia pretium massa sit amet fermentum. Vivamus finibus felis a lacinia egestas. Sed in feugiat odio, quis ultrices odio. Integer euismod velit interdum ligula consequat, tempus hendrerit est auctor. Nulla euismod mi sit amet lorem maximus accumsan eu at sapien. Pellentesque laoreet nisi sit amet lacus malesuada dictum eu sed nisi. Cras sagittis dui lectus, quis aliquet lorem feugiat in. Aenean tempus ipsum sem. ", 2,"1994-10-03","2012-12-03");
Insert into exhibitions (title, description, announced_by, start_date, end_date) values ("Chemistry is not fun", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elementum commodo enim ac suscipit. Donec lacinia pretium massa sit amet fermentum. Vivamus finibus felis a lacinia egestas. Sed in feugiat odio, quis ultrices odio. Integer euismod velit interdum ligula consequat, tempus hendrerit est auctor. Nulla euismod mi sit amet lorem maximus accumsan eu at sapien. Pellentesque laoreet nisi sit amet lacus malesuada dictum eu sed nisi. Cras sagittis dui lectus, quis aliquet lorem feugiat in. Aenean tempus ipsum sem. ", 3,"2017-01-03","2018-01-03");
Insert into exhibitions (title, description, announced_by, start_date, end_date) values ("Some future exhib", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elementum commodo enim ac suscipit. Donec lacinia pretium massa sit amet fermentum. Vivamus finibus felis a lacinia egestas. Sed in feugiat odio, quis ultrices odio. Integer euismod velit interdum ligula consequat, tempus hendrerit est auctor. Nulla euismod mi sit amet lorem maximus accumsan eu at sapien. Pellentesque laoreet nisi sit amet lacus malesuada dictum eu sed nisi. Cras sagittis dui lectus, quis aliquet lorem feugiat in. Aenean tempus ipsum sem. ", 1,"2087-01-03","2019-01-03");

Insert into paintings (description, create_date, filepath, user_id, title) values ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend volutpat erat, in varius purus tempor vel. Nunc porttitor neque congue ipsum sagittis, ut imperdiet est mollis. In eget eleifend urna. Duis ut ipsum condimentum, tincidunt dui at, tempus nulla. Vestibulum venenatis faucibus sapien, vel efficitur augue euismod eu. Integer consequat nibh eget turpis dignissim, eu maximus nisi suscipit. Suspendisse potenti. ",'1588-04-02',"f5/83/99/25/00/0f/34/e6/c9/4f/91/e6/bd/6a/14/dc",1, "Lorem");
Insert into paintings (description, create_date, filepath, user_id, title) values ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend volutpat erat, in varius purus tempor vel. Nunc porttitor neque congue ipsum sagittis, ut imperdiet est mollis. In eget eleifend urna. Duis ut ipsum condimentum, tincidunt dui at, tempus nulla. Vestibulum venenatis faucibus sapien, vel efficitur augue euismod eu. Integer consequat nibh eget turpis dignissim, eu maximus nisi suscipit. Suspendisse potenti. ",'1778-05-02',"1f/0b/98/c4/77/5f/6e/87/1b/21/67/d9/bb/d1/94/c",2, "Ipsum");
Insert into paintings (description, create_date, filepath, user_id, title) values ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend volutpat erat, in varius purus tempor vel. Nunc porttitor neque congue ipsum sagittis, ut imperdiet est mollis. In eget eleifend urna. Duis ut ipsum condimentum, tincidunt dui at, tempus nulla. Vestibulum venenatis faucibus sapien, vel efficitur augue euismod eu. Integer consequat nibh eget turpis dignissim, eu maximus nisi suscipit. Suspendisse potenti. ",'1578-04-11',"4f/c8/26/0d/46/af/22/02/ee/21/35/f9/34/7a/17/e9",3, "Karara!");
Insert into paintings (description, create_date, filepath, user_id, title) values ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend volutpat erat, in varius purus tempor vel. Nunc porttitor neque congue ipsum sagittis, ut imperdiet est mollis. In eget eleifend urna. Duis ut ipsum condimentum, tincidunt dui at, tempus nulla. Vestibulum venenatis faucibus sapien, vel efficitur augue euismod eu. Integer consequat nibh eget turpis dignissim, eu maximus nisi suscipit. Suspendisse potenti. ",'2078-04-11',"e0/59/0f/aa/bb/b2/b8/87/56/9b/7b/39/cb/4d/af/d7",3, "Karadas");
Insert into paintings (description, create_date, filepath, user_id, title) values ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend volutpat erat, in varius purus tempor vel. Nunc porttitor neque congue ipsum sagittis, ut imperdiet est mollis. In eget eleifend urna. Duis ut ipsum condimentum, tincidunt dui at, tempus nulla. Vestibulum venenatis faucibus sapien, vel efficitur augue euismod eu. Integer consequat nibh eget turpis dignissim, eu maximus nisi suscipit. Suspendisse potenti. ",'1078-04-11',"3e/68/f5/29/b0/e0/fb/6a/2d/52/19/7e/34/80/bb/5",1, "Sas! Req!");
Insert into paintings (description, create_date, filepath, user_id, title) values ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend volutpat erat, in varius purus tempor vel. Nunc porttitor neque congue ipsum sagittis, ut imperdiet est mollis. In eget eleifend urna. Duis ut ipsum condimentum, tincidunt dui at, tempus nulla. Vestibulum venenatis faucibus sapien, vel efficitur augue euismod eu. Integer consequat nibh eget turpis dignissim, eu maximus nisi suscipit. Suspendisse potenti. ",'1078-04-11',"5e/49/c6/dc/73/54/71/2b/2a/67/18/9b/a3/6b/81",2, "Bober! Seeg!");

Insert into paintings_exhibition (painting_id, exhibition_id) values (1,1);
Insert into paintings_exhibition (painting_id, exhibition_id) values (2,1);
Insert into paintings_exhibition (painting_id, exhibition_id) values (3,1);
Insert into paintings_exhibition (painting_id, exhibition_id) values (4,2);
Insert into paintings_exhibition (painting_id, exhibition_id) values (5,2);
Insert into paintings_exhibition (painting_id, exhibition_id) values (6,2);
Insert into paintings_exhibition (painting_id, exhibition_id) values (1,3);
Insert into paintings_exhibition (painting_id, exhibition_id) values (4,3);
Insert into paintings_exhibition (painting_id, exhibition_id) values (3,3);


Insert into genres (genre) values ("Fantasy");
Insert into genres (genre) values ("Computer graphic");
Insert into genres (genre) values ("Nature");
Insert into genres (genre) values ("Black");
Insert into genres (genre) values ("Synthetic");



Insert into paintings_genres (painting_id, genre_id) values (1,1);
Insert into paintings_genres (painting_id, genre_id) values (1,2);
Insert into paintings_genres (painting_id, genre_id) values (2,2);
Insert into paintings_genres (painting_id, genre_id) values (3,3);
Insert into paintings_genres (painting_id, genre_id) values (3,1);
Insert into paintings_genres (painting_id, genre_id) values (4,3);
Insert into paintings_genres (painting_id, genre_id) values (4,4);
Insert into paintings_genres (painting_id, genre_id) values (4,5);
Insert into paintings_genres (painting_id, genre_id) values (1,4);
Insert into paintings_genres (painting_id, genre_id) values (2,5);
Insert into paintings_genres (painting_id, genre_id) values (5,5);
Insert into paintings_genres (painting_id, genre_id) values (5,1);
Insert into paintings_genres (painting_id, genre_id) values (6,2);
Insert into paintings_genres (painting_id, genre_id) values (6,3);


insert into news (title, content, user_id, publish_timestamp) values ("The gallery is open!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elementum commodo enim ac suscipit. Donec lacinia pretium massa sit amet fermentum. Vivamus finibus felis a lacinia egestas. Sed in feugiat odio, quis ultrices odio. Integer euismod velit interdum ligula consequat, tempus hendrerit est auctor. Nulla euismod mi sit amet lorem maximus accumsan eu at sapien. Pellentesque laoreet nisi sit amet lacus malesuada dictum eu sed nisi. Cras sagittis dui lectus, quis aliquet lorem feugiat in. Aenean tempus ipsum sem.",1, '2016-12-21 21:47:03');
insert into news (title, content, user_id, publish_timestamp) values ("Become a volunteer!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elementum commodo enim ac suscipit. Donec lacinia pretium massa sit amet fermentum. Vivamus finibus felis a lacinia egestas. Sed in feugiat odio, quis ultrices odio. Integer euismod velit interdum ligula consequat, tempus hendrerit est auctor. Nulla euismod mi sit amet lorem maximus accumsan eu at sapien. Pellentesque laoreet nisi sit amet lacus malesuada dictum eu sed nisi. Cras sagittis dui lectus, quis aliquet lorem feugiat in. Aenean tempus ipsum sem.",3, '2017-01-21 20:47:02');
insert into news (title, content, user_id, publish_timestamp) values ("Lorem Kenti!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam elementum commodo enim ac suscipit. Donec lacinia pretium massa sit amet fermentum. Vivamus finibus felis a lacinia egestas. Sed in feugiat odio, quis ultrices odio. Integer euismod velit interdum ligula consequat, tempus hendrerit est auctor. Nulla euismod mi sit amet lorem maximus accumsan eu at sapien. Pellentesque laoreet nisi sit amet lacus malesuada dictum eu sed nisi. Cras sagittis dui lectus, quis aliquet lorem feugiat in. Aenean tempus ipsum sem.",1, '2017-02-21 19:52:01');
