package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVEMOD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * A command used to add or delete modules from the user or a contact.
 */
public class ModuleCommand extends Command {

    public static final String COMMAND_WORD = "module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds or deletes modules from contact identified by "
            + "the index number used in the displayed person list or user.\n"
            + "Parameters: user / INDEX (must be a positive integer) "
            + "[" + PREFIX_CURRENTMOD + "MOD]..."
            + "[" + PREFIX_PREVIOUSMOD + "MOD]..."
            + "[" + PREFIX_PLANNEDMOD + "MOD]..."
            + "[" + PREFIX_REMOVEMOD + "MOD]...\n"
            + "Example: " + COMMAND_WORD + " user "
            + PREFIX_CURRENTMOD + "CS2101 "
            + PREFIX_CURRENTMOD + "CS2103T "
            + PREFIX_PREVIOUSMOD + "CS2030S "
            + PREFIX_PLANNEDMOD + "CS2109S "
            + PREFIX_PLANNEDMOD + "CS3230 "
            + PREFIX_REMOVEMOD + "ST2334";

    public static final String MESSAGE_MOD_NOT_IN_LIST = "Module to be removed is not in list!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult("");
    }
}
