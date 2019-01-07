# consul模块
## windows
### 快速启动
找到本文档下的`ConsulStartup.bat`文件，在桌面管理器下双击打开即可。
### 安装consul
下载地址：https://www.consul.io/downloads.html

安装启动然后访问consul的web端：`http://127.0.0.1:8500`
###
consul文档

①https://github.com/spring-cloud/spring-cloud-consul/blob/master/docs/src/main/asciidoc/spring-cloud-consul.adoc#install-consul

②https://learn.hashicorp.com/consul/getting-started/

### 启动
（本人在windows环境下）使用命令`consul agent -dev`启动consul
#### 打开
## linux
`docker search consul`
`docker pull consul`
打开网址：http://127.0.0.1:8500 
## mac
```bash
brew install consul
```