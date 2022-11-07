package modtrekt.testutil;

import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CREDIT;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_NAME;

import modtrekt.logic.commands.AddModuleCommand;
import modtrekt.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddModuleCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MOD_NAME + module.getName().getFullName() + " ");
        sb.append(PREFIX_MOD_CODE + module.getCode().getValue() + " ");
        sb.append(PREFIX_MOD_CREDIT + module.getCredits().getValue() + " ");
        return sb.toString();
    }

}
