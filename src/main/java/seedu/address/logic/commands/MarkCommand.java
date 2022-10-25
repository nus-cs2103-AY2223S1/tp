package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.person.SessionList;
import seedu.address.model.tag.Tag;


/**
 * Marks the details of an existing person in the address book
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_HOMEWORK + "INDEX HOMEWORK]"
            + "[" + PREFIX_ATTENDANCE + "INDEX ATTENDANCE]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_HOMEWORK + " 1 ";

    public static final String MESSAGE_MARKED_PERSON_SUCCESS = "Marked Person Detail: %1$s";
    public static final String MESSAGE_NOT_VIEW_MODE =
            "You need to be in full view mode to mark a person's details.";

    private final MarkPersonDescriptor markPersonDescriptor;

    /**
     * @param markPersonDescriptor details to edit the person with
     */
    public MarkCommand(MarkPersonDescriptor markPersonDescriptor) {
        requireNonNull(markPersonDescriptor);

        this.markPersonDescriptor = new MarkPersonDescriptor(markPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Index index = Index.fromOneBased(1);
        requireNonNull(model);
        if (!model.isFullView() || model.isDayView()) {
            throw new CommandException(MESSAGE_NOT_VIEW_MODE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMark = lastShownList.get(index.getZeroBased());
        Person markedPerson = createMarkedPerson(personToMark, markPersonDescriptor);

        model.setPerson(personToMark, markedPerson);
        return new CommandResult(String.format(MESSAGE_MARKED_PERSON_SUCCESS, markedPerson));
    }

    private static Person createMarkedPerson(Person personToMark, MarkPersonDescriptor markPersonDescriptor)
            throws CommandException {
        Name name = personToMark.getName();
        Phone phone = personToMark.getPhone();
        LessonPlan lessonPlan = personToMark.getLessonPlan();
        HomeworkList updatedHomeworkList = getUpdatedHomeworkList(personToMark, markPersonDescriptor);
        AttendanceList updatedAttendanceList = getUpdatedAttendanceList(personToMark, markPersonDescriptor);
        SessionList sessionList = personToMark.getSessionList();
        GradeProgressList gradeProgressList = personToMark.getGradeProgressList();
        Set<Tag> tags = personToMark.getTags();

        return new Person(name, phone, lessonPlan, updatedHomeworkList,
                updatedAttendanceList, sessionList, gradeProgressList, tags);
    }

    public static HomeworkList getUpdatedHomeworkList(Person personToEdit, MarkPersonDescriptor markPersonDescriptor)
            throws CommandException {
        HomeworkList updatedHomeworkList = personToEdit.getHomeworkList();
        Optional<Index> homeworkIndex = markPersonDescriptor.getHomeworkIndex();
        if (homeworkIndex.isEmpty()) {
            return updatedHomeworkList;
        }
        if (!updatedHomeworkList.isValidIndex(homeworkIndex.get())) {
            throw new CommandException(HomeworkList.MESSAGE_INVALID_HOMEWORK_INDEX);
        }
        updatedHomeworkList.markAtIndex(homeworkIndex.get());
        return updatedHomeworkList;
    }

    public static AttendanceList getUpdatedAttendanceList(Person personToMark,
                                                           MarkPersonDescriptor mPerDesc) throws CommandException {
        AttendanceList updatedAttendanceList = personToMark.getAttendanceList();
        Optional<Index> attendanceIndex = mPerDesc.getAttendanceIndex();
        if (attendanceIndex.isEmpty()) {
            return updatedAttendanceList;
        }
        if (!updatedAttendanceList.isValidIndex(attendanceIndex.get())) {
            throw new CommandException(AttendanceList.MESSAGE_INVALID_ATTENDANCE_INDEX);
        }
        updatedAttendanceList.markAtIndex(attendanceIndex.get());
        return updatedAttendanceList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return markPersonDescriptor.equals(e.markPersonDescriptor);
    }

    /**
     * Stores the details to mark the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class MarkPersonDescriptor {
        private Name name;
        private Phone phone;
        private LessonPlan lessonPlan;
        private Index homeworkIndex;
        private Homework homework;
        private Index gradeProgressIndex;
        private GradeProgress gradeProgress;
        private Index attendanceIndex;
        private Attendance attendance;
        private Index sessionIndex;
        private Session session;
        private Set<Tag> tags;

        public MarkPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public MarkPersonDescriptor(MarkPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setLessonPlan(toCopy.lessonPlan);
            setHomeworkIndex(toCopy.homeworkIndex);
            setHomework(toCopy.homework);
            setGradeProgressIndex(toCopy.gradeProgressIndex);
            setGradeProgress(toCopy.gradeProgress);
            setAttendanceIndex(toCopy.attendanceIndex);
            setAttendance(toCopy.attendance);
            setSessionIndex(toCopy.sessionIndex);
            setSession(toCopy.session);
            setTags(toCopy.tags);
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

        public Optional<Session> getSession() {
            return Optional.ofNullable(session);
        }

        public Optional<Index> getSessionIndex() {
            return Optional.ofNullable(sessionIndex);
        }

        public void setSessionIndex(Index sessionIndex) {
            this.sessionIndex = sessionIndex;
        }

        public void setSession(Session session) {
            this.session = session;
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
    }
}
