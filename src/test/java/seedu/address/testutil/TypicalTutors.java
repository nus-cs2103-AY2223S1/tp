package seedu.address.testutil;

import seedu.address.model.person.tutor.Tutor;

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
}
