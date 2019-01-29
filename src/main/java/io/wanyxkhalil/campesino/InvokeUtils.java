package io.wanyxkhalil.campesino;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeUtils {

    public static <T, B> void invokeSet(T t, String columnName, B columnValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = retrieveSetMethodName(columnName);

        Method method = t.getClass().getMethod(methodName, columnValue.getClass());
        method.invoke(t, columnValue);
    }

    public static <T> Object invokeGet(T t, String columnName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = retrieveGetMethodName(columnName);

        Method method = t.getClass().getMethod(methodName);
        return method.invoke(t);
    }


    private static String retrieveSetMethodName(String fieldName) {
        return "set" + firstCharacterToUpper(fieldName);
    }

    private static String retrieveGetMethodName(String fieldName) {
        return "get" + firstCharacterToUpper(fieldName);
    }

    /**
     * 首字母大写
     */
    private static String firstCharacterToUpper(String srcStr) {
        return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
    }

}
