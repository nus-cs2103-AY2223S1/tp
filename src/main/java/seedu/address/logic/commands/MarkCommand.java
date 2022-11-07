package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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

        markedPerson.setFullView();
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

    public static HomeworkList getUpdatedHomeworkList(Person personToMark, MarkPersonDescriptor markPersonDescriptor)
            throws CommandException {
        HomeworkList updatedHomeworkList = personToMark.getHomeworkList();
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

}
