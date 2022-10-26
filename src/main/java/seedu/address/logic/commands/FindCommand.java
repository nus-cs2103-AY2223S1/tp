package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.Model.ListType;
import seedu.address.model.StudentContainsKeywordsPredicate;
import seedu.address.model.person.student.Student;


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

    private final HashMap<Prefix, String> keywords;


    private StudentContainsKeywordsPredicate<Student> studentPredicate;
//    private NameContainsKeywordsPredicate<Tutor> tutorPredicate;
//    private NameContainsKeywordsPredicate<TuitionClass> classPredicate;

    public FindCommand(HashMap<Prefix, String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ListType type = model.getCurrentListType();

        this.studentPredicate = new StudentContainsKeywordsPredicate<>(keywords);
        model.updateFilteredStudentList(studentPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));

//        switch (type) {
//        case STUDENT_LIST:
//            if (prefix.equals(PREFIX_NAME)) {
//                this.studentNamePredicate = new NameContainsKeywordsPredicate<>(keyword);
//                model.updateFilteredStudentList(studentNamePredicate);
//            } else if (prefix.equals(PREFIX_ADDRESS)) {
//                this.studentAddressPredicate = new AddressContainsKeywordsPredicate<>(keyword);
//                model.updateFilteredStudentList(studentAddressPredicate);
//            } else if (prefix.equals(PREFIX_EMAIL)) {
//                this.studentEmailPredicate = new EmailContainsKeywordsPredicate<>(keyword);
//                model.updateFilteredStudentList(studentEmailPredicate);
//            } else if (prefix.equals(PREFIX_PHONE)) {
//                this.studentPhonePredicate = new PhoneContainsKeywordsPredicate<>(keyword);
//                model.updateFilteredStudentList(studentPhonePredicate);
//            } else if (prefix.equals(PREFIX_SCHOOL)) {
//                this.studentSchoolPredicate = new SchoolContainsKeywordsPredicate<>(keyword);
//                model.updateFilteredStudentList(studentSchoolPredicate);
//            } else {
//                this.studentLevelPredicate = new LevelContainsKeywordsPredicate<>(keyword);
//                model.updateFilteredStudentList(studentLevelPredicate);
//            }
//            return new CommandResult(
//                    String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
//
//        case TUTOR_LIST:
//            if (prefix.equals(PREFIX_NAME)) {
//                this.tutorNamePredicate = new NameContainsKeywordsPredicate<>(keywords);
//                model.updateFilteredTutorList(tutorNamePredicate);
//            } else if (prefix.equals(PREFIX_ADDRESS)) {
//                this.tutorAddressPredicate = new AddressContainsKeywordsPredicate<>(keywords);
//                model.updateFilteredTutorList(tutorAddressPredicate);
//            } else if (prefix.equals(PREFIX_EMAIL)) {
//                this.tutorEmailPredicate = new EmailContainsKeywordsPredicate<>(keywords);
//                model.updateFilteredTutorList(tutorEmailPredicate);
//            } else if (prefix.equals(PREFIX_PHONE)) {
//                this.tutorPhonePredicate = new PhoneContainsKeywordsPredicate<>(keywords);
//                model.updateFilteredTutorList(tutorPhonePredicate);
//            } else if (prefix.equals(PREFIX_SCHOOL)) {
//                this.tutorQualificationPredicate = new QualificationContainsKeywordsPredicate<>(keywords);
//                model.updateFilteredTutorList(tutorQualificationPredicate);
//            } else {
//                this.tutorInstitutionPredicate = new InstitutionContainsKeywordsPredicate<>(keywords);
//                model.updateFilteredTutorList(tutorInstitutionPredicate);
//            }
//            return new CommandResult(
//                    String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size()));
//        default:
////            assert (type == ListType.TUITIONCLASS_LIST);
////            this.classPredicate = new NameContainsKeywordsPredicate<>(keywords);
////            model.updateFilteredTuitionClassList(classPredicate);
//            return new CommandResult(
//                    String.format(Messages.MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW,
//                            model.getFilteredTuitionClassList().size()));
//        }
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
//            } else if (this.tutorPredicate != null) {
//                if (otherFind.tutorPredicate != null) {
//                    return this.tutorPredicate.equals(otherFind.tutorPredicate);
//                }
//            } else if (this.classPredicate != null) {
//                if (otherFind.classPredicate != null) {
//                    return this.classPredicate.equals(otherFind.classPredicate);
//                }
            }
            return this.keywords.equals(otherFind.keywords);
        }
        return false;
    }
}
