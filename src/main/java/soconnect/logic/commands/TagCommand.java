package soconnect.logic.commands;

import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.Model;

/**
 * Contains to command word to separate tag specific commands from other commands.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        throw new ParseException("tag should not be stand alone");
    }
}
