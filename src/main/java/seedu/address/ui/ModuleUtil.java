package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.model.module.Module;


/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add module command string for adding the {@code module}.
     */
    public static String getAddModuleCommand(Module module) {
        return "m " + AddModuleCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MOD_CODE + module.getModuleCode().moduleCode + " ");
        sb.append(PREFIX_MOD_NAME + module.getModuleName().moduleName + " ");
        sb.append(PREFIX_MOD_CREDIT + module.getModuleCredit().toString() + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleCommand.EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleCode().ifPresent(moduleCode
                -> sb.append(PREFIX_MOD_CODE).append(moduleCode).append(" "));
        descriptor.getModuleName().ifPresent(moduleName
                -> sb.append(PREFIX_MOD_NAME).append(moduleName).append(" "));
        descriptor.getModuleCredit().ifPresent(moduleCredit
                -> sb.append(PREFIX_MOD_CREDIT).append(moduleCredit).append(" "));
        return sb.toString();
    }
}
