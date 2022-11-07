package seedu.hrpro.testutil;

import seedu.hrpro.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} and {@code String} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PROJECT = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PROJECT = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PROJECT = Index.fromOneBased(3);
    public static final Index INDEX_MAX_PROJECT = Index.fromOneBased(Integer.MAX_VALUE);

    public static final Index INDEX_FIRST_TASK = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_TASK = Index.fromOneBased(3);
    public static final Index INDEX_MAX_TASK = Index.fromOneBased(Integer.MAX_VALUE);

    public static final Index INDEX_FIRST_STAFF = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_STAFF = Index.fromOneBased(2);

    public static final String VALID_INDEX_ONE = "1";
    public static final String VALID_INDEX_MAX = Long.toString(Integer.MAX_VALUE);
    public static final String INVALID_INDEX_MAX_PLUS_ONE = Long.toString(Integer.MAX_VALUE + 1);
}
