# 白名单绑定系统(Java)
## Whitelist Binder(Java)

基于Rcon的Minecraft服务器白名单绑定系统，使用Java实现

开发环境参考：SpringBoot-3.2.0 Jdk-21.0.4

### 手动打包步骤
0、请修改src/main/resources/templates/index.html文件中的网页标题及application.yml中的启动端口和Rcon配置<br>
(sqlite数据库文件会在应用启动后自动在根目录下创建)

1、在pom.xml同级目录下运行指令
```maven
mvn clean package -Dmaven.test.skip=true
```
2、在target目录下找到whitelist-1.0.jar文件，移动到想要的文件夹下

3、运行whitelist-1.0.jar文件启动应用
```java
java -jar whitelist-1.0.jar
```