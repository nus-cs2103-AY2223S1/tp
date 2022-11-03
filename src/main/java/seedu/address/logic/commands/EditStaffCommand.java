package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_STAFF_DISPLAYED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STAFF;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffLeave;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing staff in a project.
 */
public class EditStaffCommand extends Command {

    public static final String COMMAND_WORD = "editstaff";

    public static final String MESSAGE_NOT_EDITED = "At least one field must be provided.";
    public static final String MESSAGE_DUPLICATE_STAFF = "This staff already exists in the project.";
    public static final String MESSAGE_EDIT_STAFF_SUCCESS = "Edited Staff in %2$s: %1$s\n"
            + "Displaying all staff in project: %2$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": The project name refers to the project whose staff is to be edited. The command "
            + "looks for the staff identified by the INDEX\nwithin the displayed staff list and edits the "
            + "staff if its in the project. Checking if the staff is in the project is through staff name only.\n"
            + "Make sure you view the correct staff list before editing a staff. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "STAFF_INDEX (must be a number from 1 to 2147483647) "
            + PREFIX_PROJECT_NAME + "PROJECT_NAME "
            + "[" + PREFIX_STAFF_NAME + "STAFF_NAME] "
            + "[" + PREFIX_STAFF_CONTACT + "STAFF_CONTACT] "
            + "[" + PREFIX_STAFF_DEPARTMENT + "STAFF_DEPARTMENT] "
            + "[" + PREFIX_STAFF_LEAVE + "STAFF_LEAVE] "
            + "[" + PREFIX_STAFF_TITLE + "STAFF_TITLE] "
            + "[" + PREFIX_TAG + "TAG] ...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PROJECT_NAME + "CS2103T TP "
            + PREFIX_STAFF_NAME + "John Doe "
            + PREFIX_STAFF_CONTACT + "98765432 ";

    private final ProjectName projectName;
    private final Index staffIndex;
    private final EditStaffDescriptor editStaffDescriptor;

    /**
     * @param projectName of the {@code Project} in the filtered project list to edit
     * @param staffIndex of the {@code Staff} in the {@code UniqueStaffList} of the {@code Project} to edit
     * @param editStaffDescriptor details to edit the staff with
     */
    public EditStaffCommand(ProjectName projectName, Index staffIndex, EditStaffDescriptor editStaffDescriptor) {
        requireNonNull(projectName);
        requireNonNull(staffIndex);
        requireNonNull(editStaffDescriptor);
        this.projectName = projectName;
        this.staffIndex = staffIndex;
        this.editStaffDescriptor = new EditStaffDescriptor(editStaffDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownProjectList = model.getFilteredProjectList();
        List<Staff> lastShownStaffList = model.getFilteredStaffList();

        checkForEmptyList(lastShownProjectList, lastShownStaffList);
        if (staffIndex.getZeroBased() >= lastShownStaffList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX);
        }

        Project toFindIn = getProjectFrom(lastShownProjectList, this.projectName);
        ProjectName foundProjectName = toFindIn.getProjectName();

        Staff toEdit = lastShownStaffList.get(staffIndex.getZeroBased());
        Staff editedStaff = createEditedStaff(toEdit, editStaffDescriptor);
        checkForDuplicateStaff(toEdit, editedStaff, toFindIn);

        toFindIn.getStaffList().setStaff(toEdit, editedStaff);
        model.setFilteredStaffList(toFindIn);
        model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(String.format(MESSAGE_EDIT_STAFF_SUCCESS, editedStaff, foundProjectName));
    }

    /**
     * Returns the project that has a project name which matches {@code projectName}.
     *
     * @param lastShownProjectList The project list currently displayed.
     * @return Project
     * @throws CommandException If project cannot be found.
     */
    private Project getProjectFrom(List<Project> lastShownProjectList, ProjectName projectName)
            throws CommandException {
        int projectIndex = 0;
        for (int i = 0; i < lastShownProjectList.size(); ++i) {
            if (lastShownProjectList.get(i).getProjectName().equals(projectName)) {
                projectIndex = i;
            }
        }

        Index index = Index.fromZeroBased(projectIndex);

        ProjectName foundProjectName = lastShownProjectList.get(index.getZeroBased()).getProjectName();
        if (!projectName.equals(foundProjectName)) {
            throw new CommandException(String.format(MESSAGE_INVALID_PROJECT, projectName.fullName));
        }
        return lastShownProjectList.get(index.getZeroBased());
    }

    /**
     * Checks if there are Projects and Staff displayed on their respectively list.
     *
     * @param projectList The displayed Project list
     * @param staffList The displayed Staff list
     * @throws CommandException Exception thrown if either list do not have anything displayed
     */
    private void checkForEmptyList(List<Project> projectList, List<Staff> staffList)
            throws CommandException {
        if (projectList.size() == 0) {
            throw new CommandException(String.format(MESSAGE_INVALID_PROJECT, projectName.fullName));
        }

        if (staffList.size() == 0) {
            throw new CommandException(String.format(MESSAGE_NO_STAFF_DISPLAYED, "editstaff command"));
        }
    }

    /**
     * Checks that the Staff to edit exist within the project and if the Staff
     * new details will not result in a duplicate Staff in the project.
     *
     * @param toEdit The staff to be edited
     * @param editedStaff New Staff with new details to replace to original Staff
     * @param toFindIn The project where the Staff is to be found in
     * @throws CommandException Duplicate staff exception thrown or if the staff is not in project
     */
    private void checkForDuplicateStaff(Staff toEdit, Staff editedStaff, Project toFindIn)
            throws CommandException {
        if (!toEdit.isSameStaff(editedStaff) && toFindIn.getStaffList().contains(editedStaff)) {
            throw new CommandException(MESSAGE_DUPLICATE_STAFF);
        }

        ProjectName foundProjectName = toFindIn.getProjectName();

        if (!toFindIn.getStaffList().contains(toEdit)) {
            throw new CommandException(String.format("Staff %1$s not found in specified project: %2$s!",
                    toEdit.getStaffName(), foundProjectName));
        }
    }

    /**
     * Creates and returns a {@code Staff} with the details of {@code staffToEdit}
     * edited with {@code editStaffDescriptor}.
     */
    private static Staff createEditedStaff(Staff staffToEdit, EditStaffDescriptor editStaffDescriptor) {
        assert staffToEdit != null;

        StaffName updatedStaffName = editStaffDescriptor.getStaffName().orElse(staffToEdit.getStaffName());
        StaffContact updatedStaffContact = editStaffDescriptor.getStaffContact().orElse(staffToEdit.getStaffContact());
        StaffDepartment updatedStaffDepartment = editStaffDescriptor.getStaffDepartment()
                .orElse(staffToEdit.getStaffDepartment());
        StaffLeave updatedStaffLeave = editStaffDescriptor.getStaffLeave().orElse(staffToEdit.getStaffLeave());
        StaffTitle updatedStaffTitle = editStaffDescriptor.getStaffTitle().orElse(staffToEdit.getStaffTitle());
        Set<Tag> updatedTags = editStaffDescriptor.getTags().orElse(staffToEdit.getTags());

        Staff newStaff = new Staff(updatedStaffName, updatedStaffContact, updatedStaffTitle, updatedStaffDepartment,
                updatedStaffLeave, updatedTags);

        return newStaff;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditStaffCommand)) {
            return false;
        }

        EditStaffCommand esc = (EditStaffCommand) other;
        return projectName.equals(esc.projectName)
                && staffIndex.equals(esc.staffIndex)
                && editStaffDescriptor.equals(esc.editStaffDescriptor);
    }


    /**
     * Stores the details to edit the staff with. Each non-empty field will
     * replace the corresponding field of the staff.
     */
    public static class EditStaffDescriptor {
        private StaffName staffName;
        private StaffContact staffContact;
        private StaffDepartment staffDepartment;
        private StaffLeave staffLeave;
        private StaffTitle staffTitle;
        private Set<Tag> tags;

        public EditStaffDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStaffDescriptor(EditStaffDescriptor toCopy) {
            setStaffName(toCopy.staffName);
            setStaffContact(toCopy.staffContact);
            setStaffDepartment(toCopy.staffDepartment);
            setStaffLeave(toCopy.staffLeave);
            setStaffTitle(toCopy.staffTitle);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(staffName, staffContact, staffDepartment, staffLeave, staffTitle, tags);
        }

        public void setStaffName(StaffName staffName) {
            this.staffName = staffName;
        }

        public Optional<StaffName> getStaffName() {
            return Optional.ofNullable(this.staffName);
        }
        public void setStaffContact(StaffContact staffContact) {
            this.staffContact = staffContact;
        }

        public Optional<StaffContact> getStaffContact() {
            return Optional.ofNullable(this.staffContact);
        }
        public void setStaffDepartment(StaffDepartment staffDepartment) {
            this.staffDepartment = staffDepartment;
        }

        public Optional<StaffDepartment> getStaffDepartment() {
            return Optional.ofNullable(this.staffDepartment);
        }
        public void setStaffLeave(StaffLeave staffLeave) {
            this.staffLeave = staffLeave;
        }

        public Optional<StaffLeave> getStaffLeave() {
            return Optional.ofNullable(this.staffLeave);
        }
        public void setStaffTitle(StaffTitle staffTitle) {
            this.staffTitle = staffTitle;
        }

        public Optional<StaffTitle> getStaffTitle() {
            return Optional.ofNullable(this.staffTitle);
        }

        /**
         * Sets {@code tags} to this object's {@code staffTags}.
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
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditStaffDescriptor)) {
                return false;
            }

            EditStaffDescriptor esd = (EditStaffDescriptor) other;

            return getStaffName().equals(esd.getStaffName())
                    && getStaffContact().equals(esd.getStaffContact())
                    && getStaffDepartment().equals(esd.getStaffDepartment())
                    && getStaffLeave().equals(esd.getStaffLeave())
                    && getStaffTitle().equals(esd.getStaffTitle())
                    && getTags().equals(esd.getTags());
        }
    }
}
