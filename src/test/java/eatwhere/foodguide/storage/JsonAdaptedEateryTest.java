package eatwhere.foodguide.storage;

import static eatwhere.foodguide.storage.JsonAdaptedEatery.MISSING_FIELD_MESSAGE_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.commons.exceptions.IllegalValueException;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Phone;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.TypicalEateries;

public class JsonAdaptedEateryTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_CUISINE = "e$$x$$$ample.co$$m";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalEateries.BENSON.getName().toString();
    private static final String VALID_PHONE = TypicalEateries.BENSON.getPhone().toString();
    private static final String VALID_EMAIL = TypicalEateries.BENSON.getCuisine().toString();
    private static final String VALID_ADDRESS = TypicalEateries.BENSON.getLocation().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalEateries.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEateryDetails_returnsEatery() throws Exception {
        JsonAdaptedEatery eatery = new JsonAdaptedEatery(TypicalEateries.BENSON);
        assertEquals(TypicalEateries.BENSON, eatery.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEatery eatery =
                new JsonAdaptedEatery(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEatery eatery = new JsonAdaptedEatery(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedEatery eatery =
                new JsonAdaptedEatery(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedEatery eatery = new JsonAdaptedEatery(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_invalidCuisine_throwsIllegalValueException() {
        JsonAdaptedEatery eatery =
                new JsonAdaptedEatery(VALID_NAME, VALID_PHONE, INVALID_CUISINE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Cuisine.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedEatery eatery = new JsonAdaptedEatery(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cuisine.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedEatery eatery =
                new JsonAdaptedEatery(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedEatery eatery = new JsonAdaptedEatery(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, eatery::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEatery eatery =
                new JsonAdaptedEatery(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        Assert.assertThrows(IllegalValueException.class, eatery::toModelType);
    }

}
