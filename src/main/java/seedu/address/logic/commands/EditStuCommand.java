package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.HelpTag;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;

/**
 * Edits the details of an existing student in the SETA.
 */
public class EditStuCommand extends Command {
    public static final String COMMAND_WORD = "editstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ATTENDANCE + "ATTENDANCE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TELEGRAM + "@johndoe "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_ATTENDANCE + "2";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the student list.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStuCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
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

        StuName updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Telegram updatedTelegram = editStudentDescriptor.getTelegram().orElse(studentToEdit.getTelegram());
        StuEmail updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Response response = studentToEdit.getResponse();
        Attendance attendance = editStudentDescriptor.getAttendance().orElse(studentToEdit.getAttendance());
        HelpTag helpTag = studentToEdit.getHelpTag();

        return new Student(updatedName, updatedTelegram, updatedEmail, response, attendance, helpTag);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStuCommand)) {
            return false;
        }

        // state check
        EditStuCommand e = (EditStuCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private StuName name;
        private Telegram telegram;
        private StuEmail email;
        private Attendance attendance;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setTelegram(toCopy.telegram);
            setEmail(toCopy.email);
            setAttendance(toCopy.attendance);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, telegram, email, attendance);
        }

        public void setName(StuName name) {
            this.name = name;
        }

        public Optional<StuName> getName() {
            return Optional.ofNullable(name);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public void setEmail(StuEmail email) {
            this.email = email;
        }

        public Optional<StuEmail> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
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

            return getName().equals(e.getName())
                    && getTelegram().equals(e.getTelegram())
                    && getEmail().equals(e.getEmail())
                    && getAttendance().equals(e.getAttendance());
        }
    }
}
