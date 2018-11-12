use oceanstudy;
drop table if exists os_fish;
create table os_fish (
	id int auto_increment not null,
    name varchar(50),
    weight double,
    length double,
    height double,
    deep int,
    age int,
    img varchar(250),
    video varchar(250),
    `status` varchar(50),
    primary key (id)
);




