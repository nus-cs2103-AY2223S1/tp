package seedu.address.model.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import seedu.address.model.item.exceptions.ItemCannotBeParentException;

/**
 * Abstract class to represent an item can only have a single parent.
 */
public abstract class AbstractSingleItem extends AbstractDisplayItem {

    protected AbstractSingleItem parent = null;

    protected AbstractSingleItem(String name, int typeFlag, int parentFlag) {
        super(name, typeFlag, parentFlag);
        parent = null;
    }

    protected String getTitle(List<String> sb, AbstractDisplayItem o) {
        sb.add(name.fullName);
        if (parent == null || parent.equals(o)) {
            Collections.reverse(sb);
            return "/" + String.join("/", sb);
        }
        return parent.getTitle(sb, o);
    }

    @Override
    public String getFullPath() {
        return getTitle(new ArrayList<String>(), null);
    }

    // @@author mohamedsaf1
    @Override
    public void setParent(DisplayItem o) {
        if (o == null) {
            parent = null;
            return;
        }
        if (!(o instanceof AbstractSingleItem)) {
            throw new ItemCannotBeParentException(o);
        }
        parent = (AbstractSingleItem) o;
    }

    public AbstractSingleItem getParent() {
        return parent;
    }

    @Override
    public Set<? extends DisplayItem> getParents() {
        if (parent == null) {
            return Set.of();
        }
        return Set.of(parent);
    }

    @Override
    public void removeParent(DisplayItem o) {
        parent = null;
    }
    // @@author

    @Override
    public String toString() {
        return getFullPath();
    }
}
