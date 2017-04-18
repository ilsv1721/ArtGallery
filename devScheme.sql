DROP database  if exists artart;

create database artart;
use artart;


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
  phone VARCHAR(45) DEFAULT '',
  PRIMARY KEY (user_id),
  UNIQUE INDEX email_UNIQUE (email ASC),
  INDEX idx_user_id (user_id ASC)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table balance
-- -----------------------------------------------------
DROP TABLE IF EXISTS balance ;

CREATE TABLE IF NOT EXISTS balance (
  balance_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  value DECIMAL NOT NULL DEFAULT 0,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
user_id INT UNSIGNED NOT NULL,
  CONSTRAINT fk_balance_users
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON DELETE CASCADE,	
  PRIMARY KEY (balance_id))
ENGINE = InnoDB;



DROP TABLE IF EXISTS adresses ;

CREATE TABLE IF NOT EXISTS adresses (
  adress_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  postal_code VARCHAR(25) NOT NULL,
  street_adress VARCHAR(150) NOT NULL,
  city VARCHAR(150) NOT NULL,
  country  VARCHAR(150) not null,
  PRIMARY KEY (adress_id),
  INDEX fk_adresses_users_idx (user_id ASC),
  CONSTRAINT fk_adresses_users
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON DELETE cascade)
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
-- Table paintings
-- -----------------------------------------------------
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
-- Table genres
-- -----------------------------------------------------
DROP TABLE IF EXISTS genres ;

CREATE TABLE IF NOT EXISTS genres (
  genre VARCHAR(70) NOT NULL,
  PRIMARY KEY (genre))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table paintegs_genres
-- -----------------------------------------------------
DROP TABLE IF EXISTS paintegs_genres ;

CREATE TABLE IF NOT EXISTS paintegs_genres (
  painting_id INT UNSIGNED NOT NULL,
  genre VARCHAR(70) NOT NULL,
  PRIMARY KEY (painting_id, genre),
  INDEX idx_painting_id (painting_id ASC),
  INDEX fk_paintegs_genres_genre_idx (genre ASC),
  CONSTRAINT fk_paintegs_genres_genre
    FOREIGN KEY (genre)
    REFERENCES genres (genre)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_paintegs_genres_paintings
    FOREIGN KEY (painting_id)
    REFERENCES paintings (painting_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table auctioins
-- -----------------------------------------------------
DROP TABLE IF EXISTS auctioins ;

CREATE TABLE IF NOT EXISTS auctioins (
  auction_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  start_datetime DATETIME NOT NULL,
  end_datetime DATETIME NOT NULL,
  initial_price DECIMAL NOT NULL,
  buy_out_price DECIMAL NULL,
  painting_id INT UNSIGNED NOT NULL,
  sold_for DECIMAL NULL,
  PRIMARY KEY (auction_id),
  INDEX fk_auctioins_paintings_idx (painting_id ASC),
  CONSTRAINT fk_auctioins_paintings
    FOREIGN KEY (painting_id)
    REFERENCES paintings (painting_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bids
-- -----------------------------------------------------
DROP TABLE IF EXISTS bids ;

CREATE TABLE IF NOT EXISTS bids (
  bid_id INT NOT NULL AUTO_INCREMENT,
  bid_time DATETIME NULL,
  bid_value DECIMAL NULL,
  user_bidder_id INT UNSIGNED NULL,
  auction_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (bid_id),
  INDEX fk_bids_user_idx (user_bidder_id ASC),
  INDEX fk_bids_auc_idx (auction_id ASC),
CONSTRAINT fk_bids_user
    FOREIGN KEY (user_bidder_id)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT fk_bids_auc
    FOREIGN KEY (auction_id)
    REFERENCES auctioins (auction_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table exhibitions
-- -----------------------------------------------------
DROP TABLE IF EXISTS exhibitions ;

CREATE TABLE IF NOT EXISTS exhibitions (
  exhibition_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
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


-- -----------------------------------------------------
-- Table exihbitions_media
-- -----------------------------------------------------
DROP TABLE IF EXISTS exhibitions_images ;

CREATE TABLE IF NOT EXISTS exhibitions_images (
  media_id INT NOT NULL AUTO_INCREMENT,
  exhibition_id INT UNSIGNED NOT NULL,
  path VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (media_id),
  INDEX fk_exibitions_media_exihbition_idx (exhibition_id ASC),
  CONSTRAINT fk_exibitions_media_exihbition
    FOREIGN KEY (exhibition_id)
    REFERENCES exhibitions (exhibition_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Inserts
-- -----------------------------------------------------
Insert into users (first_name, last_name, user_password, email, creation_date) values ("John", "Moo", "$2a$10$/0LvFCNtEf4StzpxWEQaWOamEQdY43fgiFivrs2iNd3zy1/9VJFVa", "user@manager.com","2008-10-03");


insert into roles (role) values ("ROLE_USER");
insert into roles (role) values ("ROLE_MANAGER");
insert into roles (role) values ("ROLE_ARTIST");
insert into roles (role) values ("ROLE_ADMIN");

Insert into user_roles(user_id, role_id) values ("1", "1");
Insert into user_roles(user_id, role_id) values ("1", "2");


Insert into exhibitions (title, description, announced_by, start_date, end_date) values ("The Witcher", "You can choose anyone they said", 1,"2008-10-03","2008-10-03");

Insert into exhibitions_images (exhibition_id, path) values (1, "/resources/img/exhibs/TheWitcher/witcher1.jpg");
Insert into exhibitions_images (exhibition_id, path) values (1, "/resources/img/exhibs/TheWitcher/witcher2.jpg");
Insert into exhibitions_images (exhibition_id, path) values (1, "/resources/img/exhibs/TheWitcher/witcher3.jpg");
Insert into exhibitions_images (exhibition_id, path) values (1, "/resources/img/exhibs/TheWitcher/witcher4.jpg");
Insert into exhibitions_images (exhibition_id, path) values (1, "/resources/img/exhibs/TheWitcher/witcher5.jpg");


Insert into exhibitions (title, description, announced_by, start_date, end_date) values ("The Witcher 2", "Well, we gonna do the a couple times more", 1,"2009-10-03","2009-10-03");

Insert into exhibitions_images (exhibition_id, path) values (2, "/resources/img/exhibs/TheWitcher2/witcher3art11.jpg");

Insert into exhibitions_images (exhibition_id, path) values (2, "/resources/img/exhibs/TheWitcher2/witcher3art22.jpg");



insert into news (title, content, user_id, publish_timestamp) values ("The gallery is open now!", "

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque porta velit leo. Praesent non tristique est. Morbi efficitur commodo scelerisque. Vestibulum lacinia eu sapien et egestas. Morbi mattis quam sit amet enim aliquet, ut lobortis lectus dignissim. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum pulvinar urna sit amet consectetur.

Etiam diam sem, aliquet tempor ex ut, varius ullamcorper eros. Quisque ac fermentum mauris. Nam vestibulum, tortor ac vehicula molestie, felis odio egestas ligula, id ullamcorper arcu quam consequat ante. Quisque vel pharetra eros. Integer varius turpis vel aliquam fermentum. Aliquam sollicitudin, leo eget tempor bibendum, neque massa sodales lectus, eget lobortis lorem dui sed urna. Sed ac erat a ex tempus venenatis vel ac odio. Curabitur gravida nunc augue, sed varius nibh consectetur eget. Integer ullamcorper cursus sem et tristique. Nunc nec suscipit augue. Quisque porta purus quis magna auctor, sed vulputate magna lobortis. Integer ultrices mattis vehicula. Curabitur vestibulum quam hendrerit sem hendrerit, quis dapibus urna feugiat.

Curabitur tristique justo vel ipsum gravida, in maximus tortor blandit. Nam a pellentesque justo. Nam efficitur mauris consequat elit aliquam interdum. Nullam vulputate ligula sit amet facilisis tempor. Sed sed tempor felis. Donec scelerisque orci quis mattis fermentum. Etiam eleifend ante sit amet feugiat fringilla.", 1 , '2016-08-05 18:19:03');

insert into news (title, content, user_id, publish_timestamp) values ("We are on vocation.", "

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque porta velit leo. Praesent non tristique est. Morbi efficitur commodo scelerisque. Vestibulum lacinia eu sapien et egestas. Morbi mattis quam sit amet enim aliquet, ut lobortis lectus dignissim. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum pulvinar urna sit amet consectetur.

Etiam diam sem, aliquet tempor ex ut, varius ullamcorper eros. Quisque ac fermentum mauris. Nam vestibulum, tortor ac vehicula molestie, felis odio egestas ligula, id ullamcorper arcu quam consequat ante. Quisque vel pharetra eros. Integer varius turpis vel aliquam fermentum. Aliquam sollicitudin, leo eget tempor bibendum, neque massa sodales lectus, eget lobortis lorem dui sed urna. Sed ac erat a ex tempus venenatis vel ac odio. Curabitur gravida nunc augue, sed varius nibh consectetur eget. Integer ullamcorper cursus sem et tristique. Nunc nec suscipit augue. Quisque porta purus quis magna auctor, sed vulputate magna lobortis. Integer ultrices mattis vehicula. Curabitur vestibulum quam hendrerit sem hendrerit, quis dapibus urna feugiat.

Curabitur tristique justo vel ipsum gravida, in maximus tortor blandit. Nam a pellentesque justo. Nam efficitur mauris consequat elit aliquam interdum. Nullam vulputate ligula sit amet facilisis tempor. Sed sed tempor felis. Donec scelerisque orci quis mattis fermentum. Etiam eleifend ante sit amet feugiat fringilla.", 1 , '2016-09-05 18:19:13');

insert into news (title, content, user_id, publish_timestamp) values ("Become a volunteer", "

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque porta velit leo. Praesent non tristique est. Morbi efficitur commodo scelerisque. Vestibulum lacinia eu sapien et egestas. Morbi mattis quam sit amet enim aliquet, ut lobortis lectus dignissim. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam interdum pulvinar urna sit amet consectetur.

Etiam diam sem, aliquet tempor ex ut, varius ullamcorper eros. Quisque ac fermentum mauris. Nam vestibulum, tortor ac vehicula molestie, felis odio egestas ligula, id ullamcorper arcu quam consequat ante. Quisque vel pharetra eros. Integer varius turpis vel aliquam fermentum. Aliquam sollicitudin, leo eget tempor bibendum, neque massa sodales lectus, eget lobortis lorem dui sed urna. Sed ac erat a ex tempus venenatis vel ac odio. Curabitur gravida nunc augue, sed varius nibh consectetur eget. Integer ullamcorper cursus sem et tristique. Nunc nec suscipit augue. Quisque porta purus quis magna auctor, sed vulputate magna lobortis. Integer ultrices mattis vehicula. Curabitur vestibulum quam hendrerit sem hendrerit, quis dapibus urna feugiat.

Curabitur tristique justo vel ipsum gravida, in maximus tortor blandit. Nam a pellentesque justo. Nam efficitur mauris consequat elit aliquam interdum. Nullam vulputate ligula sit amet facilisis tempor. Sed sed tempor felis. Donec scelerisque orci quis mattis fermentum. Etiam eleifend ante sit amet feugiat fringilla.", 1 , '2016-12-21 21:47:03');
