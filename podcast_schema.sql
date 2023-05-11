DROP TABLE IF EXISTS podcast_category;
DROP TABLE IF EXISTS listener; 
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS podcast;

CREATE TABLE podcast(
	podcast_id int unsigned NOT null AUTO_INCREMENT,
	podcast_name varchar(40) NOT NULL,
    podcast_author varchar(40) NOT NULL,
    rating decimal(2,1) NOT NULL,
    listeners int NOT NULL,
    date_created varchar(20),
    PRIMARY KEY (podcast_id)
);

CREATE TABLE category(
	category_id int unsigned NOT NULL AUTO_INCREMENT,
    category_name varchar(40) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE favorites(
	favorites_id int unsigned NOT NULL auto_increment,
	podcast_fk int unsigned NOT NULL,
    PRIMARY KEY (favorites_id),
    foreign key (podcast_fk) references podcast (podcast_id)
);

CREATE TABLE listener(
	listener_id int unsigned NOT NULL AUTO_INCREMENT,
	listener_name varchar(40),
    favorite_id int unsigned,
    about varchar(150),
    PRIMARY KEY (listener_id),
    FOREIGN KEY (favorite_id) references favorites (favorites_id)
);


CREATE TABLE podcast_category(
	podcast_id int unsigned NOT NULL,
	category_id int unsigned NOT NULL,
	FOREIGN KEY (podcast_id) REFERENCES podcast (podcast_id) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE
);