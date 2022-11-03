package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_FUTURE_ASSESSMENT_DIFFICULTY;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.watson.logic.algorithm.PredictionUtil;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.student.Attendance;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.subject.Subject;

/**
 * Gets the predicted score for a given student's next assessment for a subject
 * that they take.
 */
public class PredictionCommand extends Command {

    public static final String COMMAND_WORD = "predict";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Predicts the next assessment score"
        + "that a chosen student might get for a given Subject.\n"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_SUBJECT + "SUBJECT "
        + PREFIX_FUTURE_ASSESSMENT_DIFFICULTY + "DIFFICULTY (Difficulty should lie between 0 and 5 (inclusive))";

    public static final String SHOWING_PREDICTION_MESSAGE = "Opened prediction window.";
    public static final String STUDENT_NOT_FOUND_ERROR_MESSAGE =
        "Student not found! Remember to use the student's full name!";

    private static final String MESSAGE_FORMAT = "Grade prediction for %s for their next %s assessment is %.2f percent";

    private final Name name;
    private final String subjectName;
    private final double difficulty;

    /**
     * Creates a PredictionCommand to get the predicted score for a given student's next assessment
     */
    public PredictionCommand(Name name, String subjectName, double futureAssessmentDifficulty) {
        this.name = name;
        this.subjectName = subjectName;
        this.difficulty = futureAssessmentDifficulty;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Student target;
        try {
            target = model.getStudentByName(name);
        } catch (Exception e) {
            throw new CommandException(STUDENT_NOT_FOUND_ERROR_MESSAGE);
        }
        Attendance attendance = target.getAttendance();
        Subject targetSubject = null;
        for (Subject subj : target.getSubjectsTaken()) {
            if (subj.getSubjectName().equalsIgnoreCase(subjectName)) {
                targetSubject = target.getSubjectHandler().getSubject(subjectName);
            }
        }
        if (targetSubject == null) {
            throw new CommandException("Student does not take this subject");
        }
        double gradePredicted = PredictionUtil.predictGrade(targetSubject.getGrades(), attendance, difficulty);
        return new CommandResult(SHOWING_PREDICTION_MESSAGE, false, false, true, false,
            String.format(MESSAGE_FORMAT, name, targetSubject.subjectName, gradePredicted));
    }
}
