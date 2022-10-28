package seedu.studmap.model.attribute;

import static seedu.studmap.model.attribute.AttributeType.valueOf;

import java.util.Comparator;

import seedu.studmap.model.attribute.exceptions.AttributeNotFoundException;
import seedu.studmap.model.student.Student;

/**
 * Represents an attribute.
 * Guarantees: immutable; attribute is valid as declared in {@link #isValidAttributeType(String)}
 */
public abstract class Attribute {

    public static final String MESSAGE_CONSTRAINTS = "Invalid Attribute entered";

    /**
     * Returns true if a given string is a valid attribute name.
     */
    public static boolean isValidAttributeType(String test) {
        try {
            valueOf(test.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            return false;
        }
        return true;
    }

    /**
     * Returns the Comparator to sort the given attribute
     */
    public static Comparator<Student> getAttributeComparator(AttributeType attributeTypeEnum)
            throws AttributeNotFoundException {
        Comparator<Student> resultComparator;

        switch (attributeTypeEnum) {

        case NAME:
            resultComparator = Comparator.comparing(Student::getNameString, String::compareToIgnoreCase);
            break;

        case PHONE:
            resultComparator = Comparator.comparing(Student::getPhoneString);
            break;

        case MODULE:
            resultComparator = Comparator.comparing(Student::getModuleString, String::compareToIgnoreCase);
            break;

        case ID:
            resultComparator = Comparator.comparing(Student::getIdString, String::compareToIgnoreCase);
            break;

        case GIT:
            resultComparator = Comparator.comparing(Student::getGitString, String::compareToIgnoreCase);
            break;

        case HANDLE:
            resultComparator = Comparator.comparing(Student::getHandleString, String::compareToIgnoreCase);
            break;

        case EMAIL:
            resultComparator = Comparator.comparing(Student::getEmailString, String::compareToIgnoreCase);
            break;

        case ATTENDANCE:
            resultComparator = Comparator.comparing(Student::getAttendancePercentage);
            break;

        case ASSIGNMENT:
            resultComparator = Comparator.comparing(Student::getAssignmentMarkedCount);
            break;

        case PARTICIPATION:
            resultComparator = Comparator.comparing(Student::getParticipationPercentage);
            break;

        default:
            throw new AttributeNotFoundException();
        }
        return resultComparator;
    }
}
