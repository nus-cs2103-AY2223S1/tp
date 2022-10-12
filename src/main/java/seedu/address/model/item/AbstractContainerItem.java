package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.item.exceptions.ItemCannotBeParentException;

/**
 * Abstract class to represent an item that can contain other items.
 */
public abstract class AbstractContainerItem extends DisplayItemList<DisplayItem> implements DisplayItem {

    protected AbstractContainerItem parent = null;
    protected String name;
    protected String fullPath = null;

    protected AbstractContainerItem(String name, AbstractContainerItem parent) {
        this.name = name;
        this.parent = parent;
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

    private String getTitle(List<String> sb, AbstractContainerItem o) {
        sb.add(toString());
        if (parent == null || parent.equals(o)) {
            Collections.reverse(sb);
            return "/" + String.join("/", sb);
        }
        return parent.getTitle(sb, o);
    }

    protected void regenerateFullPathName() {
        fullPath = getTitle(new ArrayList<String>(), null);
    }

    public String getFullPathName() {
        if (fullPath != null) {
            regenerateFullPathName();
        }
        return getTitle(new ArrayList<String>(), null);
    }

    public String getRelativePathName(AbstractContainerItem o) {
        return getTitle(new ArrayList<String>(), o);
    }

    @Override
    public void setParent(DisplayItem o) {
        if (o == null) {
            parent = null;
            return;
        }
        if (!(o instanceof AbstractContainerItem)) {
            throw new ItemCannotBeParentException(o);
        }
        parent = (AbstractContainerItem) o;
        regenerateFullPathName();
    }

    public AbstractContainerItem getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return name;
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
