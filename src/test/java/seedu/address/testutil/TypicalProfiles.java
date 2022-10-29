package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY_PLUS_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.profile.Profile;

/**
 * A utility class containing a list of {@code Profile} objects to be used in tests.
 */
public class TypicalProfiles {
    public static final String FIRST_INDEX_TELEGRAM = "pauline";
    public static final String SECOND_INDEX_TELEGRAM = "benson_meier";

    public static final Profile ALICE = new ProfileBuilder().withName("Alice Pauline")
            .withEmail("alice@u.nus.edu").withPhone("94351253").withTelegram(FIRST_INDEX_TELEGRAM)
            .withTags("friends").build();
    public static final Profile BENSON = new ProfileBuilder().withName("Benson Meier")
            .withEmail("johnd@u.nus.edu").withPhone("98765432").withTelegram(SECOND_INDEX_TELEGRAM)
            .withTags("owesMoney", "friends").build();
    public static final Profile CARL = new ProfileBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@u.nus.edu").build();
    public static final Profile DANIEL = new ProfileBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@u.nus.edu").withTags("friends").build();
    public static final Profile ELLE = new ProfileBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@u.nus.edu").build();
    public static final Profile FIONA = new ProfileBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@u.nus.edu").build();
    public static final Profile GEORGE = new ProfileBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@u.nus.edu").build();

    // Manually added
    public static final Profile HOON = new ProfileBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@u.nus.edu").build();
    public static final Profile IDA = new ProfileBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@u.nus.edu").build();

    // Manually added - Profile's details found in {@code CommandTestUtil}
    public static final Profile AMY = new ProfileBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Profile AMY_DIFFERENT_PHONE = new ProfileBuilder(AMY)
            .withPhone(VALID_PHONE_AMY_PLUS_ONE).build();
    public static final Profile AMY_LOWER_CAPS = new ProfileBuilder(AMY).withName("amy bee").build();
    public static final Profile BOB = new ProfileBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalProfiles() {} // prevents instantiation

    public static List<Profile> getTypicalProfiles() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
