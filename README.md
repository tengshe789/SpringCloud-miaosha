# miaosha秒杀商城 2代 （Spring Cloud 版本）

### 项目名称：秒杀商城----miaosha

由于面临毕业，且时间有限，准备把这个过去的项目使用微服务技术重构一下交上去。截至到2019年9月，会完成全部的更新。

附：老版本请看`git`的`master`的0.91版本
### 项目技术：

#### 后端技术选型为：

Java 11、SpringBoot 2.1.2  与 Spring Cloud Greenwich 技术栈、swagger、Elasticsearch、MyBatis plus 、Druid 、Redis、Actuator、Log4j、Thymeleaf 、RabbitMQ等。

#### 前端技术选型为：

本人能力有限，暂时摒弃一代miaosha商城前端框架。新的前端还在学习思考ing。不更新。

#### Spring 家族技术选型

Spring Security OAuth2、Spring Cache、Spring Cloud Config、Spring Cloud Gateway、Feign、Hystrix、Spring Event

### 开发工具：

IntelliJ IDEA  x64、MySQL 8、Kubernetes、Docker、Linux、Lombok、Maven、dev-tools、Admin-UI、Git、Navicat、JMetert、Jvisualvm、Postman、Consul

### 项目描述：

随着网络商城在全球范围内的广泛应用，人们对于互联网技术的要求已不单单是浏览一下网页，日益忙碌的人们已经习惯的利用网络商城这一强大的平台实现的网上购物。可随着网络商城的用户量不断增多，网络商家的营销活动不断增多，许许多多的传统网络商城会出现一系列的性能问题。

双十一的秒杀活动家喻户晓，天猫、京东、苏宁等等电商网站经常会在凌晨零点出现服务中断现象。例如在某一个时刻抢购一个原价1999现在秒杀价只要999的手机时，会迎来一个用户请求的高峰期，可能会有几十万几百万的并发量，来抢这个手机，在高并发的情形下会对数据库服务器或者是文件服务器应用服务器造成巨大的压力，严重时说不定就宕机了。

第二个问题是，秒杀的东西都是有量的，一款手机只有10台的货物量，那么，在高并发的情况下，成千上万条数据更新数据库，那次这个时候的先后顺序是很乱的，很容易出现10台的量，抢到的人就不止10个这种严重的问题。

第三个问题是，传统的购物商。应用往往使用集群方式管理，即一个虚拟化节点上往往会部署负责多种业务的模块。在高流量的压力下往往会导致多个节点不可用，这时只能通过传统的“加机器”方式来缓解压力。这在已经步入2019年的今天是极其不可取的。

本课题的目标是设计一个“**秒杀商城**”云服务平台，解决这些严重的系统性能问题、数据一致性问题、架构问题。

平台使用微服务架构搭建，主要集成两种系统，第一种系统实现以下功能：用户登录、用户管理、权限管理；第二个系统实现以下功能：商品列表、商品详情、商品秒杀、订单详情、第三方支付。同时平台会有公共的网关负责管理权限，会有服务代理进行负载均衡策略分发流量。

### 项目特点：

1. **基于SpringBoot**，简化了大量项目配置和maven依赖，让您更专注于业务开发，独特的分包方式，代码多而不乱。
2. **Java 11 支持**：使用原生Java 11，并大量实践`stream`、`webflux `、`lambda`、三元运算符，测试数据集在速度上有巨大提升。
3. **细粒度缓存**。利用`redis`对经常调用的查询、页面、实例进行缓存，保证运行速度。
4. **注解验证**。使用`JSR303`规范和`AOP`编辑代码，使得验证逻辑从业务代码中脱离出来。
5. **异步下单**。多种分布式消息队列以及百万级并发框架`Disruptor`参与下单进程，高效且可靠。
6. **强力监控**。多种监控工具的加入使得你可以全方位的统计观测数据。
7. **日志系统**。基于`spring event`异步处理日志。
8. **容器化**。Docker、rancher的支持。

- 未来目标：争取早日建成一个高并发 、高可用 、高性能的秒杀系统平台

### 项目架构图

//TODO

### 分包结构

```
├─ xbrl-root
│  │  
│  ├─ doc---------------- 项目文档
│  │ 
│  ├─ logs---------------- 日志存放目录
│  │  
│  ├─ miaosha-common---------------- 基本依赖
│  │  ├─ miaosha-common-core---------------- 核心模块
│  │  ├─ miaosha-common-data---------------- 数据与持久化模块
│  │  ├─ miaosha-common-log---------------- 日志处理模块
│  │  ├─ miaosha-common-security---------------- 安全模块
│  │  
│  ├─ miaosha-config----------------- 配置中心
│  │  
│  ├─ miaosha-gateway-------------- 商城网关
│  │ 
│  ├─ miaosha-discovery-------------- 服务发现功能
│  │  
│  ├─ miaosha-mall-------------- 商城
│  │  
│  ├─ miaosha-plugins-------------- 插件
│  │  
│  ├─ miaosha-upms-------------- 通用的权限管理系统
│  │  
│  ├─ script-------------- 脚本语言
│  │  

```

### 怎么使用：

请看代码中doc目录下的markdown，写的零零散散，后期有时间会专门开个gitbook做为文档！


### QA

1. 为什么不用原来的JDK1.8，而换到现在的Java 11？

看到了一篇文章：https://www.optaplanner.org/blog/2019/01/17/HowMuchFasterIsJava11.html

结论：Java 11 与 Java 8 比较过程中，在几乎所有测试数据集上都有速度上的提升。平均而言，仅通过切换到 Java 11 就有 16% 的改进，这种改进可能是因为 Java 10 中引入了 JEP 307: Parallel Full GC for G1。

### 项目界面：

![登陆](http://resume.tengshe789.tech/static/%E7%99%BB%E9%99%86.jpg)

### 版本迭代Update content：

请看![CHANGELOG.md](https://github.com/tengshe789/-miaosha/blob/version2/CHANGELOG.md).

### 联系我：

微信：tengshe789 , catch me as you can~

### 参考资料

- 我博客里的参考文献

- code以及doc中标明的网页连接




