package seedu.taassist.testutil;

import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * A utility class containing a list of {@code ModuleClass} objects to be used in tests.
 */
public class TypicalModuleClasses {
    public static final ModuleClass CS1101S = new ModuleClassBuilder().withName("CS1101S")
            .withSessions("Assignment 1").build();
    public static final ModuleClass CS1231S = new ModuleClassBuilder().withName("CS1231S")
            .withSessions("Tutorial 1").build();

    public static final ModuleClass CS2100 = new ModuleClassBuilder().withName("CS1231S")
            .withSessions("Lab 2", "Assignment 4", "Tutorial 3").build();
}
