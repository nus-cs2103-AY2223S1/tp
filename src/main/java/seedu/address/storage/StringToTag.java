package seedu.address.storage;

import com.opencsv.bean.AbstractCsvConverter;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

/**
 * Converts a given {@code String} into {@code Tag} for OpenCSV use.
 */
public class StringToTag extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String value) {
        if (!Tag.isValidTagName(value)) {
            try {
                throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
            } catch (IllegalValueException e) {
                throw new RuntimeException(e);
            }
        }
        return new Tag(value);
    }
}
