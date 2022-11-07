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
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GITHUB = "rachel tan";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CURRENT_MODULE = "CS2103T*";
    private static final String INVALID_PREVIOUS_MODULE = "CS2040S*";
    private static final String INVALID_PLANNED_MODULE = "CS2109S*";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_GITHUB = BENSON.getGithub().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedCurrentModule> VALID_CURRENT_MODULES = BENSON.getCurrModules().stream()
            .map(JsonAdaptedCurrentModule::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPreviousModule> VALID_PREVIOUS_MODULES = BENSON.getPrevModules().stream()
            .map(JsonAdaptedPreviousModule::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPlannedModule> VALID_PLANNED_MODULES = BENSON.getPlanModules().stream()
            .map(JsonAdaptedPlannedModule::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB, VALID_TAGS,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                VALID_TAGS, VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB, VALID_TAGS,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB,
                VALID_TAGS, VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_GITHUB, VALID_TAGS,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_GITHUB,
                VALID_TAGS, VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_GITHUB, VALID_TAGS,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_GITHUB,
                VALID_TAGS, VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGithub_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_GITHUB, VALID_TAGS,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = Github.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGithub_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null,
                VALID_TAGS, VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Github.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB, invalidTags,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidCurrentModules_throwsIllegalValueException() {
        List<JsonAdaptedCurrentModule> invalidCurrentModules = new ArrayList<>(VALID_CURRENT_MODULES);
        invalidCurrentModules.add(new JsonAdaptedCurrentModule(INVALID_CURRENT_MODULE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB, VALID_TAGS,
                        invalidCurrentModules, VALID_PREVIOUS_MODULES, VALID_PLANNED_MODULES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPreviousModules_throwsIllegalValueException() {
        List<JsonAdaptedPreviousModule> invalidPreviousModules = new ArrayList<>(VALID_PREVIOUS_MODULES);
        invalidPreviousModules.add(new JsonAdaptedPreviousModule(INVALID_PREVIOUS_MODULE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB, VALID_TAGS,
                        VALID_CURRENT_MODULES, invalidPreviousModules, VALID_PLANNED_MODULES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPlannedModules_throwsIllegalValueException() {
        List<JsonAdaptedPlannedModule> invalidPlannedModules = new ArrayList<>(VALID_PLANNED_MODULES);
        invalidPlannedModules.add(new JsonAdaptedPlannedModule(INVALID_PLANNED_MODULE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB, VALID_TAGS,
                        VALID_CURRENT_MODULES, VALID_PREVIOUS_MODULES, invalidPlannedModules);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
