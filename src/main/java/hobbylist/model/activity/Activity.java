package hobbylist.model.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import hobbylist.commons.util.CollectionUtil;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;

/**
 * Represents an Activity in HobbyList.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Activity {

    // Identity fields
    private final Name name;

    // Data fields
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Date> listDate = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Activity(Name name, Description description, Set<Tag> tags, List<Date> listDate) {
        CollectionUtil.requireAllNonNull(name, description, tags, listDate);
        this.name = name;
        this.description = description;
        this.tags.addAll(tags);
        this.listDate.addAll(listDate);
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    public List<Date> getDate() {
        return this.listDate;
    }



    /**
     * Returns true if both activities have the same name.
     * This defines a weaker notion of equality between two activities.
     */
    public boolean isSameActivity(Activity otherActivity) {
        if (otherActivity == this) {
            return true;
        }

        return otherActivity != null
                && otherActivity.getName().equals(getName())
                && otherActivity.getDescription().equals(getDescription())
                && otherActivity.getTags().equals(getTags());
    }

    /**
     * Returns true if both activities have the same identity and data fields.
     * This defines a stronger notion of equality between two activities.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Activity)) {
            return false;
        }

        Activity otherActivity = (Activity) other;
        return otherActivity.getName().equals(getName())
                && otherActivity.getDescription().equals(getDescription())
                && otherActivity.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        if (!listDate.isEmpty()) {
            builder.append("; Date: ")
                    .append(this.listDate.get(0).toString());
        }
        return builder.toString();
    }

}
