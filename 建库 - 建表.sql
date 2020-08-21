create database yuetingfm charset utf8mb4;

use yuetingfm;

-- 密码使用 char(64) 是因为 经过 sha-256 进行加密后, 密码的长度就固定为 64 位的长度
create table users (
    uid int primary key auto_increment comment '用户id',
    username varchar(64) not null unique comment '用户名',
    password char(64) not null comment '经过 sha-256计算后的用户密码'
);
create table books (
    bid int primary key auto_increment comment '小说id',
    uid int not null comment '上传用户的 id',
    title varchar(100) not null comment '小说名称'
);

create table sections (
    sid int primary key auto_increment comment '章节 id',
    bid int not null comment '属于哪一本小说的 id',
    name varchar(100) comment '章节名称'
);

create table audios (
    aid int primary key auto_increment comment '音频 id',
    sid int not null comment '属于哪个章节的 id',
    uuid char(36) not null comment 'uuid',
    type varchar(20) not null comment '音频类型 audio/wmv audio/mp3',
    content longblob default null comment '音频内容'
);