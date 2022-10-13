package seedu.address.testutil;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.logic.commands.CloneCommand.DUPLICATE_NAME_EXTENSION;

public class ClonePersonBuilder {

    private Person clonedPerson;

    public ClonePersonBuilder (Person personToClone) {
        Name updatedName = new Name(personToClone.getName() + DUPLICATE_NAME_EXTENSION);
        Phone updatedPhone = personToClone.getPhone();
        Email updatedEmail = personToClone.getEmail();
        Address updatedAddress = personToClone.getAddress();
        Set<Tag> updatedTags = (personToClone.getTags() != null) ? personToClone.getTags() : null;

        this.clonedPerson = new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    public Person build() {
        return clonedPerson;
    }
}
