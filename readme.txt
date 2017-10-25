项目各模块简介
spring-aop：
maven java项目，学习了spring-aop的使用
spring-mvc:
集成spring-security的spring-mvc项目，主要学习spring-security的权限认证功能
spring-data：
maven java项目，学习了spring-data-jpa、spring-data-mongodb、spring-data-redis的使用



遇到的错误/异常：
1.unexpected exception: java.lang.NoClassDefFoundError: org/apache/log4j/LogManager
依赖了log4j的jar包，也成功下载到了本地仓库，启动web应用依然报此错误，说明jar包没有成功copy到lib目录，需要手动配置
http://www.cnblogs.com/foxting/p/6793574.html

2.模块项目呈现灰色
项目被ignore，右侧菜单栏Maven Project——右键项目，Unignore Project即可

3.redis NoSuchMethodError: org.springframework.util.Assert.isTrue(ZLjava/util/function/Supplier;)V
spring-data-redis版本和jedis不兼容，降低版本

4.JedisConnectionException: java.net.SocketTimeoutException: connect timed out

5.redis protected-mode配置用于设置禁止公网访问redis，加强redis安全
启用条件有两个：1）没有bind ip 2）没有设置访问密码，如果启用了则只能使用lookback ip(127.0.0.1)访问redis，如果是外网访问则会报错


