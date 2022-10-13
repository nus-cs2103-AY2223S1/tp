package seedu.address.testutil;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.logic.commands.CloneCommand.DUPLICATE_NAME_EXTENSION;

public class ClonePersonBuilder {

    private Person clonedPerson;

    public ClonePersonBuilder (Person personToClone) {
        Name updatedName = new Name(personToClone.getName() + DUPLICATE_NAME_EXTENSION);
        Phone clonedPhone = personToClone.getPhone();
        Email clonedEmail = personToClone.getEmail();
        Address clonedAddress = personToClone.getAddress();
        Gender clonedGender = personToClone.getGender();
        Birthdate clonedBirthdate = personToClone.getBirthdate();
        Race clonedRace = personToClone.getRace();
        Religion clonedReligion = personToClone.getReligion();
        Survey clonedSurvey = personToClone.getSurvey();
        Set<Tag> clonedTags = (personToClone.getTags() != null) ? personToClone.getTags() : null;

        this.clonedPerson = new Person(updatedName, clonedPhone, clonedEmail, clonedAddress, clonedGender, clonedBirthdate, clonedRace, clonedReligion, clonedSurvey, clonedTags);
    }

    public Person build() {
        return clonedPerson;
    }
}
