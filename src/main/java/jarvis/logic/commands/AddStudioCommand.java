package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static jarvis.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.LessonAttendance;
import jarvis.model.LessonDesc;
import jarvis.model.LessonNotes;
import jarvis.model.Model;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;

/**
 * Adds a studio lesson to the lesson book.
 */
public class AddStudioCommand extends Command {

    public static final String COMMAND_WORD = "addstudio";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a studio lesson to JARVIS.\n"
            + "Parameters: "
            + PREFIX_LESSON + "LESSON_DESC "
            + PREFIX_START_DATE_TIME + "START_DATE_TIME "
            + PREFIX_END_DATE_TIME + "END_DATE_TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LESSON + "Studio 3 "
            + PREFIX_START_DATE_TIME + "2022-10-12T14:00 "
            + PREFIX_END_DATE_TIME + "2022-10-12T16:00 ";

    public static final String MESSAGE_SUCCESS = "New studio added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDIO = "This studio already exists in JARVIS";

    private final LessonDesc studioDesc;
    private final TimePeriod studioPeriod;

    /**
     * Creates an AddStudioCommand to add the specified {@code Studio}
     * with {@code studioDesc, studioPeriod}
     */
    public AddStudioCommand(LessonDesc studioDesc, TimePeriod studioPeriod) {
        requireNonNull(studioDesc);
        requireNonNull(studioPeriod);
        this.studioDesc = studioDesc;
        this.studioPeriod = studioPeriod;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();

        LessonAttendance studioAttendance = new LessonAttendance(lastShownStudentList);
        LessonNotes studioNotes = new LessonNotes(lastShownStudentList);
        StudioParticipation studioParticipation = new StudioParticipation(lastShownStudentList);

        Studio studioToAdd = new Studio(studioDesc, studioPeriod,
                studioAttendance, studioParticipation, studioNotes);

        if (model.hasLesson(studioToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDIO);
        }

        model.addLesson(studioToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studioToAdd), true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudioCommand // instanceof handles nulls
                && studioDesc.equals(((AddStudioCommand) other).studioDesc))
                && studioPeriod.equals(((AddStudioCommand) other).studioPeriod);
    }


}
