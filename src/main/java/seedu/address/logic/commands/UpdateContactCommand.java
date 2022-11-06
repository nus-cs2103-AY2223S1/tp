package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.person.BasePerson;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;
import seedu.address.model.person.Uid;

/**
 * Represents the command to add contact details of attending physician or next
 * of kin to a patient.
 */
public class UpdateContactCommand extends Command {
    public static final String COMMAND_WORD = "updatecontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Update next of kin or attending physician contact details "
            + "to the selected patient. \n"
            + "Parameters: id/ [UID]"
            + "n/ [CONTACT NAME]\n"
            + "p/ [CONTACT PHONE]\n"
            + "e/ [CONTACT EMAIL]\n"
            + "c/ [CONTACT CATEGORY]: either D for Physician or K for Next of Kin\n"
            + "Example: " + COMMAND_WORD + " id/3 "
            + "n/ John Doe p/ 81234567 e/ johndoe@example.com c/ D";

    public static final String MESSAGE_UPDATE_CONTACT_SUCCESS = "Added contact details to patient with UID: %s, "
            + "Contact Name: %s, Phone: %s, Email: %s, Category: %s";

    private final Uid uid;

    private final Name name;

    private final Phone phone;

    private final Email email;

    private final Category category;

    /**
     * Create a new UpdateContactCommand.
     *
     * @param i patient uid
     * @param n contact name
     * @param p contact phone number
     * @param e contact email
     * @param c contact category
     */
    public UpdateContactCommand(Uid i, Name n, Phone p, Email e, Category c) {
        uid = i;
        name = n;
        email = e;
        phone = p;
        category = c;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Patient patientToEdit = getPersonToEdit(model);

        Patient editedPatient = updateContact(patientToEdit);

        model.setPerson(patientToEdit, editedPatient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_UPDATE_CONTACT_SUCCESS, uid, name, phone, email, category));
    }

    private Patient getPersonToEdit(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit = lastShownList.stream()
                .filter(x -> x.getUid().equals(uid))
                .findAny()
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID));
        if (!personToEdit.getCategory().equals(new Category(Category.PATIENT_SYMBOL))) {
            throw new CommandException(Messages.MESSAGE_UPDATECONTACT_INVALID_CATEGORY);
        }
        return (Patient) personToEdit;
    }

    private Patient updateContact(Patient patientToEdit) throws CommandException {
        BasePerson updatedContact;
        if (category.equals(new Category(Category.PHYSICIAN_SYMBOL))) {
            updatedContact = new Physician(name, phone, email);
            return new Patient(patientToEdit.getUid(), patientToEdit.getName(), patientToEdit.getGender(),
                    patientToEdit.getPhone(), patientToEdit.getEmail(), patientToEdit.getAddress(),
                    patientToEdit.getTags(), patientToEdit.getDatesSlots(),
                    Optional.of((Physician) updatedContact), patientToEdit.getNextOfKin());
        } else if (category.equals(new Category(Category.NEXTOFKIN_SYMBOL))) {
            updatedContact = new NextOfKin(name, phone, email);
            return new Patient(patientToEdit.getUid(), patientToEdit.getName(), patientToEdit.getGender(),
                    patientToEdit.getPhone(), patientToEdit.getEmail(), patientToEdit.getAddress(),
                    patientToEdit.getTags(), patientToEdit.getDatesSlots(),
                    patientToEdit.getAttendingPhysician(), Optional.of((NextOfKin) updatedContact));
        } else {
            throw new CommandException(Messages.MESSAGE_UPDATECONTACT_INVALID_CONTACT_CATEGORY);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateContactCommand)) {
            return false;
        }

        // state check
        UpdateContactCommand e = (UpdateContactCommand) other;
        return uid.equals(e.uid)
                && name.equals(e.name)
                && phone.equals(e.phone)
                && email.equals(e.email)
                && category.equals(e.category);
    }
}
