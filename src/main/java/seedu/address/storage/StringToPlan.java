package seedu.address.storage;

import com.opencsv.bean.AbstractCsvConverter;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.tag.Tag;

/**
 * Converts a given {@code String} into {@code Tag} for OpenCSV use.
 */
public class StringToPlan extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String value) {
        if (value.equals("")) {
            return new Plan("null");
        }
        return new Plan(value);
    }
}
