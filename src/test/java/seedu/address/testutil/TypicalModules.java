package seedu.address.testutil;


import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_DESCRIPTION_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_DESCRIPTION_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MODULE_COORDINATOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2102 = new ModuleBuilder().withName("Database Systems")
            .withModuleCode("CS2102").withModuleDescription("Management of databases")
            .withTags("important").build();
    public static final Module CS2106 = new ModuleBuilder().withName("Intro to OS")
            .withModuleCode("CS2106").withModuleDescription("Learn about OSes")
            .withTags("important", "ModuleCoordinator").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module CS2103T = new ModuleBuilder().withName(VALID_MODULE_NAME_SWE)
            .withModuleCode(VALID_MODULE_CODE_SWE)
            .withModuleDescription(VALID_MODULE_DESCRIPTION_SWE)
            .withTags(VALID_TAG_IMPORTANT).build();
    public static final Module CS2107 = new ModuleBuilder().withName(VALID_MODULE_NAME_CYBERSEC)
            .withModuleCode(VALID_MODULE_CODE_CYBERSEC)
            .withModuleDescription(VALID_MODULE_DESCRIPTION_CYBERSEC)
            .withTags(VALID_TAG_IMPORTANT, VALID_TAG_MODULE_COORDINATOR).build();

    private TypicalModules() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical modules.
     */
    public static AddressBook getTypicalAddressBookWithModules() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2102, CS2106, CS2103T, CS2107));
    }
}
