package com.qzl.lzshzz.util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringHelper {

    /**
     * 将字符串数组联合成一个字符串，数组元素中间用分隔符分开
     *
     * @param seperator 分隔符
     * @param strings   字符串数组
     * @return 返回字符串
     */
    public static String join(String seperator, String[] strings) {
        int length = strings.length;
        if (length == 0)
            return "";
        StringBuffer buf = new StringBuffer(length * strings[0].length())
                .append(strings[0]);
        for (int i = 1; i < length; i++) {
            buf.append(seperator).append(strings[i]);
        }
        return buf.toString();
    }

    /**
     * 将字符串重复Copy几次，形成新的字符串
     *
     * @param string 字符串
     * @param times  重复次数
     */
    public static String repeat(String string, int times) {
        StringBuffer buf = new StringBuffer(string.length() * times);
        for (int i = 0; i < times; i++)
            buf.append(string);
        return buf.toString();
    }

    /**
     * 将字符串中的某个字符串替换为另外一个字符串
     *
     * @param template    操作的字符串
     * @param placeholder 被替换的字符串
     * @param replacement 替换的的字符串
     */
    public static String replace(String template, String placeholder,
                                 String replacement) {
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        } else {
            return new StringBuffer(template.substring(0, loc)).append(
                    replacement).append(
                    replace(template.substring(loc + placeholder.length()),
                            placeholder, replacement)).toString();
        }
    }

    /**
     * 将字符串中的某个字符串替换为另外一个字符串，仅被替换一次
     *
     * @param template    操作的字符串
     * @param placeholder 被替换的字符串
     * @param replacement 替换的的字符串
     */

    public static String replaceOnce(String template, String placeholder,
                                     String replacement) {
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        } else {
            return new StringBuffer(template.substring(0, loc)).append(
                    replacement).append(
                    template.substring(loc + placeholder.length())).toString();
        }
    }

    /**
     * 将字符串分割，返回数组
     *
     * @param seperators 分割字符
     * @param list       字符串
     */
    public static String[] split(String seperators, String list) {
        StringTokenizer tokens = new StringTokenizer(list, seperators);
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            result[i++] = tokens.nextToken();
        }
        return result;
    }

    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, ".");
    }

    public static String unqualify(String qualifiedName, String seperator) {
        return qualifiedName
                .substring(qualifiedName.lastIndexOf(seperator) + 1);
    }

    public static String qualifier(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        if (loc < 0) {
            return "";
        } else {
            return qualifiedName.substring(0, loc);
        }
    }

    public static String root(String qualifiedName) {
        int loc = qualifiedName.indexOf(".");
        return (loc < 0) ? qualifiedName : qualifiedName.substring(0, loc);
    }

    public static boolean booleanValue(String tfString) {
        String trimmed = tfString.trim().toLowerCase();
        return "true".equals(trimmed) || "t".equals(trimmed);
    }

    public static String toString(Object[] array) {
        int len = array.length;
        if (len == 0)
            return "";
        StringBuffer buf = new StringBuffer(len * 12);
        for (int i = 0; i < len - 1; i++) {
            buf.append(array[i]).append(", ");
        }
        return buf.append(array[len - 1]).toString();
    }

    public static String[] multiply(String string, Iterator<Object> placeholders,
                                    Iterator<Object> replacements) {
        String[] result = new String[]{string};
        while (placeholders.hasNext()) {
            result = multiply(result, (String) placeholders.next(),
                    (String[]) replacements.next());
        }
        return result;
    }

    private static String[] multiply(String[] strings, String placeholder,
                                     String[] replacements) {
        String[] results = new String[replacements.length * strings.length];
        int n = 0;
        for (int i = 0; i < replacements.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                results[n++] = replaceOnce(strings[j], placeholder,
                        replacements[i]);
            }
        }
        return results;
    }

    /**
     * 取得某一个Char字符在字符串中的个数
     */
    public static int getValueCount(String str, char c) {
        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if (a == c) {
                n++;
            }
        }
        return n;
    }


    public static String toString(Object obj) {
        if (obj == null || "".equals(obj.toString())
                || "null".equals(obj.toString())) {
            return "";
        } else {
            String objValue = obj.toString().trim();
            return objValue;
        }
    }

    /**
     * 截取汉字或汉字、字符混合串的前n位，如果第n位为双字节字符，则截取前n-1位
     *
     * @throws UnsupportedEncodingException
     */
    public static String leftCut(String str, int n) throws UnsupportedEncodingException {
        byte[] b_str = str.getBytes("utf-8");
        byte[] new_str;
        int k;
        if (b_str.length < n) {
            return str;
        } else {
            if (b_str[n] < 0 && b_str[n - 1] > 0) {
                k = n - 1;
            } else {
                k = n;
            }
            new_str = new byte[k];
            for (int i = 0; i < k; i++) {
                new_str[i] = b_str[i];
            }
            return new String(new_str, "utf-8");
        }
    }

    public static String getLimitLengthString(String str, int len) throws UnsupportedEncodingException {
        int counterOfDoubleByte = 0;
        byte b[] = str.getBytes("GBK");
        if (b.length <= len)
            return str;
        for (int i = 0; i < len; i++) {
            if (b[i] < 0)
                counterOfDoubleByte++;
        }
        if (counterOfDoubleByte % 2 == 0)
            return new String(b, 0, len, "GBK") + "...";
        else
            return new String(b, 0, len - 1, "GBK") + "...";
    }

    /**
     * 将 a,b,c,d, 转换成 'a','b','c','d'
     */
    public static String addSingleMark(String strContent) {
        String[] str = StringHelper.split(",", strContent);
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            newStr.append("'" + str[i] + "',");
        }
        return newStr.toString().substring(0, newStr.length() - 1);
    }

    /**
     * 将回车换行符替换成HTML换行符*
     */
    public static String addBr(String Content) {
        StringBuffer makeContent = new StringBuffer();
        StringTokenizer strToken = new StringTokenizer(Content, "\n");
        while (strToken.hasMoreTokens()) {
            makeContent.append("<br>" + strToken.nextToken());
        }
        return makeContent.toString();
    }

    /**
     * 将HTML换行符替换成回车换行符*
     */
    public static String subtractBr(String Content) {
        StringBuffer makeContent = new StringBuffer();
        StringTokenizer strToken = new StringTokenizer(Content, "<br>");
        while (strToken.hasMoreTokens()) {
            makeContent.append("\n" + strToken.nextToken());
        }
        return makeContent.toString();
    }

    /**
     * 检查字符串是否为非零长度的有效字符串
     *
     * @param var 需检查的字符串
     * @return 不为空字符串返回true，否则返回false
     */
    public static boolean checkString(String var) {
        boolean bRtn = true;
        if (var == null) {
            bRtn = false;
        } else {
            if (var.length() < 1)
                bRtn = false;
        }
        return bRtn;
    }

    public static boolean checkLong(String var) {
        boolean bRtn = true;
        try {
            Long.parseLong(var);
            bRtn = true;
        } catch (Exception e) {
            bRtn = false;
        }
        return bRtn;
    }

    public static boolean checkFloat(String var) {
        boolean bRtn = true;
        try {
            /*Float.parseFloat(var);*/
            bRtn = true;
        } catch (Exception e) {
            bRtn = false;
        }
        return bRtn;
    }

    public static boolean checkDouble(String var) {
        boolean bRtn = true;
        try {
            /*Double.parseDouble(var);*/
            bRtn = true;
        } catch (Exception e) {
            bRtn = false;
        }
        return bRtn;
    }

    public static boolean isNumeric(String str) {
        int begin = 0;
        boolean once = true;
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        str = str.trim();
        if (str.startsWith("+") || str.startsWith("-")) {
            if (str.length() == 1) {
                // "+" "-"
                return false;
            }
            begin = 1;
        }
        for (int i = begin; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                if (str.charAt(i) == '.' && once) {
                    // '.' can only once
                    once = false;
                } else {
                    return false;
                }
            }
        }
        if (str.length() == (begin + 1) && !once) {
            // "." "+." "-."
            return false;
        }
        return true;
    }


    /**
     * 检查是否是有效的电子邮件格式
     *
     * @param str
     * @return
     */
    public static boolean checkMail(String str) {
        Pattern p = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否为有效的电话号码
     *
     * @param str
     * @return
     */
    public static boolean checkPhone(String str) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean checkMobile(String str) {
        Pattern p = Pattern.compile("^13\\d{9}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否为有效的身份证号码
     *
     * @param str
     * @return
     */
    public static boolean checkIDCard(String str) {

        class IDCard {
            final int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8,
                    4, 2, 1};

            final int[] vi = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};

            private int[] ai = new int[18];

            public IDCard() {
            }

            public boolean Verify(String idcard) {
                if (idcard.length() == 15) {
                    idcard = uptoeighteen(idcard);
                }
                if (idcard.length() != 18) {
                    return false;
                }
                String verify = idcard.substring(17, 18);
                if (verify.equals(getVerify(idcard))) {
                    return true;
                }
                return false;
            }

            public String getVerify(String eightcardid) {
                int remaining = 0;
                if (eightcardid.length() == 18) {
                    eightcardid = eightcardid.substring(0, 17);
                }
                if (eightcardid.length() == 17) {
                    int sum = 0;
                    for (int i = 0; i < 17; i++) {
                        String k = eightcardid.substring(i, i + 1);
                        ai[i] = Integer.parseInt(k);
                    }
                    for (int i = 0; i < 17; i++) {
                        sum = sum + wi[i] * ai[i];
                    }
                    remaining = sum % 11;
                }
                return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
            }

            public String uptoeighteen(String fifteencardid) {
                String eightcardid = fifteencardid.substring(0, 6);
                eightcardid = eightcardid + "19";
                eightcardid = eightcardid + fifteencardid.substring(6, 15);
                eightcardid = eightcardid + getVerify(eightcardid);
                return eightcardid;
            }

        }

        IDCard idCard = new IDCard();
        return idCard.Verify(str);
    }

    /**
     * 检查是否为有效的网页地址
     *
     * @param str
     * @return
     */
    public static boolean checkURL(String str) {
        Pattern p = Pattern.compile("^[\\w\\.]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean checkURL2(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z]+://([\\w\\-\\+%]+\\.)+[\\w\\-\\+%]+(:\\d+)?(/[\\w\\-\\+%])*(/.*)?$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否为有效的邮政编码
     *
     * @param str
     * @return
     */
    public static boolean checkZIP(String str) {
        Pattern p = Pattern.compile("^\\d{6}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否为有效的客户帐号
     *
     * @param str
     * @return
     */
    public static boolean checkAccount(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){0,30}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否为有效的客户帐号
     *
     * @param str
     * @return
     */
    public static boolean checkID(String str) {
        Pattern p = Pattern.compile("^[0-9]{4,32}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static List<String> getDiff(List<String> list) {
        List<String> result = new ArrayList<String>();
        String temp;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if (isExist(result, temp))
                continue;
            else
                result.add(temp);

        }
        return result;
    }

    private static boolean isExist(List<String> list, String str) {
        for (String temp : list)
            if (str.equals(temp))
                return true;
        return false;
    }

    /**
     * 校验长度小于len的字符串;0,可以为空,1,不能为空*
     */
    public static boolean checkStringLen(String Content, int len, String emptyState) {
        try {
            if (emptyState == null || "0".equals(emptyState)) {
                if (Content == null)
                    return true;
                else if (Content.length() >= len)
                    return false;
                else
                    return true;
            } else if ("1".equals(emptyState)) {
                if (Content == null || "".equals(Content.trim()) || Content.length() >= len)
                    return false;
                else return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证字符串是否为中文字符
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        String strTemp = null;
        try {
            strTemp = new String(str.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //如果值为空，通过校验
        if ("".equals(str))
            return true;
        Pattern p = Pattern.compile("/[^\u4E00-\u9FA5]/g,''");
        Matcher m = p.matcher(strTemp);
        return m.matches();
    }

    /**
     * 检查是否为有效的客户帐号
     *
     * @param str
     * @return
     */
    public static boolean checkEntAccount(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9][\\w]{1,32}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否全部为英文字母
     *
     * @param str
     * @return
     */
    public static boolean checkEn(String str) {
        Pattern p = Pattern.compile("^[A-Za-z]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否为合法的IP
     *
     * @param str
     * @return
     */
    public static boolean checkIp(String str) {
        String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检验输入的值整数位最多6位，小数位最多2位
     */
    public static boolean checkMoney(String str) {
        String money = "^(\\d?\\d?\\d?\\d?\\d?\\d?[.]\\d?\\d?)|(\\d?\\d?\\d?\\d?\\d?\\d?)$";
        Pattern p = Pattern.compile(money);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean chenkbanj(String str) {
        String ints = "^(\\d{1,32})$";
        Pattern p = Pattern.compile(ints);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static boolean checkNumBan(String str) {
        Pattern p = Pattern.compile("^(\\d+,)*\\d*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String showInt(int i) {
        String intStr = String.valueOf(i);
        String[] strArr = intStr.split(",");
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j <= strArr.length; j++) {
            sb.append(strArr[j]);
        }
        return sb.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String splitListToString(String seperators, List list) {
        String temp = list.toString();
        StringBuffer strSb = new StringBuffer();
        String[] arr = split(",", temp);
        for (int i = 0; i < arr.length; i++) {
            strSb.append(arr[i].trim());
        }
        return strSb.toString().substring(1, strSb.length() - 1);
    }

    public static String splitListToStringIncDou(String seperators, String list) {
        String temp = list.toString();
        StringBuffer strSb = new StringBuffer();
        if (temp.indexOf(",") <= 0) {
            return list;
        }
        String[] arr = split(",", temp);
        for (int i = 0; i < arr.length; i++) {
            strSb.append(arr[i].trim()).append("<br/>");
        }
        return strSb.toString().substring(1, strSb.length() - 2);
    }

    /**
     * JAVA判断字符串数组中是否包含某字符串元素
     *
     * @return 包含则返回true，否则返回false
     */
    public static boolean isIn(String substring, String[] source) {
        if (source == null || source.length == 0) {
            return false;
        }
        for (int i = 0; i < source.length; i++) {
            String aSource = source[i];
            if (aSource.equals(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法描述 : 判断非LIST对象是否为空：如：字符串、Map、number、javaBean等。
     *
     * @param obj
     * @return
     */
    public static boolean isEmptyObject(Object obj) {
        if (StringHelper.toString(obj).equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法描述 : 判断List对象是否为空
     *
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmptyList(List list) {
        if (list != null && list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 方法描述 : 判断Map对象是否为空
     *
     * @param Map
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmptyMap(Map map) {
        if (map != null && map.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Object转int
     *
     * @param obj
     * @return int
     */
    public static int convertToInt(Object obj) {
        if (obj == null)
            return 0;
        try {
            int result = Integer.parseInt(obj.toString().trim());
            return result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        String account = "http://gs.bnet.com/aa/";
        System.err.println(checkURL2(account));
    }

    /**
     * 方法描述 : 判断字符串非空。
     *
     * @param obj
     * @return
     */
    public static boolean isEmptyString(String obj) {
        if (obj == null || obj.equals("") || obj.equals("null") || obj.equals("NULL") || obj.equals("undefined")) {
            return true;
        } else {
            return false;
        }
    }
}
