package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;
import seedu.address.model.person.Uid;

/**
 * Represents the command to assign an attending physician to a patient.
 */
public class SetPhysicianCommand extends Command {
    public static final String COMMAND_WORD = "setphys";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds contact details of the attending physician "
            + "to the selected patient. \n"
            + "Parameters: id/ [UID]"
            + "n/ [PHYSICIAN NAME]\n"
            + "p/ [PHYSICIAN PHONE]\n"
            + "e/ [PHYSICIAN EMAIL]\n"
            + "Example: " + COMMAND_WORD + " id/3 "
            + "n/ John Doe p/ 81234567 e/ johndoe@example.com";

    public static final String MESSAGE_ADD_PHYS_SUCCESS = "Added attending physician to patient with UID: %s, "
            + "Physician Name: %s ," + "Phone: %s, Email: %s";

    private final Uid uid;

    private final Name pName;

    private final Phone pPhone;

    private final Email pEmail;

    /**
     * Create a new SetPhysicianCommand.
     * @param i index
     * @param n physician's name
     * @param p physician's phone number
     * @param e physician's email
     */
    public SetPhysicianCommand(Uid i, Name n, Phone p, Email e) {
        uid = i;
        pName = n;
        pEmail = e;
        pPhone = p;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToEdit = lastShownList.stream().filter(x -> x.getUid().equals(uid)).findAny()
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID));
        if (!personToEdit.getCategory().equals(new Category(Category.PATIENT_SYMBOL))) {
            throw new CommandException(Messages.MESSAGE_SETPHYS_INVALID_CATEGORY);
        }

        Physician physician = new Physician(pName, pPhone, pEmail);

        Patient editedPatient = new Patient(personToEdit.getUid(),
                personToEdit.getName(), personToEdit.getGender(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), ((Patient) personToEdit).getDatesTimes(), ((Patient)
                personToEdit).getVisitStatus(), Optional.of(physician), Optional.empty());

        model.setPerson(personToEdit, editedPatient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_PHYS_SUCCESS, uid, pName, pPhone, pEmail));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetPhysicianCommand)) {
            return false;
        }

        // state check
        SetPhysicianCommand e = (SetPhysicianCommand) other;
        return uid.equals(e.uid)
                && pName.equals(e.pName)
                && pPhone.equals(e.pPhone)
                && pEmail.equals(e.pEmail);
    }
}
