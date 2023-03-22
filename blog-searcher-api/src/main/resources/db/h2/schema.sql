DROP TABLE if exists BlogSearchQueryCount;

CREATE TABLE BlogSearchQueryCount (
  id         INTEGER AUTO_INCREMENT PRIMARY KEY,
  searchQuery   VARCHAR(30) NOT NULL,
  searchCount   INTEGER NOT NULL,
  lastUpdateDate DATE NOT NULL DEFAULT NOW()
);
ALTER TABLE BlogSearchQueryCount
ADD CONSTRAINT queryUnique
UNIQUE ( searchQuery )