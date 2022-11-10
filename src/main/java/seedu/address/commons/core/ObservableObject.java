package seedu.address.commons.core;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValueBase;

/**
 * Class representing an observable expression value.
 */
public class ObservableObject<E> extends ObservableValueBase<E> {
    private E object;
    private int listenerCount = 0;

    public ObservableObject(E object) {
        this.object = object;
    }

    public ObservableObject() {
        this(null);
    }

    @Override
    public E getValue() {
        return this.object;
    }

    public void setValue(E object) {
        this.object = object;
        fireValueChangedEvent();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        super.addListener(listener);
        listenerCount++;
    }
    @Override
    public void addListener(ChangeListener<? super E> listener) {
        super.addListener(listener);
        listenerCount++;
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        super.removeListener(listener);
        listenerCount--;
    }


    @Override
    public void removeListener(ChangeListener<? super E> listener) {
        super.removeListener(listener);
        listenerCount--;
    }
}
