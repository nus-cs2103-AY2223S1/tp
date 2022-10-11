package seedu.address.model.person;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Personality {

    public static final String MESSAGE_CONSTRAINTS =
            "Personalities should only contain alphabets and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z\\s]";

    public final String personPersonality;

    public ArrayList<String> arrayOfPersonalities;

    /**
     * Constructs a {@code Personality}.
     *
     * @param personality A valid personality.
     */
    public Personality(String personality) {
        requireNonNull(personality);
        checkArgument(isValidPersonality(personality), MESSAGE_CONSTRAINTS);
        arrayOfPersonalities = new ArrayList<>();
        personPersonality = personality;
        arrayOfPersonalities.add(personality);
    }

    /**
     * Returns true if a given string is a valid Personality.
     */
    public static boolean isValidPersonality(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void add(String personality) {
        if (!arrayOfPersonalities.contains(personality)) {
            arrayOfPersonalities.add(personality);
        }
    }

    @Override
    public String toString() {
        return "Personalities: " + arrayOfPersonalities.toString();
    }

    @Override
    public int hashCode() {
        return arrayOfPersonalities.hashCode();
    }

}
