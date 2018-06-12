package cn.tengshe789.redis;

public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
