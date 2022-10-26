package seedu.address.model.attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.attribute.exceptions.AttributeException;
import seedu.address.model.attribute.exceptions.DuplicateAttributeException;

/**
 * Represents a Person's custom fields pairings in the address book.
 */
public class AttributeList {

    // A list of fields
    private final List<Attribute<?>> attributeList;

    // For logging purposes before updating UI
    private static final Logger logger = LogsCenter.getLogger(AttributeList.class);

    /**
     * Constructs a new Fields instance.
     */
    public AttributeList() {
        attributeList = new ArrayList<>();
    }

    /**
     * Adds a field to the list of fields.
     *
     * @param attribute A Field instance to be added to the list.
     */
    public void addAttribute(Attribute<?> attribute) {
        attributeList.add(attribute);
        logger.info(String.format("Attribute added successfully: %s", attribute.getAttributeType()));
    }


    /**
     * Adds a field to the list of fields by a given field name.
     *
     * @param <T> type of value
     * @param attributeName the name of the Field instance to be added to the list.
     * @param value the value of the field.
     */
    public <T> void addAttribute(String attributeName, T value) throws AttributeException {
        if (this.checkForAttributeName(attributeName) != null) {
            String existingName = this.checkForAttributeName(attributeName).getAttributeType();
            throw new DuplicateAttributeException(existingName, attributeName);
        }
        AbstractAttribute<T> attribute = new AbstractAttribute<T>(attributeName, value) {
            @Override
            public Map<String, Object> toSaveableData() {
                return null;
            }
        };
        this.addAttribute(attribute);
    }

    /**
     * Adds a field
     *
     * @param attributeName
     * @throws AttributeException
     */
    public void addAttribute(String attributeName) throws AttributeException {
        if (this.checkForAttributeName(attributeName) != null) {
            String existingName = this.checkForAttributeName(attributeName).getAttributeType();
            throw new DuplicateAttributeException(existingName, attributeName);
        }
        AbstractAttribute<String> attribute = new AbstractAttribute<>(attributeName, null) {
            @Override
            public Map<String, Object> toSaveableData() {
                return null;
            }
        };
        this.addAttribute(attribute);
    }

    /**
     * Checks for attribute name
     * @param attributeName
     * @return
     */
    public Attribute<?> checkForAttributeName(String attributeName) {
        List<Attribute<?>> lst = attributeList.stream()
                .filter(attr -> attr.isNameMatch(attributeName))
                .collect(Collectors.toList());
        if (lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

    /**
     * Deletes an attribute
     * @param type
     */
    public void deleteAttribute(String type) {
        attributeList.removeIf(attr -> attr.isNameMatch(type));
    }

    /**
     * Removes a field from the list of fields.
     *
     * @param attribute A field to be removed from the list.
     * @return true if the Field was removed successfully, false otherwise.
     */
    public boolean removeAttribute(Attribute<?> attribute) {
        return attributeList.remove(attribute);
    }

    /**
     * Removes a field from the list of fields.
     *
     * @param attributeName The name of the field to be removed from the list.
     */
    public void removeField(String attributeName) {
        List<Attribute<?>> attributesToRemove = attributeList.stream()
                .filter(attribute -> attribute.getAttributeType().equalsIgnoreCase(attributeName))
                .collect(Collectors.toList());
        attributeList.removeAll(attributesToRemove);
    }

    /**
     * Updates the Field object with a new Field object.
     *
     * @param oldAttribute The old Field object from the Person.
     * @param newAttribute The new Field object to be updated.
     */
    public void updateAttribute(Attribute<?> oldAttribute, Attribute<?> newAttribute) {
        int index = attributeList.indexOf(oldAttribute);
        attributeList.set(index, newAttribute);
    }

    /**
     * Retrieves the value of a field given by a specified name.
     *
     * @param name The name of the field to be searched.
     * @return the value of the field queried.
     */
    public Object retrieveFieldValue(String name) {
        for (Attribute<?> attribute : attributeList) {
            if (attribute.getAttributeType().equals(name)) {
                return attribute.getAttributeContent();
            }
        }
        return "Value not found";
    }

    /**
     * Adds all items from a given list of fields to the list stored in the
     * Fields object.
     *
     * @param attributeList A list of fields to add.
     */
    public void addAll(List<Attribute<?>> attributeList) {
        this.attributeList.addAll(attributeList);
    }

    /**
     * Adds all items from a given Fields instance.
     *
     * @param attributeList A Fields object containing field information to be added from.
     */
    public void addAll(AttributeList attributeList) {
        if (attributeList != null && !attributeList.isEmpty()) {
            this.attributeList.addAll(attributeList.toList());
        }
    }

    /**
     * Returns a List representation of the {@code Fields} instance.
     *
     * @return a List containing the {@code Field} instances.
     */
    public List<Attribute<?>> toList() {
        return new ArrayList<>(attributeList);
    }

    /**
     * Checks if the {@code Fields} is empty.
     *
     * @return true if there are no {@code Field} instances stored in this
     *         {@code Fields}
     *         object, false otherwise.
     */
    public boolean isEmpty() {
        return attributeList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        attributeList.forEach(builder::append);
        return builder.toString();
    }
}
