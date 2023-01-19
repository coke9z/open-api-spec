CREATE TABLE IF NOT EXISTS test_database.test_table (
  namename VARCHAR(32) NOT NULL PRIMARY KEY,
  age INT,
  query VARCHAR(32) NOT NULL,
  header VARCHAR(32) NOT NULL
) engine = InnoDB default charset = utf8;
