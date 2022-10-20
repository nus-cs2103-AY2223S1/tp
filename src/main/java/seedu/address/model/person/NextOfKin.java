package seedu.address.model.person;

import seedu.address.model.category.Category;

public class NextOfKin extends BasePerson {

    public NextOfKin(Name n, Phone p, Email e) {
        super(n, p, e);
    }

    @Override
    public Category getCategory() {
        return new Category(Category.NEXTOFKIN_SYMBOL);
    }
}
