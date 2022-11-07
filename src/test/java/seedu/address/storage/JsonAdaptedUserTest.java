package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedUser.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.XAVIER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.EmptyUser;

public class JsonAdaptedUserTest {
    private static final String INVALID_NAME = "Thom@s";
    private static final String INVALID_PHONE = "+654321";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.sg";
    private static final String INVALID_GITHUB = "thomas lim";
    private static final String INVALID_CURRENT_MODULE = "CS2103T*";
    private static final String INVALID_PREVIOUS_MODULE = "CS2040S*";
    private static final String INVALID_PLANNED_MODULE = "CS2109S*";

    private static final String VALID_NAME = XAVIER.getName().toString();
    private static final String VALID_PHONE = XAVIER.getPhone().toString();
    private static final String VALID_EMAIL = XAVIER.getEmail().toString();
    private static final String VALID_ADDRESS = XAVIER.getAddress().toString();
    private static final String VALID_GITHUB = XAVIER.getGithub().toString();
    private static final boolean USER_IS_EMPTY = true;
    private static final boolean USER_NOT_EMPTY = false;
    private static final List<JsonAdaptedCurrentModule> VALID_CURRENT_MODULES = XAVIER.getCurrModules().stream()
            .map(JsonAdaptedCurrentModule::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPreviousModule> VALID_PREVIOUS_MODULES = XAVIER.getPrevModules().stream()
            .map(JsonAdaptedPreviousModule::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPlannedModule> VALID_PLANNED_MODULES = XAVIER.getPlanModules().stream()
            .map(JsonAdaptedPlannedModule::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedLesson> VALID_LESSONS = new ArrayList<>();

    private final EmptyUser emptyUser = new EmptyUser();


    @Test
    public void toModelType_validExistingUserDetails_returnsExistingUser() throws Exception {
        JsonAdaptedUser user = new JsonAdaptedUser(XAVIER);
        assertEquals(XAVIER, user.toModelType());
    }

    @Test
    public void toModelType_validEmptyUserDetails_returnsEmptyUser() throws Exception {
        JsonAdaptedUser user = new JsonAdaptedUser(emptyUser);
        assertEquals(emptyUser, user.toModelType());
    }

    @Test
    public void toModelType_allValidDetailsEmptyTrue_returnsEmptyUser() throws Exception {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_IS_EMPTY);
        assertEquals(emptyUser, user.toModelType());
    }

    @Test
    public void toModelType_allInvalidDetailsEmptyTrue_returnsEmptyUser() throws Exception {
        List<JsonAdaptedCurrentModule> invalidCurrentModules = new ArrayList<>(VALID_CURRENT_MODULES);
        List<JsonAdaptedPreviousModule> invalidPreviousModules = new ArrayList<>(VALID_PREVIOUS_MODULES);
        List<JsonAdaptedPlannedModule> invalidPlannedModules = new ArrayList<>(VALID_PLANNED_MODULES);
        JsonAdaptedUser user =
                new JsonAdaptedUser(INVALID_NAME, INVALID_PHONE, INVALID_EMAIL, INVALID_ADDRESS, INVALID_GITHUB,
                        invalidCurrentModules, invalidPreviousModules, invalidPlannedModules, VALID_LESSONS,
                        USER_IS_EMPTY);
        assertEquals(emptyUser, user.toModelType());
    }

    @Test
    public void toModelType_allNullDetailsEmptyTrue_returnsEmptyUser() throws Exception {
        JsonAdaptedUser user =
                new JsonAdaptedUser(null, null, null, null, null, null, null, null, null, USER_IS_EMPTY);
        assertEquals(emptyUser, user.toModelType());
    }


    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_NOT_EMPTY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS, USER_NOT_EMPTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedUser person =
                new JsonAdaptedUser(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_NOT_EMPTY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedUser person = new JsonAdaptedUser(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS, USER_NOT_EMPTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedUser person =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_NOT_EMPTY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedUser person = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_GITHUB,
                VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS, USER_NOT_EMPTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedUser person =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_GITHUB,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_NOT_EMPTY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedUser person = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_GITHUB,
                VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS, USER_NOT_EMPTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGithub_throwsIllegalValueException() {
        JsonAdaptedUser person =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_GITHUB,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_NOT_EMPTY);
        String expectedMessage = Github.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGithub_throwsIllegalValueException() {
        JsonAdaptedUser person = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null,
                VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS, USER_NOT_EMPTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Github.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCurrentModules_throwsIllegalValueException() {
        List<JsonAdaptedCurrentModule> invalidCurrentModules = new ArrayList<>(VALID_CURRENT_MODULES);
        invalidCurrentModules.add(new JsonAdaptedCurrentModule(INVALID_CURRENT_MODULE));
        JsonAdaptedUser person =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                        invalidCurrentModules, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_NOT_EMPTY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPreviousModules_throwsIllegalValueException() {
        List<JsonAdaptedPreviousModule> invalidPreviousModules = new ArrayList<>(VALID_PREVIOUS_MODULES);
        invalidPreviousModules.add(new JsonAdaptedPreviousModule(INVALID_PREVIOUS_MODULE));
        JsonAdaptedUser person =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                        VALID_CURRENT_MODULES, invalidPreviousModules, VALID_PLANNED_MODULES, VALID_LESSONS,
                        USER_NOT_EMPTY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPlannedModules_throwsIllegalValueException() {
        List<JsonAdaptedPlannedModule> invalidPlannedModules = new ArrayList<>(VALID_PLANNED_MODULES);
        invalidPlannedModules.add(new JsonAdaptedPlannedModule(INVALID_PLANNED_MODULE));
        JsonAdaptedUser person =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, invalidPlannedModules, VALID_LESSONS,
                        USER_NOT_EMPTY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
