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
        if (!Plan.isValidPlan(value)) {
            try {
                throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
            } catch (IllegalValueException e) {
                throw new RuntimeException(e);
            }
        }
        return new Plan(value);
    }
}
