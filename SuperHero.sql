create database SuperHero;

use SuperHero;

select * from Hero;
select * from Location;
select * from Organisation;
select * from HeroOrganisation;
select * from Sighting;
select * from SuperPower;

create table Hero(
heroId int primary key,
heroName varchar (20) not null,
heroDesc varchar (40) not null,
heroSuperPower varchar (20) not null);


insert into Hero
values(1, "SuperMan", "Man of Steel", "Super Strength"),
(2, "SpiderMan ", "Friendly Neighbourhood Hero", "Web Slinging"),
(3, "IronMan", "Heavy Metal Hero", "High Tech Genius");


create table Organisation(
orgId int primary key,
orgName varchar (20) not null,
orgDesc varchar (40) not null,
orgAddress varchar (30) not null);

insert into Organisation
values(101, "Warner Bros", "American Entertainment Studio", "4000 Warner Boulevard Burbank"),
(102, "Marvel", "Home of the SuperHeroes", "500 S Buena Vista St"),
(103, "Universal Studios", "The Entertainment Capital", "100 Universal City Plaza");

create table Sighting(
sightingId int primary key,
heroId int,
locationId int,
locationDate date,
constraint foreign key(HEROID) REFERENCES Hero(HEROID),
constraint foreign key(LOCATIONID) REFERENCES Location(LOCATIONID));

insert into Sighting
values(111, 1, 001, "2023-01-01"),
(222, 2, 002, "2022-07-14"),
(333, 3, 003, "2021-12-10");

create table Location(
locationId int primary key,
locationName varchar (30) not null,
locationDesc varchar (40) not null,
locationAddress varchar (40) not null,
coordinates varchar (50) not null);

insert into Location
values(001, "California", "Malibu", "PACIFIC COAST HWY", "34N, 118W"),
(002, "New York", "Central Park", "59th to 110th Street", "40N, 74W"),
(003, "Paris", "Eiffel Tower", "Champ de Mars", "48N, 2E");


create table HeroOrganisation(
serialId int primary key,
heroId int,
orgId int,
constraint foreign key(HEROID) REFERENCES Hero(HEROID),
constraint foreign key(ORGID) REFERENCES Organisation(ORGID));

insert into HeroOrganisation
values(1101, 1, 101),
(1102, 2, 102),
(1103, 3, 103);

create table SuperPower(
superPowerId int primary key,
superPowerName varchar (20),
heroId int,
constraint foreign key(HEROID) REFERENCES Hero(HEROID));

insert into SuperPower
values(01, "Super Strength",1),
(02, "Web Slinging",2),
(03, "High Tech Genius",3);


drop table Location;

ALTER TABLE Hero
ADD COLUMN imagePath VARCHAR(255);

create database testSuperHero;
use testSuperHero;