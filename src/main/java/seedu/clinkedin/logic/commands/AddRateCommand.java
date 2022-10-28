package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.clinkedin.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.person.exceptions.RatingAlreadyExistsException;

/**
 * Changes rating of an existing person in the address book.
 */
public class AddRateCommand extends Command {

    public static final String COMMAND_WORD = "addrate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an optional rating to the person identified"
            + "by the index number in the address book. "
            + "Existing rating will be overwritten by the input. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "rate INDEX rate/RATING \n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "rate/4\n\n"
            + " will add a rating of 4 to the first person in the address book.";

    public static final String MESSAGE_ADD_RATING_SUCCESS = "Added rating to Person: %1$s";
    public static final String MESSAGE_DELETE_RATING_SUCCESS = "Removed rating from Person: %1$s";
    public static final String MESSAGE_RATING_EXIST = "Rating for this person already exists";

    private final Index index;

    private final Rating rating;

    /**
     * @param index of the person in the filtered person list to edit the rating
     * @param rating rating of the person to be updated to
     */
    public AddRateCommand(Index index, Rating rating) {
        requireAllNonNull(index, rating);
        this.index = index;
        this.rating = rating;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!personToEdit.getRating().toString().equals("0")) {
            throw new CommandException(MESSAGE_RATING_EXIST);
        }
        UniqueTagTypeMap tagMap = new UniqueTagTypeMap();
        tagMap.setTagTypeMap(personToEdit.getTags());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), tagMap, personToEdit.getStatus(), personToEdit.getNote(), rating,
                personToEdit.getLinks());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_RATING_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddRateCommand)) {
            return false;
        }

        // state check
        AddRateCommand e = (AddRateCommand) other;
        return index.equals(e.index)
                && rating.equals(e.rating);
    }
}
