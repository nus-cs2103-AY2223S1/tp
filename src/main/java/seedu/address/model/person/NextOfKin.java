package seedu.address.model.person;

import seedu.address.model.category.Category;

/**
 * Represents Next of Kin for patient
 */
public class NextOfKin extends BasePerson {

    /**
     * Initialise next of kin user with name, phone and email.
     *
     * @param n name
     * @param p phone
     * @param e email
     */
    public NextOfKin(Name n, Phone p, Email e) {
        super(n, p, e);
    }

    @Override
    public Category getCategory() {
        return new Category(Category.NEXTOFKIN_SYMBOL);
    }

    public boolean isNextOfKin() {
        return true;
    }
}
