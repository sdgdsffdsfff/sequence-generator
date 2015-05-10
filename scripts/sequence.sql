-- 创建存放sequence的数据库
create database  sequence_database;
-- 创建sequence表
CREATE TABLE sequence_database.sequence ( 
	name varchar(64) NOT NULL comment "sequence的名称", 
	value bigint(20) NOT NULL comment "sequence的值",
	gmt_modified datetime NOT NULL comment "格林威治修改时间"
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 单元测试的初始化数据
insert into sequence_database.sequence values('test1',0,now());
insert into sequence_database.sequence values('test2',0,now());

