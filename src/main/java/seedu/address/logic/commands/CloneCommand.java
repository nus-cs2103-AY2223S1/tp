package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * Clones an existing person in the address book.
 */
public class CloneCommand extends Command {

    public static final String COMMAND_WORD = "clone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clones the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "A new person with different name but same details will be added to the address book.\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLONE_PERSON_SUCCESS = "Cloned Person: %1$s";

    public static final String DUPLICATE_NAME_EXTENSION = " copy";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to cloned
     */
    public CloneCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToClone = lastShownList.get(index.getZeroBased());
        Person clonedPerson = createClonedPerson(model, personToClone);
        model.addPerson(clonedPerson);
        return new CommandResult(String.format(MESSAGE_CLONE_PERSON_SUCCESS, clonedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToClone}.
     */
    private static Person createClonedPerson(Model model, Person personToClone) {
        assert personToClone != null;

        if (!model.hasPerson(personToClone)) {
            return personToClone;
        }

        Name updatedName = new Name(personToClone.getName() + DUPLICATE_NAME_EXTENSION);
        Phone clonedPhone = personToClone.getPhone();
        Email clonedEmail = personToClone.getEmail();
        Address clonedAddress = personToClone.getAddress();
        Gender clonedGender = personToClone.getGender();
        Birthdate clonedBirthdate = personToClone.getBirthdate();
        Race clonedRace = personToClone.getRace();
        Religion clonedReligion = personToClone.getReligion();
        Survey clonedSurvey = personToClone.getSurvey();
        Set<Tag> clonedTags = (personToClone.getTags() != null) ? personToClone.getTags() : null;

        Person clonedPerson = new Person(updatedName, clonedPhone, clonedEmail, clonedAddress,
                clonedGender, clonedBirthdate, clonedRace, clonedReligion, clonedSurvey, clonedTags);
        return createClonedPerson(model, clonedPerson);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CloneCommand)) {
            return false;
        }

        // state check
        CloneCommand e = (CloneCommand) other;
        return index.equals(e.index);
    }
}
