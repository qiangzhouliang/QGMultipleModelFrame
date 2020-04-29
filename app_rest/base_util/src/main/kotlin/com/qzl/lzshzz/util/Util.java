package com.qzl.lzshzz.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Util {
    /**
     * @param @param  map
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getPostParamter
     * @Description: 获取请求get参数
     * @author 强周亮
     * @date 2018年11月15日 上午10:24:54
     */
    public static String getParamter(Map map) {
        Set keSet = map.entrySet();
        String string = "?";
        for (Iterator itr = keSet.iterator(); itr.hasNext(); ) {
            Map.Entry me = (Map.Entry) itr.next();
            Object ok = me.getKey();
            Object ov = me.getValue();
            String[] value = new String[1];
            if (ov instanceof String[]) {
                value = (String[]) ov;
            } else {
                value[0] = ov.toString();
            }

            for (int k = 0; k < value.length; k++) {
                string += ok + "=" + value[k] + "&";
            }
        }
        return string;
    }

    /**
     * @param @param  inStream
     * @param @return
     * @param @throws IOException    参数
     * @return byte[]    返回类型
     * @throws
     * @Title: input2byte
     * @Description: 字节流转换为byte
     * @author 强周亮
     * @date 2018年11月15日 下午5:11:31
     */
    public static byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * @param @param ins
     * @param @param file    参数
     * @return void    返回类型
     * @throws
     * @Title: inputstreamtofile
     * @Description: 输入流转文件
     * @author 强周亮
     * @date 2018年11月15日 下午5:16:09
     */
    public static void inputstreamtofile(InputStream ins, File file) {
        OutputStream os;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param @param  map
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getParamter
     * @Description: 获取post参数
     * @author 强周亮
     * @date 2018年11月14日 下午8:23:51
     */
    public static MultiValueMap getPostParamter(Map map) {
        Set keSet = map.entrySet();
        MultiValueMap valueMap = new LinkedMultiValueMap();
        for (Iterator itr = keSet.iterator(); itr.hasNext(); ) {
            Map.Entry me = (Map.Entry) itr.next();
            Object ok = me.getKey();
            Object ov = me.getValue();
            String[] value = new String[1];
            if (ov instanceof String[]) {
                value = (String[]) ov;
            } else {
                value[0] = ov.toString();
            }

            for (int k = 0; k < value.length; k++) {
                valueMap.add(ok, value[k]);
            }
        }
        return valueMap;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDirFile(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
        return true;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return true;
    }
}