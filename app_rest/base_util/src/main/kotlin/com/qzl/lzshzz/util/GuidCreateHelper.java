package com.qzl.lzshzz.util;

import java.util.UUID;

/**
 * @author <a href=" ">hanjp</a>
 * @version 1.0.0
 */
public class GuidCreateHelper {

    public static String getGuid() {
        UUID uuid = UUID.randomUUID();
        String guid = uuid.toString().replace("-", "");
        return guid;
    }
}
