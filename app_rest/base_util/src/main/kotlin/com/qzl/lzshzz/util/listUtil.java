/**
 * @Title: listUtil.java
 * @Package com.gsww.util
 * @author 强周亮
 * @date 2019年1月8日 上午11:18:49
 * @version V1.0
 */
package com.qzl.lzshzz.util;

import java.util.HashSet;
import java.util.List;

/**
 * @author 强周亮
 * @ClassName: listUtil
 * @Description:
 * @date 2019年1月8日 上午11:18:49
 */
public class listUtil {
    /**
     * @Title: removeDuplicate
     * @Description: list去重
     * @author 强周亮
     * @date 2019年1月8日 上午11:19:14
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
