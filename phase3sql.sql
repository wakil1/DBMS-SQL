drop table Store_Member cascade constraints; /*Used in place of Member*/
drop table MemProfile cascade constraints; /*Used in place of Profile*/
drop table Movie cascade constraints;
drop table Watch cascade constraints;
drop table Actor cascade constraints;
drop table Genre cascade constraints;
drop table Likes cascade constraints;
drop table Movie_Genre cascade constraints;

create table Store_Member(
    member_ID varchar2(50),
    first_name varchar2(20),
    last_name varchar2(20),
    card_number integer,
    exp_date integer,
    primary key(member_ID)
);

insert into Store_Member values ('dsmith', 'David', 'Smith', 2091, 0922); 
insert into Store_Member values ('ktran', 'Kenny', 'Tran', 8347, 1022); 
insert into Store_Member values ('awayne', 'Alex',  'Wayne', 6171, 1122);
insert into Store_Member values ('jlynn', 'Joanne', 'Lynn', 8881, 0822);
insert into Store_Member values ('cwang', 'Christine', 'Wang', 5201, 1222);

create table MemProfile(
    member_ID varchar2(10),
    profile_name varchar2(20),
    primary key(profile_name, member_ID),
    foreign key(member_ID) references Store_Member
        on delete cascade
);

insert into MemProfile values('dsmith', 'danny132');
insert into MemProfile values('ktran', 'XxKennyTxX');
insert into MemProfile values('awayne', 'WayneTheMan');
insert into MemProfile values('jlynn', 'JoLynn666');
insert into MemProfile values('cwang', 'CWang007');


create table Movie(
    movie_ID varchar2(20),
    title varchar2(50),
    movie_year integer,
    producer varchar2(20),
    avg_rating integer,
    primary key(movie_ID)
);

insert into Movie values('HP1SStone', 'Harry Potter and the Sorcerers Stone', 2001, 'Warner Bros', 81);
insert into Movie values('HP4GobOfFire', 'Harry Potter and the Goblet of Fire', 2005, 'Warner Bros', 74);
insert into Movie values('HP7Pt1', 'Harry Potter and the Deathly Hallows Part 1', 2010, 'Warner Bros', 71);
insert into Movie values('HP7Pt2', 'Harry Potter and the Deathly Hallows Part 2', 2011, 'Warner Bros', 83);
insert into Movie values('SW2CloneWars', 'Star Wars Episode 2 Attack of the Clones', 2002, 'Lucas Films', 65);
insert into Movie values('SW3RevOfSith', 'Star Wars Episode 3 Revenge of the Sith', 2005, 'Lucas Films', 80);
insert into Movie values('SW7Awakens', 'Star Wars The Force Awakens', 2015, 'Disney', 82);
insert into Movie values('LOTRFellowship', 'Lord of The Rings The Fellowship', 2001, 'New Line Cinema', 92);
insert into Movie values('LOTRTwoTowers', 'Lord of The Rings The Two Towers', 2002, 'New Line Cinema', 87);
insert into Movie values('LOTRKing', 'Lord of The Rings the Return of the King', 2003, 'New Line Cinema', 94);


create table Watch(
    rating integer,
    movie_ID varchar2(50),
    profile_name varchar2(50),
    member_ID varchar2(50),
    primary key(movie_ID, profile_name, member_ID),
    foreign key(movie_ID) references Movie,
    foreign key(profile_name, member_ID) references MemProfile
);

insert into Watch values(94, 'LOTRKing', 'CWang007', 'cwang');
insert into Watch values(74, 'HP4GobOfFire', 'danny132', 'dsmith');
insert into Watch values(71, 'HP7Pt1', 'XxKennyTxX', 'ktran');
insert into Watch values(83, 'HP7Pt2', 'XxKennyTxX', 'ktran');
insert into Watch values(65, 'SW2CloneWars', 'WayneTheMan', 'awayne');
insert into Watch values(80, 'SW3RevOfSith', 'WayneTheMan', 'awayne');
insert into Watch values(82, 'SW7Awakens', 'JoLynn666', 'jlynn');
insert into Watch values(92, 'LOTRFellowship', 'JoLynn666', 'jlynn');
insert into Watch values(86, 'LOTRTwoTowers', 'CWang007', 'cwang');


create table Actor(
    actor_ID varchar2(50),
    movie_ID varchar2(50),
    first_name varchar2(50),
    last_name varchar2(50),
    primary key(actor_ID, movie_ID),
    foreign key (movie_ID) references Movie
        on delete cascade
);

insert into Actor values('DRad', 'HP1SStone', 'Daniel', 'Radcliffe');
insert into Actor values('RGrint', 'HP1SStone', 'Rupert', 'Grint');
insert into Actor values('EWatson', 'HP1SStone', 'Emma', 'Watson');
insert into Actor values('KLeung', 'HP4GobOfFire', 'Katie', 'Leung');
insert into Actor values('ARick', 'HP7Pt1', 'Alan', 'Rickman');
insert into Actor values('OPhelps', 'HP7Pt1', 'Oliver', 'Phelps');
insert into Actor values('ELynch', 'HP7Pt2', 'Evanna', 'Lynch');
insert into Actor values('JPhelps', 'HP7Pt2', 'James', 'Phelps');
insert into Actor values('HayChris', 'SW2CloneWars', 'Hayden', 'Christensen');
insert into Actor values('NatPort', 'SW2CloneWars', 'Natalie', 'Portman');
insert into Actor values('EMcGreg', 'SW2CloneWars', 'Ewan', 'McGregor');
insert into Actor values('SamJack', 'SW3RevOfSith', 'Samuel', 'Jackson');
insert into Actor values('ChrLee', 'SW3RevOfSith', 'Christopher','Lee');
insert into Actor values('EWood', 'LOTRFellowship', 'Elijah', 'Wood');
insert into Actor values('IanMck', 'LOTRFellowship', 'Ian', 'McKellen');
insert into Actor values('VigMor', 'LOTRTwoTowers', 'Viggo', 'Moretnsen');
insert into Actor values('OrBloom', 'LOTRTwoTowers', 'Orlando', 'Bloom');
insert into Actor values('JRhyD', 'LOTRTwoTowers', 'John', 'RhysDavies');
insert into Actor values('SAstin', 'LOTRKing', 'Sean', 'Astin');
insert into Actor values('BBoyd', 'LOTRKing', 'Billy', 'Boyd');



create table Genre(
    m_genre varchar2(50), 
    primary key(m_genre)
);

insert into Genre values('Fantasy');
insert into Genre values('Adventure');
insert into Genre values('ScienceFiction');
insert into Genre values('Magical');
insert into Genre values('Thriller');
insert into Genre values('Action');

create table Likes(
    profile_name varchar2(50),
    member_ID varchar2(50),
    m_genre varchar2(50),
    primary key(profile_name, m_genre),
    foreign key(profile_name, member_ID) references MemProfile,
    foreign key(m_genre) references Genre(m_genre)
);
insert into Likes values('danny132','dsmith', 'Fantasy');
insert into Likes values('danny132','dsmith','Adventure');
insert into Likes values('XxKennyTxX','ktran', 'ScienceFiction');
insert into Likes values('XxKennyTxX','ktran', 'Magical');
insert into Likes values('WayneTheMan','awayne', 'Thriller');
insert into Likes values('WayneTheMan','awayne', 'Action');
insert into Likes values('JoLynn666','jlynn', 'Adventure');
insert into Likes values('JoLynn666','jlynn', 'ScienceFiction');
insert into Likes values('CWang007','cwang', 'Fantasy');
insert into Likes values('CWang007','cwang','Thriller');


create table Movie_genre(
    m_genre varchar2(50),
    movie_ID varchar2(50),
    primary key(m_genre, movie_ID),
    foreign key(m_genre) references Genre,
    foreign key(movie_ID) references Movie
);

insert into Movie_genre values('Magical', 'HP1SStone');
insert into Movie_genre values('Magical', 'HP4GobOfFire');
insert into Movie_genre values('Magical', 'HP7Pt1');
insert into Movie_genre values('Fantasy', 'HP7Pt2');
insert into Movie_genre values('ScienceFiction', 'SW2CloneWars');
insert into Movie_genre values('ScienceFiction', 'SW3RevOfSith');
insert into Movie_genre values('ScienceFiction', 'SW7Awakens');
insert into Movie_genre values('Adventure', 'LOTRFellowship');
insert into Movie_genre values('Adventure', 'LOTRTwoTowers');
insert into Movie_genre values('Adventure', 'LOTRKing');

drop trigger notTooManyMemPRofile;

Create Trigger notTooManyMemPRofile
before insert on MemProfile
for each row
declare 
res_count Integer;
Too_many Exception;
Begin
    Select Count(*) into res_count
    From MemProfile
    where  member_ID=:New.member_ID;
    if res_count >5 THEN
        Raise Too_many;
    END IF;
    EXCEPTION
        when Too_many then
        Raise_application_error(-20001,'Too many MemProfiles!');
    End;
/


drop trigger computeAvg;

CREATE TRIGGER computeAvg
Before INSERT ON Watch
FOR EACH ROW
Begin
    UPDATE Movie
    SET avg_Rating = (SELECT AVG(rating) FROM Watch
                         WHERE Watch.movie_id =: new.movie_id)
    WHERE movie_ID =: NEW.movie_id;
end;
/
