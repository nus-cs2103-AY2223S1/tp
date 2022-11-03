package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommandInterface;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Marks a task as complete
 */
public class ForEachTeamCommand extends TeamCommand implements PureCommandInterface {
    public static final String SUBCOMMAND_WORD = "foreach";

    public static final String MESSAGE_USAGE = TeamCommand.getFullCommand(SUBCOMMAND_WORD)
        + "for each task in the current list, execute subsequent commands with that task as context\n"
        + "e.g. " + getFullCommand(SUBCOMMAND_WORD) + "task delete";

    private static final String ON_COMPLETE = "Completed task loop! (failed: %d/%d executions)";

    private final Command nextCmd;

    /**
     * Creates a ForEachTeam Command
     *
     * @param nextCmd next command to be executed
     * @throws ParseException thrown when next command fails to parse properly
     */
    public ForEachTeamCommand(String nextCmd) throws ParseException {
        try {
            this.nextCmd = AddressBookParser.get().parseCommand(nextCmd);
        } catch (ParseException ps) {
            throw new ParseException("Syntax Error");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownList = new ArrayList<>(model.getFilteredTeamList());
        int[] skipped = {0, lastShownList.size()};
        lastShownList.forEach(t -> {
            try {
                nextCmd.setInput(t);
                nextCmd.execute(model);
            } catch (CommandException e) {
                skipped[0]++;
            }
        });
        return new CommandResult(String.format(ON_COMPLETE, skipped[0], skipped[1]));
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        return this;
    }
}
