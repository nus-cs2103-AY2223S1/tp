package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_EMAIL;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_TAG;
import static jeryl.fyp.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.commons.util.CollectionUtil;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.DeadlineList;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.StudentName;
import jeryl.fyp.model.tag.Tag;

/**
 * Edits the details of an existing student in the FYP manager.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by unique student ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: STUDENT_ID "
            + "[" + PREFIX_STUDENT_NAME + "NAME] "
            + "[" + PREFIX_PROJECT_NAME + "PROJECT_NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " A0123456X "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the FYP manager.";
    private final StudentId studentId;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
 * @param studentId of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(StudentId studentId, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(studentId);
        requireNonNull(editStudentDescriptor);

        this.studentId = studentId;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        Index targetIndex = model.getIndexByStudentId(studentId);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_STUDENT_NOT_FOUND);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudentId(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        StudentName updatedStudentName = editStudentDescriptor.getStudentName().orElse(studentToEdit.getStudentName());
        StudentId updatedStudentId = editStudentDescriptor.getStudentId().orElse(studentToEdit.getStudentId());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        ProjectName updatedProjectName = editStudentDescriptor.getProjectName().orElse(studentToEdit.getProjectName());
        ProjectStatus updatedProjectStatus =
                editStudentDescriptor.getProjectStatus().orElse(studentToEdit.getProjectStatus());
        DeadlineList updatedDeadlineList =
                editStudentDescriptor.getDeadlineList().orElse(studentToEdit.getDeadlineList());
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());

        return new Student(updatedStudentName, updatedStudentId, updatedEmail,
                updatedProjectName, updatedProjectStatus, updatedDeadlineList, updatedTags);
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
        return studentId.equals(e.studentId)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private StudentName studentName;
        private StudentId id;
        private Email email;
        private ProjectName projectName;
        private ProjectStatus projectStatus;
        private DeadlineList deadlineList;
        private Set<Tag> tags;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setStudentName(toCopy.studentName);
            setStudentId(toCopy.id);
            setEmail(toCopy.email);
            setProjectName(toCopy.projectName);
            setProjectStatus(toCopy.projectStatus);
            setDeadlineList(toCopy.deadlineList);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(studentName, email, projectName, tags);
        }

        public void setStudentName(StudentName studentName) {
            this.studentName = studentName;
        }

        public Optional<StudentName> getStudentName() {
            return Optional.ofNullable(studentName);
        }

        public void setStudentId(StudentId id) {
            this.id = id;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(id);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setProjectName(ProjectName projectName) {
            this.projectName = projectName;
        }

        public Optional<ProjectName> getProjectName() {
            return Optional.ofNullable(projectName);
        }

        public void setProjectStatus(ProjectStatus projectStatus) {
            this.projectStatus = projectStatus;
        }

        public Optional<ProjectStatus> getProjectStatus() {
            return Optional.ofNullable(projectStatus);
        }

        public void setDeadlineList(DeadlineList deadlineList) {
            this.deadlineList = deadlineList;
        }

        public Optional<DeadlineList> getDeadlineList() {
            return Optional.ofNullable(deadlineList);
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
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getStudentName().equals(e.getStudentName())
                    && getEmail().equals(e.getEmail())
                    && getStudentId().equals(getStudentId())
                    && getProjectName().equals(e.getProjectName())
                    && getTags().equals(e.getTags())
                    && getDeadlineList().equals(e.getDeadlineList());
        }
    }
}
