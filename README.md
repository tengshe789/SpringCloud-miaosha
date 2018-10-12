# miaosha秒杀商城Beta

### 项目名称：秒杀商城----miaosha

一个简单的有秒杀功能的电子商城项目，适合新晋程序员阅读参考。
A simple spike shopping mall project, suitable for new people to read. It can be used as a paper material for academic defense.

### 项目技术：

JDK 8、SpringBoot 1.5、MyBatis 1.3、Druid 1.1、Redis、JSR223、JSR303、Actuator、Log4j、Lombok、Thymeleaf 、RabbitMQ、Bootstrap、jQuery、Ajax

### 开发工具：

IntelliJ IDEA  x64、MySQL 8、Docker、Undertow、Linux、Maven、dev-tools、Admin-UI、Git、Navicat、JMetert、Jvisualvm

### 项目描述：

该项目的侧重点主要就是秒杀商品这个功能，就是网络卖家可以发布一些超低价格的商品，所有买家在同一时间在网上抢拍。<br/>
后台个人独立搭建，主要包含以下功能：用户登录、商品列表、商品详情、商品秒杀、订单详情，我会不断对其做高并发方面的优化。

### 项目特点：

1. 基于SpringBoot，简化了大量项目配置和maven依赖，让您更专注于业务开发，独特的分包方式，代码多而不乱。
2. 页面缓存与SSR。利用Thymeleaf 模板引擎对页面进行封装和渲染，使臃肿的html代码变得简洁，更加易维护。
3. 对象缓存。利用redis对经常调用的查询进行缓存，提升运行速度。
4. 统一拦截器。控制器层统一的异常拦截机制，利用`@ControllerAdvice`统一对异常拦截，有利于对异常的维护。
5. 高效密码加密。客户端将输入密码与固定的盐值进行md5哈希算法进行计算，用ajax将数值传入服务端吗，防止用户明文密码在网络进行传输，预防数据库被盗，避免通过MD5反推出密码，双重保险。
6. 注解验证。使用JSR303自定义校验器，实现对用户账号、密码的验证，使得验证逻辑从业务代码中脱离出来。
7. 分布式Session。使用客户端存储法和缓存存储法，解决session不一致问题。
8. **界面静态化**。重新设计秒杀商品页面，页面内容静态化，用户请求不需要经过应用服务。
9. 异步下单。使用了Rabbitmq，用户提交请求时直接返回入队，性能也会提高。
10. 秒杀地址隐藏。自动生成秒杀地址，预防高流量访问。
11. Jvm调优。固定堆内存大小，减少内存自动扩容和收缩带来的性能损失。
12. 谷歌V8渲染。使用JSR223规范，利用JS v8引擎渲染计算图形验证码。
13. 容器化。使用docker进行容器化，使部署简便快捷。
14. 负载均衡。使用nginx反向代理进行负载均衡，增加吞吐量、加强网络数据处理能力、提高网络的灵活性和可用性。
15. 后台监控功能。使用基于Actuator的Admin UI显示各个服务的状态。

### 未来目标

- 未来目标：争取早日建成一个高并发 、高可用 、高性能的秒杀系统平台
- 计划：
  ①引入solr，实现商品搜索 
  ② 增加redis仆人，完善可用性 
  ③Tomcat服务端优化，寻找靠谱的CDN节点 
  ④练习vue.js，使用vue.js重构UI 
  ⑤引入高性能jvm，摆脱java8 
  ⑥应用程序容器化，转向微服务
  ⑦增强持续集成的可行性，减少项目部署的步骤and时间
  ⑧使用Webflux重构系统，争取早日脱离Servlet容器

### 项目界面：

![登陆](http://resume.tengshe789.tech/static/%E7%99%BB%E9%99%86.jpg)

### 怎么使用：

等我写完在告诉你
### 联系我：

微信：tengshe789

好多人加我微信让我教他，结果发现都是大佬。我很菜的~！！！

### 版本迭代Update content：

#### 第15版version 0.93

java 11 可以运行！

#### 第14版version 0.92

集成spring-mail，增加发送邮件功能！

#### 第13版version 0.91

修复异常。日常更新。

#### 第12版version 0.90 beta

抱歉，这是一个无法正常实现秒杀功能的版本，其他功能正常运行，正在努力的找bug

新加了：

- 秒杀地址url隐藏功能
- 使用JSR223规范的高级运算验证码功能

#### 第11版version 0.89 beta

修复注释中一大堆错别字；修复商品详情界面无法显示的bug（js条件语句写错了，为什么没人提醒我！？？）

#### 第10版version 0.88 beta

将serlvet容器由tomcat换成undertow，性能大概提升25%。新增增加热部署工具dev-tools

#### 第9版version 0.87 beta

抖了个小机灵，数据库新增商品iPhone Xs Max。新增分布式Session的相关注释，通俗易懂~

#### 第8版version 0.86 beta

增加了docker配置文件

#### 第7版version 0.85 beta

修复若干错误。目前已知的bug有，ui乱码，mq启动异常

#### 第6版version 0.81

修复一大堆的bug
#### 第5版version 0.8

增加中间件rabbitMQ
#### 第4版version 0.7
解决无限刷单问题，将部分界面静态化，将reids缓存地址换回本地服务器
#### 第3版version 0.6
将Springboot版本更新成1.5.13，加入商品列表、商品详情的页面级缓存
#### 第2版version 0.55
还在全力建设ing，另外紧急修复看到商品列表以后，无法跳转对应商品详细信息的BUG。
#### 第1版version 0.5
登陆界面，简单列表界面，剩余主要功能还未完成

项目地址：https://github.com/tengshe789/-miaosha

### 参考资料

我博客里的参考文献

代码中注释的网页连接

慕课网的若鱼大大的教程

it黑马培训机构2017年的分布式商城解决方案（[code](http://www.itheima.com/special/hmjavaeezly/index.shtml)）

程序员小柒的[开源项目](https://gitee.com/52itstyle/spring-boot-seckill)

