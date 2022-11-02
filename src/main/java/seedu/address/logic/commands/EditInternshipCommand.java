package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.model.person.PersonId;

/**
 * Edits the details of an existing internship in InterNUS.
 */
public class EditInternshipCommand extends Command {

    public static final String COMMAND_WORD = "edit -i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY] "
            + "[" + PREFIX_INTERNSHIP_ROLE + "ROLE] "
            + "[" + PREFIX_INTERNSHIP_STATUS + "STATUS] "
            + "[" + PREFIX_INTERVIEW_DATE + "INTERVIEW_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERNSHIP_STATUS + "ACCEPTED ";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in InterNUS.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * Creates an EditInternshipCommand to edit an {@code Internship}.
     *
     * @param index of the internship in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditInternshipCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
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
        if (editedInternship.getContactPersonId() != null) {
            model.refreshPersonList();
        }
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship));
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
                                                     EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        // internshipId should always be unchanged
        InternshipId internshipId = internshipToEdit.getInternshipId();
        // contactPersonId should not be changed by EditInternshipCommand
        PersonId contactPersonId = internshipToEdit.getContactPersonId();

        CompanyName updatedCompanyName = editInternshipDescriptor.getCompanyName()
                .orElse(internshipToEdit.getCompanyName());
        InternshipRole updatedInternshipRole = editInternshipDescriptor.getInternshipRole()
                .orElse(internshipToEdit.getInternshipRole());
        InternshipStatus updatedInternshipStatus = editInternshipDescriptor.getInternshipStatus()
                .orElse(internshipToEdit.getInternshipStatus());
        InterviewDate updatedInterviewDate = editInternshipDescriptor.getInterviewDate()
                .orElse(internshipToEdit.getInterviewDate());

        return new Internship(
                internshipId,
                updatedCompanyName,
                updatedInternshipRole,
                updatedInternshipStatus,
                contactPersonId,
                updatedInterviewDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipCommand)) {
            return false;
        }

        // state check
        EditInternshipCommand e = (EditInternshipCommand) other;
        return index.equals(e.index)
                && editInternshipDescriptor.equals(e.editInternshipDescriptor);
    }

    /**
     * Stores the details to edit the internship with. Each non-empty field value will replace the
     * corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private CompanyName companyName;
        private InternshipRole internshipRole;
        private InternshipStatus internshipStatus;
        private InterviewDate interviewDate;

        public EditInternshipDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompanyName(toCopy.companyName);
            setInternshipRole(toCopy.internshipRole);
            setInternshipStatus(toCopy.internshipStatus);
            setInterviewDate(toCopy.interviewDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, internshipRole, internshipStatus, interviewDate);
        }

        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }

        public void setInternshipRole(InternshipRole internshipRole) {
            this.internshipRole = internshipRole;
        }

        public Optional<InternshipRole> getInternshipRole() {
            return Optional.ofNullable(internshipRole);
        }

        public void setInternshipStatus(InternshipStatus internshipStatus) {
            this.internshipStatus = internshipStatus;
        }

        public Optional<InternshipStatus> getInternshipStatus() {
            return Optional.ofNullable(internshipStatus);
        }

        public void setInterviewDate(InterviewDate interviewDate) {
            this.interviewDate = interviewDate;
        }

        public Optional<InterviewDate> getInterviewDate() {
            return Optional.ofNullable(interviewDate);
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

            return getCompanyName().equals(e.getCompanyName())
                    && getInternshipRole().equals(e.getInternshipRole())
                    && getInternshipStatus().equals(e.getInternshipStatus())
                    && getInterviewDate().equals(e.getInterviewDate());
        }
    }
}
