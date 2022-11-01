package hobbylist.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.activity.Review;
import hobbylist.model.activity.Status;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;
import hobbylist.model.util.SampleDataUtil;

/**
 * A utility class to help with building Activity objects.
 */
public class ActivityBuilder {

    public static final String DEFAULT_NAME = "A Tale of Two Cities";
    public static final String DEFAULT_DESCRIPTION = "Historical novel by Charles Dickens";

    private Name name;
    private Description description;
    private Set<Tag> tags;
    private Optional<Date> dateList;
    private Status status;
    private Optional<Review> review;

    /**
     * Creates a {@code ActivityBuilder} with the default details.
     */
    public ActivityBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        dateList = Optional.empty();
        status = new Status();
        review = Optional.empty();
    }

    /**
     * Initializes the ActivityBuilder with the data of {@code activityToCopy}.
     */
    public ActivityBuilder(Activity activityToCopy) {
        name = activityToCopy.getName();
        description = activityToCopy.getDescription();
        tags = new HashSet<>(activityToCopy.getTags());
        dateList = activityToCopy.getDate();
        status = activityToCopy.getStatus();
        review = activityToCopy.getReview();
    }

    /**
     * Sets the {@code Name} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Activity} that we are building.
     */
    public ActivityBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@Code Review} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withReview(String review) {
        this.review = Optional.of(new Review(review));
        return this;
    }

    /**
     * Try to build an activity for test.
     */
    public Activity build() {

        return new Activity(name, description, tags, dateList, status, review);
    }

}
