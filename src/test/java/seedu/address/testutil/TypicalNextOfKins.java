package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.nextofkin.NextOfKin;

/**
 * A utility class containing a list of {@code NextOfKin} objects to be used in tests.
 */
public class TypicalNextOfKins {

    public static final NextOfKin NEXTOFKIN1 = new NextOfKinBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withRelationship("Father")
            .build();

    public static final NextOfKin NEXTOFKIN2 = new NextOfKinBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withRelationship("Mother")
            .build();

    public static final NextOfKin NEXTOFKIN3 = new NextOfKinBuilder().withName("Cassie Jack")
            .withAddress("255, Pixel Ave 2, #05-10")
            .withEmail("casjac@notgmail.com")
            .withPhone("98554512")
            .withTags("owesMoney")
            .withRelationship("Guardian")
            .build();

    public static final NextOfKin AMY_NEXTOFKIN = new NextOfKinBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withRelationship(VALID_RELATIONSHIP_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();

    public static final NextOfKin BOB_NEXTOFKIN = new NextOfKinBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withRelationship(VALID_RELATIONSHIP_BOB)
            .withTags(VALID_TAG_FRIEND)
            .build();

    private TypicalNextOfKins() {} // prevents instantiation

    public static AddressBook getTypicalStudentsAddressBook() {
        AddressBook ab = new AddressBook();

        List<NextOfKin> nextOfKinList = new ArrayList<>(Arrays.asList(TypicalNextOfKins.NEXTOFKIN1,
                TypicalNextOfKins.NEXTOFKIN2));
        for (NextOfKin nok : nextOfKinList) {
            ab.addPerson(nok);
        }
        return ab;
    }
}
