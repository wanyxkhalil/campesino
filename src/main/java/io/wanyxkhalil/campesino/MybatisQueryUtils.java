package io.wanyxkhalil.campesino;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 将集合转为字符串，用于mybatis查询语句使用集合查询参数的情况
 */
public class MybatisQueryUtils {

    /**
     * 默认为mysql意义的空字符串
     */
    private static final String EMPTY_RESULT = "\'\'";

    /**
     * 数据分隔符
     */
    private static final String DELIMITER = ",";

    /**
     * 转换为查询字符串，数字不添加单引号
     */
    public static <T, E extends Number> String joining4Query(Collection<T> list, Function<T, E> mapFunc) {
        if (isEmpty(list)) {
            return EMPTY_RESULT;
        }

        return list.stream().map(mapFunc).map(Object::toString).collect(Collectors.joining(DELIMITER));
    }

    /**
     * 转换为查询字符串，数字不添加单引号
     */
    public static <E extends Number> String joining4Query(Collection<E> coll) {
        if (isEmpty(coll)) {
            return EMPTY_RESULT;
        }

        return coll.stream().map(Object::toString).collect(Collectors.joining(DELIMITER));
    }

    /**
     * 转换为查询字符串，字符串需添加单引号
     */
    public static String joining4QueryWithString(Collection<String> coll) {
        if (isEmpty(coll)) {
            return EMPTY_RESULT;
        }

        return coll.stream().map(a -> String.format("'%s'", a)).collect(Collectors.joining(DELIMITER));
    }

    /**
     * 判断集合是否为空
     */
    private static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
