package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;

import java.util.HashMap;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.Model.ListType;
import seedu.address.model.StudentContainsKeywordsPredicate;
import seedu.address.model.TuitionClassContainsKeywordsPredicate;
import seedu.address.model.TutorContainsKeywordsPredicate;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students / tutors / tuition classes\n"
            + "(depending on current displayed list)\n"
            + "based on pairs of prefixes and keywords (case-insensitive) and\n"
            + "displays them as a list with index numbers.\n"
            + "Format: PREFIX/KEYWORDS [MORE PREFIX/KEYWORDS]...\n"
            + "\nParameters for Student:\nn/NAME a/ADDRESS e/EMAIL p/PHONE s/SCHOOL l/LEVEL #/TAG\n"
            + "\nParameters for Tutor:\nn/NAME a/ADDRESS e/EMAIL p/PHONE q/QUALIFICATION i/INSTITUTION #/TAG\n"
            + "\nParameters for Tuition Class:\nn/NAME d/DAY s/SUBJECT l/LEVEL t/TIME #/TAG\n"
            + "\nExample for Student:\n" + COMMAND_WORD + " n/alice e/alice@example.com s/Keming \n"
            + "\nExample for Tutor:\n" + COMMAND_WORD + " n/john a/clementi q/computing #/alwaysLate\n"
            + "\nExample for Tuition Class:\n" + COMMAND_WORD + " n/P2Math d/tuesday s/mathematics t/18:00\n"
            + "\nNOTE: Must include at least one pair of prefix and keywords.\n"
            + "NOTE: If repeated prefixes are specified, only the latest one will be taken.";

    public static final String FEEDBACK_MESSAGE = "Valid find command format:\n"
            + "find PREFIX/KEYWORD [MORE PREFIX/KEYWORD]...\n";

    private final HashMap<Prefix, String> keywords;

    private StudentContainsKeywordsPredicate<Student> studentPredicate;
    private TutorContainsKeywordsPredicate<Tutor> tutorPredicate;
    private TuitionClassContainsKeywordsPredicate<TuitionClass> classPredicate;

    public FindCommand(HashMap<Prefix, String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ListType type = model.getCurrentListType();

        switch (type) {
        case STUDENT_LIST:
            this.studentPredicate = new StudentContainsKeywordsPredicate<>(keywords);
            model.updateFilteredStudentList(studentPredicate);
            return new CommandResult(
                    String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));

        case TUTOR_LIST:
            this.tutorPredicate = new TutorContainsKeywordsPredicate<>(keywords);
            model.updateFilteredTutorList(tutorPredicate);
            return new CommandResult(
                    String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size()));

        default:
            this.classPredicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
            model.updateFilteredTuitionClassList(classPredicate);
            return new CommandResult(
                    String.format(MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW,
                            model.getFilteredTuitionClassList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof FindCommand) {
            FindCommand otherFind = (FindCommand) other;

            if (this.studentPredicate != null) {
                if (otherFind.studentPredicate != null) {
                    return this.studentPredicate.equals(((FindCommand) other).studentPredicate);
                }
            } else if (this.tutorPredicate != null) {
                if (otherFind.tutorPredicate != null) {
                    return this.tutorPredicate.equals(otherFind.tutorPredicate);
                }
            } else if (this.classPredicate != null) {
                if (otherFind.classPredicate != null) {
                    return this.classPredicate.equals(otherFind.classPredicate);
                }
            }
            return this.keywords.equals(otherFind.keywords);
        }
        return false;
    }
}
