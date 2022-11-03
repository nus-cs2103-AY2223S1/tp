package seedu.address.model.commission;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.customer.Customer;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.UniqueIterationList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Commission in ArtBuddy.
 * Guarantees: except description, all details are present and not null, field values are validated, immutable.
 */
public class Commission {
    private static final UniqueCommissionList allCommissions = new UniqueCommissionList();

    // Identity fields
    private final Title title;
    private final Fee fee;
    private final Deadline deadline;
    private final CompletionStatus completionStatus;
    private final Customer customer;

    // Data field
    private final UniqueIterationList iterations;
    private final Set<Tag> tags;

    // Optional fields
    private final Description description;

    /**
     * Represents a more defined CompletionStatus of the commission,
     * taking into account the number of iterations in the commission
     * on top of its CompletionStatus.
     */
    public enum CompletionStatusString {
        COMPLETED {
            @Override
            public String toString() {
                return "Completed";
            }
        },
        IN_PROGRESS {
            @Override
            public String toString() {
                return "In Progress";
            }
        },
        NOT_STARTED {
            @Override
            public String toString() {
                return "Not Started";
            }
        }
    }

    /**
     * Constructs a Commission.
     * @param builder Instance of CommissionBuilder.
     */

    public Commission(CommissionBuilder builder, Customer customer) {
        title = builder.title;
        fee = builder.fee;
        deadline = builder.deadline;
        completionStatus = builder.status;
        tags = builder.tags;
        description = builder.description;
        iterations = new UniqueIterationList();
        builder.iterations.forEach(iteration -> {
            iterations.add(new Iteration(iteration.getDate(), iteration.getDescription(), iteration.getImagePath(),
                    iteration.getFeedback()));
        });
        this.customer = customer;
    }

    public Title getTitle() {
        return title;
    }

    public Optional<Description> getDescription() {
        return Optional.ofNullable(description);
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

    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    /**
     * Returns a String representation of the Commission's completion status,
     * based on whether it has been marked as completed, as determined by its
     * {@code CompletionStatus}, as well as the number of Iterations in its
     * {@code UniqueIterationList}.
     *
     * @return {@code COMPLETED}, if the commission's CompletionStatus is completed. Else,
     *         returns {@code IN_PROGRESS} if there are iterations in the Commission, and
     *         {@code NOT_STARTED} otherwise.
     */
    public CompletionStatusString getCompletionStatusString() {
        if (completionStatus.isCompleted) {
            return CompletionStatusString.COMPLETED;
        }

        if (iterations.size() > 0) {
            return CompletionStatusString.IN_PROGRESS;
        }

        return CompletionStatusString.NOT_STARTED;
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

    /**
     * Returns true if both commissions have the same title.
     * This defines a weaker notion of equality between two commissions.
     */
    public boolean isSameCommission(Commission otherCommission) {
        if (otherCommission == this) {
            return true;
        }

        return otherCommission != null
                && otherCommission.getTitle().equals(getTitle())
                && otherCommission.getCustomer().equals(getCustomer());
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
                && otherCommission.getCustomer().isSameCustomer(getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fee, deadline, tags, description, iterations, completionStatus, customer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Fee: ")
                .append(getFee())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Completed: ")
                .append(getCompletionStatus());

        if (getDescription().isPresent()) {
            builder.append("; Description: ")
                    .append(getDescription().get());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public static void addCommission(Commission commission) {
        Commission.allCommissions.add(commission);
    }

    public static void deleteCommission(Commission commission) {
        Commission.allCommissions.remove(commission);
    }

    public static void setCommission(Commission oldCommission, Commission newCommission) {
        Commission.allCommissions.setCommission(oldCommission, newCommission);
    }

    public static UniqueCommissionList getAllCommissions() {
        return allCommissions;
    }

    /**
     * Builder class for Commission.
     */
    public static class CommissionBuilder {
        // required parameters
        private Title title;
        private Fee fee;
        private Deadline deadline;
        private CompletionStatus status;
        private Set<Tag> tags = new HashSet<>();
        private UniqueIterationList iterations = new UniqueIterationList();

        // optional parameters
        private Description description;

        /**
         * Builds CommissionBuilder with all required fields.
         */
        public CommissionBuilder(Title title, Fee fee, Deadline deadline, CompletionStatus status, Set<Tag> tags) {
            requireAllNonNull(title, fee, deadline, status, tags);
            this.title = title;
            this.fee = fee;
            this.deadline = deadline;
            this.status = status;
            this.tags.addAll(tags);
        }

        /**
         * Sets description and returns itself.
         */
        public CommissionBuilder setDescription(Description description) {
            requireNonNull(description);
            this.description = description;
            return this;
        }

        /**
         * Sets iterations and returns itself.
         */
        public CommissionBuilder setIterations(UniqueIterationList uniqueIterationList) {
            requireNonNull(uniqueIterationList);
            this.iterations = uniqueIterationList;
            return this;
        }

        /**
         * Sets iterations and returns itself.
         */
        public CommissionBuilder addIteration(Iteration iteration) {
            requireNonNull(iteration);
            iterations.add(iteration);
            return this;
        }

        public Commission build(Customer customer) {
            return new Commission(this, customer);
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append(title)
                .append("; Fee: ")
                .append(fee)
                .append("; Deadline: ")
                .append(deadline)
                .append("; Completed: ")
                .append(status);

            if (description != null) {
                builder.append("; Description: ")
                    .append(description);
            }

            if (!tags.isEmpty()) {
                builder.append("; Tags: ");
                tags.forEach(builder::append);
            }
            return builder.toString();
        }
    }
}
