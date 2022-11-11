package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddPersonToModuleCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Name;

/**
 * A utility class for the association between person (identified by name) and module (identified
 * by module code).
 */
public class PersonToModuleUtil {

    /**
     * Returns an add command string for adding the association between the person (identified by
     * {@code name}) and module (identified by {@code moduleCode}).
     */
    public static String getAddPersonToModuleCommand(ModuleCode moduleCode, Name name) {
        return AddPersonToModuleCommand.COMMAND_WORD + " " + getPersonToModuleDetails(moduleCode, name);
    }

    /**
     * Returns the command string for the details of the given association between the person
     * (identified by {@code name}) and module (identified by {@code moduleCode}).
     */
    public static String getPersonToModuleDetails(ModuleCode moduleCode, Name name) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE_CODE + moduleCode.value + " ");
        sb.append(PREFIX_NAME + name.fullName + " ");
        return sb.toString();
    }
}
