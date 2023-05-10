INSERT INTO podcast (podcast_name, podcast_author, rating, listeners, date_created) VALUES ("Serial", "Sarah Koenig", 5.0, 5, "2014-10-03");
INSERT INTO podcast (podcast_name, podcast_author, rating, listeners, date_created) VALUES ("Cultish", "Jeff Durbin", 4.5, 2, "2018-10-20");
INSERT INTO podcast (podcast_name, podcast_author, rating, listeners, date_created) VALUES ("Bible Thinker", "Mike Winger", 4.8, 4, "2013-06-16");
INSERT INTO podcast (podcast_name, podcast_author, rating, listeners, date_created) VALUES ("The Babylon Bee", "The Babylon Bee", 3.0, 3, "2019-06-15");
INSERT INTO podcast (podcast_name, podcast_author, rating, listeners, date_created) VALUES ("Armchair Expert", "Dax Shepard", 4.0, 1, "2018-02-06");

INSERT INTO category (category_name) VALUES ("True Crime");
INSERT INTO category (category_name) VALUES ("Religous");
INSERT INTO category (category_name) VALUES ("Comedy");
INSERT INTO category (category_name) VALUES ("Serialized");
INSERT INTO category (category_name) VALUES ("Satire"); 

INSERT into favorites(favorites_id, podcast_fk) values (1, 1);
INSERT into favorites(favorites_id, podcast_fk) values (2, 1);
INSERT into favorites(favorites_id, podcast_fk) values (3, 2);
INSERT into favorites(favorites_id, podcast_fk) values (4, 1);
INSERT into favorites(favorites_id, podcast_fk) values (5, 5);

INSERT INTO listener(listener_name, favorite_id, about) VALUES ("Lucie", 1, "Enjoys podcasts and long walks on the beach");
INSERT INTO listener(listener_name, favorite_id, about) VALUES ("Sarah", 2, "Enjoys podcasts and her dogs");
INSERT INTO listener(listener_name, favorite_id, about) VALUES ("Khalil", 3, "New here.");
INSERT INTO listener(listener_name, favorite_id, about) VALUES ("Tahmid", 4, "Love a good true crime!");
INSERT INTO listener(listener_name, favorite_id, about) VALUES ("Kyren", 5, "AKA Buzzlightyear");

INSERT into podcast_category(podcast_fk, category_fk) values (1, 1);
INSERT into podcast_category(podcast_fk, category_fk) values (1, 4);
INSERT into podcast_category(podcast_fk, category_fk) values (2, 2);
INSERT into podcast_category(podcast_fk, category_fk) values (2, 2);
INSERT into podcast_category(podcast_fk, category_fk) values (3, 2);
INSERT into podcast_category(podcast_fk, category_fk) values (4, 3);
INSERT into podcast_category(podcast_fk, category_fk) values (4, 5);
INSERT into podcast_category(podcast_fk, category_fk) values (5, 3);