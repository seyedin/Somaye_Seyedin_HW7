create database authorhub7
    with owner "Maktab";

create table public.users
(
    id       serial
        primary key,
    username varchar(100) not null
        unique,
    password varchar(255) not null
);

alter table public.users
    owner to "Maktab";

create table public.moderators
(
    id integer not null
        primary key
        references public.users
            on delete cascade
);

alter table public.moderators
    owner to "Maktab";

create table public.authors
(
    id           integer      not null
        primary key
        references public.users
            on delete cascade,
    nationalcode varchar(100) not null
        unique,
    birthday     date
);

alter table public.authors
    owner to "Maktab";

create table public.categories
(
    id          serial
        primary key,
    title       varchar(100) not null
        unique,
    description text
);

alter table public.categories
    owner to "Maktab";

create table public.tags
(
    id    serial
        primary key,
    title varchar(100) not null
        unique
);

alter table public.tags
    owner to "Maktab";

create table public.articles
(
    id               serial
        primary key,
    title            varchar(100) not null,
    brief            varchar(255) not null,
    content          text         not null,
    create_date      date    default CURRENT_DATE,
    publish_date     date,
    last_update_date date,
    is_published     boolean default false,
    article_status   varchar(20),
    category_id      integer
        references public.categories
            on delete cascade,
    author_id        integer
                                  references public.authors
                                      on delete set null
);

alter table public.articles
    owner to "Maktab";

create table public.article_tags
(
    article_id integer not null
        references public.articles
            on delete cascade,
    tag_id     integer not null
        references public.tags
            on delete cascade,
    primary key (article_id, tag_id)
);

alter table public.article_tags
    owner to "Maktab";

