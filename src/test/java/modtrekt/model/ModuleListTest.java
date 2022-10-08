package modtrekt.model;

import static modtrekt.testutil.Assert.assertThrows;
import static modtrekt.testutil.TypicalModules.ST2334;
import static modtrekt.testutil.TypicalModules.getTypicalModuleList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modtrekt.model.module.Module;

public class ModuleListTest {

    private final ModuleList moduleList = new ModuleList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), moduleList.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModuleList_replacesData() {
        ModuleList newData = getTypicalModuleList();
        moduleList.resetData(newData);
        assertEquals(newData, moduleList);
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.hasModule(null));
    }

    @Test
    public void hasModule_personNotInModuleList_returnsFalse() {
        assertFalse(moduleList.hasModule(ST2334));
    }

    @Test
    public void hasModule_personInModuleList_returnsTrue() {
        moduleList.addModule(ST2334);
        assertTrue(moduleList.hasModule(ST2334));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> moduleList.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyModuleList whose persons list can violate interface constraints.
     */
    private static class ModuleListStub implements ReadOnlyModuleList {
        private final ObservableList<Module> persons = FXCollections.observableArrayList();

        ModuleListStub(Collection<Module> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return persons;
        }
    }

}
