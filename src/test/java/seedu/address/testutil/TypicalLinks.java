package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.team.Link;
import seedu.address.model.team.Url;

/**
 * A utility class containing a list of {@code Link} objects to be used in tests.
 */
public class TypicalLinks {
    public static final Link LINK_GOOGLE = new Link(new Name("google"), new Url("https://google.com"));

    public static final Link LINK_GOOGLE_DUPLICATED = new Link(new Name("google"), new Url("https://google.com"));

    public static final Link LINK_FACEBOOK = new Link(new Name("google"), new Url("https://facebook.com"));
}
