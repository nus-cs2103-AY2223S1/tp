package seedu.address.model.person;

import seedu.address.model.category.Category;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class Nurse extends Person{

    public Nurse(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(uid, name, gender, phone, email, address, tags);
    }

    public Category getCategory() {
        return new Category("N");
    }

    @Override
    public String toString(){
        return "Category: " + getCategory() + " " + super.toString();
    }

}
