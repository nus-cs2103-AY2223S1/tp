package seedu.uninurse.model.condition;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.condition.exceptions.DuplicateConditionException;

/**
 * Represents a unique list of medical conditions for a particular patient.
 * Two conditions with the same name are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class ConditionList implements GenericList<Condition> {
    public static final String MESSAGE_UNSUPPORTED_EDIT = "Conditions cannot be edited, only added or deleted";

    private ArrayList<Condition> internalConditionList;

    /**
     * Constructs an empty {@code ConditionList}.
     */
    public ConditionList() {
        internalConditionList = new ArrayList<>();
    }

    /**
     * Constructs a {@code ConditionList} with a given list of conditions.
     */
    public ConditionList(ArrayList<Condition> conditions) {
        requireNonNull(conditions);
        internalConditionList = conditions;
    }

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

        ArrayList<Condition> updatedConditions = new ArrayList<>(internalConditionList);
        updatedConditions.add(condition);
        return new ConditionList(updatedConditions);
    }

    @Override
    public ConditionList delete(int index) {
        ArrayList<Condition> updatedConditions = new ArrayList<>(internalConditionList);
        updatedConditions.remove(index);
        return new ConditionList(updatedConditions);
    }

    @Override
    public ConditionList edit(int index, Condition condition) {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED_EDIT);
    }

    @Override
    public Condition get(int index) {
        return internalConditionList.get(index);
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
    public ArrayList<Condition> getInternalList() {
        // returns a copy of the internal list to prevent modification to original one
        return new ArrayList<>(internalConditionList);
    }
}
