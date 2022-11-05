package seedu.studmap.model.attribute;

import static seedu.studmap.model.attribute.AttributeType.valueOf;

import java.util.Comparator;

import seedu.studmap.model.attribute.exceptions.AttributeNotFoundException;
import seedu.studmap.model.order.Order;
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
    public static Comparator<Student> getAttributeComparator(AttributeType attributeTypeEnum, Order order)
            throws AttributeNotFoundException {
        Comparator<Student> resultComparator;

        switch (attributeTypeEnum) {

        case NAME:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getCmpNameString,
                        Comparator.nullsFirst(String::compareToIgnoreCase));
            } else {
                resultComparator = Comparator.comparing(Student::getCmpNameString,
                        Comparator.nullsLast(String::compareToIgnoreCase));
            }
            break;

        case PHONE:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getCmpPhoneString,
                        Comparator.nullsFirst(String::compareToIgnoreCase));
            } else {
                resultComparator = Comparator.comparing(Student::getCmpPhoneString,
                        Comparator.nullsLast(String::compareToIgnoreCase));
            }
            break;

        case MODULE:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getCmpModuleString,
                        Comparator.nullsFirst(String::compareToIgnoreCase));
            } else {
                resultComparator = Comparator.comparing(Student::getCmpModuleString,
                        Comparator.nullsLast(String::compareToIgnoreCase));
            }
            break;

        case ID:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getCmpIdString,
                        Comparator.nullsFirst(String::compareToIgnoreCase));
            } else {
                resultComparator = Comparator.comparing(Student::getCmpIdString,
                        Comparator.nullsLast(String::compareToIgnoreCase));
            }
            break;

        case GIT:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getCmpGitString,
                        Comparator.nullsFirst(String::compareToIgnoreCase));
            } else {
                resultComparator = Comparator.comparing(Student::getCmpGitString,
                        Comparator.nullsLast(String::compareToIgnoreCase));
            }
            break;

        case HANDLE:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getCmpHandleString,
                        Comparator.nullsFirst(String::compareToIgnoreCase));
            } else {
                resultComparator = Comparator.comparing(Student::getCmpHandleString,
                        Comparator.nullsLast(String::compareToIgnoreCase));
            }
            break;

        case EMAIL:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getCmpEmailString,
                        Comparator.nullsFirst(String::compareToIgnoreCase));
            } else {
                resultComparator = Comparator.comparing(Student::getCmpEmailString,
                        Comparator.nullsLast(String::compareToIgnoreCase));
            }
            break;

        case ATTENDANCE:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getAttendancePercentageForDsc);
            } else {
                resultComparator = Comparator.comparing(Student::getAttendancePercentageForAsc);
            }
            break;

        case ASSIGNMENT:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getAssignmentMarkedCountForDsc);
            } else {
                resultComparator = Comparator.comparing(Student::getAssignmentMarkedCountForAsc);
            }
            break;

        case PARTICIPATION:
            if (order.isDescending()) {
                resultComparator = Comparator.comparing(Student::getParticipationPercentageForDsc);
            } else {
                resultComparator = Comparator.comparing(Student::getParticipationPercentageForAsc);
            }
            break;

        default:
            throw new AttributeNotFoundException();
        }
        return resultComparator;
    }
}
