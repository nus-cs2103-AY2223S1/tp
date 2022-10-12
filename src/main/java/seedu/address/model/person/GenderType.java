package seedu.address.model.person;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum class of a Person's gender in the address book.
 * The 3 types of gender enum are: MALE, FEMALE, NA
 */
public enum GenderType {

    // Declaration order defines the natural order of GenderType
    // Natural order is used in the compareTo() method

    NO_GENDER("NA"), //NA is the default gender option
    FEMALE("Female"),
    MALE("Male");

    private static final Set<String> MALE_GENDERS = new HashSet<>(Arrays.asList("m", "male", "M", "Male"));
    private static final Set<String> FEMALE_GENDERS = new HashSet<>(Arrays.asList("F", "Female", "f", "female"));
    private String gender;


    GenderType(String gender) {
        this.gender = gender;
    }

    public static GenderType getGenderType(String gender) {
        if (MALE_GENDERS.contains(gender)) {
            return MALE;
        } else if (FEMALE_GENDERS.contains(gender)) {
            return FEMALE;
        } else {
            return NO_GENDER;
        }
    }

    @Override
    public String toString() {
        return this.gender;
    }
}
