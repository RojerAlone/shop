# Shop 校企联合实训项目
## 项目信息
- 项目名字：WePlay
- 项目描述：基于SSM框架的网上游戏商城
## 环境配置
- JDK 1.8
- MySQL 5.7.18
- Tomcat 8.5.15
- Maven 3
- Spring 4.3
- MyBatis 3.4
### maven 阿里云仓库
```xml
<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>        
</mirror>
```
# 项目结构
> controller 控制器 

> entity 用到的pojo
>> dto 数据传输实体

> mapper 相当于dao，用于数据库操作

> services 存放service接口以及实现
>> impl service的实现

> utils 工具包
