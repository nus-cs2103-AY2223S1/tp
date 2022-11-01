package seedu.address.testutil;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_FIRST_TASK = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);
    public static final Index INDEX_UNMARKED_TASK = Index.fromOneBased(1);
    public static final Index INDEX_MARKED_TASK = Index.fromOneBased(6);
    public static final Index INDEX_LINKED_TASK = Index.fromOneBased(4);
    public static final Index INDEX_PRIORITY_TAG_TASK = Index.fromOneBased(7);
    public static final Index INDEX_DEADLINE_TAG_TASK = Index.fromOneBased(8);

    public static final Index INDEX_LAST_TASK = Index.fromOneBased(9);

}
