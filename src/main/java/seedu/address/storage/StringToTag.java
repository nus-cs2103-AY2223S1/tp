package seedu.address.storage;

import com.opencsv.bean.AbstractCsvConverter;

import seedu.address.model.tag.Tag;

/**
 * Converts a given {@code String} into {@code Tag} for OpenCSV use.
 */
public class StringToTag extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String value) {
        if (value.equals("")) {
            return new Tag("null");
        }
        return new Tag(value);
    }
}
