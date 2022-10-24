package gim.testutil;

import java.util.ArrayList;
import java.util.List;

import gim.commons.core.index.Index;


/**
 * A utility class containing a list of {@code Index} objects, and Index ArrayLists to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_EXERCISE = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_EXERCISE = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_EXERCISE = Index.fromOneBased(3);

    public static final ArrayList<Index> INDEX_LIST_FIRST_SECOND = new ArrayList<>(
            List.of(INDEX_FIRST_EXERCISE, INDEX_SECOND_EXERCISE));

    public static final ArrayList<Index> INDEX_LIST_SECOND_THIRD = new ArrayList<>(
            List.of(INDEX_SECOND_EXERCISE, INDEX_THIRD_EXERCISE));
}
