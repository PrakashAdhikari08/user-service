DROP TABLE IF EXISTS user_tbl;

CREATE TABLE user_tbl (
                               user_id INT AUTO_INCREMENT  PRIMARY KEY,
                               first_name VARCHAR(250) NOT NULL,
                               last_name VARCHAR(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL,
                               date_of_birth DATE
);