create table posts (
    id bigint auto_increment,
    name varchar(255),
    message varchar(255),
    createAt timestamp
);

create table users (
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table login_attempts (
    id bigint auto_increment,
    username varchar(50),
    ipAddress varchar(50),
    loginTime timestamp
);