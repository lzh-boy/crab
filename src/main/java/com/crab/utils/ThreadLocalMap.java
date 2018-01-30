package com.crab.utils;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalMap {



    protected static ThreadLocal<Map<String, Object>> threadLocal = new MapThreadLocal();

    protected static Map<String, Object> getThreadLocalContext() {
        Map<String, Object> stringObjectMap = threadLocal.get();
        return stringObjectMap;
    }


    public static void put(String key, Object value) {
        getThreadLocalContext().put(key, value);
    }

    public static void remove(String key) {
        getThreadLocalContext().remove(key);
    }

    public static Object get(String key) {
        return getThreadLocalContext().get(key);
    }

    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
        @Override
        protected Map<String, Object> initialValue() {
//            return super.initialValue();// 父类该方法return null;
            return new HashMap<String ,Object>() {
                @Override
                public Object put(String key, Object value) {
                    return super.put(key, value);
                }
            };
        }
    }
}
