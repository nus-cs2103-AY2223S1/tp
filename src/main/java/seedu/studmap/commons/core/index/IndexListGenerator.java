package seedu.studmap.commons.core.index;

import java.util.List;
import java.util.function.Function;

import seedu.studmap.model.Model;

/**
 * An abstract class for a functional interface that generates indices.
 */
public abstract class IndexListGenerator implements Function<Model, List<Index>> {
}
