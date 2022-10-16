package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.workbook.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.workbook.commons.core.Messages;
import seedu.workbook.commons.core.index.Index;
import seedu.workbook.commons.util.CollectionUtil;
import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.model.Model;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Phone;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.internship.Stage;
import seedu.workbook.model.tag.Tag;

/**
 * Edits the details of an existing internship in the work book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_STAGE + "STAGE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the address book.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param index of the internship in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternshipDescriptor);

        this.index = index;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        model.commitWorkBook();
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship));
    }

    /**
     * Creates and returns a {@code Internship} with the details of
     * {@code internshipToEdit} edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
            EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        Company updatedCompany = editInternshipDescriptor.getCompany().orElse(internshipToEdit.getCompany());
        Role updatedRole = editInternshipDescriptor.getRole().orElse(internshipToEdit.getRole());
        Email updatedEmail = editInternshipDescriptor.getEmail().orElse(internshipToEdit.getEmail());
        Stage updatedStage = editInternshipDescriptor.getStage().orElse(internshipToEdit.getStage());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(internshipToEdit.getTags());

        return new Internship(updatedCompany, updatedRole, updatedEmail, updatedStage, updatedTags);
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
                && editInternshipDescriptor.equals(e.editInternshipDescriptor);
    }

    /**
     * Stores the details to edit the internship with. Each non-empty field value
     * will replace the corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private Company company;
        private Role role;
        private Email email;
        private Stage stage;
        private Set<Tag> tags;

        public EditInternshipDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompany(toCopy.company);
            setRole(toCopy.role);
            setEmail(toCopy.email);
            setStage(toCopy.stage);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(company, role, email, stage, tags);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }
        public void setStage(Stage stage) {
            this.stage = stage;
        }

        public Optional<Stage> getStage() {
            return Optional.ofNullable(stage);
        }


        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.
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

            return getCompany().equals(e.getCompany())
                    && getRole().equals(e.getRole())
                    && getEmail().equals(e.getEmail())
                    && getStage().equals(e.getStage())
                    && getTags().equals(e.getTags());
        }
    }
}
