package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_CANADA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_UK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_TYPE_MINEPLEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_TYPE_SURVIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MINECRAFT_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MINECRAFT_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVER_111_222;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVER_123_456;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SOCIAL_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SOCIAL_TWITTER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_INTERVAL_SUNDAY_NIGHT_TO_MONDAY_MORNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_INTERVAL_TUES_AFTERNOON;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withName("Alice Pauline")
            .withMinecraftName("Alice123")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withCountry("Singapore")
            .withServers("server@111.222.333")
            .withSocial("twitter@aliceP", "ig@AliceP")
            .withGameType("MinePlex", "Survival")
            .withTimeIntervals("mon@2300-tue@0000").build();
    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withMinecraftName("Benson123")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withCountry("Singapore")
            .withServers("server@111.222.444")
            .withGameType("Hunger Games")
            .withTimeIntervals("mon@1200-mon@1400").build();
    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withMinecraftName("Carl")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withTags("friends")
            .withCountry("Singapore")
            .withServers("server@111.123")
            .withGameType("Hunger Games")
            .withTimeIntervals("tue@2200 - tue@2300").build();
    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withMinecraftName("Dan")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends")
            .withCountry("USA")
            .withTags("buddy", "friend")
            .withGameType("MinePlex")
            .withTimeIntervals("mon@1100-mon@1430").build();
    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withMinecraftName("Elli")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withTags("friends")
            .withCountry("USA")
            .withGameType("adventure")
            .withServers("random@111.222", "random@333.444")
            .withTimeIntervals("wed@2000-wed@2200").build();
    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withMinecraftName("KUNCZ")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withTags("friends")
            .withGameType("MinePlex")
            .withServers("server@111.222.333")
            .withCountry("Japan").build();
    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withMinecraftName("dinnerbone")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withTags("friends")
            .withCountry("USA").build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withName("Hoon Meier")
            .withMinecraftName("aaaaaa")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder()
            .withName("Ida Mueller")
            .withMinecraftName("wsssd")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave").build();

    public static final Person JUSTIN = new PersonBuilder()
            .withName("Justin Bieber")
            .withMinecraftName("bustinjieber")
            .withPhone("97654786")
            .withEmail("bieber@gmail.com")
            .withAddress("National University of Singapore")
            .withSocial("facebook@bieber")
            .withCountry("Singapore")
            .withServers("minecraft@123.456", "minecraft@456.789")
            .withTags("popstar")
            .withGameType("creative")
            .withTimeIntervals("sun@2300-mon@0100").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withMinecraftName(VALID_MINECRAFT_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withSocial(VALID_SOCIAL_INSTAGRAM)
            .withCountry(VALID_COUNTRY_CANADA)
            .withGameType(VALID_GAME_TYPE_MINEPLEX)
            .withServers(VALID_SERVER_111_222)
            .withTimeIntervals(VALID_TIME_INTERVAL_SUNDAY_NIGHT_TO_MONDAY_MORNING)
            .build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withMinecraftName(VALID_MINECRAFT_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withGameType(VALID_GAME_TYPE_SURVIVAL)
            .withServers(VALID_SERVER_123_456)
            .withSocial(VALID_SOCIAL_TWITTER)
            .withCountry(VALID_COUNTRY_UK)
            .withTimeIntervals(VALID_TIME_INTERVAL_TUES_AFTERNOON)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    public static final String KEYWORD_MATCHING_SINGAPORE = "Singapore"; // A keyword that matches SINGAPORE

    public static final String MONDAY_NOON_DAY_TIME_IN_WEEK = "mon@1200"; // A keyword for Monday Noon

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, IDA, JUSTIN));
    }
}
