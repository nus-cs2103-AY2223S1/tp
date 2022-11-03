package seedu.taassist.testutil;

import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalSessions.TUTORIAL_1;

import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * A utility class containing a list of {@code ModuleClass} objects to be used in tests.
 */
public class TypicalModuleClasses {
    public static final ModuleClass CS1101S =
            new ModuleClassBuilder().withName("CS1101S").withSessions(LAB_1).build();
    public static final ModuleClass CS1231S =
            new ModuleClassBuilder().withName("CS1231S").withSessions(ASSIGNMENT_1, TUTORIAL_1).build();
}
