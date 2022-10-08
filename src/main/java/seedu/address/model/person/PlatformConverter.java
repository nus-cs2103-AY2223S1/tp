package seedu.address.model.person;

import seedu.address.logic.parser.exceptions.SocialNotFoundException;

/**
 * Parses a Person's social media platform from a string.
 */
public class PlatformConverter {
    /**
     * Parses a string to a Platform enum.
     *
     * @param platformStr String to be parsed.
     * @return Platform enum.
     * @throws SocialNotFoundException If the string is not a valid platform.
     */
    public static Platform stringToPlatform(String platformStr) throws SocialNotFoundException {
        switch (platformStr) {
        case "fb":
            return Platform.fb;
        case "ig":
            return Platform.ig;
        case "sc":
            return Platform.sc;
        default:
            throw new SocialNotFoundException();
        }
    }

}
