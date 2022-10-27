package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommandInterface;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

//@@author connlim

/**
 * Marks a task as complete
 */
public class SelectTeamCommand extends TeamCommand implements PureCommandInterface {
    public static final String SUBCOMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = TeamCommand.getFullCommand(SUBCOMMAND_WORD)
            + "selects a team and execute subsequent commands with that team as context\n"
            + "e.g. " + getFullCommand(SUBCOMMAND_WORD) + "1 contains description";

    private final Index targetIndex;
    private final Command nextCmd;

    public SelectTeamCommand(Index targetIndex, String nextCmd) throws ParseException {
        this.targetIndex = targetIndex;
        try {
            this.nextCmd = AddressBookParser.get().parseCommand(nextCmd);
        } catch (ParseException e) {
            throw new ParseException("Syntax error parsing select");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownList = model.getFilteredTeamList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Group group = lastShownList.get(targetIndex.getZeroBased());

        nextCmd.setInput(group);
        return nextCmd.execute(model);
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        return;
    }
}
