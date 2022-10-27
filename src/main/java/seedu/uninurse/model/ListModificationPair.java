package seedu.uninurse.model;

/**
 * An association class storing the type of modification and the index of modification in a {@code GenericList}
 */
public class ListModificationPair {
    private final ModificationType type;
    private final int index;

    /**
     * Constructs a {@code ListModificationPair} with a {@code ModificationType} and a index
     */
    public ListModificationPair(ModificationType type, int index) {
        this.type = type;
        this.index = index;
    }

    public boolean isAdd() {
        return type == ModificationType.ADD;
    }

    public boolean isEdit() {
        return type == ModificationType.EDIT;
    }

    public boolean isDelete() {
        return type == ModificationType.DELETE;
    }

    public int getIndex() {
        return index;
    }
}
