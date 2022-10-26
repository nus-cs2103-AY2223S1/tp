package seedu.address.model.attribute;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AccessDisplayFlags.DEFAULT;
import static seedu.address.model.AccessDisplayFlags.DISPLAY_OK;
import static seedu.address.model.AccessDisplayFlags.HIDE_TYPE;
import static seedu.address.model.AccessDisplayFlags.MENU_OK;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Creates an Abstract class to handle repeated and overused methods when making
 * Attributes.
 */
public abstract class AbstractAttribute<T> implements Attribute<T> {
    protected T value;
    protected String typeName;
    private int accessCtrl;

    /**
     * Creates an instance of an abstract attribute class
     */
    public AbstractAttribute(String typeName, T value, int accessCtrl) {
        requireNonNull(typeName);

        this.typeName = typeName;
        this.value = value;
        this.accessCtrl = accessCtrl;
    }

    public AbstractAttribute(String typeName, T value) {
        this(typeName, value, DEFAULT);
    }

    @Override
    public boolean isAllFlagMatch(int flag) {
        return (accessCtrl & flag) == flag;
    }

    @Override
    public boolean isAnyFlagMatch(int flag) {
        return (accessCtrl & flag) > 0;
    }

    @Override
    public T getAttributeContent() {
        return value;
    }

    @Override
    public String getAttributeType() {
        return typeName;
    }

    @Override
    public boolean isVisibleInMenu() {
        return isAllFlagMatch(MENU_OK);
    }

    @Override
    public boolean isDisplayable() {
        return isAllFlagMatch(DISPLAY_OK);
    }

    @Override
    public <U> boolean isSameType(Attribute<U> o) {
        return o.getAttributeType().equals(typeName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attribute // instanceof handles nulls
                        && value.equals(((Attribute<?>) other).getAttributeContent())); // state check
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        if (isAllFlagMatch(HIDE_TYPE)) {
            return value.toString();
        }
        return String.format("%s: %s", typeName, value);
    }

    @Override
    public int hashCode() {
        return typeName.hashCode() ^ value.hashCode() ^ accessCtrl;
    }

    @Override
    public Node getJavaFxRepresentation() {
        String txt;
        if (isAllFlagMatch(HIDE_TYPE)) {
            txt = value.toString();
        } else {
            txt = String.format("%s: %s", typeName, value);
        }

        Label ret = new Label();
        ret.setText(txt);
        return ret;
    }
}
