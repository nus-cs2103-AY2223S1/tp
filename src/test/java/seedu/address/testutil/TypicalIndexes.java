package seedu.address.testutil;

import java.util.Set;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Set<Index> INDEX_SET_FIRST_PERSON = Set.of(Index.fromOneBased(1));
    public static final Set<Index> INDEX_SET_FIRST_AND_SECOND_PERSON =
            Set.of(Index.fromOneBased(1), Index.fromOneBased(2));
    public static final Set<Index> INDEX_SET_SECOND_AND_FIRST_PERSON =
            Set.of(Index.fromOneBased(2), Index.fromOneBased(1));
}
