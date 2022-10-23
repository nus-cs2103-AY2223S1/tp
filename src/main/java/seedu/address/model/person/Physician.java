package seedu.address.model.person;

import seedu.address.model.category.Category;

/**
 * Represents a physician in the database.
 */
public class Physician extends BasePerson {

    public Physician(Name n, Phone p, Email e) {
        super(n, p, e);
    }

    @Override
    public Category getCategory() {
        return new Category(Category.PHYSICIAN_SYMBOL);
    }
}
