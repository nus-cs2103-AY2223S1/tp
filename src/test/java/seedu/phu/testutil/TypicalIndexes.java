package seedu.phu.testutil;

import java.util.Set;

import seedu.phu.commons.core.index.Index;
import seedu.phu.commons.core.index.Indexes;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_INTERNSHIP = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_INTERNSHIP = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_INTERNSHIP = Index.fromOneBased(3);
    public static final Indexes INDEXES_FIRST_INTERNSHIP =
            new Indexes(Set.of(Index.fromOneBased(1)));
    public static final Indexes INDEXES_FIRST_AND_SECOND_INTERNSHIP =
            new Indexes(Set.of(Index.fromOneBased(1), Index.fromOneBased(2)));
    public static final Indexes INDEXES_SECOND_AND_FIRST_INTERNSHIP =
            new Indexes(Set.of(Index.fromOneBased(2), Index.fromOneBased(1)));
}
