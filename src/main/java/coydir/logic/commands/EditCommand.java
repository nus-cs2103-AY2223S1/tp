package coydir.logic.commands;

import static coydir.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static coydir.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static coydir.logic.parser.CliSyntax.PREFIX_EMAIL;
import static coydir.logic.parser.CliSyntax.PREFIX_LEAVE;
import static coydir.logic.parser.CliSyntax.PREFIX_NAME;
import static coydir.logic.parser.CliSyntax.PREFIX_PHONE;
import static coydir.logic.parser.CliSyntax.PREFIX_POSITION;
import static coydir.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import coydir.commons.core.Messages;
import coydir.commons.core.index.Index;
import coydir.commons.util.CollectionUtil;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.Address;
import coydir.model.person.Department;
import coydir.model.person.Email;
import coydir.model.person.EmployeeId;
import coydir.model.person.Name;
import coydir.model.person.Person;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.model.person.Rating;
import coydir.model.tag.Tag;

/**
 * Edits the details of an existing person in the database.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer, not too large, i.e. < 2^31 - 1) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_LEAVE + "LEAVE] "
            + "[" + PREFIX_TAG + "TAG] \n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the database.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;
        int currentLeaves = personToEdit.getTotalNumberOfLeaves();

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Position updatedPosition = editPersonDescriptor.getPosition().orElse(personToEdit.getPosition());
        Department updatedDepartment = editPersonDescriptor.getDepartment().orElse(personToEdit.getDepartment());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        int updatedLeaves = editPersonDescriptor.getLeaves().orElse(currentLeaves);
        EmployeeId employeeId = personToEdit.getEmployeeId();
        Rating rating = personToEdit.getRating();
        int currentLeavesLeft = personToEdit.getLeavesLeft();
        Person p = new Person(
                updatedName, employeeId, updatedPhone, updatedEmail, updatedPosition,
                updatedDepartment, updatedAddress, updatedTags, updatedLeaves, rating);
        p.setLeavesLeft(updatedLeaves - currentLeaves + currentLeavesLeft);
        p.setLeaves(personToEdit.getLeaves());
        p.setRatingHistory(personToEdit.getRatingHistory());
        return p;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Position position;
        private Department department;
        private Address address;
        private int totalNumberofLeaves;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setPosition(toCopy.position);
            setDepartment(toCopy.department);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setLeaves(toCopy.totalNumberofLeaves);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            Integer temp = null;
            if (totalNumberofLeaves > 0) {
                temp = totalNumberofLeaves;
            }
            return CollectionUtil.isAnyNonNull(name, phone, email, position, department, address, tags, temp);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public Optional<Department> getDepartment() {
            return Optional.ofNullable(department);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setLeaves(int leaves) {
            this.totalNumberofLeaves = leaves;
        }

        public Optional<Integer> getLeaves() {
            Integer temp = null;
            if (totalNumberofLeaves > 0) {
                temp = totalNumberofLeaves;
            }
            return Optional.ofNullable(temp);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getPosition().equals(e.getPosition())
                    && getDepartment().equals(e.getDepartment())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
