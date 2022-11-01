package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MINECRAFTNAME = "Rachel Tan";
    private static final String INVALID_PHONE = "+651a234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_COUNTRY = "USA122";
    private static final String INVALID_SERVER = "randomMinecraftServer";
    private static final String INVALID_TIME_INTERVAL = "mon@0800 to mon@1000";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_MINECRAFTNAME = BENSON.getMinecraftName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_COUNTRY = BENSON.getCountry().toString();
    private static final List<JsonAdaptedSocial> VALID_SOCIALS = BENSON.getSocials().stream()
            .map(JsonAdaptedSocial::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedMinecraftServer> VALID_SERVERS = BENSON.getServers().stream()
            .map(JsonAdaptedMinecraftServer::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedGameType> VALID_GAME_TYPE = BENSON.getGameType().stream()
            .map(JsonAdaptedGameType::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTimeInterval> VALID_TIME_INTERVAL = BENSON.getTimesAvailable().stream()
            .map(JsonAdaptedTimeInterval::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_MINECRAFTNAME, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS, VALID_TAGS,
                        VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_MINECRAFTNAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALS, VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME,
                        INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS,
                        VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME,
                null, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALS, VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME,
                VALID_PHONE, null, VALID_ADDRESS,
                VALID_SOCIALS, VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME, VALID_PHONE,
                        INVALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS,
                        VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME, VALID_PHONE,
                        VALID_EMAIL, INVALID_ADDRESS, VALID_SOCIALS,
                        VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME,
                VALID_PHONE, VALID_EMAIL, null,
                VALID_SOCIALS, VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS,
                        invalidTags, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidMinecraftName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_MINECRAFTNAME,
                        VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS,
                        VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = MinecraftName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMinecraftName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALS, VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MinecraftName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCountry_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME,
                        VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS,
                        VALID_TAGS, VALID_SERVERS, INVALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = Country.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCountry_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALS, VALID_TAGS, VALID_SERVERS, null, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Country.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidServers_throwsIllegalValueException() {
        List<JsonAdaptedMinecraftServer> invalidServers = new ArrayList<>(VALID_SERVERS);
        invalidServers.add(new JsonAdaptedMinecraftServer(INVALID_SERVER));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS,
                        VALID_TAGS, invalidServers, VALID_COUNTRY, VALID_GAME_TYPE, VALID_TIME_INTERVAL);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidTimeIntervals_throwsIllegalValueException() {
        List<JsonAdaptedTimeInterval> invalidTimeIntervals = new ArrayList<>(VALID_TIME_INTERVAL);
        invalidTimeIntervals.add(new JsonAdaptedTimeInterval(INVALID_TIME_INTERVAL));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MINECRAFTNAME, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_SOCIALS,
                        VALID_TAGS, VALID_SERVERS, VALID_COUNTRY, VALID_GAME_TYPE, invalidTimeIntervals);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
