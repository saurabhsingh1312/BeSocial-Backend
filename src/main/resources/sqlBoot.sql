DROP OGREDDIT IF EXISTS;
CREATE DATABASE OGREDDIT;
USE OGREDDIT;

CREATE TABLE POST(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    body VARCHAR(300),
    publishtime DATE,
    likes BIGINT,
    dislikes BIGINT,
    currentlevel BIGINT,
    comments BIGINT REFERENCES POST(currentlevel)
    
);  

INSERT INTO POST(body,publishtime,likes,dislikes,currentlevel,comments) VALUES("Root1" ,NOW(),2,0,0,1);
INSERT INTO POST(body,publishtime,likes,dislikes,currentlevel,comments) VALUES("Root2" ,NOW(),2,0,0,null);


INSERT INTO POST(body,publishtime,likes,dislikes,currentlevel,comments) VALUES("Child1OfRoot1" ,NOW(),0,0,1,null);
INSERT INTO POST(body,publishtime,likes,dislikes,currentlevel,comments) VALUES("Child2OfRoot1" ,NOW(),4,0,1,null);