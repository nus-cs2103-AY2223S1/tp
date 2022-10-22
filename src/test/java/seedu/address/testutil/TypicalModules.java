package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_2;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.module.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.task.Task;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final List<Task> VALID_TASKS = getTypicalTasks();

    public static final Module CS2103T = new ModuleBuilder().withModuleCode("CS2103T")
            .withModuleTitle("Software Engineering").build();

    public static final Module GE3238 = new ModuleBuilder().withModuleCode("GE3238")
            .withModuleTitle("GIS Design and Practices")
            .withLinks(new HashSet<Link>(Arrays.asList(
                    new Link(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_URL),
                    new Link(VALID_MODULE_LINK_ALIAS_2 , VALID_MODULE_LINK_URL_2))))
            .build();


    // Not inside typical modules
    public static final Module CS2106 = new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE)
            .withModuleTitle(VALID_CS2106_MODULE_TITLE).build();
    public static final Module MA2001 = new ModuleBuilder().withModuleCode(VALID_MA2001_MODULE_CODE)
            .withModuleTitle(VALID_MA2001_MODULE_TITLE).build();
    public static final Module CS2103T_WITH_TASK_A =
            new ModuleBuilder().withModuleCode("CS2103T").withTasks(VALID_TASKS.subList(0, 1))
                    .withModuleTitle("Software Engineering").build();
    public static final Module CS2106_WITH_TASK_A =
            new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).withTasks(VALID_TASKS.subList(0, 1))
            .withModuleTitle(VALID_CS2106_MODULE_TITLE).build();
    public static final Module MA2001_WITH_TASK_A =
            new ModuleBuilder().withModuleCode(VALID_MA2001_MODULE_CODE).withTasks(VALID_TASKS.subList(0, 1))
                    .withModuleTitle(VALID_MA2001_MODULE_TITLE).build();
    private TypicalModules() {} // prevents instantiation

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2106, MA2001, GE3238));
    }
}
