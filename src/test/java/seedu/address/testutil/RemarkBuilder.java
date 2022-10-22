package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.remark.Remark;
import seedu.address.model.remark.RemarkAddress;
import seedu.address.model.remark.RemarkName;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Remark objects.
 */
public class RemarkBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "Bedok Mall";
    private RemarkName name;
    private RemarkAddress address;
    private Set<Tag> tags;

    /**
     * Creates a {@code RemarkBuilder} with the default details.
     */
    public RemarkBuilder() {
        name = new RemarkName(DEFAULT_NAME);
        address = new RemarkAddress(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the RemarkBuilder with the data of {@code companyToCopy}.
     */
    public RemarkBuilder(Remark companyToCopy) {
        name = companyToCopy.getName();
        address = companyToCopy.getAddress();
        tags = new HashSet<>(companyToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Remark} that we are building.
     */
    public RemarkBuilder withName(String name) {
        this.name = new RemarkName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Remark} that we are building.
     */
    public RemarkBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Remark} that we are building.
     */
    public RemarkBuilder withAddress(String address) {
        this.address = new RemarkAddress(address);
        return this;
    }

    public Remark build() {
        return new Remark(name, address, tags);
    }

}
