package common.porval.com;

/**
 * Created by jiangpeng on 15/7/22.
 *
 */
public class StringUtils {
    /**
     * 判断n个字符串均不为空
     *
     * @param args 要判断的字符串
     * @return 有任何一个字符串为空，会返回false。全部不为空则返回true。
     */
    public static boolean isNotEmpty(CharSequence... args) {
        if (args != null) {
            for (CharSequence text : args) {
                if (isEmpty(text)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 判断给定的字符串是否为空。
     *
     * @param text 要判断的字符串
     * @return 给定的字符串为空时返回true。否则返回false。
     */
    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }

    /**
     * 比较两个字符串是否相等，同时检查null字符串，保证不会崩溃
     *
     * @param text1
     * @param text2
     * @return
     */
    public static boolean equals(CharSequence text1, CharSequence text2) {
        if (text1 == null || text2 == null) {
            return (text1 == null && text2 == null);
        } else {
            return text1.toString().equals(text2.toString());
        }
    }

    /**
     * 比较两个字符串是否相等（忽略大小写），同时检查null字符串，保证不会崩溃
     *
     * @param text1
     * @param text2
     * @return
     */
    public static boolean equalsIgnoreCase(CharSequence text1, CharSequence text2) {
        if (text1 == null || text2 == null) {
            return (text1 == null && text2 == null);
        } else {
            return text1.toString().equalsIgnoreCase(text2.toString());
        }
    }

    /**
     * 去掉字符串首尾的空格
     *
     * @param str
     * @return
     */
    public static CharSequence trim(CharSequence str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }

        return str.toString().trim();
    }

    /**
     * 忽略大小写，包含关系
     * 如果存在null,默认返回false
     *
     * @param str
     * @param key
     * @return
     */
    public static boolean containsIgnoreCase(String str, String key) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(key)) {
            return false;
        }

        return str.toLowerCase().contains(key.toLowerCase());
    }

    /**
     * 判断某一个字符串里边是否含有换行符
     *
     * @param str 要判断的字符串
     * @return 如果含有换行符返回true。否则返回false。
     */
    public static boolean containsLineBreak(String str) {
        return isNotEmpty(str) && (str.contains("\n") || str.contains("\r"));
    }

    /**
     * 判断某一个字符串里边是否含有参数里边指明的所有子串
     *
     * @param findFrom
     * @param args
     * @return
     */
    public static boolean containsAll(String findFrom, CharSequence... args) {
        if (isEmpty(findFrom)) {
            return false;
        }

        if (args != null) {
            for (CharSequence arg : args) {
                if (!findFrom.contains(arg)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 检查query是否在args中
     *
     * @param query 要查询的字符串
     * @param list  被查询的字符串列表
     * @return 如果query在list之中，返回true。否则返回false。
     */
    public static boolean inList(CharSequence query, CharSequence... list) {
        for (CharSequence arg : list) {
            if (StringUtils.equals(query, arg)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 将一个字符串转换成整数。如果字符串不能够顺利转换，返回0
     *
     * @param str 要转换的字符串
     * @return 转换成的整数
     */
    public static int toInt(CharSequence str) {
        try {
            return Integer.valueOf(String.valueOf(str));
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 将一个字符串转换成Float。如果字符串不能够顺利转换，返回0
     *
     * @param str 要转换的字符串
     * @return 转换成的Float
     */
    public static float toFloat(String str) {
        try {
            return Float.valueOf(str);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 判断一个字符串是不是一个数字。
     *
     * @param str 要判断的字符串
     * @return 如果是数字返回true，否则返回false。
     */
    public static boolean isNumber(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
