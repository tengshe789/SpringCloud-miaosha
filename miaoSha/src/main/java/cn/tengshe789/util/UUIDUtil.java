package cn.tengshe789.util;

import java.util.UUID;

/**
 * @author tengshe789
 *  自动生成UUID
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
