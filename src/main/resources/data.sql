insert into users (username, password, enabled) values ('admin', '$argon2id$v=19$m=4096,t=10,p=1$ral9H997xzoOfJrbRu9edg$nngD+tX8tUSQv88en+Up0cxNgbuvp+Q9iqXD/vxS0No', true);
insert into users (username, password, enabled) values ('user1', '$argon2id$v=19$m=4096,t=10,p=1$ral9H997xzoOfJrbRu9edg$nngD+tX8tUSQv88en+Up0cxNgbuvp+Q9iqXD/vxS0No', true);
insert into users (username, password, enabled) values ('user2', '$argon2id$v=19$m=4096,t=10,p=1$ral9H997xzoOfJrbRu9edg$nngD+tX8tUSQv88en+Up0cxNgbuvp+Q9iqXD/vxS0No', true);



insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('user1', 'ROLE_USER');
insert into authorities (username, authority) values ('user2', 'ROLE_USER');