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

    protected AbstractContainerItem parent = null;
    protected String fullPath = null;

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
}
