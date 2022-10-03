package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.Tag;

/**
 * Represents the state of the focus mode.
 */
public class FocusMode implements ReadOnlyFocusMode {

    private boolean isActive;

    //TODO: replace with class
    private Tag focusedClass;

    public FocusMode() {
        this.isActive = false;
    }

    //TODO: replace with class
    @Override
    public void enter(Tag focusedClass) {
        requireNonNull(focusedClass);
        this.isActive = true;
        this.focusedClass = focusedClass;
    }

    /**
     * Exits focus mode.
     */
    @Override
    public void exit() {
        this.isActive = false;
        this.focusedClass = null;
    }

    //TODO: replace with class
    @Override
    public Tag getFocusedClass() {
        // this getter method should not be called if the focused class is null (if focus mode is inactive)
        requireNonNull(focusedClass);
        return focusedClass;
    }

    @Override
    public boolean isInFocusMode() {
        return isActive;
    }
}
