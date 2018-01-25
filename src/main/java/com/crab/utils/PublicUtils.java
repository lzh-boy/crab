package com.crab.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;


public class PublicUtils {

    /**
     * checkParameterNotNull
     */
    public static boolean isNull(Object object) {
        boolean result;
        if (object instanceof Collection) {
            result = CollectionUtils.isEmpty(((Collection) object));
        } else if (object instanceof Object[]) {
            result = null == object || (((Object[]) object).length == 0);
        } else if (object instanceof String) {
            result = StringUtils.isEmpty(object);
        } else {
            result = object == null;
        }
        return result;
    }

    public static synchronized String UUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String createComplexCode(Integer length) {
        if (length > 50) {
            length = 50;
        }
        String code = "";
        Random random = new Random();
        while (true) {
            if (code.length() == length) {
                break;
            }
            int nextInteger = random.nextInt(127);
            if (nextInteger < 33 || nextInteger == 92 || nextInteger == 47 || nextInteger == 34) {
                continue;
            }
            char nextChar = (char) nextInteger;
            if (code.indexOf(nextChar) > 0) {
                continue;
            }
            code += nextChar;
        }
        return code;
    }

}
