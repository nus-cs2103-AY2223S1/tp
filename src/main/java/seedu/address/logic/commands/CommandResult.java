package seedu.address.logic.commands;

import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Prediction information should be shown to the user.
     */
    private final boolean showPrediction;

    private final String gradePredicted;

    private final boolean showGradeWindow;

    private List<Person> studentsToGrade;

    private String assessmentString;
    /**
     *
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, false, "0.0");
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showPrediction, boolean showGradeWindow, String gradePredicted) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showPrediction = showPrediction;
        this.gradePredicted = gradePredicted;
        this.showGradeWindow = showGradeWindow;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false,false, "0.0");
    }
    public CommandResult(String feedbackToUser, boolean showGradeWindow,
                         List<Person> studentsToGrade, String assessmentString) {
        this(feedbackToUser, false, false, false, true, "0.0");
        this.studentsToGrade = studentsToGrade;
        this.assessmentString = assessmentString;
    }
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowPrediction() {
        return showPrediction;
    }

    public boolean isShowGradeWindow() { return showGradeWindow;}
    public boolean isExit() {
        return exit;
    }

    public String getGradePredicted() {
        return gradePredicted;
    }

    public List<Person> getStudentsToGrade() {
        return studentsToGrade;
    }
    public String getAssessmentString() {return assessmentString;}
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
               && showHelp == otherCommandResult.showHelp
               && exit == otherCommandResult.exit;
    }

}
