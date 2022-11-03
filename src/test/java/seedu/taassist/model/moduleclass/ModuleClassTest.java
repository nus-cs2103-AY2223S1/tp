package seedu.taassist.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.UniqueList;

public class ModuleClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        String validModuleClassName = "CS1101S";
        List<Session> validSessions = Arrays.asList(ASSIGNMENT_1);

        // null name
        assertThrows(NullPointerException.class, () -> new ModuleClass(null));

        // null session
        assertThrows(NullPointerException.class, () -> new ModuleClass(validModuleClassName,
                (UniqueList<Session>) null));

        // null name
        assertThrows(NullPointerException.class, () -> new ModuleClass(null, validSessions));
    }

    @Test
    public void constructor_invalidModuleClassName_throwsIllegalArgumentException() {
        String invalidModuleClassName = "+moduleClass*";
        assertThrows(IllegalArgumentException.class, () -> new ModuleClass(invalidModuleClassName));
    }

    @Test
    public void isValidModuleClassName() {
        // null module class name
        assertThrows(NullPointerException.class, () -> ModuleClass.isValidModuleClassName(null));

        // special characters are not allowed
        assertFalse(ModuleClass.isValidModuleClassName("~CS1101S!+"));

        // whitespaces only is not allowed
        assertFalse(ModuleClass.isValidModuleClassName("  "));

        // class names should not begin with whitespace
        assertFalse(ModuleClass.isValidModuleClassName(" CS1101S"));

        // empty name is not allowed
        assertFalse(ModuleClass.isValidModuleClassName(""));
    }

    @Test
    public void isSame_sameModuleClassName_returnsTrue() {
        // same object -> returns true
        assertTrue(CS1101S.isSame(CS1101S));

        // same name -> returns true
        ModuleClass cs1101sCopy = new ModuleClass(CS1101S.getClassName());
        assertTrue(cs1101sCopy.isSame(CS1101S));
    }

    @Test
    public void isSame_differentModuleClassName_returnsFalse() {
        assertFalse(CS1231S.isSame(CS1101S));
    }

    @Test
    public void equals_sameModuleClassNameAndSession_returnsTrue() {
        // same object -> returns true
        assertTrue(CS1101S.equals(CS1101S));

        // same name and sessions -> returns true
        ModuleClass cs1101sCopy = new ModuleClass(CS1101S.getClassName(), CS1101S.getSessions());
        assertTrue(cs1101sCopy.equals(CS1101S));
    }
}
