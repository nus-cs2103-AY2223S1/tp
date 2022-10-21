package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CRITICAL_ILLNESS_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISABILITY_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIFE_INSURANCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.CriticalIllnessInsurance;
import seedu.address.model.person.DisabilityInsurance;
import seedu.address.model.person.Email;
import seedu.address.model.person.HealthInsurance;
import seedu.address.model.person.Insurance;
import seedu.address.model.person.LifeInsurance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reminder;
import seedu.address.model.tag.Tag;

/**
 * Edits the insurance details of an existing person in the address book.
 */
public class InsuranceCommand extends Command {

    public static final String COMMAND_WORD = "insurance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the types of insurances that the person identified "
            + "by the index number used in the displayed person list has. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_HEALTH_INSURANCE + "] (Health Insurance) "
            + "[" + PREFIX_DISABILITY_INSURANCE + "] (Disability Insurance) "
            + "[" + PREFIX_CRITICAL_ILLNESS_INSURANCE + "] (Critical Illness Insurance) "
            + "[" + PREFIX_LIFE_INSURANCE + "] (Life Insurance) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_HEALTH_INSURANCE + " "
            + PREFIX_LIFE_INSURANCE;

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Insurance Of Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one insurance field to edit must be provided.";

    private final Index index;
    private final EditInsuranceDescriptor editInsuranceDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editInsuranceDescriptor insurance details to edit the person with
     */
    public InsuranceCommand(Index index, EditInsuranceDescriptor editInsuranceDescriptor) {
        requireNonNull(index);
        requireNonNull(editInsuranceDescriptor);

        this.index = index;
        this.editInsuranceDescriptor = new EditInsuranceDescriptor(editInsuranceDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editInsuranceDescriptor);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editInsuranceDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditInsuranceDescriptor editInsuranceDescriptor) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Birthday birthday = personToEdit.getBirthday();
        Reminder reminders = personToEdit.getReminders();
        Insurance updatedHealthInsurance = editInsuranceDescriptor
                .getHealthInsurance().orElse(new HealthInsurance(false));
        Insurance updatedDisabilityInsurance = editInsuranceDescriptor.getDisabilityInsurance()
                .orElse(new DisabilityInsurance(false));
        Insurance updatedCriticalIllnessInsurance = editInsuranceDescriptor.getCriticalIllnessInsurance()
                .orElse(new CriticalIllnessInsurance(false));
        Insurance updatedLifeInsurance = editInsuranceDescriptor.getLifeInsurance()
                .orElse(new LifeInsurance(false));
        Set<Tag> tags = personToEdit.getTags();

        return new Person(name, phone, email, address, birthday,
                updatedHealthInsurance, updatedDisabilityInsurance, updatedCriticalIllnessInsurance,
                updatedLifeInsurance, reminders, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InsuranceCommand)) {
            return false;
        }

        // state check
        InsuranceCommand e = (InsuranceCommand) other;
        return index.equals(e.index)
                && editInsuranceDescriptor.equals(e.editInsuranceDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditInsuranceDescriptor {
        private Insurance healthInsurance;
        private Insurance disabilityInsurance;
        private Insurance criticalIllnessInsurance;
        private Insurance lifeInsurance;

        public EditInsuranceDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInsuranceDescriptor(EditInsuranceDescriptor toCopy) {
            setHealthInsurance(toCopy.healthInsurance);
            setDisabilityInsurance(toCopy.disabilityInsurance);
            setCriticalIllnessInsurance(toCopy.criticalIllnessInsurance);
            setLifeInsurance(toCopy.lifeInsurance);
        }

        public void setHealthInsurance(Insurance healthInsurance) {
            this.healthInsurance = healthInsurance;
        }

        public Optional<Insurance> getHealthInsurance() {
            return Optional.ofNullable(healthInsurance);
        }

        public void setDisabilityInsurance(Insurance disabilityInsurance) {
            this.disabilityInsurance = disabilityInsurance;
        }

        public Optional<Insurance> getDisabilityInsurance() {
            return Optional.ofNullable(disabilityInsurance);
        }

        public void setCriticalIllnessInsurance(Insurance criticalIllnessInsurance) {
            this.criticalIllnessInsurance = criticalIllnessInsurance;
        }

        public Optional<Insurance> getCriticalIllnessInsurance() {
            return Optional.ofNullable(criticalIllnessInsurance);
        }

        public void setLifeInsurance(Insurance lifeInsurance) {
            this.lifeInsurance = lifeInsurance;
        }

        public Optional<Insurance> getLifeInsurance() {
            return Optional.ofNullable(lifeInsurance);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInsuranceDescriptor)) {
                return false;
            }

            // state check
            EditInsuranceDescriptor e = (EditInsuranceDescriptor) other;

            return getHealthInsurance().equals(e.getHealthInsurance())
                    && getDisabilityInsurance().equals(e.getDisabilityInsurance())
                    && getCriticalIllnessInsurance().equals(e.getCriticalIllnessInsurance())
                    && getLifeInsurance().equals(e.getLifeInsurance());
        }
    }
}
