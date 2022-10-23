package seedu.rc4hdb.ui;

import javafx.beans.value.ObservableValueBase;

/**
 * Represents an item that can be listened to by JavaFX GUI components.
 */
public class ObservableItem<T> extends ObservableValueBase<T> {

    private T item;

    public ObservableItem(T item) {
        this.item = item;
    }

    @Override
    public T getValue() {
        return item;
    }

    public void setValue(T item) {
        this.item = item;
        fireValueChangedEvent();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ObservableItem // instanceof handles nulls
                && item.equals(((ObservableItem<?>) other).getValue()));
    }

}
