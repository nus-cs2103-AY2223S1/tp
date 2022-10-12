package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.item.exceptions.ItemCannotBeParentException;

/**
 * Abstract class to represent an item that can contain other items.
 */
public abstract class AbstractContainerItem extends DisplayItemList<DisplayItem> implements DisplayItem {

    private static final String PATH_VALIDATION_REGEX = "([a-zA-Z0-9_-]+\\/?)+([a-zA-Z0-9_-]+)";

    protected AbstractContainerItem parent;
    protected String fullPath;

    public AbstractContainerItem(AbstractContainerItem parent) {
        this(parent, null);
    }

    /**
     * Constructs an AbstractContainerItem.
     *
     * @param parent reference that will be containing this current AbstractContainerItem.
     * @param fullPath that includes all parents from root to this AbstractContainerItem.
     */
    public AbstractContainerItem(AbstractContainerItem parent, String fullPath) {
        this.parent = parent;
        this.fullPath = fullPath;
    }

    /**
     * Checks if the path is valid. Only alphanumeric, hyphen, underscore and slash are allowed.
     *
     * @param path to reach the current AbstractContainerItem.
     * @return true if path is valid, false otherwise.
     */
    public static boolean isValidPath(String path) {
        return path.matches(PATH_VALIDATION_REGEX);
    }

    @Override
    public void add(DisplayItem toAdd) {
        requireNonNull(toAdd);
        toAdd.setParent(this);
        super.add(toAdd);
    }

    @Override
    public <T extends DisplayItem> void setItems(List<T> items) {
        requireAllNonNull(items);
        // guards
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(this)) {
                throw new ItemCannotBeParentException(this);
            }
        }

        for (int i = 0; i < items.size(); i++) {
            items.get(i).setParent(this);
        }

        super.setItems(items);
    }

    @Override
    public <T extends DisplayItem> void setItems(DisplayItemList<T> replacement) {
        requireAllNonNull(replacement);
        setItems(replacement.internalList);
    }

    private String getTitle(List<String> sb) {
        sb.add(toString());
        if (parent == null) {
            Collections.reverse(sb);
            return sb.stream().collect(Collectors.joining("/"));
        }
        return parent.getTitle(sb);
    }

    protected void regenerateFullPathName() {
        fullPath = getTitle(new ArrayList<String>());
    }

    public String getFullPathName() {
        if (fullPath != null) {
            regenerateFullPathName();
        }
        return fullPath;
    }

    @Override
    public void setParent(DisplayItem o) {
        if (!(o instanceof AbstractContainerItem)) {
            throw new ItemCannotBeParentException(o);
        }
        parent = (AbstractContainerItem) o;
    }

    public AbstractContainerItem getParent() {
        return parent;
    }

    @Override
    public boolean isPartOfContext(DisplayItem o) {
        AbstractContainerItem temp = parent;
        while (parent != null) {
            if (parent.equals(o)) {
                return true;
            }

            temp = temp.getParent();
        }

        return o == null;
    }
}
