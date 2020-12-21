# JNDI

JNDI: Java Naming and  Directory Interface,is a Java [API](https://en.wikipedia.org/wiki/Application_programming_interface) for a [directory service](https://en.wikipedia.org/wiki/Directory_service) that allows Java software clients to discover and look up data and resources (in the form of Java [objects](https://en.wikipedia.org/wiki/Object_(computer_science))) via a name. 



## 1.延迟加载与立即加载

- 延迟加载:按需加载，在真正使用数据时才进行查询，不使用时并不查询。
	- 一对多，多对多时使用
- 立即加载:方法调用后立即进行查询
	- 多对一，一对一时使用

## 2.缓存的使用

- 缓存:存在于内存中的临时数据，能够减少与数据库的交互，提升效率
	- 适用于缓存:查询频率高，并不经常改变，数据的正确与否与最后结果无关、影响不大
	- 不适用于缓存:数据的正确性对结果的影响很大
- mybatis中的缓存:
	- 一级缓存:指的是SqlSession对象中的缓存
		- 当执行查询后，查询的结果会同时保存在SqlSession提供的一块区域，该区域是一个Map,当再次执行相同的查询时，会先去SqlSession对象中进行查看是否存在，一旦存在则直接使用。
		- 当SqlSession对象消失时，一级缓存也消失。通过SqlSession.close()和SqlSession.clearCache()可以清空缓存。
		- 当调用SqlSession的修改、添加、删除、commit()、close()等方法时会清空一级缓存
	- 二级缓存
		- mybatis中SqlSessionFactory对象中的缓存，同一个SqlSessionFactory对象创建的Sqlsession对象共享其缓存。
		- 二级缓存的配置步骤:
			- mybatis配置文件SqlMapConfig:
			- IUserDao.xml中配置:
			- sql语句中配置
		- 二级缓存中存放的是数据不是对象，会将缓存中数据的值填充到一个新的对象中返回

## 3.使用Java注解配置mybatis

