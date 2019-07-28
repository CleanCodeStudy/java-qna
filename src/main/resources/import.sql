INSERT INTO ACCOUNT (user_id,name,email,password) VALUES ('user','이름','example@gmail.com','1234')
INSERT INTO QUESTION (id,author_id,content,register_date,title, update_date,deleted) VALUES (1,1,'내용',now(),'제목',now(),false)
INSERT INTO ANSWER (register_date, update_date, content, deleted , title, author_id, question_id) VALUES (now(), now(), '댓글내용', false, '댓글제목', 1, 1)
INSERT INTO ANSWER (register_date, update_date, content, deleted, title, author_id, question_id) VALUES (now(), now(), '댓글내용2', false, '댓글제목2', 1, 1)

