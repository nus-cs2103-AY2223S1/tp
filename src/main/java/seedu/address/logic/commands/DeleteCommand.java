package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list "
            + "or by their name.\n"
            + "Parameters: INDEX (must be a positive integer) or " + PREFIX_NAME + "NAME (must be a valid name)\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " " + PREFIX_NAME + "alice";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    private final Name targetName;

    /**
     * @param targetIndex Index of the person in the filtered person list to delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetName = null;
    }

    /**
     * @param targetName Name of the person in the person list to delete
     */
    public DeleteCommand(Name targetName) {
        this.targetName = targetName;
        this.targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        assert (targetIndex != null) || (targetName != null) : "either targetIndex or targetName should not be null";

        if (targetIndex != null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            model.deletePersonReminders(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        } else {
            List<String> nameKeywords = Arrays.asList(targetName.fullName.split("\\s+"));

            List<Person> target = lastShownList.stream().filter(person -> nameKeywords.stream()
                    .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)))
                    .collect(Collectors.toList());
            if (target.size() > 1) {
                throw new CommandException(Messages.MESSAGE_MULTIPLE_PERSON_DISPLAYED_NAME);
            }

            if (target.size() == 0) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
            }

            Person personToDelete = target.get(0);
            model.deletePerson(personToDelete);
            model.deletePersonReminders(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && (targetIndex != null ? targetIndex.equals(((DeleteCommand) other).targetIndex)
                : targetName.equals(((DeleteCommand) other).targetName))); // state check
    }
}
