# miaosha秒杀商城beta

### 项目名称：秒杀商城----miaosha

一个简单的有秒杀功能的电子商城项目，适合新晋程序员阅读参考。
A simple spike shopping mall project, suitable for new people to read. It can be used as a paper material for academic defense.

### 项目技术：

SpringBoot 1.5、MyBatis 1.3、JDK 8、Druid 1.1、Redis 4.09、JSR303、Log4j、Lombok、Undertow、Thymeleaf 、Bootstrap、jQuery、Ajax、RabbitMQ、dev-tools

### 开发工具：
IntelliJ IDEA  x64、MySQL 8、Tomcat、Linux、Maven、Git、Navicat、JMetert、Jvisualvm

### 项目描述：

该项目的侧重点主要就是秒杀商品这个功能，就是网络卖家可以发布一些超低价格的商品，所有买家在同一时间在网上抢拍。<br/>
后台个人独立搭建，主要包含以下功能：用户登录、商品列表、商品详情、商品秒杀、订单详情，我会不断对其做高并发方面的优化。

### 项目优化(不断更新)：

密码md5加密，系统SSR化，分布式Session同步系统,页面缓存,对象级缓存，数据库缓存，商品界面静态化，异步下单，jvm调优

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

![列表](http://resume.tengshe789.tech/static/%E5%95%86%E5%93%81%E5%88%97%E8%A1%A8.jpg)

### 怎么使用：

#### 数据库MySql
默认用户名root
密码123456

#### redis
启动程序必须开启redis，否则启动失败。我用的是windows版的redis，当然，使用远程服务连接linux的redis更好，配置文件都在resources中的application.properties里面，可以自行配制
#### thymeleaf
页面模板在resources/templates/中，可以自己配置
#### RabbitMQ

启动程序必须开启RabbitMQ，否则启动失败。我用的是windows版的RabbitMQ，当然，使用远程服务连接linux的RabbitMQ更好，配置文件都在resources中的application.properties里面，连接的用户名和密码都是guest，剩下的可以自行配制
### 联系我：

微信：tengshe789

### 版本迭代Update content：

#### 第10版version 0.88 beta

将serlvet容器由tomcat换成undertow，性能大概提升25%。新增增加热部署工具dev-tools

#### 第9版version 0.87 beta

抖了个小机灵，数据库新增商品iPhone Xs Max。新增分布式Session的相关注释，通俗易懂~

#### 第8版version 0.86 beta

将秒杀接口的地址没隐藏😂增加了docker配置文件

#### 第7版version 0.85 beta

将秒杀接口的地址隐藏。目前已知的bug有，ui乱码，mq启动异常

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
