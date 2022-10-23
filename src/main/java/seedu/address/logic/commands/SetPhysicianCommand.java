package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.person.*;

import java.util.List;
import java.util.Optional;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class SetPhysicianCommand extends Command {
    public static final String COMMAND_WORD = "setphys";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds contact details of the attending physician "
            + "to the selected patient. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "n/ [PHYSICIAN NAME]\n"
            + "p/ [PHYSICIAN PHONE]\n"
            + "e/ [PHYSICIAN EMAIL]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "n/ John Doe p/ 81234567 e/ johndoe@example.com";

    public static final String MESSAGE_ADD_PHYS_SUCCESS = "Added attending physician to patient at Index: %d, "
            + "Physician Name: %s ," + "Phone: %s, Email: %s";

    private final Index index;

    private final Name pName;

    private final Phone pPhone;

    private final Email pEmail;

    public SetPhysicianCommand(Index i, Name n, Phone p, Email e) {
        index = i;
        pName = n;
        pEmail = e;
        pPhone = p;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!personToEdit.getCategory().equals(new Category(Category.PATIENT_SYMBOL))) {
            throw new CommandException(Messages.MESSAGE_SETPHYS_INVALID_CATEGORY);
        }

        Physician physician = new Physician(pName, pPhone, pEmail);

        Patient editedPatient = new Patient(personToEdit.getUid(),
                personToEdit.getName(), personToEdit.getGender(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), ((Patient) personToEdit).getDatesTimes(),
                ((Patient) personToEdit).getVisitStatus(), Optional.of(physician), Optional.empty()
        );

        model.setPerson(personToEdit, editedPatient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_PHYS_SUCCESS, index.getOneBased(), pName, pPhone, pEmail));
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
        return index.equals(e.index)
                && pName.equals(e.pName)
                && pPhone.equals(e.pPhone)
                && pEmail.equals(e.pEmail);
    }
}
