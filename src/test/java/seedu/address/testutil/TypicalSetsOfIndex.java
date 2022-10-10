package seedu.address.testutil;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Set<Index>} objects to be used in tests.
 */
public class TypicalSetsOfIndex {

    public static final Set<Index> SET_OF_ONE_INDEX = new LinkedHashSet<>(Arrays.asList(INDEX_FIRST_PERSON));
    public static final Set<Index> SET_OF_SECOND_INDEX = new LinkedHashSet<>(Arrays.asList(INDEX_SECOND_PERSON));
}
