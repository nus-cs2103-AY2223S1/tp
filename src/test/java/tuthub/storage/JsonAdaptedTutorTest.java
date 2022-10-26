package tuthub.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tuthub.storage.JsonAdaptedTutor.MISSING_FIELD_MESSAGE_FORMAT;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import tuthub.commons.exceptions.IllegalValueException;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.Rating;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.TeachingNomination;
import tuthub.model.tutor.Year;

public class JsonAdaptedTutorTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MODULE = "CS100";
    private static final String INVALID_YEAR = "0";
    private static final String INVALID_STUDENTID = "C1234567L";
    private static final String INVALID_TEACHINGNOMINATION = "A";
    private static final String INVALID_RATING = "-1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedModule> VALID_MODULES = BENSON.getModules().stream()
        .map(JsonAdaptedModule::new)
        .collect(Collectors.toList());
    private static final String VALID_YEAR = BENSON.getYear().toString();
    private static final String VALID_STUDENTID = BENSON.getStudentId().toString();
    private static final String VALID_TEACHINGNOMINATION = BENSON.getTeachingNomination().toString();
    private static final String VALID_RATING = BENSON.getRating().toString();
    private static final String VALID_COMMENTS = BENSON.getComments().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTutorDetails_returnsTutor() throws Exception {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(BENSON);
        assertEquals(BENSON, tutor.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                    VALID_MODULES, VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                    VALID_RATING, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(null, VALID_PHONE, VALID_EMAIL,
            VALID_MODULES, VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
            VALID_RATING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                    VALID_MODULES, VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS,
                    VALID_TEACHINGNOMINATION, VALID_RATING, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, null, VALID_EMAIL,
            VALID_MODULES, VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
            VALID_RATING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        List<JsonAdaptedModule> invalidModules = new ArrayList<>(VALID_MODULES);
        invalidModules.add(new JsonAdaptedModule(INVALID_MODULE));
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                invalidModules, VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                VALID_RATING, VALID_TAGS);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                VALID_RATING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES,
                INVALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                VALID_RATING, VALID_TAGS);
        String expectedMessage = Year.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullYear_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES,
                null, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                VALID_RATING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Year.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                    VALID_MODULES, VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                    VALID_RATING, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, null,
            VALID_MODULES, VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
            VALID_RATING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES,
                        VALID_YEAR, INVALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                        VALID_RATING, VALID_TAGS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_MODULES, VALID_YEAR, null, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                VALID_RATING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidTeachingNomination_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES, VALID_YEAR,
                        VALID_STUDENTID, VALID_COMMENTS, INVALID_TEACHINGNOMINATION,
                        VALID_RATING, VALID_TAGS);
        String expectedMessage = TeachingNomination.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullTeachingNomination_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES, VALID_YEAR,
                        VALID_STUDENTID, VALID_COMMENTS, null,
                        VALID_RATING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TeachingNomination.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES, VALID_YEAR,
                        VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                        INVALID_RATING, VALID_TAGS);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES, VALID_YEAR,
                        VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                        null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULES,
                    VALID_YEAR, VALID_STUDENTID, VALID_COMMENTS, VALID_TEACHINGNOMINATION,
                        VALID_RATING, invalidTags);
        assertThrows(IllegalValueException.class, tutor::toModelType);
    }

}
