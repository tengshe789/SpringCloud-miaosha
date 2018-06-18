package cn.tengshe789.redis;

public class MiaoshaKey extends BasePrefix {
    public MiaoshaKey(String prefix) {
        super( prefix);
    }
    public static MiaoshaKey isOver = new MiaoshaKey( "go");
}
