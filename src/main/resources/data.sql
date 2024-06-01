insert into article (title,content,hashtag,created_by,modified_by,created_at,modified_at)
values ('경기도','나는 행복하다.','hello','정재연','정재연','2023-01-01','2024-12-12');
insert into article (title,content,hashtag,created_by,modified_by,created_at,modified_at)
values ('강원도','나는 행복하다.','hello','정재연','정재연','2023-01-01','2024-12-12');
insert into article (title,content,hashtag,created_by,modified_by,created_at,modified_at)
values ('충청도','나는 행복하다.','hello','정재연','정재연','2023-01-01','2024-12-12');
insert into article (title,content,hashtag,created_by,modified_by,created_at,modified_at)
values ('경상도','나는 행복하다.','hello','정재연','정재연','2023-01-01','2024-12-12');
insert into article_comment (content,article_id,created_by,modified_by,created_at,modified_at)
values ('행복해지자',1,'정재연','정재연','2023-01-01','2024-12-12');
insert into article_comment (content,article_id,created_by,modified_by,created_at,modified_at)
values ('행복하다.',1,'정재연','정재연','2023-01-01','2024-12-12');
insert into article_comment (content,article_id,created_by,modified_by,created_at,modified_at)
values ('행복할꺼야',1,'정재연','정재연','2023-01-01','2024-12-12');
insert into user_account (user_id, user_password, nickname, email, name, created_at, created_by, modified_at, modified_by)
values ('jjyeon','jjyeon','jjyeon','wodus9193@naver.com','jjyeon',now(),'jjyeon',now(),'jjyeon')