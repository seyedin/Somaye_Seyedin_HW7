--===============================================================
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
--===============================================================
--Inheritance: REFERENCES / foreign key : users(id)
-- /If user deleted then delete moderator: ON DELETE CASCADE
CREATE TABLE IF NOT EXISTS moderators
(
    id INT PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE
);
--===============================================================
CREATE TABLE IF NOT EXISTS authors
(
    id           INT PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    nationalCode VARCHAR(100) NOT NULL UNIQUE,
    birthday     DATE
);
--===============================================================
CREATE TABLE IF NOT EXISTS categories
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(100) NOT NULL UNIQUE,
    description text
);
--===============================================================
CREATE TABLE IF NOT EXISTS tags
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL UNIQUE
);
--===============================================================
CREATE TABLE IF NOT EXISTS articles
(
    id               SERIAL PRIMARY KEY,
    title            VARCHAR(100) NOT NULL,
    brief            VARCHAR(255) NOT NULL,
    content          TEXT         NOT NULL,
    create_date      DATE    DEFAULT CURRENT_DATE,
    publish_date     DATE,
    last_update_date DATE,
    is_published     BOOLEAN DEFAULT FALSE,
    article_status   varchar(20),
    category_id      INT REFERENCES categories (id) ON DELETE CASCADE,       -- many to one
    author_id        INT          REFERENCES authors (id) ON DELETE SET NULL -- one to many
);
--===============================================================
CREATE TABLE IF NOT EXISTS article_tags
(
    article_id INT REFERENCES articles (id) ON DELETE CASCADE,
    tag_id     INT REFERENCES tags (id) ON DELETE CASCADE,
    PRIMARY KEY (article_id, tag_id)
);
--=============DML===============================================
insert into users (username, password)
values ('admin', 'admin123');
--===============================================================
--select u.* from users u where u.username ='admin';
--select u.id from users u where u.username ='admin';
insert into moderators (id)
values ((select u.id from users u where u.username = 'admin'));
--===============================================================
insert into users (username, password)
values ('author1', '123');
--===============================================================
insert into users (username, password)
values ('author2', '321');
--===============================================================
insert into authors (id, nationalCode)
values (2, '123');
insert into users (username, password)
values ('author1', '123');
--===============================================================
insert into users (username, password)
values ('author2', '321');
--===============================================================
insert into authors (id, nationalCode)
values (2, '123');
--===============================================================
insert into authors (id, nationalCode, birthday)
values (3, '321', current_date);
--===============================================================
insert into authors (id, nationalCode)
values (3, '321');
--===============================================================
update authors a
set birthday = current_date - interval '20 years'
where a.id = 2;
--===============================================================
update authors a
set birthday = current_date - interval '30 years'
where a.id = 3;
--===============================================================

insert into categories(title, description)
values ('Technology', 'Articles about technology');
insert into categories(title, description)
values ('Science', 'Articles about science');
insert into categories(title, description)
values ('Art', 'Articles about art');
--===============================================================
insert into tags(title)
values ('Tech');
insert into tags(title)
values ('Science');
--===============================================================
insert into articles(title, brief, content, create_date,
                     publish_date, last_update_date,
                     is_published, article_status,
                     category_id, author_id)
values ('Paint', 'Brief paint', 'Content paint',
        current_date, current_date,
        current_date, true, 'APPROVED',
        1, 3);
insert into articles(title, brief, content, create_date,
                     publish_date, last_update_date,
                     is_published, article_status,
                     category_id, author_id)
values ('Math', 'Brief Math', 'Content Math',
        current_date, current_date,
        current_date, true, 'APPROVED',
        2, 2);
insert into articles(title, brief, content, create_date,
                     publish_date, last_update_date,
                     is_published, article_status,
                     category_id, author_id)
values ('Java', 'Brief Java', 'Content Java',
        current_date, current_date,
        current_date, true, 'APPROVED',
        3, 2);
insert into articles(title, brief, content, create_date,
                     publish_date, last_update_date,
                     is_published, article_status,
                     category_id, author_id)
values ('C++', 'Brief C++', 'Content C++',
        current_date, current_date,
        current_date, true, 'APPROVED',
        3, 2);
insert into articles(title, brief, content, create_date,
                     publish_date, last_update_date,
                     is_published, article_status,
                     category_id, author_id)
values ('PHP', 'Brief PHP', 'Content PHP',
        current_date, current_date,
        current_date, true, 'APPROVED',
        3, 2);
--===============================================================
insert into article_tags (article_id, tag_id)
values (1, 1);
insert into article_tags (article_id, tag_id)
values (2, 3);
insert into article_tags (article_id, tag_id)
values (3, 2);
insert into article_tags (article_id, tag_id)
values (4, 2);
insert into article_tags (article_id, tag_id)
values (5, 3);
--===============================================================
-- نام ارتیکل هایی که تک سخت دارند رو نشون بده
SELECT a.title, a.article_status, tags.title
FROM articles a
         JOIN article_tags ON a.id = article_tags.article_id
         JOIN tags on article_tags.tag_id = tags.id
WHERE tags.id = 3;
--==================OR=============================================
SELECT a.title, a.article_status, t.title
FROM articles a,
     article_tags at,
     tags t
WHERE a.id = at.article_id
  AND at.tag_id = t.id
  AND t.id = 3;
-- like --> = :برای استرینگ لایک میزارن
--===============================================================
SELECT a.title, a.article_status, t.title
FROM tags t
         JOIN article_tags ON t.id = article_tags.tag_id
         JOIN articles a on article_tags.article_id = a.id
WHERE t.id = 3;
--===============================================================