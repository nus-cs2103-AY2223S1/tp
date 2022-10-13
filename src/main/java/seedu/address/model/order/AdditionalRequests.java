package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * A class that represents the additional requests of an order.
 */
public class AdditionalRequests {

    private final ArrayList<String> additionalRequests = new ArrayList<>();

    /**
     * Constructs the AdditionalRequests object with an array.
     *
     * @param descriptions The string descriptions of these additional requests.
     */
    public AdditionalRequests(String... descriptions) {
        requireNonNull(descriptions);
        additionalRequests.addAll(Arrays.asList(descriptions));
    }

    public AdditionalRequests(Collection<String> descriptions) {
        additionalRequests.addAll(descriptions);
    }

    public ArrayList<String> getAdditionalRequests() {
        return additionalRequests;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof AdditionalRequests) {
            AdditionalRequests otherAdditionalRequests = (AdditionalRequests) other;
            return additionalRequests.equals(otherAdditionalRequests.additionalRequests);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return additionalRequests.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (String description : additionalRequests) {
            builder.append(i).append(". ").append(description).append(System.lineSeparator());
            i++;
        }
        return builder.toString();
    }

}
