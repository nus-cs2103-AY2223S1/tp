package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommandInterface;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Marks a task as complete
 */
public class SelectPersonCommand extends PersonCommand implements PureCommandInterface {
    public static final String SUBCOMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = PersonCommand.getFullCommand(SUBCOMMAND_WORD)
        + "selects a person and execute subsequent commands with that person as context\n"
        + "e.g. " + getFullCommand(SUBCOMMAND_WORD) + "1 contains Description";

    private final Index targetIndex;
    private final Command nextCmd;

    /**
     * Constructor to create a select person command
     */
    public SelectPersonCommand(Index targetIndex, String nextCmd) throws ParseException {
        this.targetIndex = targetIndex;
        try {
            this.nextCmd = AddressBookParser.get().parseCommand(nextCmd);
        } catch (ParseException ps) {
            throw new ParseException("Syntax Error: \n" + ps.getMessage());
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        nextCmd.setInput(model.getFromFilteredPerson(targetIndex));
        return nextCmd.execute(model);
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        return this;
    }
}
