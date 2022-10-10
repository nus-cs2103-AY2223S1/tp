package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.tutor.Tutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Tutor} objects to be used in tests.
 */
public class TypicalTutors {

    public static final Tutor TUTOR1 = new TutorBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withQualification("Bachelor of Computing")
            .withInstitution("National University of Singapore")
            .build();

    public static final Tutor TUTOR2 = new TutorBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withQualification("Bachelor of Engineering")
            .withInstitution("Nanyang Technological University")
            .build();

    private TypicalTutors() {} // prevents instantiation

    public static AddressBook getTypicalTutorsAddressBook() {
        AddressBook ab = new AddressBook();

        List<Tutor> tutorList = new ArrayList<>(Arrays.asList(TypicalTutors.TUTOR1, TypicalTutors.TUTOR2));
        for (Tutor t : tutorList) {
            ab.addPerson(t);
        }
        return ab;
    }
}
