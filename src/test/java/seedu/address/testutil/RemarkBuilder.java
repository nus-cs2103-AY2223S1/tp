package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.remark.Remark;
import seedu.address.model.remark.Text;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Remark objects.
 */
public class RemarkBuilder {

    public static final String DEFAULT_TEXT = "Fast dealer";
    private Text text;
    private Set<Tag> tags;

    /**
     * Creates a {@code RemarkBuilder} with the default details.
     */
    public RemarkBuilder() {
        text = new Text(DEFAULT_TEXT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the RemarkBuilder with the data of {@code remarkToCopy}.
     */
    public RemarkBuilder(Remark remarkToCopy) {
        text = remarkToCopy.getText();
        tags = new HashSet<>(remarkToCopy.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Remark} that we are building.
     */
    public RemarkBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Text} of the {@code Remark} that we are building.
     */
    public RemarkBuilder withText(String text) {
        this.text = new Text(text);
        return this;
    }

    public Remark build() {
        return new Remark(text, tags);
    }

}
