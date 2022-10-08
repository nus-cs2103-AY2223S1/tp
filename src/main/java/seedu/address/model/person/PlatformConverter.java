package seedu.address.model.person;

import seedu.address.logic.parser.exceptions.SocialNotFoundException;

public class PlatformConverter {

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
