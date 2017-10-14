error：
1.unexpected exception: java.lang.NoClassDefFoundError: org/apache/log4j/LogManager
依赖了log4j的jar包，也成功下载到了本地仓库，启动web应用依然报此错误，说明jar包没有成功copy到lib目录，需要手动配置
http://www.cnblogs.com/foxting/p/6793574.html

2.模块项目呈现灰色
项目被ignore，右侧菜单栏Maven Project——右键项目，Unignore Project即可
