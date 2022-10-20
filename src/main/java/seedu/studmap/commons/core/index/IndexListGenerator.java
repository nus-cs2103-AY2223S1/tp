package seedu.studmap.commons.core.index;

import java.util.List;
import java.util.function.Function;

import seedu.studmap.model.Model;

/**
 * An abstract class for a functional interface that generates indices.
 */
public abstract class IndexListGenerator implements Function<Model, List<Index>> {
    @Override
    public <V> Function<V, List<Index>> compose(Function<? super V, ? extends Model> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Model, V> andThen(Function<? super List<Index>, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
