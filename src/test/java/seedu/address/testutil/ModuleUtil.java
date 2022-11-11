package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_TITLE;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddModuleCommand(Module module) {
        return AddModuleCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE_CODE + module.getModuleCode().value + " ");
        sb.append(PREFIX_MODULE_TITLE + module.getModuleTitle().value + " ");
        return sb.toString();
    }

    /**
     * Returns the command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleCode().ifPresent(
                moduleCode -> sb.append(PREFIX_MODULE_CODE).append(moduleCode.value).append(" "));
        descriptor.getModuleTitle().ifPresent(
                moduleTitle -> sb.append(PREFIX_MODULE_TITLE).append(moduleTitle.value).append(" "));
        return sb.toString();
    }
}
