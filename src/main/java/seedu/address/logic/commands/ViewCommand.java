package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Indexes;
import seedu.address.commons.exceptions.IllegalIndexException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Finds and lists all details of specified internship.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all details of specified internship"
            + " contain any of the specified keywords (case-insensitive). "
            + "Companies matching the name will be returned.\n"
            + "Parameters: view index\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Displaying more details";
    private final Indexes targetIndex;
    public ViewCommand(Indexes targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        UniquePersonList targetPersons;

        try {
            targetPersons = targetIndex.getAllPersonsFromIndexes(lastShownList);
            System.out.println(targetPersons);
        } catch (IllegalIndexException error) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        List<String> keywordList = new ArrayList<>();
        for (Person person : targetPersons) {
            keywordList.add(person.getName().toString());
        }
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(keywordList));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
