package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.FilePath.EMPTY_FILEPATH;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FilePath;
import seedu.address.model.person.Person;

/**
 * Changes the remark of an existing person in the address book.
 */
public class SetPersonFileCommand extends Command {

    public static final String COMMAND_WORD = "filepath";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the file path of the person identified "
            + "by the index number used in the last person listing.\n"
            + "Existing file path will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_FILEPATH + "[FilePath]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_FILEPATH + "C:/Users/Ryzen/repos/CS2103T/tp/data/Test_PDF.pdf";

    public static final String MESSAGE_CHANGE_FILEPATH_SUCCESS = "Added file path to Person: %1$s";
    public static final String MESSAGE_DELETE_FILEPATH_SUCCESS = "File path set to placeholder pdf for Person: %1$s";

    private final Index index;
    private final FilePath filePath;

    /**
     * @param index of the person in the filtered person list to edit the filePath
     * @param filePath of the person to be updated to
     */
    public SetPersonFileCommand(Index index, FilePath filePath) {
        requireAllNonNull(index, filePath);

        this.index = index;
        this.filePath = filePath;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getDescription(),
                personToEdit.getNetWorth(), personToEdit.getMeetingTimes(), filePath, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the file path is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !filePath.value.equals(EMPTY_FILEPATH) ? MESSAGE_CHANGE_FILEPATH_SUCCESS
                : MESSAGE_DELETE_FILEPATH_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetPersonFileCommand)) {
            return false;
        }

        // state check
        SetPersonFileCommand e = (SetPersonFileCommand) other;
        return index.equals(e.index)
                && filePath.equals(e.filePath);
    }
}
