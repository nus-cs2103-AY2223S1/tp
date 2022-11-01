package jarvis.logic.commands;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.LessonDesc;
import jarvis.model.Model;
import jarvis.model.Student;
import jarvis.model.TimePeriod;

/**
 * Adds a consultation lesson to the lesson book.
 */
public class AddConsultCommand extends Command {

    public static final String COMMAND_WORD = "addconsult";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation lesson to JARVIS.\n"
            + "Parameters: "
            + "[" + PREFIX_LESSON + "LESSON_DESC] "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_START_TIME + "START_TIME ["
            + PREFIX_END_DATE + "END_DATE] "
            + PREFIX_END_TIME + "END_TIME "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX..."
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_LESSON + "Consultation on recursion "
            + PREFIX_START_DATE + "2022-10-14 "
            + PREFIX_START_TIME + "12:00 "
            + PREFIX_END_TIME + "14:00 "
            + PREFIX_STUDENT_INDEX + "3 "
            + PREFIX_STUDENT_INDEX + "4";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULT = "This consultation already exists in JARVIS";
    public static final String MESSAGE_TIME_PERIOD_CLASH = "This consultation overlaps with another lesson in JARVIS";
    public static final String MESSAGE_START_DATE_AFTER_END_DATE =
            "This consultation's end date time must be after start date time";

    private final LessonDesc consultDesc;
    private final TimePeriod consultPeriod;
    private final Set<Index> studentIndexSet;

    /**
     * Creates an AddConsultCommand to add the specified {@code Consult} with
     * {@code consultDesc, consultPeriod, studentIndexSet}
     */
    public AddConsultCommand(LessonDesc consultDesc, TimePeriod consultPeriod,
                                  Set<Index> studentIndexSet) {
        requireAllNonNull(consultPeriod, studentIndexSet);
        this.consultDesc = consultDesc;
        this.consultPeriod = consultPeriod;
        this.studentIndexSet = studentIndexSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        List<Lesson> allLessonList = model.getFilteredLessonList();

        Set<Student> studentSet = new TreeSet<>((s1, s2) -> {
            int result = s1.getName().toString().toLowerCase().compareTo(s2.getName().toString().toLowerCase());
            if (result == 0) {
                return s1.getMatricNum().toString().compareTo(s2.getMatricNum().toString());
            }
            return result;
        });

        for (Index studentIndex : studentIndexSet) {
            if (studentIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            Student studentToAdd = lastShownList.get(studentIndex.getZeroBased());
            studentSet.add(studentToAdd);
        }

        Consult consultToAdd = new Consult(consultDesc, consultPeriod, studentSet);

        if (model.hasLesson(consultToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULT);
        } else if (model.hasPeriodClash(consultToAdd)) {
            allLessonList.stream().filter(consultToAdd::hasTimingConflict).forEach(Lesson::markClash);
            // Update for JavaFX
            for (Lesson l : allLessonList) {
                if (l.hasTimingConflict()) {
                    model.setLesson(l, l);
                }
            }
            throw new CommandException(MESSAGE_TIME_PERIOD_CLASH);
        }

        model.addLesson(consultToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, consultToAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof AddConsultCommand)) { // instanceof handles nulls
            return false;
        }

        AddConsultCommand ac = (AddConsultCommand) other;

        boolean consultDescEquality;
        if (consultDesc == null) {
            consultDescEquality = ac.consultDesc == null;
        } else {
            consultDescEquality = ac.equals(ac.consultDesc);
        }
        return consultDescEquality
                && consultPeriod.equals(ac.consultPeriod)
                && studentIndexSet.equals(ac.studentIndexSet);
    }
}
