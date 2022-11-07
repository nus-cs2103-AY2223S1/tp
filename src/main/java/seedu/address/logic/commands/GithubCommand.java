package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Person;

/**
 * Opens Github User profile page on browser.
 */
public class GithubCommand extends Command {

    public static final String COMMAND_WORD = "github";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens up Github Profile page associated to index specified "
            + "address in default browser.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_GITHUB_PERSON_SUCCESS = "Opening Person's Github Profile Page\n";

    public static final String MESSAGE_NO_GITHUB_ERROR = "There is no github username associated with this Person!";

    private final Index targetIndex;

    public GithubCommand(Index index) {
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personOfInterest = lastShownList.get(targetIndex.getZeroBased());
        GithubUsername emptyUsername = new GithubUsername("", false);
        if (personOfInterest.getUsername().equals(emptyUsername)) {
            throw new CommandException(MESSAGE_NO_GITHUB_ERROR);
        }
        model.openGithub(personOfInterest);
        return new CommandResult(MESSAGE_GITHUB_PERSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubCommand // instanceof handles nulls
                && targetIndex.equals(((GithubCommand) other).targetIndex)); // state check
    }
}
