package qzl.com.basecommon.common;

import qzl.com.tools.operate.java.ReadProperties;

/**
 * Created by 魏荣 on 2015/05/14.
 */
public class Constant {
    public static String baseUrl= ReadProperties.getPropertyByStr("server.url");
    public static int pageSize = ReadProperties.getPropertyByInt("list.pagesize");   //分页大小
    /**
     * 参数加密字符串
     */
    public static String PKEY = "Ab0hU6KRzycTBiOPfa4Hgxmq1lSj8LJt";

    public static final String RESPONSE_ERROR = "false";//请求结果-失败
}
