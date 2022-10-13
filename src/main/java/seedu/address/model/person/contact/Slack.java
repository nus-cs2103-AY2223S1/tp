package seedu.address.model.person.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Slack account in the contact book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSlack(String)}
 */
public class Slack extends Contact {

    public static final String MESSAGE_CONSTRAINTS = "Slack channel names must be up to 20 characters long.";
    public static final String VALIDATION_REGEX = "^[a-z0-9-_]{1}[a-z0-9-_]{0,20}$";
    private static final String SLACK_LINK_PREFIX = "https://slack.com/app_redirect?channel=";
    private static final ContactType CONTACT_TYPE = ContactType.SLACK;

    /**
     * Constructs an {@code Slack}.
     *
     * @param channelName A valid channel name.
     */
    public Slack(String channelName) {
        super(CONTACT_TYPE, channelName, SLACK_LINK_PREFIX + channelName);
        requireNonNull(channelName);
        checkArgument(isValidSlack(channelName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid slack channel name.
     */
    public static boolean isValidSlack(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
