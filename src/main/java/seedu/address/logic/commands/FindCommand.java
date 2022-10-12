package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Model.ListType;
import seedu.address.model.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final List<String> keywords;

    private NameContainsKeywordsPredicate<Person> personPredicate;
    private NameContainsKeywordsPredicate<Student> studentPredicate;
    private NameContainsKeywordsPredicate<Tutor> tutorPredicate;
    private NameContainsKeywordsPredicate<TuitionClass> classPredicate;

    public FindCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ListType type = model.getCurrentListType();

        switch (type) {
        case STUDENT_LIST:
            this.studentPredicate = new NameContainsKeywordsPredicate<>(keywords);
            model.updateFilteredStudentList(studentPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
        case TUTOR_LIST:
            this.tutorPredicate = new NameContainsKeywordsPredicate<>(keywords);
            model.updateFilteredTutorList(tutorPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size()));
        case TUITIONCLASS_LIST:
            this.classPredicate = new NameContainsKeywordsPredicate<>(keywords);
            model.updateFilteredTuitionClassList(classPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW,
                            model.getFilteredTuitionClassList().size()));
        case PERSON_LIST:
            this.personPredicate = new NameContainsKeywordsPredicate<>(keywords);
            model.updateFilteredPersonList(personPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
        default:
            break;
        }

        throw new CommandException("Current list type is not valid");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof FindCommand) {
            FindCommand otherFind = (FindCommand) other;

            if (this.studentPredicate != null || otherFind.studentPredicate != null) {
                return this.studentPredicate.equals(((FindCommand) other).studentPredicate);
            } else if (this.tutorPredicate != null || otherFind.tutorPredicate != null) {
                return this.tutorPredicate.equals(otherFind.tutorPredicate);
            } else if (this.classPredicate != null || otherFind.classPredicate != null) {
                return this.classPredicate.equals(otherFind.classPredicate);
            } else if (this.personPredicate != null || otherFind.personPredicate != null) {
                return this.personPredicate.equals(otherFind.personPredicate);
            } else {
                return this.keywords.equals(otherFind.keywords);
            }
        } else {
            return false;
        }
    }
}
