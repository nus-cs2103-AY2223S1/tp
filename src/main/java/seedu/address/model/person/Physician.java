package seedu.address.model.person;

import seedu.address.model.category.Category;

/**
 * Represents a physician in the database.
 */
public class Physician extends BasePerson {

    public Physician(Name n, Phone p, Email e) {
        super(new Name("Dr " + n.fullName), p, e);
    }

    @Override
    public Category getCategory() {
        return new Category(Category.PHYSICIAN_SYMBOL);
    }

    @Override
    public String toString() {
        return "Attending Physician " + super.toString();
    }

    public boolean isPhysician() {
        return true;
    }
}
