package coydir.testutil;

import coydir.commons.core.index.Index;
import coydir.model.person.EmployeeId;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final EmployeeId ID_FIRST_EMPLOYEE = new EmployeeId("1");
    public static final EmployeeId ID_SECOND_EMPLOYEE = new EmployeeId("2");

}
