# Mybatis-notes

## 1.简介

- 基于Java的持久层框架
- 支持自定义SQL、存储过程以及高级映射
- 内部封装jdbc，屏蔽了jdbc api的底层访问 细节，使开发者只需要关注于sql语句本身

- 支持使用注解或者是xml进行配置

### 1.1配置

#### 1.1.1添加mybatis依赖

使用Maven构建时，在`pom.xml`中添加mybatis坐标:

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```

#### 1.1.2 mybatis主配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--添加mybatis文档类型定义-->
<!--文档类型定义（DTD）可定义合法的XML文档构建模块。它使用一系列合法的元素来定义文档的结构。-->
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--mybatis主配置文件-->
<!--所有的配置都在<configuration/>标签中进行配置-->
<configuration>
    <!--1.properties标签: 引入外部配置文件 使用resources标签   -->
    <properties resource="jdbcConfig.properties"/>
    <!--除此之外还可以使用file 引入外部配置文件-->
    <!--    <properties url="file:///D://prj//mybatis//Mybatis-notes//mybatis-notes//mybatis-crud//src//main//resources//jdbcConfig.properties"/>-->

    <!--2.全局配置参数-->
    <settings>
        <!--开启延迟加载-->
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"/>
    </settings>

    <!--3.配置别名 降低冗余的全类名书写 仅仅用于xml配置-->
    <typeAliases>
        <!-- 之后任何需要引用该类的地方都可以通过使用 user来引用 -->
        <typeAlias type="com.half.domain.User" alias="user"/>
        <!--package属性:mybatis会在该包下扫描需要的JavaBean 默认会将首字母小写作为别名 如domain.blog.Author的别名为author-->
        <package name="com.half.domain"/>
    </typeAliases>

    <!--4.typeHandlers-->
    <!--在从preparedStatement中取出参数或者是从结果集取值时会使用类型处理器进行类型转换 -->


    <!--5.配置环境-->
    <!--mybatis支持配置成多种环境，便于在如开发、测试等使用不同的配置 -->
    <!--尽管支持多个环境配置 但每个SqlSessionFactory实例也只能选择一种，每个数据库对应一个SqlSessionFactory实例-->
    <!--默认使用的环境ID -->
    <environments default="mysql">
        <!--可配置多个环境 并指明不同的id 用于引用 此处只配置了一个 -->
        <environment id="mysql">
            <!--事务管理器的配置 -->
            <!--在 MyBatis 中有两种类型的事务管理器（也就是 type="[JDBC|MANAGED]"） -->
            <!--JDBC – 这个配置直接使用了 JDBC 的提交和回滚设施，它依赖从数据源获得的连接来管理事务作用域。-->
            <!--如果使用Spring则无需进行配置，Spring模块会使用自带管理器覆盖之前的配置-->
            <transactionManager type="JDBC"></transactionManager>
            <!-- 数据源 使用标准的JDBC数据源接口进行配置 三种内建的数据源类型（也就是 type="[UNPOOLED|POOLED|JNDI]"）-->
            <!--UNPOOLED:每次请求时都打开关闭连接,适用于对于数据库可用性不高的简单应用 可配置属性:driver、url、username、password、defaultTransactionIsolationLevel 、defaultNetworkTimeout -->
            <!--POOLED:使用池管理连JDBC接对象，避免创建连接实例所需要的初始化和认证时间，能使得并发web应用快速响应请求 -->
            <!--JNDI:从jndi上下文中获取数据源的引用-->
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!--指定映射配置文件的位置-->
    <mappers>
        <!--可以使用package配置包的别名 配置后不需要再单独列出mapper的路径        -->
        <package name="com.half.dao"/>
        <!--        <mapper class="com.half.com.half.custom.dao.IUserDao"/>-->
    </mappers>
</configuration>
```

#### 1.1.3 映射配置文件

​		mybatis的强大之处在于映射器配置，通过简单的映射器配置，可免去大量的JDBC代码，使开发者仅需要关注sql语句。

- **resultType**:期望返回结果的全限定类名或者是别名，如果是集合类型声明为集合中的元素类型即可与resultMap二者仅能出现一个

- **resultMap**:用于进行JavaBean属性与数据库查询结果列名间的匹配

	- ```xml
		    <resultMap id="user" type="User">
		        <!--JavaBean属性为userId,数据库列名为id 进行二者的匹配 -->
		        <!--id 元素对应的属性会被标记为对象的标识符，在比较对象实例时使用 -->
		        <id property="userId" column="id"></id>
		        <result property="userName" column="username"/>
		    </resultMap>
		```

	- **association**：用于指明一个复杂类型，封装到结果map中

		- ```xml
			    <resultMap id="accountUserMap" type="account">
			        <id property="id" column="id"/>
			        <result property="uid" column="uid"/>
			        <result property="money" column="money"/>
			        <!--每个账户属于一个用户，因此查询账户时有可能需要查询到用于的信息，此处使用<association/>标签将这个属性封装到resultMap中 -->
			        <!--指明查询用户的方法 参数 返回结果类型-->
			        <association property="user" column="uid" javaType="user" select="sql.dao.IUserDao.findOne">
			        </association>
			    </resultMap>
			```

	- **collection**:将集合类型的复杂类型封装到map中:

		- ```xml
			    <resultMap id="userAccountMap" type="user">
			        <id property="id" column="id"></id>
			        <result property="username" column="username"/>
			        <result property="address" column="address"/>
			        <result property="sex" column="sex"/>
			        <result property="birthday" column="birthday"/>
			        <!-- 配置user对象中accounts对象 一个用户可能持有多个账户 查询用户信息时可能需要知道账户的信息-->
			        <!--使用collection标签指明查询账户的方法 -->
			        <collection property="accounts" ofType="account" select="sql.dao.IAccountDao.findById" column="id">
			
			        </collection>
			    </resultMap>
			```

- **cache**:mybatis内置了强大的事务性查询缓存机制

	- 在mybatis配置文件种添加`</cache>`标签即可开启全局的二级缓存
	- 映射语句文件中的所有 select 语句的结果将会被缓存。
	- 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
	- 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
	- 缓存不会定时进行刷新（也就是说，没有刷新间隔）。
	- 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
	- 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。

- **#{}与${}的区别**:

	- #{}表示一个占位符号 通过#{}可以实现preparedStatement向占位符中设置值，自动进行java类型和jdbc类型转换，#{}可以有效防止sql注入。 #{}可以接收简单类型值或pojo属性值。 如果parameterType传输单个简单类型值，#{}括号中可以是value或其它名称。
	-  ${}表示拼接sql串 通过${}可以将parameterType 传入的内容拼接在sql中且不进行jdbc类型转换， ${}可以接收简单类型值或pojo属性值，如果parameterType传输单个简单类型值，${}括号中只能是value。



### 1.2 缓存

缓存:存在于内存中的临时数据，能够减少与数据库的交互，提升效率

- 适用于缓存:查询频率高，并不经常改变，数据的正确与否与最后结果无关、影响不大

- 不适用于缓存:数据的正确性对结果的影响很大

	

mybatis中的缓存

- 一级缓存:存在于SqlSession中的缓存
	
	- 当调用SqlSession对象的修改、添加、删除、commit()、close()、clearCache()等方法时会清空一级缓存
- 二级缓存:SqlSessionFactory对象的缓存，由同一个SqlSessionFactory创建的SqlSession共享其中的缓存。
	- 配置二级缓存的步骤:
		- 1.mybatis的主配置文件SqlMapConfig.xml中，添加<setting name="cacheEnabled" value="true"/>
		- 2.映射文件中开启二级缓存支持，添加<cache/>  缓存只作用于 cache 标签所在的映射文件中的语句
		- 3.映射文件指定的sql语句标签中 添加"useCache=true"
	- 二级缓存中存储的数据并不是对象，一组组键值对，每次创建新的对象填充数据后返回。
	
	

缓存是如何保持数据一致性的?

缓存的清除策略:

- `LRU` – 最近最少使用：移除最长时间不被使用的对象。这是默认使用的清除策略。
- `FIFO` – 先进先出：按对象进入缓存的顺序来移除它们。
- `SOFT` – 软引用：基于垃圾回收器状态和软引用规则移除对象。
- `WEAK` – 弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象。

### 1.3动态SQL

​		动态SQL是mybatis的强大特性之一。能够避免根据不同条件拼接SQL时的复杂和繁琐。

​		OGNL:**Object-Graph Navigation Language** (OGNL) is an open-source [Expression Language (EL)](https://en.wikipedia.org/wiki/Unified_Expression_Language) for [Java](https://en.wikipedia.org/wiki/Java_(programming_language))

#### 1.3.1 if

```xml
<select id="findActiveBlogWithTitleLike"
     resultType="Blog">
  SELECT * FROM BLOG
  WHERE state = ‘ACTIVE’
  <if test="title != null">
    AND title like #{title}
  </if>
</select>
```

#### 1.3.2 choose、when、otherwise

​		从多个条件中选择一个使用，有点像switch语句。

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG WHERE state = ‘ACTIVE’
  <choose>
    <when test="title != null">
      AND title like #{title}
    </when>
    <when test="author != null and author.name != null">
      AND author_name like #{author.name}
    </when>
    <otherwise>
      AND featured = 1
    </otherwise>
  </choose>
</select>
```

#### 1.3.3 trim、where、set

下面的sql语句当传入的条件不确定时最终可能无法拼接为一个有效的sql语句:

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG
  WHERE
  <if test="state != null">
    state = #{state}
  </if>
  <if test="title != null">
    AND title like #{title}
  </if>
  <if test="author != null and author.name != null">
    AND author_name like #{author.name}
  </if>
</select>
```

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG
  <where>
    <if test="state != null">
         state = #{state}
    </if>
    <if test="title != null">
        AND title like #{title}
    </if>
    <if test="author != null and author.name != null">
        AND author_name like #{author.name}
    </if>
  </where>
</select>
```

​		<where>会当内部查询条件不为空时才返回，同时会将多余的AND OR过滤掉。

​		如果这种过滤不满足需要，还可以使用<trim/>配置。

使用<set/>根据条件动态更新:

```xml
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>
```

#### 1.3.4 foreach

用于对集合进行遍历，比如in条件语句:

```xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  WHERE ID in
  <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
</select>
```

#### 1.3.5 script

要在带注解的映射器接口类中使用动态 SQL，可以使用 *script* 元素。比如:

```xml
    @Update({"<script>",
      "update Author",
      "  <set>",
      "    <if test='username != null'>username=#{username},</if>",
      "    <if test='password != null'>password=#{password},</if>",
      "    <if test='email != null'>email=#{email},</if>",
      "    <if test='bio != null'>bio=#{bio}</if>",
      "  </set>",
      "where id=#{id}",
      "</script>"})
    void updateAuthorValues(Author author);

```

#### 1.3.6 bind

`bind` 元素允许你在 OGNL 表达式以外创建一个变量，并将其绑定到当前的上下文。比如：

```xml
<select id="selectBlogsLike" resultType="Blog">
  <bind name="pattern" value="'%' + _parameter.getTitle() + '%'" />
  SELECT * FROM BLOG
  WHERE title LIKE #{pattern}
</select>
```

#### 1.3.7多数据库支持

如果配置了 databaseIdProvider，你就可以在动态代码中使用名为 “_databaseId” 的变量来为不同的数据库构建特定的语句。比如下面的例子：

```xml
<insert id="insert">
  <selectKey keyProperty="id" resultType="int" order="BEFORE">
    <if test="_databaseId == 'oracle'">
      select seq_users.nextval from dual
    </if>
    <if test="_databaseId == 'db2'">
      select nextval for seq_users from sysibm.sysdummy1"
    </if>
  </selectKey>
  insert into users values (#{id}, #{name})
</insert>
```

### 1.4  注解

```java
//开启缓存支持
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有
     */
    @Select("select * from user")
    //为配置的userMap起一个id 此后就可以引用这个
    @Results(id = "userMap", value = {@Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            //一对多使用@Many注解
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.half.annotation.dao.IAccountDao.findByUid" ,
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /*添加**/
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void save(User user);

    @Update("update user set username=#{username},sex=#{sex},address=#{address},birthday=#{birthday} where id=#{id}")
    void update(User user);

    @Delete("delete from user where id =#{id}")
    void delete(Integer id);

    @Select("select * from user where id=#{id}")
    @ResultMap(value = {"userMap"})
    User findOne(Integer id);

    //@Select("select * from user where username like #{name}")
    @Select("select * from user where username like '%${value}%'")
    @ResultMap(value = {"userMap"})
    List<User> findByName(String name);

    @Select("select count(*) from user")
    Integer findTotal();
}

```

```java
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            //一对一 使用`@One`注解
            @Result(column = "uid", property = "user", one = @One(select = "com.half.annotation.dao.IUserDao.findOne", fetchType = FetchType.EAGER)
            )
    })
    List<Account> findAll();

    @Select("select * from account where uid=#{uid}")
    List<Account> findByUid(Integer uid);
```

### 1.5 连接池

#### 1.5.1 作用

- 避免重复的建立和释放连接资源，减少开销提升效率。

#### 1.5.2 Mybatis中的连接池

​		mybatis主配置文件`SqlMapConfig.xml`的`<dataSource/>`标签用于配置 数据源，其`type`属性用于选取连接池。虽然数据源的配置是可选的，但如果要采用延迟加载特性，必须配置数据源。

​		由三种内建类型的数据源:type=[UNPOOLED | POOLED | JNDI] :

- `UNPOOLED`:
	- 每次请求时都重新打开和关闭连接
	- 速度稍慢，适用于队数据库连接性可用性要求不高的应用
	- 性能依赖于书库性能
	- 可配置的属性包含:`driver`、`url`、`username`、`password`、`defaultTransactionIsolationLevel(数据库默认隔离级别)`、`defaultNetworkTimeout(等待数据库操作完成的默认网络超时时间)`、也可以为数据库驱动传递属性，如:`driver.encoding=UTF`
- `POOLED`
	- 使用`池`来管理JDBC连接，避免了创建新的连接实例所需的初始化和认证时间，支持并发web应用
	- 可配置属性除了，`UNPOOLED`中可配置属性外，还包含:
		- `poolMaximumActiveConnections`：任意时间内可存在的活动连接数，默认为10
		- `poolMaxiumActiveIdleConnections`：任意时间可存在的空闲连接数
		- `poolMaximumCheckoutTime` – 在被强制返回之前，池中连接被检出（checked out）时间，默认值：20000 毫秒（即 20 秒）
		- `poolPingQuery` – 发送到数据库的侦测查询，用来检验连接是否正常工作并准备接受请求。
		- `poolPingEnabled` – 是否启用侦测查询。若开启，需要配置`poolPingQuey`为一个可执行的sql语句，默认为false
- `JNDI`:
	- 这个数据源实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的数据源引用。这种数据源配置只需要两个属性：
		- 使用服务器提供的JNDI技术来获取数据源，仅能在web或者maven的war融创使用，Tomcat服务器使用的连接池是dbcp连接池
		- `initial_context` – 这个属性用来在 InitialContext 中寻找上下文
		- `data_source` – 这是引用数据源实例位置的上下文路径。提供了 initial_context 配置时会在其返回的上下文中进行查找，没有提供时则直接在 InitialContext 中查找。

### 1.5  配置文件的读取

1. 使用类加载器：只能读取类路径的配置文件
2. 使用ServletContext对象的getRealPath()