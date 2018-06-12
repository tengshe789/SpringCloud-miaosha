package cn.tengshe789.redis;

public class MiaoshaUserKey extends BasePrefix{

    private static final int TOKEN_EXIPRE= 3600*24*2;
    private MiaoshaUserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }
    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXIPRE,"tk ");

}
