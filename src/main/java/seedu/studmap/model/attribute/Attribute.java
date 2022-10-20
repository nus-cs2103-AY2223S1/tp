package seedu.studmap.model.attribute;

import java.util.Comparator;
import java.util.Set;

import seedu.studmap.model.attribute.exceptions.AttributeNotFoundException;
import seedu.studmap.model.student.Student;

/**
 * Represents an attribute.
 * Guarantees: immutable; attribute is valid as declared in {@link #isValidAttributeType(String)}
 */
public abstract class Attribute {

    public static final String MESSAGE_CONSTRAINTS = "Invalid Attribute entered";
    public static final Set<String> ATTRIBUTE_SET = Set.of("name", "phone", "address", "email", "attendance");

    /**
     * Returns true if a given string is a valid attribute name.
     */
    public static boolean isValidAttributeType(String test) {
        return ATTRIBUTE_SET.contains(test);
    }

    /**
     * Returns the Comparator to sort the given attribute
     *
     */
    public static Comparator<Student> getAttributeComparator(String attributeType) throws AttributeNotFoundException {
        Comparator<Student> resultComparator = Comparator.comparing(Student::getNameString);
        if (isValidAttributeType(attributeType)) {
            switch (attributeType) {

            case ("name"):
                resultComparator = Comparator.comparing(Student::getNameString);
                break;

            case ("phone"):
                resultComparator = Comparator.comparing(Student::getPhoneString);
                break;

            case ("address"):
                resultComparator = Comparator.comparing(Student::getAddressString);
                break;

            case ("email"):
                resultComparator = Comparator.comparing(Student::getEmailString);
                break;

            case ("attendance"):
                resultComparator = Comparator.comparing(Student::getAttendancePercentage);
                break;

            default:
                throw new AttributeNotFoundException();
            }
        }
        return resultComparator;
    }
}
