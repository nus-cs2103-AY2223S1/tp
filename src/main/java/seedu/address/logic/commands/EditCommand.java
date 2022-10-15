package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEPROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.DurationList;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_LESSON_PLAN + "LESSON PLAN] "
            + "[" + PREFIX_HOMEWORK + "INDEX HOMEWORK]"
            + "[" + PREFIX_ATTENDANCE + "INDEX ATTENDANCE]"
            + "[" + PREFIX_GRADEPROGRESS + "INDEX GRADE PROGRESS]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
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
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        LessonPlan updatedLessonPlan = editPersonDescriptor.getLessonPlan()
                .orElse(personToEdit.getLessonPlan());
        HomeworkList updatedHomeworkList = personToEdit.getHomeworkList();
        AttendanceList updatedAttendanceList = personToEdit.getAttendanceList();
        GradeProgressList updatedGradeProgressList = getUpdatedGradeProgressList(personToEdit, editPersonDescriptor);
        DurationList updatedDurationList = personToEdit.getDurationList();
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedLessonPlan,
                updatedHomeworkList, updatedAttendanceList, updatedDurationList, updatedGradeProgressList, updatedTags);
    }

    /**
     * Returns the updated {@code HomeworkList} if edited, or the original list if not.
     */
    public static HomeworkList getUpdatedHomeworkList(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        HomeworkList updatedHomeworkList = personToEdit.getHomeworkList();
        Optional<Homework> homework = editPersonDescriptor.getHomework();
        Optional<Index> homeworkIndex = editPersonDescriptor.getHomeworkIndex();
        if (homeworkIndex.isEmpty() || homework.isEmpty()) {
            return updatedHomeworkList;
        }
        if (!updatedHomeworkList.isValidIndex(homeworkIndex.get())) {
            throw new CommandException(HomeworkList.MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        updatedHomeworkList.editAtIndex(homeworkIndex.get(), homework.get());
        return updatedHomeworkList;
    }

    /**
     * Returns the updated {@code AttendanceList} if edited, or the original list if not.
     */
    public static AttendanceList getUpdatedAttendanceList(Person personToEdit,
            EditPersonDescriptor editPersonDescriptor) throws CommandException {
        AttendanceList updatedAttendanceList = personToEdit.getAttendanceList();
        Optional<Attendance> attendance = editPersonDescriptor.getAttendance();
        Optional<Index> attendanceIndex = editPersonDescriptor.getAttendanceIndex();
        if (attendanceIndex.isEmpty() || attendance.isEmpty()) {
            return updatedAttendanceList;
        }
        if (!updatedAttendanceList.isValidIndex(attendanceIndex.get())) {
            throw new CommandException(AttendanceList.MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        updatedAttendanceList.editAtIndex(attendanceIndex.get(), attendance.get());
        return updatedAttendanceList;
    }

    /**
     * Returns the updated {@code GradeProgressList} if edited, or the original list if not.
     */
    public static GradeProgressList getUpdatedGradeProgressList(Person personToEdit,
            EditPersonDescriptor editPersonDescriptor) throws CommandException {
        GradeProgressList updatedGradeProgressList = personToEdit.getGradeProgressList();
        Optional<GradeProgress> gradeProgress = editPersonDescriptor.getGradeProgress();
        Optional<Index> gradeProgressIndex = editPersonDescriptor.getGradeProgressIndex();
        if (gradeProgressIndex.isEmpty() || gradeProgress.isEmpty()) {
            return updatedGradeProgressList;
        }
        if (!updatedGradeProgressList.isValidIndex(gradeProgressIndex.get())) {
            throw new CommandException(GradeProgressList.MESSAGE_INVALID_GRADE_PROGRESS_INDEX);
        }
        updatedGradeProgressList.editAtIndex(gradeProgressIndex.get(), gradeProgress.get());
        return updatedGradeProgressList;
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
        private LessonPlan lessonPlan;
        private Index homeworkIndex;
        private Homework homework;
        private Index gradeProgressIndex;
        private GradeProgress gradeProgress;
        private Index attendanceIndex;
        private Attendance attendance;
        private Set<Tag> tags;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setLessonPlan(toCopy.lessonPlan);
            setHomeworkIndex(toCopy.homeworkIndex);
            setHomework(toCopy.homework);
            setGradeProgressIndex(toCopy.gradeProgressIndex);
            setGradeProgress(toCopy.gradeProgress);
            setAttendanceIndex(toCopy.attendanceIndex);
            setAttendance(toCopy.attendance);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, lessonPlan, homework, gradeProgress, attendance, tags);
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

        public void setLessonPlan(LessonPlan lessonPlan) {
            this.lessonPlan = lessonPlan;
        }

        public Optional<LessonPlan> getLessonPlan() {
            return Optional.ofNullable(lessonPlan);
        }

        public void setHomework(Homework homework) {
            this.homework = homework;
        }

        public Optional<Homework> getHomework() {
            return Optional.ofNullable(homework);
        }

        public void setHomeworkIndex(Index homeworkIndex) {
            this.homeworkIndex = homeworkIndex;
        }

        public Optional<Index> getHomeworkIndex() {
            return Optional.ofNullable(homeworkIndex);
        }

        public void setGradeProgress(GradeProgress gradeProgress) {
            this.gradeProgress = gradeProgress;
        }

        public Optional<GradeProgress> getGradeProgress() {
            return Optional.ofNullable(gradeProgress);
        }

        public void setGradeProgressIndex(Index gradeProgressIndex) {
            this.gradeProgressIndex = gradeProgressIndex;
        }

        public Optional<Index> getGradeProgressIndex() {
            return Optional.ofNullable(gradeProgressIndex);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
        }

        public void setAttendanceIndex(Index attendanceIndex) {
            this.attendanceIndex = attendanceIndex;
        }

        public Optional<Index> getAttendanceIndex() {
            return Optional.ofNullable(attendanceIndex);
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
                    && getLessonPlan().equals(e.getLessonPlan())
                    && getTags().equals(e.getTags());
        }
    }
}
