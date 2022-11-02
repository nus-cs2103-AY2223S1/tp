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
    private Optional<Date> date;
    private Status status;
    private Optional<Review> review;
    private int rating;

    /**
     * Creates a {@code ActivityBuilder} with the default details.
     */
    public ActivityBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        date = Optional.empty();
        status = new Status();
        review = Optional.empty();
        rating = 0;
    }

    /**
     * Initializes the ActivityBuilder with the data of {@code activityToCopy}.
     */
    public ActivityBuilder(Activity activityToCopy) {
        name = activityToCopy.getName();
        description = activityToCopy.getDescription();
        tags = new HashSet<>(activityToCopy.getTags());
        date = activityToCopy.getDate();
        status = activityToCopy.getStatus();
        review = activityToCopy.getReview();
        rating = activityToCopy.getRating();
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
     * Sets the {@Code Status} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@Code Date} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withDate(String date) {
        this.date = Optional.of(new Date(date));
        return this;
    }

    /**
     * Sets the {@Code Rating} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }

    /**
     * Try to build an activity for test.
     */
    public Activity build() {
        return new Activity(name, description, tags, date, rating, status, review);
    }

}
