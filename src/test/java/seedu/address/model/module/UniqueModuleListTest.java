package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MODULE_COORDINATOR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2102;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.testutil.ModuleBuilder;

public class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(CS2103T));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.add(CS2103T);
        assertTrue(uniqueModuleList.contains(CS2103T));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleList.add(CS2103T);
        Module editedCS2103T = new ModuleBuilder(CS2103T).withName(VALID_MODULE_NAME_SWE)
                .withTags(VALID_TAG_IMPORTANT).build();
        assertTrue(uniqueModuleList.contains(editedCS2103T));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        uniqueModuleList.add(CS2103T);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(CS2103T));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(null, CS2103T));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(CS2103T, null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.setModule(CS2103T, CS2103T));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.setModule(CS2103T, CS2103T);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2103T);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
        uniqueModuleList.add(CS2103T);
        Module editedCS2103T = new ModuleBuilder(CS2103T).withName(VALID_MODULE_NAME_CYBERSEC)
                .withTags(VALID_TAG_MODULE_COORDINATOR).build();
        uniqueModuleList.setModule(CS2103T, editedCS2103T);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(editedCS2103T);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.setModule(CS2103T, CS2102);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2102);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.add(CS2102);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModule(CS2103T, CS2102));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(CS2103T));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.remove(CS2103T);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((UniqueModuleList) null));
    }

    @Test
    public void setModules_uniqueModulesList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.add(CS2103T);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2102);
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.add(CS2103T);
        List<Module> moduleList = Collections.singletonList(CS2102);
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2102);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(CS2103T, CS2103T);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}
