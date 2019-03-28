#### 第24版 2019-3-28 version 2 snapshot
1. 完善多个服务的配置文件
2. mall服务中部分代码重构+完善
3. 增加两个服务监控组件
4. 完善rabbitMQ框架

#### 第23版 2019-3-26 version 2 snapshot
1. 完善配置文件中关于 jasypt 的配置
2. 继续修复几个有关使用jdk11的bug
3. 添加自定义过滤client的网关filter
4. 添加自定义校验密码的网关filter

#### 第22版 2019-3-25 version 2 snapshot
1. 增加服务网关的几个自定义filter
2. 增加spring-cloud-bus，解决服务网关启动时报http 530 异常
3. 更新readme.md
4. 优化多个配置文件
5. 修复几个有关使用jdk11的bug

#### 第21版 2019-3-22 version 2 snapshot
1. 增加rest模板的负载均衡
2. 根据配置spring security oauth2 ，个性化客户端模式携带的 token 内容 

#### 第20版 2019-3-21 version 2 snapshot
修复bug

#### 第19版 2019-3-21 version 2 snapshot
1.新增鉴权中心模块
2.更新readme
3.修复些许bug
4.简化jackon依赖
5.简化swagger配置
6.将sys模块拆分
7.更新部分部署文档

#### 第18版 2019-3-21 version 2 snapshot
1.新增鉴权中心模块
2.更新readme
3.修复些许bug
4.简化jackon依赖
5.简化swagger配置
6.将sys模块拆分
7.更新部分部署文档

#### 第17版 2019-3-20 version 2 snapshot
1.重新分包，结构更加简明。
2.删除大量不必要的代码，减少耦合
3.重新命名项目名称
4.增加bom模块，版本管理更加简单
5.将旧版-miaosha与新版融合，但是重新安排微服务调用链，未大规模重构代码（以后再说）
6.增加些许工具类

#### 第16版 2019-2-18 version 2 snapshot
+ 更新README.md

#### 第15版 2019-2-18 version 2 snapshot
+ 更新权限管理系统

+ 完善分包策略

+ 更新部分依赖选型（如果时间不充裕可能会更换部分技术）

#### 第14版 2019-1-7 version 2 snapshot
+ spring cloud 重构

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