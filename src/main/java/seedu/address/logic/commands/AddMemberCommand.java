package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person identified using it's displayed index to the current team.
 */
@CommandLine.Command(name = "member")
public class AddMemberCommand extends Command {

    public static final String COMMAND_WORD = "add member";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a person identified using it's displayed index to the current team.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ADD_MEMBER_SUCCESS = "Added Member: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the team";

    @CommandLine.Parameters(arity = "1")
    private Index targetIndex;

    public AddMemberCommand() {
    }

    public AddMemberCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person toAdd = lastShownList.get(targetIndex.getZeroBased());

        if (model.getTeam().hasMember(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.getTeam().addMember(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_MEMBER_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMemberCommand // instanceof handles nulls
                && targetIndex.equals(((AddMemberCommand) other).targetIndex)); // state check
    }

}
