package io.wanyxkhalil.campesino;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class InvokeUtilsTest {


    private <T, B> void invokeSet(T t, String columnName, B columnValue) {
        try {
            InvokeUtils.invokeSet(t, columnName, columnValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("修改字段反射错误");
        }
    }

    private <T> Object invokeGet(T t, String columnName) {
        try {
            return InvokeUtils.invokeGet(t, columnName);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("读取字段反射错误");
        }
    }

    @Test
    public void test() {
        Info info = new Info();
        invokeSet(info, "a", 1);
        System.out.println(invokeGet(info, "a"));
    }

    private static class Info {
        private Integer a;

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }
    }
}
