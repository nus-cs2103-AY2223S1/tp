package seedu.studmap.commons.core.index;

import java.util.List;

import seedu.studmap.model.Model;

/**
 * An IndexListGenerator that generates all the indices of filtered students.
 */
public class AllIndexGenerator extends IndexListGenerator {


    @Override
    public List<Index> apply(Model model) {
        return model.getFilteredStudentIndices();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof AllIndexGenerator)) {
            return false;
        }

        return true;
    }
}
