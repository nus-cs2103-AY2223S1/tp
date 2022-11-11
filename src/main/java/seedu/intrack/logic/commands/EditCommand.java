package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.util.CollectionUtil;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.model.internship.Salary;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.Task;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;

/**
 * Edits the details of an existing internship in the tracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the selected internship. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "COMPANY] "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_WEBSITE + "WEBSITE] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SALARY + "2000 "
            + PREFIX_EMAIL + "newemail@example.com";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited internship: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_INTERNSHIP =
            "This internship application already exists in the tracker.";

    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditCommand(EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(editInternshipDescriptor);
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Internship> lastShownList = model.getSelectedInternship();
        if (lastShownList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_NO_INTERNSHIP_SELECTED);
        }
        Internship internshipToEdit = lastShownList.get(0);
        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setInternship(internshipToEdit, editedInternship);
        model.updateSelectedInternship(a -> a.isSameInternship(editedInternship));

        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship));
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
                                                     EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        Name updatedName = editInternshipDescriptor.getName().orElse(internshipToEdit.getName());
        Position updatedPosition = editInternshipDescriptor.getPosition().orElse(internshipToEdit.getPosition());
        Status updatedStatus = internshipToEdit.getStatus(); // edit command does not allow editing status
        Email updatedEmail = editInternshipDescriptor.getEmail().orElse(internshipToEdit.getEmail());
        Website updatedWebsite = editInternshipDescriptor.getWebsite().orElse(internshipToEdit.getWebsite());
        List<Task> updatedTasks = internshipToEdit.getTasks(); // edit command does allow editing tasks
        Salary updatedSalary = editInternshipDescriptor.getSalary().orElse(internshipToEdit.getSalary());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(internshipToEdit.getTags());
        Remark updatedRemark = internshipToEdit.getRemark(); // edit command does not allow editing remarks

        return new Internship(updatedName, updatedPosition, updatedStatus, updatedEmail, updatedWebsite,
                updatedTasks, updatedSalary, updatedTags, updatedRemark);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && editInternshipDescriptor.equals(((EditCommand) other).editInternshipDescriptor)); // state check
    }

    /**
     * Stores the details to edit the internship with. Each non-empty field value will replace the
     * corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private Name name;
        private Position position;
        private Email email;
        private Website website;
        private Salary salary;
        private Set<Tag> tags;

        public EditInternshipDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setName(toCopy.name);
            setPosition(toCopy.position);
            setEmail(toCopy.email);
            setWebsite(toCopy.website);
            setSalary(toCopy.salary);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, position, email, website, salary, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setWebsite(Website website) {
            this.website = website;
        }

        public Optional<Website> getWebsite() {
            return Optional.ofNullable(website);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
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
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            // state check
            EditInternshipDescriptor e = (EditInternshipDescriptor) other;

            return getName().equals(e.getName())
                    && getPosition().equals(e.getPosition())
                    && getEmail().equals(e.getEmail())
                    && getWebsite().equals(e.getWebsite())
                    && getSalary().equals(e.getSalary())
                    && getTags().equals(e.getTags());
        }
    }

}
