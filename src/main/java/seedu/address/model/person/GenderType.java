package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum class of a Person's gender in the address book.
 * The 3 types of gender enum are: MALE, FEMALE, NA
 */
public enum GenderType {
    MALE("Male"),
    FEMALE("Female"),
    NA("N/A"); //NA is the default gender option

    private static final List<String> MALE_GENDERS = new ArrayList<>(Arrays.asList("m", "male", "M", "Male"));
    private static final List<String> FEMALE_GENDERS = new ArrayList<>(Arrays.asList("F", "Female", "f", "female"));
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
            return NA;
        }
    }

    @Override
    public String toString() {
        return this.gender;
    }
}
