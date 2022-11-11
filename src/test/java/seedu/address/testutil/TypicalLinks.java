package seedu.address.testutil;

import seedu.address.model.team.Link;
import seedu.address.model.team.LinkName;
import seedu.address.model.team.Url;

/**
 * A utility class containing a list of {@code Link} objects to be used in tests.
 */
public class TypicalLinks {
    public static final Link LINK_GOOGLE = new Link(new LinkName("google"), new Url("https://google.com"));

    public static final Link LINK_GOOGLE_DUPLICATED = new Link(new LinkName("google"), new Url("https://google.com"));

    public static final Link LINK_FACEBOOK = new Link(new LinkName("facebook"), new Url("https://facebook.com"));

    public static final Link LINK_YOUTUBE = new Link(new LinkName("youtube"), new Url("https://youtube.com"));
}
