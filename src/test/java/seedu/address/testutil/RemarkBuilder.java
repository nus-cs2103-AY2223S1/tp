package seedu.address.testutil;

import seedu.address.model.remark.Remark;
import seedu.address.model.remark.Text;

/**
 * A utility class to help with building Remark objects.
 */
public class RemarkBuilder {

    public static final String DEFAULT_TEXT = "Fast dealer";
    private Text text;

    /**
     * Creates a {@code RemarkBuilder} with the default details.
     */
    public RemarkBuilder() {
        text = new Text(DEFAULT_TEXT);
    }

    /**
     * Initializes the RemarkBuilder with the data of {@code remarkToCopy}.
     */
    public RemarkBuilder(Remark remarkToCopy) {
        text = remarkToCopy.getText();
    }

    /**
     * Sets the {@code Text} of the {@code Remark} that we are building.
     */
    public RemarkBuilder withText(String text) {
        this.text = new Text(text);
        return this;
    }

    public Remark build() {
        return new Remark(text);
    }

}
