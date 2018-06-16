package cn.tengshe789.redis;

public interface KeyPrefix {
    public int expireSeconds();//生存时间
    public String getPrefix();//
}
