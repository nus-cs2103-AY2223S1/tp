package seedu.waddle.testutil;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.core.index.MultiIndex;

/**
 * Represents a series of MultiIndex objects.
 */
public class TypicalMultiIndexes {
    public static final MultiIndex MULTI_INDEX_FIRST_UNSCHEDULED_ITEM =
            new MultiIndex().addIndex(Index.fromOneBased(1));
    public static final MultiIndex MULTI_INDEX_SECOND_UNSCHEDULED_ITEM =
            new MultiIndex().addIndex(Index.fromOneBased(2));
    public static final MultiIndex MULTI_INDEX_THIRD_UNSCHEDULED_ITEM =
            new MultiIndex().addIndex(Index.fromOneBased(3));
    public static final MultiIndex MULTI_INDEX_FIRST_DAY_FIRST_ITEM =
            new MultiIndex().addIndex(Index.fromOneBased(1)).addIndex(Index.fromOneBased(1));
    public static final MultiIndex MULTI_INDEX_FIRST_DAY_SECOND_ITEM =
            new MultiIndex().addIndex(Index.fromOneBased(1)).addIndex(Index.fromOneBased(2));
    public static final MultiIndex MULTI_INDEX_SECOND_DAY_FIRST_ITEM =
            new MultiIndex().addIndex(Index.fromOneBased(2)).addIndex(Index.fromOneBased(1));
}
