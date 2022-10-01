package seedu.address.model;

import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of the state of the focus mode.
 */
public interface ReadOnlyFocusMode {
    /**
     * TODO: replace with class
     * Enters into focus mode for the given class.
     *
     * @param classToFocus Class corresponding to the focus mode.
     */
    void enter(Tag classToFocus);

    /**
     * Exits focus mode.
     */
    void exit();

    /**
     * TODO: replace with class
     * Sets a new class to focus on.
     *
     * @param classToFocus Class corresponding to the focus mode.
     */
    void setFocusedClass(Tag classToFocus);

    /**
     * TODO: replace with class
     * Returns the class currently being focused on.
     *
     * @return Class currently being focused on.
     */
    Tag getFocusedClass();

    /**
     * Checks whether focus mode is active.
     *
     * @return True, if focus mode is active.
     */
    boolean isInFocusMode();
}
