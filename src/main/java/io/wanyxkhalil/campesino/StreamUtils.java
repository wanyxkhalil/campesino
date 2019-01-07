package io.wanyxkhalil.campesino;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 常用语句
 */
public class StreamUtils {

    /**
     * 转map，重复key时使用旧值
     */
    public static <T, A, B> Map<A, B> toMap(Collection<T> collection, Function<T, A> funcA, Function<T, B> funcB) {
        return toMap(collection.stream(), funcA, funcB);
    }

    /**
     * 转map，重复key时使用旧值
     */
    public static <T, A, B> Map<A, B> toMap(T[] arr, Function<T, A> funcA, Function<T, B> funcB) {
        return toMap(Arrays.stream(arr), funcA, funcB);
    }

    /**
     * 转map，重复key时使用旧值
     */
    public static <T, A, B> Map<A, B> toMap(Stream<T> stream, Function<T, A> funcA, Function<T, B> funcB) {
        return stream.collect(Collectors.toMap(funcA, funcB, (o, n) -> o));
    }
}
