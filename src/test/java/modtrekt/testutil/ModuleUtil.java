package modtrekt.testutil;

import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CREDIT;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_NAME;

import modtrekt.logic.commands.AddCommand;
import modtrekt.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Module person) {
        return AddCommand.COMMAND_WORD + " " + getModuleDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MOD_NAME + module.getName().fullName + " ");
        sb.append(PREFIX_MOD_CODE + module.getCode().value + " ");
        sb.append(PREFIX_MOD_CREDIT + module.getCredits().value + " ");
        return sb.toString();
    }

}
