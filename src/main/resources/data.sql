INSERT INTO USER(name, email, password) VALUES('Moderator', 'moderator@gmail.com', '$2a$10$I3JODllZ0r.QM6rLXX02p.oGIZmrpwiOoH4B4yjjfhtKkVtJ2FX6i');
INSERT INTO USER(name, email, password) VALUES('Student', 'student@gmail.com', '$2a$10$I3JODllZ0r.QM6rLXX02p.oGIZmrpwiOoH4B4yjjfhtKkVtJ2FX6i');

INSERT INTO AUTHORITIES(id, role) VALUES(1, 'ROLE_MODERATOR');
INSERT INTO AUTHORITIES(id, role) VALUES(2, 'ROLE_STUDENT');

INSERT INTO USER_AUTHORITIES(user_id, authorities_id) VALUES(1, 1);
INSERT INTO USER_AUTHORITIES(user_id, authorities_id) VALUES(2, 2);

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programming');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPIC(title, message, created_At, status, user_id, course_id) VALUES('Dumb', 'Dumb description', '2020-08-08T20:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPIC(title, message, created_At, status, user_id, course_id) VALUES('Dumb 2', 'Dumb description', '2020-08-08T12:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPIC(title, message, created_At, status, user_id, course_id) VALUES('Dumb 3', 'Tag HTML', '2020-08-08T14:00:00', 'NAO_RESPONDIDO', 1, 2);
