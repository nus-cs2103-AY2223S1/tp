package seedu.rc4hdb.testutil;


import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_GENDER_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_GENDER_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_HOUSE_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_HOUSE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_NAME_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_NAME_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;

/**
 * A utility class containing a list of {@code Resident} objects to be used in tests.
 */
public class TypicalResidents {

    public static final Resident ALICE = new ResidentBuilder()
            .withName("Alice Pauline")
            .withPhone("94351253")
            .withEmail("alice@example.com")
            .withRoom("11-04")
            .withGender("F")
            .withHouse("A")
            .withMatricNumber("A0431582U")
            .withTags("friends").build();
    public static final Resident BENSON = new ResidentBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("benson@example.com")
            .withRoom("12-01")
            .withGender("M")
            .withHouse("U")
            .withMatricNumber("A0728394U")
            .withTags("owesMoney", "friends").build();
    public static final Resident CARL = new ResidentBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withRoom("05-08")
            .withGender("M")
            .withHouse("D")
            .withMatricNumber("A0273917U").build();
    public static final Resident DANIEL = new ResidentBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withRoom("12-11")
            .withGender("M")
            .withHouse("N")
            .withMatricNumber("A0293856U")
            .withTags("friends").build();
    public static final Resident ELLE = new ResidentBuilder()
            .withName("Elle Meyer")
            .withPhone("94822241")
            .withEmail("werner@example.com")
            .withRoom("06-22")
            .withGender("F")
            .withHouse("U")
            .withMatricNumber("A0827394U").build();
    public static final Resident FIONA = new ResidentBuilder()
            .withName("Fiona Kunz")
            .withPhone("94824271")
            .withEmail("lydia@example.com")
            .withRoom("03-7")
            .withGender("F")
            .withHouse("A")
            .withMatricNumber("A0827360U").build();
    public static final Resident GEORGE = new ResidentBuilder()
            .withName("George Best")
            .withPhone("94824421")
            .withEmail("anna@example.com")
            .withRoom("09-14")
            .withGender("M")
            .withHouse("D")
            .withMatricNumber("A0738491U").build();

    // Manually added
    public static final Resident HOON = new ResidentBuilder()
            .withName("Hoon Meier")
            .withPhone("84824241")
            .withEmail("stefan@example.com")
            .withRoom("06-33")
            .withGender("M")
            .withHouse("N")
            .withMatricNumber("A0299388U").build();
    public static final Resident IDA = new ResidentBuilder()
            .withName("Ida Mueller")
            .withPhone("84821311")
            .withEmail("hans@example.com")
            .withRoom("02-14")
            .withGender("F")
            .withHouse("A")
            .withMatricNumber("A0778937U").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Resident AMY = new ResidentBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withRoom(VALID_ROOM_AMY)
            .withGender(VALID_GENDER_AMY)
            .withHouse(VALID_HOUSE_AMY)
            .withMatricNumber(VALID_MATRIC_NUMBER_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Resident BOB = new ResidentBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withRoom(VALID_ROOM_BOB)
            .withGender(VALID_GENDER_BOB)
            .withHouse(VALID_HOUSE_BOB)
            .withMatricNumber(VALID_MATRIC_NUMBER_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalResidents() {} // prevents instantiation

    /**
     * Returns an {@code ResidentBook} with all the typical residents.
     */
    public static ResidentBook getTypicalResidentBook() {
        ResidentBook rb = new ResidentBook();
        for (Resident resident : getTypicalResidents()) {
            rb.addResident(resident);
        }
        return rb;
    }

    public static List<Resident> getTypicalResidents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
