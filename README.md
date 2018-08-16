# miaosha秒杀商城
### 项目名称：秒杀商城----miaosha
一个简单的有秒杀功能的电子商城项目，适合新人阅读。可以作为学业答辩论文材料。
A simple spike shopping mall project, suitable for new people to read. It can be used as a paper material for academic defense.
### 项目技术：
SpringBoot 1.5.13、MyBatis 1.31、JDK 1.8、Druid 1.1.10、Redis 4.09、JSR303、Log4j、Lombok、Thymeleaf 、Bootstrap、jQuery、Ajax、RabbitMQ

### 开发工具：
IntelliJ IDEA 2018.1 x64、MySQL 8.0、Tomcat、Linux、Maven、Git、Navicat、JMeter

### 项目描述：
该项目的侧重点主要就是秒杀商品这个功能，就是网络卖家可以发布一些超低价格的商品，所有买家在同一时间在网上抢拍。<br/>
后台个人独立搭建，主要包含以下功能：用户登录、商品列表、商品详情、商品秒杀、订单详情，我会不断对其做高并发方面的优化。

### 项目优化(不断更新)：
分布式Session同步系统,页面缓存,对象级缓存，页面静态化

### 项目界面：
暂不更新

### 怎么使用：
#### 数据库MySql
默认用户名root
密码123456
#### redis
启动程序必须开启redis，否则启动失败。我用的是windows版的redis，当然，使用远程服务连接linux的redis更好，配置文件都在resources中的application.properties里面，可以自行配制
#### thymeleaf
页面模板在resources/templates/中，可以自己配置
#### redis
启动程序必须开启RabbitMQ，否则启动失败。我用的是windows版的RabbitMQ，当然，使用远程服务连接linux的RabbitMQ更好，配置文件都在resources中的application.properties里面，连接的用户名和密码都是guest，剩下的可以自行配制

### 联系我：
微信：tengshe789

### 版本迭代Update content：
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
