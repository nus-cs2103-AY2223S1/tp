package seedu.address.testutil;

import seedu.address.model.team.Link;
import seedu.address.model.team.LinkName;
import seedu.address.model.team.Url;

public class LinkBuilder {
    public static final String DEFAULT_NAME = "Google";
    public static final String DEFAULT_URL = "https://google.com";
    private LinkName name;
    private Url url;

    /**
     * Creates a {@code LinkBuilder} with the default details.
     */
    public LinkBuilder() {
        name = new LinkName(DEFAULT_NAME);
        url = new Url(DEFAULT_URL);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code linkToCopy}.
     */
    public LinkBuilder(Link linkToCopy) {
        name = linkToCopy.getDisplayedName();
        url = linkToCopy.getUrl();
    }

    /**
     * Sets the {@code Name} of the {@code Link} that we are building.
     */
    public LinkBuilder withName(String name) {
        this.name = new LinkName(name);
        return this;
    }


    /**
     * Sets the {@code Url} of the {@code Link} that we are building.
     */
    public LinkBuilder withEmail(String url) {
        this.url = new Url(url);
        return this;
    }

    public Link build() {
        return new Link(name, url);
    }
}
