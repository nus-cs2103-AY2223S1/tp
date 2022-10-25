package seedu.address.model.attribute;

import java.util.Map;

import javafx.scene.Node;

public interface Attribute<T> {
    /**
     * Get the type label of this attribute.
     *
     * @return
     */
    String getAttributeType();

    /**
     * Get the content of this attribute.
     *
     * @return
     */
    T getAttributeContent();

    /**
     * Returns true if the attribute can be displayed in the menu screen.
     */
    boolean isVisibleInMenu();

    /**
     * Returns true if the attribute can be displayed graphically.
     *
     * @return
     */
    boolean isDisplayable();

    /**
     * Returns true if all bits of flag is true
     */
    boolean isAllFlagMatch(int flag);

    /**
     * Returns true if any bit of the flag is true
     */
    boolean IsAnyFlagMatch(int flag);

    /**
     * Returns the UI representation of the attribute to be added into the Javafx
     * parent.
     *
     * @return
     */
    Node getJavaFxRepresentation();

    /**
     * Checks if this object has the same type as another attribute.
     */
    <U> boolean isSameType(Attribute<U> o);

    /**
     * Returns a map representation of attribute that is saveable by Storage.
     * 
     * @return
     */
    Map<String, Object> toSaveableData();
}