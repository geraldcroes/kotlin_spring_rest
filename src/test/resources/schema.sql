CREATE TABLE IF NOT EXISTS `movies` (
 `uid` varchar(32) NOT NULL,
 `title` varchar(200) NOT NULL,
 `year` int(5) NOT NULL,
 PRIMARY KEY (`uid`)
);

CREATE TABLE IF NOT EXISTS `actors` (
 `uid` varchar(32) NOT NULL,
 `name` varchar(200) NOT NULL,
 PRIMARY KEY (`uid`)
);

CREATE TABLE IF NOT EXISTS `characters` (
 `uid` varchar(32) NOT NULL,
 `name` varchar(200) NOT NULL,
 PRIMARY KEY (`uid`)
);

CREATE TABLE IF NOT EXISTS `actings` (
 `actor_uid` varchar(32) NOT NULL,
 `movie_uid` varchar(32) NOT NULL,
 `character_uid` varchar(32) NOT NULL
)