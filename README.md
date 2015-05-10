## sequence-generator
### 简介
* 类似于oracle的sequence,但更加强大
* 支持分布式环境下sequence的生成
* 使用乐观锁和AtomicLong确保sequence的唯一性
* 使用及其简单

### db script
	CREATE TABLE sequence_database.sequence ( 
		name varchar(64) NOT NULL comment "sequence的名称", 
		value bigint(20) NOT NULL comment "sequence的值",
		gmt_modified datetime NOT NULL comment "格林威治修改时间"
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
* 使用前需要对要使用的sequence name进行db初始化,具体见sequence-generator的测试用例以及sequence.sql

### 配置
* sequence-generator发到maven私服，在具体的应用系统中进行依赖
* 添加如下的spring配置文件
		
		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:sequence="https://raw.githubusercontent.com/spccold/xsd/master"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
        	 http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
		     https://raw.githubusercontent.com/spccold/xsd/master
         	 https://raw.githubusercontent.com/spccold/xsd/master/sequence-3.0.xsd">
         
			<sequence:sequence name="test1"/>	
			<sequence:sequence name="test2"/>
		</beans>
* test1/test2就是你在数据库中初始化的sequence name,在代码中通过spring注解`@Resource(name = "test1")`和`@Resource(name = "test2")`即可完成sequence的引用,`nextValue()`会递增返回序列,具体见测试用例

### 运维
* db的配置全在sequence-jdbc.yml
* sequence-generator本身就是支持分布式的,但是为了达到高可用，建议通过MHA搭建超过两台以上的mysql server,把它们的vip填写在sequence-jdbc.yml中的url一栏