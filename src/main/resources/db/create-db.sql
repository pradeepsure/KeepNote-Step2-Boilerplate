  CREATE TABLE Notes(
  note_id  INTEGER PRIMARY KEY,
  note_title VARCHAR(45) NOT NULL,
  note_content VARCHAR(500) NULL,
  note_status VARCHAR(10) NULL,
  note_creation_date DATE NULL,
);