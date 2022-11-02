package seedu.studmap.logic.commands;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_GIT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.studmap.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.commons.util.CollectionUtil;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Module;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.student.StudentID;
import seedu.studmap.model.student.TeleHandle;
import seedu.studmap.model.tag.Tag;

/**
 * Edits the details of an existing student in the student map.
 */
public class EditCommand extends EditStudentCommand<EditCommand.EditCommandStudentEditor> {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_ID + "ID] "
            + "[" + PREFIX_GIT + "GITUSER] "
            + "[" + PREFIX_HANDLE + "TELEHANDLE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_EDIT_MULTIPLE_STUDENTS_SUCCESS = "Edited %1$d students.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public EditCommand(IndexListGenerator indexListGenerator, EditCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        return String.format(MESSAGE_EDIT_MULTIPLE_STUDENTS_SUCCESS, editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NOT_EDITED;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return executeNoRefresh(model);
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
        return indicesToEdit.equals(e.indicesToEdit)
                && studentEditor.equals(e.studentEditor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditCommandStudentEditor implements StudentEditor {
        private Name name;
        private Phone phone;
        private Email email;
        private Module module;
        private StudentID id;
        private GitName gitName;
        private TeleHandle handle;
        private Set<Tag> tags;

        public EditCommandStudentEditor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCommandStudentEditor(EditCommandStudentEditor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setModule(toCopy.module);
            setId(toCopy.id);
            setGitName(toCopy.gitName);
            setHandle(toCopy.handle);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, module,
                    id, gitName, handle, tags);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public void setModule(Module module) {
            this.module = module;
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
        }

        public void setId(StudentID id) {
            this.id = id;
        }

        public Optional<StudentID> getId() {
            return Optional.ofNullable(id);
        }

        public void setHandle(TeleHandle handle) {
            this.handle = handle;
        }

        public Optional<TeleHandle> getHandle() {
            return Optional.ofNullable(handle);
        }

        public void setGitName(GitName name) {
            this.gitName = name;
        }

        public Optional<GitName> getGitName() {
            return Optional.ofNullable(gitName);
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommandStudentEditor)) {
                return false;
            }

            // state check
            EditCommandStudentEditor e = (EditCommandStudentEditor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getModule().equals(e.getModule())
                    && getId().equals(e.getId())
                    && getGitName().equals(e.getGitName())
                    && getHandle().equals(e.getHandle())
                    && getTags().equals(e.getTags());
        }

        @Override
        public Student editStudent(Student studentToEdit) {
            assert studentToEdit != null;

            StudentData studentData = studentToEdit.getStudentData();
            studentData.setName(getName().orElse(studentToEdit.getName()));
            studentData.setPhone(getPhone().orElse(studentToEdit.getPhone()));
            studentData.setEmail(getEmail().orElse(studentToEdit.getEmail()));
            studentData.setModule(getModule().orElse(studentToEdit.getModule()));
            studentData.setId(getId().orElse(studentToEdit.getId()));
            studentData.setGitUser(getGitName().orElse(studentToEdit.getGitName()));
            studentData.setTeleHandle(getHandle().orElse(studentToEdit.getTeleHandle()));
            studentData.setTags(getTags().orElse(studentToEdit.getTags()));

            return new Student(studentData);
        }

        @Override
        public boolean hasEdits() {
            return CollectionUtil.isAnyNonNull(name, phone, email, module,
                    id, gitName, handle, tags);
        }
    }
}
