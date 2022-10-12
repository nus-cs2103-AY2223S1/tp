package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.field.Field;

/**
 * Represents a Person's custom fields pairings in the address book.
 */
public class Fields {

    // A list of fields
    private List<Field> fields;

    /**
     * Constructs a new Fields instance.
     */
    public Fields() {
        fields = new ArrayList<>();
    }

    /**
     * Adds a field to the list of fields.
     *
     * @param field A Field instance to be added to the list.
     */
    public void addField(Field field) {
        fields.add(field);
    }

    /**
     * Adds a field to the list of fields by a given field name.
     *
     * @param fieldName the name of the Field instance to be added to the list.
     */
    public void addField(String fieldName) {
        Field field = new Field(fieldName);
        fields.add(field);
    }

    /**
     * Removes a field from the list of fields.
     *
     * @param field A field to be removed from the list.
     * @return true if the Field was removed successfully, false otherwise.
     */
    public boolean removeField(Field field) {
        return fields.remove(field);
    }

    /**
     * Updates the Field object with a new Field object.
     *
     * @param oldField The old Field object from the Person.
     * @param newField The new Field object to be updated.
     */
    public void updateField(Field oldField, Field newField) {
        int index = fields.indexOf(oldField);
        fields.set(index, newField);
    }

    /**
     * Retreive the value of a field given by a specified name.
     *
     * @param name The name of the field to be searched.
     * @return the value of the field queried.
     */
    public String retrieveFieldValue(String name) {
        for (Field field : fields) {
            if (field.isNameMatch(name)) {
                return field.getValue();
            }
        }
        return "Value not found";
    }

    /**
     * Adds all items from a given list of fields to the list stored in the
     * Fields object.
     *
     * @param fields A list of fields to add.
     */
    public void addAll(List<Field> fields) {
        this.fields.addAll(fields);
    }

    /**
     * Adds all items from a given Fields instance.
     *
     * @param fields A Fields object containing field information to be added from.
     */
    public void addAll(Fields fields) {
        if (fields != null && !fields.isEmpty()) {
            this.fields.addAll(fields.toList());
        }
    }

    public List<Field> toList() {
        return fields;
    }

    public boolean isEmpty() {
        return fields.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        fields.forEach(builder::append);
        return builder.toString();
    }
}
