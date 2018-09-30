package cn.tengshe789.redis;

public class MiaoshaKey extends BasePrefix {

    public MiaoshaKey(int expireSeconds, String prefix) {
        super( prefix);
    }
    public static MiaoshaKey isOver = new MiaoshaKey( 0,"go");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey( 60,"mp");
    //验证码时间设置长一点，本次设置的5分钟
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey( 300 ,"vc");
}
