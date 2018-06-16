package cn.tengshe789.redis;

public class OrderKey extends BasePrefix {

    //永久不过期
    public OrderKey(String prefix) {
        super (prefix);
    }

    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
