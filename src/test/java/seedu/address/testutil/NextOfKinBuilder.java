package seedu.address.testutil;

import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.nextofkin.Relationship;


/**
 * A utility class to help with building NextOfKin objects.
 */
public class NextOfKinBuilder extends PersonBuilder {

    public static final String DEFAULT_RELATIONSHIP = "Mother";

    private Relationship relationship;

    /**
     * Creates a {@code NextOfKinBuilder} with the default details.
     */
    public NextOfKinBuilder() {
        super();
        relationship = Relationship.createRelationship(DEFAULT_RELATIONSHIP);
    }

    /**
     * Initializes the NextOfKinBuilder with the data of {@code nextOfKinToCopy}.
     */
    public NextOfKinBuilder(NextOfKin nextOfKinToCopy) {
        super(nextOfKinToCopy);
        relationship = nextOfKinToCopy.getRelationship();
    }

    /**
     * Sets the {@code Name} of the {@code NextOfKin} that we are building.
     */
    @Override
    public NextOfKinBuilder withName(String name) {
        return (NextOfKinBuilder) super.withName(name);
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code NextOfKin} that we are building.
     */
    @Override
    public NextOfKinBuilder withTags(String ... tags) {
        return (NextOfKinBuilder) super.withTags(tags);
    }

    /**
     * Sets the {@code Address} of the {@code NextOfKin} that we are building.
     */
    @Override
    public NextOfKinBuilder withAddress(String address) {
        return (NextOfKinBuilder) super.withAddress(address);
    }

    /**
     * Sets the {@code Phone} of the {@code NextOfKin} that we are building.
     */
    @Override
    public NextOfKinBuilder withPhone(String phone) {
        return (NextOfKinBuilder) super.withPhone(phone);
    }

    /**
     * Sets the {@code Email} of the {@code NextOfKin} that we are building.
     */
    @Override
    public NextOfKinBuilder withEmail(String email) {
        return (NextOfKinBuilder) super.withEmail(email);
    }

    /**
     * Sets the {@code Level} of the {@code NextOfKin} that we are building.
     */
    public NextOfKinBuilder withRelationship(String relationship) {
        this.relationship = Relationship.createRelationship(relationship);
        return this;
    }

    /**
     * Builds {@code NextOfKin} object.
     *
     * @return A NextOfKin object.
     */
    public NextOfKin build() {
        return new NextOfKin(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getTags(), relationship);
    }
}
