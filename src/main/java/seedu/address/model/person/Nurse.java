package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Set;

public class Nurse extends Person{

    public Nurse(Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, gender, phone, email, address, tags);
    }

    public String getCategory() {
        return "N";
    }

    @Override
    public String toString(){
        return getCategory()+ " " + super.toString();
    }

}
