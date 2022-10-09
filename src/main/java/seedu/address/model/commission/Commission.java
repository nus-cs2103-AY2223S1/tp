package seedu.address.model.commission;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.customer.Customer;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.UniqueIterationList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Commission in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Commission {

    // Identity fields
    private final Title title;
    private final Description description;
    private final Fee fee;
    private final Deadline deadline;
    private final CompletionStatus completionStatus;
    private final Set<Tag> tags = new HashSet<>();
    private Customer customer;

    // Data field
    private final UniqueIterationList iterations;

    /**
     * Every field must be present and not null.
     */
    public Commission(Title title, Description description, Fee fee, Deadline deadline, Set<Tag> tags,
                      CompletionStatus completionStatus, Customer customer) {
        requireAllNonNull(title, description, fee, deadline, tags, completionStatus);
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.deadline = deadline;
        this.tags.addAll(tags);
        this.completionStatus = completionStatus;
        this.customer = customer;
        iterations = new UniqueIterationList();
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Fee getFee() {
        return fee;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Customer getCustomer() {
        return customer;
    }

    public UniqueIterationList getIterations() {
        return iterations;
    }

    public ObservableList<Iteration> getIterationList() {
        return iterations.asUnmodifiableObservableList();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns true if both commissions have the same title.
     * This defines a weaker notion of equality between two commissions.
     */
    public boolean isSameCommission(Commission otherCommission) {
        if (otherCommission == this) {
            return true;
        }

        return otherCommission != null
                && otherCommission.getTitle().equals(getTitle());
    }

    /**
     * Adds an iteration to the commission.
     *
     * @param iteration The iteration to be added to the commission.
     */
    public void addIteration(Iteration iteration) {
        requireNonNull(iteration);
        iterations.add(iteration);
    }

    /**
     * Checks whether an iteration with the same identity as {@code iteration}
     * exists in the commission's iteration list.
     */
    public boolean hasIteration(Iteration iteration) {
        requireNonNull(iteration);
        return iterations.contains(iteration);
    }

    /**
     * Replaces the given iteration {@code target} in the list with {@code editedIteration}.
     * {@code target} must exist in the address book.
     * The iteration identity of {@code editedIteration} must not be the same as another
     * existing iteration in the commission's iteration list.
     */
    public void setIteration(Iteration target, Iteration editedIteration) {
        requireAllNonNull(target, editedIteration);
        iterations.setIteration(target, editedIteration);
    }

    /**
     * Removes {@code key} from this {@code Commission}.
     * {@code key} must exist in the commission's iteration list.
     */
    public void removeIteration(Iteration key) {
        requireNonNull(key);
        iterations.remove(key);
    }

    /**
     * Returns true if both commissions have the same fields.
     * This defines a stronger notion of equality between two commissions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Commission)) {
            return false;
        }

        Commission otherCommission = (Commission) other;
        return otherCommission.getTitle().equals(getTitle())
                && otherCommission.getFee().equals(getFee())
                && otherCommission.getDeadline().equals(getDeadline())
                && otherCommission.getTags().equals(getTags())
                && otherCommission.getDescription().equals(getDescription())
                && otherCommission.getCompletionStatus().equals(getCompletionStatus())
                && otherCommission.getCustomer().equals(getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fee, deadline, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription())
                .append("; Fee: ")
                .append(getFee())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Completed:")
                .append(getCompletionStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
