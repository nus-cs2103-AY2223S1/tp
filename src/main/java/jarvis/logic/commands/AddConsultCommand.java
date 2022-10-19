package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Consult;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
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
            + PREFIX_LESSON + "LESSON_DESC "
            + PREFIX_START_DATE_TIME + "START_DATE_TIME "
            + PREFIX_END_DATE_TIME + "END_DATE_TIME "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LESSON + "Consultation on recursion "
            + PREFIX_START_DATE_TIME + "2022-10-14 20:00 "
            + PREFIX_END_DATE_TIME + "2022-10-14 21:00 "
            + PREFIX_STUDENT_INDEX + "3 "
            + PREFIX_STUDENT_INDEX + "4";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULT = "This consultation already exists in JARVIS";

    private final LessonDesc consultDesc;
    private final TimePeriod consultPeriod;
    private final Set<Index> studentIndexSet;

    /**
     * Creates an AddConsultCommand to add the specified {@code Consult} with
     * {@code consultDesc, consultPeriod, studentIndexSet}
     */
    public AddConsultCommand(LessonDesc consultDesc, TimePeriod consultPeriod,
                                  Set<Index> studentIndexSet) {
        requireNonNull(consultDesc);
        requireNonNull(consultPeriod);
        requireNonNull(studentIndexSet);
        this.consultDesc = consultDesc;
        this.consultPeriod = consultPeriod;
        this.studentIndexSet = studentIndexSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        Set<Student> studentSet = new HashSet<>();
        for (Index studentIndex : studentIndexSet) {
            if (studentIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            Student studentToAdd = lastShownList.get(studentIndex.getZeroBased());
            studentSet.add(studentToAdd);
        }
        LessonAttendance consultAttendance = new LessonAttendance(studentSet);
        LessonNotes consultNotes = new LessonNotes(studentSet);
        Consult consultToAdd = new Consult(consultDesc, consultPeriod, consultAttendance, consultNotes);

        if (model.hasLesson(consultToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULT);
        }

        model.addLesson(consultToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, consultToAdd), true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddConsultCommand // instanceof handles nulls
                && consultDesc.equals(((AddConsultCommand) other).consultDesc))
                && consultPeriod.equals(((AddConsultCommand) other).consultPeriod)
                && studentIndexSet.equals(((AddConsultCommand) other).studentIndexSet);
    }
}
