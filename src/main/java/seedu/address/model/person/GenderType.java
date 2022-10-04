package seedu.address.model.person;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
public enum GenderType {
    MALE("Male"),
    FEMALE("Female"),
    NA("N/A"); //NA is default gender option

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
