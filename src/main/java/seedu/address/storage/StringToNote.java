package seedu.address.storage;

import com.opencsv.bean.AbstractCsvConverter;

import seedu.address.model.portfolio.Note;

/**
 * Converts a given {@code String} into {@code Tag} for OpenCSV use.
 */
public class StringToNote extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String value) {
        if (value.equals("")) {
            return new Note("null");
        }
        return new Note(value);
    }
}
