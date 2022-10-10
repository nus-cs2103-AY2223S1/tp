package seedu.taassist.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.moduleclass.exceptions.DuplicateModuleClassException;
import seedu.taassist.model.moduleclass.exceptions.ModuleClassNotFoundException;

public class UniqueModuleClassListTest {
    private final UniqueModuleClassList uniqueModuleClassList = new UniqueModuleClassList();

    @Test
    public void contains_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleClassList.contains(null));
    }

    @Test
    public void contains_moduleClassNotInList_returnsFalse() {
        assertFalse(uniqueModuleClassList.contains(CS1101S));
    }

    @Test
    public void contains_moduleClassInList_returnsTrue() {
        uniqueModuleClassList.add(CS1101S);
        assertTrue(uniqueModuleClassList.contains(CS1101S));
    }

    @Test
    public void add_duplicateModuleClass_throwsDuplicateModuleClassException() {
        uniqueModuleClassList.add(CS1101S);
        assertThrows(DuplicateModuleClassException.class, () -> uniqueModuleClassList.add(CS1101S));
    }

    @Test
    public void add_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleClassList.add(null));
    }

    @Test void remove_existingModuleClass_removesModuleClass() {
        uniqueModuleClassList.add(CS1101S);
        uniqueModuleClassList.remove(CS1101S);
        UniqueModuleClassList expectedUniqueModuleClassList = new UniqueModuleClassList();
        assertEquals(expectedUniqueModuleClassList, uniqueModuleClassList);

    }

    @Test
    public void remove_moduleClassDoesNotExist_throwsModuleClassNotFoundException() {
        assertThrows(ModuleClassNotFoundException.class, () -> uniqueModuleClassList.remove(CS1101S));
    }

    @Test
    public void remove_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleClassList.remove(null));
    }

    @Test
    public void setModuleClasses_list_replacesOwnListWithProvidedList() {
        uniqueModuleClassList.add(CS1101S);
        List<ModuleClass> moduleClassList = Collections.singletonList(CS1231S);
        uniqueModuleClassList.setModuleClasses(moduleClassList);
        UniqueModuleClassList expectedUniqueModuleClassList = new UniqueModuleClassList();
        expectedUniqueModuleClassList.add(CS1231S);
        assertEquals(expectedUniqueModuleClassList, uniqueModuleClassList);
    }

    @Test
    public void setModuleClasses_listWithDuplicates_throwsDuplicateModuleClassException() {
        List<ModuleClass> listWithDuplicateModuleClasses = Arrays.asList(CS1101S, CS1101S);
        assertThrows(DuplicateModuleClassException.class, () ->
                uniqueModuleClassList.setModuleClasses(listWithDuplicateModuleClasses));
    }

    @Test
    public void setModuleClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueModuleClassList.setModuleClasses((List<ModuleClass>) null));
    }

    @Test
    public void setModuleClasses_uniqueModuleClassList_replacesOwnListWithProvidedList() {
        uniqueModuleClassList.add(CS1101S);
        UniqueModuleClassList expectedUniqueModuleClassList = new UniqueModuleClassList();
        expectedUniqueModuleClassList.add(CS1231S);
        uniqueModuleClassList.setModuleClasses(expectedUniqueModuleClassList);
        assertEquals(expectedUniqueModuleClassList, uniqueModuleClassList);
    }

    @Test
    public void setModuleClasses_nullUniqueModuleClassList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueModuleClassList.setModuleClasses((UniqueModuleClassList) null));
    }
}
