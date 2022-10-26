package seedu.address.model.social;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Social in uNivUSal.
 */
public class Social {

    enum Socials {
        WHATSAPP,
        TELEGRAM,
        EMAIL,
        INSTAGRAM
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Socials can take any values, and it should not be blank";

    /*
     * The first character of the social link must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    //links array holds the links for the socials of the person.
    //The index of the links in the array corresponds with the enum declared.
    //Example: links[0] should hold the link to the Whatsapp social media account.
    private String[] links;

    private Socials preferred;

    /**
     * Constructs a {@code Social}.
     */
    public Social() {
        this.links = new String[4];
    }

    /**
     * Returns true if a given string is a valid URL link.
     */
    public static boolean isValidSocial(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Adds the Whatsapp link of the person into the links array.
     * @param link
     */
    public void addWhatsapp(String link) {
        requireNonNull(link);
        links[0] = link;
    }

    /**
     * Adds the Telegram link of the person into the links array.
     * @param link
     */
    public void addTelegram(String link) {
        requireNonNull(link);
        links[1] = link;
    }

    /**
     * Adds the Email link of the person into the links array.
     * @param link
     */
    public void addEmail(String link) {
        requireNonNull(link);
        links[2] = link;
    }

    /**
     * Adds the Instagram link of the person into the links array.
     * @param link
     */
    public void addInstagram(String link) {
        requireNonNull(link);
        links[3] = link;
    }

    /**
     * Sets the Whatsapp link to null
     */
    public void deleteWhatsapp() {
        links[0] = null;
    }

    /**
     * Sets the Telegram link to null
     */
    public void deleteTelegram() {
        links[1] = null;
    }

    /**
     * Sets the Email link to null
     */
    public void deleteEmail() {
        links[2] = null;
    }

    /**
     * Sets the Instagram link to null
     */
    public void deleteInstagram() {
        links[3] = null;
    }

    /**
     * Returns Whatsapp link of the person.
     *
     * @return Whatsapp link of the person.
     */
    public String getWhatsapp() {
        return links[0];
    }

    /**
     * Returns Telegram link of the person.
     *
     * @return Telegram link of the person.
     */
    public String getTelegram() {
        return links[1];
    }

    /**
     * Returns Email link of the person.
     *
     * @return Email link of the person.
     */
    public String getEmail() {
        return links[2];
    }

    /**
     * Returns Instagram link of the person.
     *
     * @return Instagram link of the person.
     */
    public String getInstagram() {
        return links[3];
    }

    /**
     * Sets input to be the preferred Socials for communication.
     * @param input preferred Socials for communication.
     */
    public void prefer(String input) {
        for (Socials s : Socials.values()) {
            if (input.equalsIgnoreCase(s.toString())) {
                this.preferred = s;
            }
        }
    }

    /**
     * Returns the preferred Socials for communication.
     * @return preferred Socials for communication.
     */
    public String getPreferred() {
        return this.preferred.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Social // instanceof handles nulls
                && this.toString().equals(((Social) other).toString())); // state check
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            builder.append(links[i - 1]);
            builder.append(" ");
        }
        builder.append(preferred);
        return builder.toString();
    }
}
