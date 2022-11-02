package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's social media in the address book.
 */
public class GameType {

    public static final String MESSAGE_CONSTRAINTS =
            "Any string can be accepted";

    /*
     * The first character of the social must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*";

    private String preferredGameType;

    /**
     * Constructs a {@code GameType}.
     *
     * @param gameType a valid preferred game type.
     */

    public GameType(String gameType) {
        requireNonNull(gameType);
        checkArgument(isValidGameType(gameType), MESSAGE_CONSTRAINTS);
        preferredGameType = gameType;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidGameType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return preferredGameType;
    }

    public String getGameTypeName() {
        return preferredGameType;
    }

    public static String getMessageConstraints() {
        return MESSAGE_CONSTRAINTS;
    }

    /**
     * Returns hashcode for purpose of the equals method.
     */
    @Override
    public int hashCode() {
        return this.getGameTypeName().hashCode();
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        } else if (!(other instanceof GameType)) {
            return false;
        }

        GameType gameType = (GameType) other;

        return this.preferredGameType.equals(gameType.preferredGameType);

    }

}

