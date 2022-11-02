package modtrekt.testutil;

import modtrekt.logic.commands.CdModuleCommand;

/**
 * Utility class that returns an instance of CdModuleCommand.
 */
public class CdModuleCommandBuilder {

    /**
     * Returns an instance of CdModuleCommand according to the specified attributes.
     */
    public static CdModuleCommand build(String argument) {
        return new CdModuleCommand(argument);
    }
}
