package seedu.address.model.attribute;

import java.util.Map;

import javafx.scene.Node;

/**
 * Interface to represent the information required to represent one attribute as
 * well as methods to handle conversion of data and represenation of data in the
 * GUI.
 */
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
     * Checks whether a string has the same name as the attribute, regardless
     * of the case.
     *
     * @return true if the string is equal to the attribute name, false otherwise
     */
    boolean isNameMatch(String name);

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
    boolean isAnyFlagMatch(int flag);

    /**
     * Returns true of any of the bits of the style flag settings is true
     */
    boolean isAnyStyleMatch(int flag);

    /**
     * Returns true of all of the bits of the style flag settings is true
     */
    boolean isAllStyleMatch(int flag);

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
