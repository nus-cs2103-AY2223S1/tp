package seedu.uninurse.model.condition;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.condition.exceptions.ConditionNotFoundException;
import seedu.uninurse.model.condition.exceptions.DuplicateConditionException;

/**
 * Represents a unique list of medical conditions for a particular patient.
 * Two conditions with the same name are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class ConditionList implements GenericList<Condition> {
    public static final String MESSAGE_UNSUPPORTED_EDIT = "Conditions cannot be edited, only added or deleted";

    private final List<Condition> internalConditionList;

    /**
     * Constructs an empty {@code ConditionList}.
     */
    public ConditionList() {
        internalConditionList = new ArrayList<>();
    }

    /**
     * Constructs a {@code ConditionList}.
     * @param conditions The given list of conditions.
     */
    public ConditionList(List<Condition> conditions) {
        requireNonNull(conditions);
        internalConditionList = conditions;
    }

    /**
     * Checks if the list already contains a given condition.
     * @param toCheck The condition to be checked.
     * @return Returns true if the list contains an equivalent condition as the given argument.
     */
    public boolean contains(Condition toCheck) {
        requireNonNull(toCheck);
        return internalConditionList.stream().anyMatch(toCheck::equals);
    }

    @Override
    public ConditionList add(Condition condition) {
        requireNonNull(condition);
        if (contains(condition)) {
            throw new DuplicateConditionException();
        }

        List<Condition> updatedConditions = new ArrayList<>(internalConditionList);
        updatedConditions.add(condition);
        return new ConditionList(updatedConditions);
    }

    @Override
    public ConditionList delete(int index) {
        try {
            List<Condition> updatedConditions = new ArrayList<>(internalConditionList);
            updatedConditions.remove(index);
            return new ConditionList(updatedConditions);
        } catch (IndexOutOfBoundsException e) {
            throw new ConditionNotFoundException();
        }
    }

    @Override
    public ConditionList edit(int index, Condition condition) {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED_EDIT);
    }

    @Override
    public Condition get(int index) {
        try {
            return internalConditionList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ConditionNotFoundException();
        }
    }

    @Override
    public int size() {
        return internalConditionList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalConditionList.isEmpty();
    }

    @Override
    public List<Condition> getInternalList() {
        // returns a copy of the internal list to prevent modification to original one
        return Collections.unmodifiableList(internalConditionList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        internalConditionList.forEach(c -> {
            int index = internalConditionList.indexOf(c);
            if (index == 0) {
                builder.append(index + 1)
                        .append(". ")
                        .append(c);
            } else {
                builder.append("\n")
                        .append(index + 1)
                        .append(c);
            }
        });
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConditionList // instanceof handles nulls
                && internalConditionList.equals(((ConditionList) other).internalConditionList));
    }

    @Override
    public int hashCode() {
        return internalConditionList.hashCode();
    }
}
