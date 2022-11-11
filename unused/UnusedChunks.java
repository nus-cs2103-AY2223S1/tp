// @@author jasonchristopher21

@Override
public void addAttribute(String attributeName, String attributeContent) throws AttributeException {
    requireAllNonNull(attributeName, attributeContent);
    attributes.addAttribute(attributeName, attributeContent);
}

@Override
public void editAttribute(String attributeName, String attributeContent) throws AttributeException {
    requireAllNonNull(attributeName, attributeContent);
    attributes.editAttribute(attributeName, attributeContent);
}


/**
 * Retrieves the Fields instance of the Person.
 *
 * @return the Fields instance of the Person.
 */
public AttributeList getFields() {
    return this.attributes;
}

/**
 * Adds a Field to the Fields of the Person.
 *
 * @param fieldName the field name to be added.
 */
public void addField(String fieldName) throws AttributeException {
    attributes.addAttribute(fieldName);
}

/**
 * Removes a field from the Fields of the Person
 *
 * @param fieldName the field name to be removed.
 */
public void removeField(String fieldName) {
    attributes.removeField(fieldName);
}

// @@author mohamedsaf1

    /**
     * Parses a {@code String level} into a {@code Progress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code level} is invalid.
     */
    public static Progress parseProgress(String level) throws ParseException {
        requireNonNull(level);
        String trimmedProgress = level.trim();
        if (!Progress.isValidProgress(trimmedProgress)) {
            throw new ParseException(Progress.MESSAGE_CONSTRAINTS);
        }
        return new Progress(level);
    }
