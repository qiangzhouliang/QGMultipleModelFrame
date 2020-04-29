package com.qzl.lzshzz.common.client;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HttpSend {

    private static String host = CONST.HOST;

    private static long time;

    private static String sign;

    private static String appId = CONST.APPID;

    private static String appSecret = CONST.SECRET;

    private static String ver = "1.0";

    private static String nonce = "12312313123123123";

    private static String id = "12345";


    protected static Map<String, Object> paramsInit(Map<String, Object> paramsMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        time = System.currentTimeMillis() / 1000;

        //
        StringBuilder paramString = new StringBuilder();
        List<String> paramList = new ArrayList<String>();
        for (String key : paramsMap.keySet()) {
            paramList.add(key + ":" + paramsMap.get(key));
        }
        /**
         * 为计算签名串，参数按照字母升序排列 第一步：计算“签名原始串 将params部分的deviceId、accessToken、userId、phone
         * 按字母升序排序，按逗号分割组成无空格字符串，并在字符串最后拼接time、nonce、appSecret， 第二步：计算MD5值
         */
        String[] params = paramList.toArray(new String[paramList.size()]);
        Arrays.sort(params);
        for (String param : params) {
            paramString.append(param).append(",");
        }
        paramString.append("time:").append(time).append(",");
        paramString.append("nonce:").append(nonce).append(",");
        paramString.append("appSecret:").append(appSecret);
        sign = null;
        // 计算MD5得值
        try {
            System.out.println("传入参数：" + paramString.toString().trim());
            System.out.println("sign值[" + paramString.toString().trim() + "]");
            sign = DigestUtils.md5Hex(paramString.toString().trim().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> systemMap = new HashMap<String, Object>();
        systemMap.put("ver", ver);
        systemMap.put("sign", sign);
        systemMap.put("appId", appId);
        systemMap.put("nonce", nonce);
        systemMap.put("time", time);
        map.put("system", systemMap);
        map.put("params", paramsMap);
        map.put("id", id);
        return map;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        HttpSend.host = host;
    }

    public static long getTime() {
        return time;
    }

    public static void setTime(long time) {
        HttpSend.time = time;
    }

    public static String getSign() {
        return sign;
    }

    public static void setSign(String sign) {
        HttpSend.sign = sign;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        HttpSend.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        HttpSend.appSecret = appSecret;
    }

    public static String getVer() {
        return ver;
    }

    public static void setVer(String ver) {
        HttpSend.ver = ver;
    }

    public static String getNonce() {
        return nonce;
    }

    public static void setNonce(String nonce) {
        HttpSend.nonce = nonce;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        HttpSend.id = id;
    }

    /**
     * 转为16进制方法
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String paraTo16(String str) throws UnsupportedEncodingException {
        StringBuffer hs = new StringBuffer();

        byte[] byStr = str.getBytes("UTF-8");
        for (int i = 0; i < byStr.length; i++) {
            String temp = "";
            temp = (Integer.toHexString(byStr[i] & 0xFF));
            if (temp.length() == 1) {
                temp = "%0" + temp;
            } else {
                temp = "%" + temp;
            }
            hs.append(temp);
        }
        return hs.toString().toUpperCase();
    }

    public static String postSend(String strUrl, String param) {
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();

            //POST方法时使用
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
