package cn.tengshe789.redis;

public interface KeyPrefix {
	public int expireSeconds();//过期时间

	public String getPrefix();//前缀
}
