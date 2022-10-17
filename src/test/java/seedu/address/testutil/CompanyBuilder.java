package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.company.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Company objects.
 */
public class CompanyBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "Bedok Mall";
    private CompanyName name;
    private CompanyAddress address;
    private Set<Tag> tags;

    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        name = new CompanyName(DEFAULT_NAME);
        address = new CompanyAddress(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code companyToCopy}.
     */
    public CompanyBuilder(Company companyToCopy) {
        name = companyToCopy.getName();
        address = companyToCopy.getAddress();
        tags = new HashSet<>(companyToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Company} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = new CompanyName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Company} that we are building.
     */
    public CompanyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAddress(String address) {
        this.address = new CompanyAddress(address);
        return this;
    }

    public Company build() {
        return new Company(name, address, tags);
    }

}
