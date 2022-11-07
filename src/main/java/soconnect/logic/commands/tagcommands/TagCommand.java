package soconnect.logic.commands.tagcommands;

import soconnect.logic.commands.Command;

/**
 * Contains to command word to separate tag specific commands from other commands.
 */
public abstract class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";
}
