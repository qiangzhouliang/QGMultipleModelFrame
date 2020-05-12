package qzl.com.tools.utils


import java.io.UnsupportedEncodingException
import java.util.*
import java.util.regex.Pattern

object StringHelper {

    /**
     * 将字符串数组联合成一个字符串，数组元素中间用分隔符分开
     *
     * @param seperator
     * 分隔符
     * @param strings
     * 字符串数组
     *
     * @return 返回字符串
     */
    fun join(seperator: String, strings: Array<String>): String {
        val length = strings.size
        if (length == 0) {
            return ""
        }
        val buf = StringBuffer(length * strings[0].length)
            .append(strings[0])
        for (i in 1 until length) {
            buf.append(seperator).append(strings[i])
        }
        return buf.toString()
    }

    /**
     * 将字符串重复Copy几次，形成新的字符串
     *
     * @param string
     * 字符串
     * @param times
     * 重复次数
     */
    fun repeat(string: String, times: Int): String {
        val buf = StringBuffer(string.length * times)
        for (i in 0 until times) {
            buf.append(string)
        }
        return buf.toString()
    }

    /**
     * 将字符串中的某个字符串替换为另外一个字符串
     *
     * @param template
     * 操作的字符串
     * @param placeholder
     * 被替换的字符串
     * @param replacement
     * 替换的的字符串
     */
    fun replace(template: String, placeholder: String, replacement: String): String {
        val loc = template.indexOf(placeholder)
        return if (loc < 0) {
            template
        } else {
            StringBuffer(template.substring(0, loc)).append(replacement)
                .append(replace(template.substring(loc + placeholder.length), placeholder, replacement)
            ).toString()
        }
    }

    /**
     * 将字符串中的某个字符串替换为另外一个字符串，仅被替换一次
     *
     * @param template
     * 操作的字符串
     * @param placeholder
     * 被替换的字符串
     * @param replacement
     * 替换的的字符串
     */
    fun replaceOnce(template: String, placeholder: String, replacement: String): String {
        val loc = template.indexOf(placeholder)
        return if (loc < 0) {
            template
        } else {
            StringBuffer(template.substring(0, loc)).append(replacement)
                .append(template.substring(loc + placeholder.length)).toString()
        }
    }

    /**
     * 将字符串分割，返回数组
     *
     * @param seperators
     * 分割字符
     * @param list
     * 字符串
     */
    fun split(seperators: String, list: String): Array<String?> {
        val tokens = StringTokenizer(list, seperators)
        val result = arrayOfNulls<String>(tokens.countTokens())
        var i = 0
        while (tokens.hasMoreTokens()) {
            result[i++] = tokens.nextToken()
        }
        return result
    }

    @JvmOverloads
    fun unqualify(qualifiedName: String, seperator: String = "."): String {
        return qualifiedName
            .substring(qualifiedName.lastIndexOf(seperator) + 1)
    }

    fun qualifier(qualifiedName: String): String {
        val loc = qualifiedName.lastIndexOf(".")
        return if (loc < 0) {
            ""
        } else {
            qualifiedName.substring(0, loc)
        }
    }

    fun root(qualifiedName: String): String {
        val loc = qualifiedName.indexOf(".")
        return if (loc < 0) qualifiedName else qualifiedName.substring(0, loc)
    }

    fun booleanValue(tfString: String): Boolean {
        val trimmed = tfString.trim { it <= ' ' }.toLowerCase()
        return "true" == trimmed || "t" == trimmed
    }

    fun toString(array: Array<Any>): String {
        val len = array.size
        if (len == 0) {
            return ""
        }
        val buf = StringBuffer(len * 12)
        for (i in 0 until len - 1) {
            buf.append(array[i]).append(", ")
        }
        return buf.append(array[len - 1]).toString()
    }

    /**
     * 取得某一个Char字符在字符串中的个数
     *
     */
    fun getValueCount(str: String, c: Char): Int {
        var n = 0
        for (i in 0 until str.length) {
            val a = str[i]
            if (a == c) {
                n++
            }
        }
        return n
    }


    fun toString(obj: Any?): String {
        return if (obj == null || "" == obj.toString() || "null" == obj.toString()) {
            ""
        } else {
            obj.toString().trim { it <= ' ' }
        }
    }

    /** 截取汉字或汉字、字符混合串的前n位，如果第n位为双字节字符，则截取前n-1位
     * @throws UnsupportedEncodingException
     */
    @Throws(UnsupportedEncodingException::class)
    fun leftCut(str: String, n: Int): String {
        val b_str = str.toByteArray(charset("utf-8"))
        val new_str: ByteArray
        val k: Int
        if (b_str.size < n) {
            return str
        } else {
            if (b_str[n] < 0 && b_str[n - 1] > 0) {
                k = n - 1
            } else {
                k = n
            }
            new_str = ByteArray(k)
            for (i in 0 until k) {
                new_str[i] = b_str[i]
            }
            return String(new_str, charset("utf-8"))
        }
    }

    @Throws(UnsupportedEncodingException::class)
    fun getLimitLengthString(str: String, len: Int): String {
        var counterOfDoubleByte = 0
        val b = str.toByteArray(charset("GBK"))
        if (b.size <= len) {
            return str
        }
        for (i in 0 until len) {
            if (b[i] < 0) {
                counterOfDoubleByte++
            }
        }
        return if (counterOfDoubleByte % 2 == 0) {
            String(b, 0, len, charset("GBK")) + "..."
        } else {
            String(b, 0, len - 1, charset("GBK")) + "..."
        }
    }

    /** 将 a,b,c,d, 转换成 'a','b','c','d'  */
    fun addSingleMark(strContent: String): String {
        val str = StringHelper.split(",", strContent)
        var newStr = ""
        for (i in str.indices) {
            newStr += "'" + str[i] + "',"
        }
        newStr = newStr.substring(0, newStr.length - 1)
        return newStr
    }

    /** 将回车换行符替换成HTML换行符*  */
    fun addBr(Content: String): String {
        val makeContent = StringBuilder()
        val strToken = StringTokenizer(Content, "\n")
        while (strToken.hasMoreTokens()) {
            makeContent.append("<br>" + strToken.nextToken())
        }
        return makeContent.toString()
    }

    /** 将HTML换行符替换成回车换行符*  */
    fun subtractBr(Content: String): String {
        val makeContent = StringBuilder()
        val strToken = StringTokenizer(Content, "<br>")
        while (strToken.hasMoreTokens()) {
            makeContent.append("\n" + strToken.nextToken())
        }
        return makeContent.toString()
    }

    /**
     * 检查字符串是否为非零长度的有效字符串
     *
     * @param var
     * 需检查的字符串
     * @return 不为空字符串返回true，否则返回false
     */
    fun checkString(`var`: String?): Boolean {
        var bRtn = true
        if (`var` == null) {
            bRtn = false
        } else {
            if (`var`.length < 1) {
                bRtn = false
            }
        }
        return bRtn
    }

    /**
     * 检查字符串是否是合法整数
     *
     * @param var
     * 传入需要检查的字符串
     * @return 如果为合法整数返回true，否则返回false
     */
    fun checkInt(`var`: String): Boolean {
        var bRtn = true
        try {
            if (Integer.parseInt(`var`) > Integer.MAX_VALUE || Integer.parseInt(`var`) < Integer.MIN_VALUE) {
                bRtn = false
            }
        } catch (e: Exception) {
            bRtn = false
        }

        return bRtn
    }

    fun checkLong(`var`: String): Boolean {
        var bRtn = true
        bRtn = try {
            java.lang.Long.parseLong(`var`)
            true
        } catch (e: Exception) {
            false
        }

        return bRtn
    }

    fun checkFloat(`var`: String): Boolean {
        var bRtn = true
        bRtn = try {
            java.lang.Float.parseFloat(`var`)
            true
        } catch (e: Exception) {
            false
        }

        return bRtn
    }

    fun checkDouble(`var`: String): Boolean {
        var bRtn = true
        bRtn = try {
            java.lang.Double.parseDouble(`var`)
            true
        } catch (e: Exception) {
            false
        }

        return bRtn
    }

    fun isNumeric(str: String?): Boolean {
        var str = str
        var begin = 0
        var once = true
        if (str == null || "" == str.trim { it <= ' ' }) {
            return false
        }
        str = str.trim { it <= ' ' }
        if (str.startsWith("+") || str.startsWith("-")) {
            if (str.length == 1) {
                // "+" "-"
                return false
            }
            begin = 1
        }
        for (i in begin until str.length) {
            if (!Character.isDigit(str[i])) {
                if (str[i] == '.' && once) {
                    // '.' can only once
                    once = false
                } else {
                    return false
                }
            }
        }
        return !(str.length == begin + 1 && !once)
    }


    /**
     * 检查是否是有效的电子邮件格式
     * @param str
     * @return
     */
    fun checkMail(str: String): Boolean {
        val p = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*")
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 检查是否为有效的电话号码
     * @param str
     * @return
     */
    fun checkPhone(str: String): Boolean {
        val p = Pattern.compile("^(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})?(-\\d+)?$")
        val m = p.matcher(str)
        return m.matches()
    }

    fun checkMobile(str: String): Boolean {
        val p = Pattern.compile("^13\\d{9}$")
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 检查是否为有效的身份证号码
     * @param str
     * @return
     */
    fun checkIDCard(str: String): Boolean {

        class IDCard {
            val wi = intArrayOf(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1)

            val vi = intArrayOf(1, 0, 'X'.toInt(), 9, 8, 7, 6, 5, 4, 3, 2)

            private val ai = IntArray(18)

            fun Verify(idcard: String): Boolean {
                var idcard = idcard
                if (idcard.length == 15) {
                    idcard = uptoeighteen(idcard)
                }
                if (idcard.length != 18) {
                    return false
                }
                val verify = idcard.substring(17, 18)
                return verify == getVerify(idcard)
            }

            fun getVerify(eightcardid: String): String {
                var eightcardid = eightcardid
                var remaining = 0
                if (eightcardid.length == 18) {
                    eightcardid = eightcardid.substring(0, 17)
                }
                if (eightcardid.length == 17) {
                    var sum = 0
                    for (i in 0..16) {
                        val k = eightcardid.substring(i, i + 1)
                        ai[i] = Integer.parseInt(k)
                    }
                    for (i in 0..16) {
                        sum = sum + wi[i] * ai[i]
                    }
                    remaining = sum % 11
                }
                return if (remaining == 2) "X" else vi[remaining].toString()
            }

            fun uptoeighteen(fifteencardid: String): String {
                var eightcardid = fifteencardid.substring(0, 6)
                eightcardid = eightcardid + "19"
                eightcardid = eightcardid + fifteencardid.substring(6, 15)
                eightcardid = eightcardid + getVerify(eightcardid)
                return eightcardid
            }

        }

        val idCard = IDCard()
        return idCard.Verify(str)
    }

    /**
     * 检查是否为有效的网页地址
     * @param str
     * @return
     */
    fun checkURL(str: String): Boolean {
        val p = Pattern.compile("^[\\w\\.]+$")
        val m = p.matcher(str)
        return m.matches()
    }

    fun checkURL2(str: String): Boolean {
        val p = Pattern.compile("^[a-zA-Z]+://([\\w\\-\\+%]+\\.)+[\\w\\-\\+%]+(:\\d+)?(/[\\w\\-\\+%])*(/.*)?$")
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 检查是否为有效的邮政编码
     * @param str
     * @return
     */
    fun checkZIP(str: String): Boolean {
        val p = Pattern.compile("^\\d{6}$")
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 检查是否为有效的客户帐号
     * @param str
     * @return
     */
    fun checkAccount(str: String): Boolean {
        val p = Pattern.compile("^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){0,30}$")
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 检查是否为有效的客户帐号
     * @param str
     * @return
     */
    fun checkID(str: String): Boolean {
        val p = Pattern.compile("^[0-9]{4,32}$")
        val m = p.matcher(str)
        return m.matches()
    }

    fun getDiff(list: List<String>): List<String> {
        val result = ArrayList<String>()
        var temp: String
        for (i in list.indices) {
            temp = list[i]
            if (isExist(result, temp))
                continue
            else
                result.add(temp)

        }
        return result
    }

    private fun isExist(list: List<String>, str: String): Boolean {
        for (temp in list)
            if (str == temp)
                return true
        return false
    }

    /** 校验长度小于len的字符串;0,可以为空,1,不能为空*  */
    fun checkStringLen(Content: String?, len: Int, emptyState: String?): Boolean {
        return try {
            if (emptyState == null || "0" == emptyState) {
                when {
                    Content == null -> true
                    Content.length >= len -> false
                    else -> true
                }
            } else if ("1" == emptyState) {
                !(Content == null || "" == Content.trim { it <= ' ' } || Content.length >= len)
            } else
                false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }

    /**
     * 验证字符串是否为中文字符
     * @param str
     * @return
     */
    fun isChinese(str: String): Boolean {
        var strTemp: String? = null
        try {
            strTemp = String(str.toByteArray(charset("ISO-8859-1")), charset("GBK"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        //如果值为空，通过校验
        if ("" == str)
            return true
        val p = Pattern.compile("/[^\u4E00-\u9FA5]/g,''")
        val m = p.matcher(strTemp)
        return m.matches()
    }

    /**
     * 检查是否为有效的客户帐号
     * @param str
     * @return
     */
    fun checkEntAccount(str: String): Boolean {
        val p = Pattern.compile("^[a-zA-Z0-9][\\w]{1,32}$")
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 检查是否全部为英文字母
     * @param str
     * @return
     */
    fun checkEn(str: String): Boolean {
        val p = Pattern.compile("^[A-Za-z]+$")
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 检查是否为合法的IP
     * @param str
     * @return
     */
    fun checkIp(str: String): Boolean {
        val regex =
            "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))"
        val p = Pattern.compile(regex)
        val m = p.matcher(str)
        return m.matches()
    }

    /**检验输入的值整数位最多6位，小数位最多2位 */
    fun checkMoney(str: String): Boolean {
        val money = "^(\\d?\\d?\\d?\\d?\\d?\\d?[.]\\d?\\d?)|(\\d?\\d?\\d?\\d?\\d?\\d?)$"
        val p = Pattern.compile(money)
        val m = p.matcher(str)
        return m.matches()
    }

    fun chenkbanj(str: String): Boolean {
        val ints = "^(\\d{1,32})$"
        val p = Pattern.compile(ints)
        val m = p.matcher(str)
        return m.matches()
    }


    fun checkNumBan(str: String): Boolean {
        val p = Pattern.compile("^(\\d+,)*\\d*$")
        val m = p.matcher(str)
        return m.matches()
    }

    fun showInt(i: Int): String {
        val intStr = i.toString()
        val strArr = intStr.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuffer()
        for (j in 0..strArr.size) {
            sb.append(strArr[j])
        }
        return sb.toString()
    }

    fun splitListToString(seperators: String, list: List<*>): String {
        val temp = list.toString()
        val strSb = StringBuffer()
        val arr = split(",", temp)
        for (i in arr.indices) {
            strSb.append(arr[i]?.trim { it <= ' ' })
        }
        return strSb.toString().substring(1, strSb.length - 1)
    }

    fun splitListToStringIncDou(seperators: String, list: String): String {
        val strSb = StringBuffer()
        if (list.indexOf(",") <= 0) {
            return list
        }
        val arr = split(",", list)
        for (i in arr.indices) {
            strSb.append(arr[i]?.trim { it <= ' ' }).append("<br/>")
        }
        return strSb.toString().substring(1, strSb.length - 2)
    }

    /**
     * JAVA判断字符串数组中是否包含某字符串元素
     * @return 包含则返回true，否则返回false
     */
    fun isIn(substring: String, source: Array<String>?): Boolean {
        if (source == null || source.isEmpty()) {
            return false
        }
        for (i in source.indices) {
            val aSource = source[i]
            if (aSource == substring) {
                return true
            }
        }
        return false
    }


    /**
     * 方法描述 : 判断List对象是否为空
     * @param list
     * @return
     */
    fun isEmptyList(list: List<*>?): Boolean {
        return !(list != null && list.isNotEmpty())
    }

    /**
     * 方法描述 : 判断Map对象是否为空
     * @param map
     * @return
     */
    fun isEmptyMap(map: Map<*, *>?): Boolean {
        return !(map != null && map.isNotEmpty())
    }

    /**
     * Object转int
     * @param obj
     * @return int
     */
    fun convertToInt(obj: Any?): Int {
        if (obj == null)
            return 0
        return try {
            Integer.parseInt(obj.toString().trim { it <= ' ' })
        } catch (e: NumberFormatException) {
            0
        }

    }

    @JvmStatic
    fun main(args: Array<String>) {
        val account = "http://gs.bnet.com/aa/"
        System.err.println(checkURL2(account))
    }

    /**
     * 方法描述 : 判断字符串非空。
     * @param obj
     * @return
     */
    @JvmStatic
    fun isEmptyString(obj: String?): Boolean {
        return obj == null || "" == obj || "null" == obj || "NULL" == obj || "undefined" == obj
    }
}
