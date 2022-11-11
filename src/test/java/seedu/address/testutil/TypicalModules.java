package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_5_WITH_HTTPS_WWW;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.Task;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final List<Task> VALID_TASKS = getTypicalTasks();
    public static final Set<Person> VALID_PERSONS = new HashSet<>(getTypicalPersons());

    public static final Module CS2103T = new ModuleBuilder().withModuleCode("CS2103T")
            .withModuleTitle("Software Engineering").build();
    public static final Module GE3238 = new ModuleBuilder().withModuleCode("GE3238")
            .withModuleTitle("GIS Design and Practices")
            .withLinks(new HashSet<Link>(Arrays.asList(
                    new Link(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_URL),
                    new Link(VALID_MODULE_LINK_ALIAS_2, VALID_MODULE_LINK_URL_2),
                    new Link(VALID_MODULE_LINK_ALIAS_5, VALID_MODULE_LINK_URL_5_WITH_HTTPS_WWW))))
            .build();

    public static final Module CS2106 = new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE)
            .withModuleTitle(VALID_CS2106_MODULE_TITLE).build();
    public static final Module MA2001 = new ModuleBuilder().withModuleCode(VALID_MA2001_MODULE_CODE)
            .withModuleTitle(VALID_MA2001_MODULE_TITLE).build();

    public static final Module CS2103T_WITH_TASK_A = new ModuleBuilder().withModuleCode("CS2103T")
            .withTasks(VALID_TASKS.subList(0, 1))
            .withModuleTitle("Software Engineering").build();
    public static final Module CS2106_WITH_TYPICAL_TASKS =
            new ModuleBuilder(CS2106).withTasks(VALID_TASKS).build();

    public static final Module CS2106_WITH_ALICE_BENSON =
            new ModuleBuilder(CS2106).withTasks(VALID_TASKS)
                    .withPersons(new HashSet<>(Arrays.asList(ALICE, BENSON))).build();
    public static final Module MA2001_WITH_BENSON_CARL_DANIEL =
            new ModuleBuilder(MA2001).withTasks(VALID_TASKS)
                    .withPersons(new HashSet<>(Arrays.asList(BENSON, CARL, DANIEL))).build();

    private TypicalModules() {} // prevents instantiation

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2106, MA2001, GE3238));
    }

    public static List<Module> getTypicalModulesWithAssociations() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2106_WITH_ALICE_BENSON,
                MA2001_WITH_BENSON_CARL_DANIEL, GE3238));
    }
}
