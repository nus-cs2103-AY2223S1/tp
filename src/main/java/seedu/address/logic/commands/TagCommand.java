package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        throw new ParseException("tag should be stand alone.");
    }
}
