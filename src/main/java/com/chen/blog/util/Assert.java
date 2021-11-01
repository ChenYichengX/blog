package com.chen.blog.util;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName Assert
 * @Author ChenYicheng
 * @Description 判断是否为空-断言工具类
 * @Date 2021/10/29 11:22
 */
public abstract class Assert {
    public Assert() {
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            if (o instanceof String) {
                String str = (String)o;
                if (str.length() == 0) {
                    return true;
                }
            }

            if (o instanceof Collection) {
                return ((Collection)o).isEmpty();
            } else {
                return o instanceof Map && ((Map)o).size() == 0;
            }
        }
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isNotBlank(Object o) {
        return !isBlank(o);
    }

    public static boolean isBlank(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            String str = (String)o;
            return isBlank(str);
        } else {
            return false;
        }
    }

    public static boolean isBlank(String str) {
        if (str != null && str.length() != 0) {
            for(int i = 0; i < str.length(); ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean equals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            return str1.equals(str2);
        } else {
            return str1 == str2;
        }
    }
}
