# Mybatis-notes

## 1.Mybatis概述

​	Mybatis是一款持久层框架，支持自定义SQL、存储过程、高级映射，能够通过使用Java注解或者是xml的配置方法建立起数据库记录和Java POJO的映射，免去使用JDBC代码及设置参数获取结果集的工作。	 

### 1.1入门

#### 1.1.1添加mybatis依赖

使用Maven构建时，在`pom.xml`中添加mybatis坐标:

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```

#### 1.1.2构建SqlSessionFactory

​		mybati应用以`SqlSessionFactory`的实例为核心,可以通过`SqlSessionFactoryBuilder`构建，`SqlSessionFactoryBuilder`通过XML文件中预定义的`configuration`实例来构建`SqlSessionFcatory`实例。Mybatis提供了`Resources`工具类提供多种重载方式以支持从输入流、类路径等加载资源文件。

```java
String resource = "org/mybatis/example/mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
```

#### 1.1.3 mybatis主配置文件

​		下面所示是一个mybatis的主配置文件，能够配置数据源、决定事物作用域以及控制方式的事务管理器。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--mybatis主配置文件-->
<configuration>
    <!--    配置环境-->
    <environments default="mysql">
        <environment id="mysql">
            <transactionManager type="JDBC"></transactionManager>
            <!-- 连接池-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
    <!--指定映射配置文件的位置-->
    <mappers>
        <mapper resource="com/half/dao/IUserDao.xml"/>
    </mappers>
</configuration>
```

​		除了使用XML配置方式外，也支持使用Java代码来配置与之等价的配置项:

```java
DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
TransactionFactory transactionFactory = new JdbcTransactionFactory();
Environment environment = new Environment("development", transactionFactory, dataSource);
Configuration configuration = new Configuration(environment);
configuration.addMapper(BlogMapper.class);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
```

#### 1.1.4 构建SqlSessionFactory,构建Session,执行SQL

​	通过SqlSessionFactory能够创建session,进而执行SQL,如:

```java
try (SqlSession session = sqlSessionFactory.openSession()) {
  Blog blog = (Blog) session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
}
```





配置文件的读取:

1. 使用类加载器：只能读取类路径的配置文件
2. 使用ServletContext对象的getRealPath()