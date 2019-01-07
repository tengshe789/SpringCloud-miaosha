FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER randyvan007@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /miaosha-discovery

WORKDIR /miaosha-discovery

EXPOSE 8766

ADD ./miaosha-discovery/target/miaosha-discovery.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar miaosha-discovery.jar
