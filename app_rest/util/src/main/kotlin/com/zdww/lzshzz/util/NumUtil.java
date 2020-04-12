package com.zdww.lzshzz.util;

import java.text.DecimalFormat;

/**
 * @author 强周亮
 * @desc 数字转换
 * @email 2538096489@qq.com
 * @time 2018-09-17 16:37
 * @class hzz
 * @package com.gsww.hzz.tools.utils
 */
public class NumUtil {
    /**
     * @param douStr 需要转换的字符串
     * @return 转换后的返回值
     * @desc 将double类型的string转成int
     * @author 强周亮(Qzl)
     * @time 2018-09-17 16:45
     */
    public static int doubleToInt(Object douStr) {
        if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                if (StringHelper.toString(douStr).contains(".")) {
                    String str = StringHelper.toString(douStr).substring(0, StringHelper.toString(douStr).indexOf("."));
                    return Integer.parseInt(str);
                } else {
                    return Integer.parseInt(StringHelper.toString(douStr));
                }
            } catch (Exception e) {
//                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * @param douStr 需要转换的字符串
     * @return 转换后的返回值
     * @desc 将double类型的string转成int
     * @author 强周亮(Qzl)
     * @time 2018-09-17 16:45
     */
    public static String doubleToIntStr(Object douStr) {
        if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                DecimalFormat df = new DecimalFormat("#,###");
                if (StringHelper.toString(douStr).contains(".")) {
                    return df.format(Integer.parseInt(StringHelper.toString(douStr).substring(0, StringHelper.toString(douStr).indexOf("."))));
                } else {
                    return df.format(Integer.parseInt(StringHelper.toString(douStr)));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return StringHelper.toString(douStr);
            }
        } else {
            return "0";
        }
    }

    /**
     * @desc 将double转换为int形的字符串-没有格式
     * @author 强周亮
     * @time 2019-01-03 09:50
     */
    public static String doubleToIntStrNoFormat(Object douStr) {
        if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                return StringHelper.toString(douStr).substring(0, StringHelper.toString(douStr).indexOf("."));
            } catch (Exception e) {
                return StringHelper.toString(douStr);
            }
        } else {
            return "0";
        }
    }

    /**
     * @desc 将double类型的string转成double
     * @author 强周亮
     * @time 2018-10-25 15:08
     */
    public static double doubleStrToDouble(Object douStr) {

        if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                return Double.valueOf(StringHelper.toString(douStr));
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * @desc 计算百分比
     * @author 强周亮
     * @time 2019-01-04 14:12
     */
    public static String percentage(Object a, Object b) {
        Double complate = NumUtil.doubleStrToDouble(a);
        Double all = NumUtil.doubleStrToDouble(b);
        DecimalFormat df = new DecimalFormat("0.00");
        if (all <= 0 || complate <= 0) {
            return "0.00";
        } else {
            return df.format((complate / all) * 100);
        }
    }
}
