# Shop SSM框架搭建的网上游戏商城
## 项目信息
- 项目名字：WePlay
- 项目描述：基于SSM框架的网上游戏商城
## 环境配置
- JDK 1.8
- Spring 4.3.9
- MyBatis 3.4
- MySQL 5.7.18
- Maven 3
- Redis 3.0
- Tomcat 8.5.15
### maven 阿里云仓库
```xml
<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>        
</mirror>
```
# 一些问题及解决方案
## 跨域请求
　　在`Controller`控制器中添加了`@CrossOrigin`注解。
## 用`token`验证用户身份
　　使用唯一识别码`token`来识别每一个用户，用户登陆后生成一个`token`写入数据库和缓存中，同时写入到response中的cookie中，这样以后每次请求都会带上这个`token`，用`AuthInterceptor`对所有的请求进行拦截，根据`token`从缓存中查取对应的`uid`，再从数据库中查取对应的用户详情，并存到[`UserHolder`](https://github.com/RojerAlone/shop/blob/master/src/main/java/cn/cie/utils/UserHolder.java)这个工具类中。
## 拦截器进行权限处理
　　很多接口只有用户验证了才能进行下一步操作，写了一个拦截器对特定的请求进行拦截，如果用户没有登录，就跳转到登陆页面，登陆成功后跳转到之前的页面。
## 使用`Redis`
　　几乎所有的页面都会加载商品种类，因此将商品的种类信息放入缓存中；网站首页有一个每日推荐，是每天零时随机生成5个商品，由于这些商品访问频率高，因此将这5个商品放入缓存中，可将访问速度由10\~20ms 缩减到3\~5ms。

　　同时用户`token`也放入了缓存中，并设置过期时间为1天，如果用户选择了保持登陆状态7天，那么`token`过期后再从数据库中取出放入缓存中。

　　用户注册后需要验证邮箱，将验证码放入缓存中，15分钟过期。

　　将重量型事件抽取出来，用Redis模拟消息队列，另起一个线程从此队列中获取消息并执行，比如发送邮件操作。
## 使用`protostuff`进行序列化和反序列化
　　`protostuff`是基于`protobuff`的Java实现类库，不需要再编写`.proto`描述文件，优点是大大加快了序列化速度和反序列化速度，而且序列化后占空间十分小，效率很高，Github上有人做了[测试](https://github.com/eishay/jvm-serializers/wiki)。缺点是需要传入`Schema`进行序列化和反序列化，因此编写了`RedisUtil`工具类包装了`Jedis`接口以及`protostuff`。
