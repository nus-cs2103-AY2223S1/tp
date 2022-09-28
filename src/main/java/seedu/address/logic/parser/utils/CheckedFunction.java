package seedu.address.logic.parser.utils;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}
