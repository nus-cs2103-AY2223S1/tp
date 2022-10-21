package seedu.studmap.commons.core.index;

import java.util.List;

import seedu.studmap.model.Model;

/**
 * An IndexListGenerator that generates a list containing a single index.
 */
public class SingleIndexGenerator extends IndexListGenerator {

    private Index index;

    public SingleIndexGenerator(Index index) {
        this.index = index;
    }

    @Override
    public List<Index> apply(Model model) {
        return List.of(index);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof SingleIndexGenerator)) {
            return false;
        }

        SingleIndexGenerator e = (SingleIndexGenerator) other;
        return this.index.equals(e.index);
    }
}
