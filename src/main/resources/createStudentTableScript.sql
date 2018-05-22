CREATE TABLE student
(
  name     VARCHAR(20),
  ser_name VARCHAR(20),
  phone    VARCHAR(20) NOT NULL,
  email    VARCHAR(20) NOT NULL
);

CREATE UNIQUE INDEX student_phone_uindex
  ON student (phone);

CREATE UNIQUE INDEX student_email_uindex
  ON student (email);