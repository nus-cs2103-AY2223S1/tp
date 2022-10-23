package foodwhere.storage;

import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalReviews.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;

public class JsonAdaptedReviewTest {
    private static final String INVALID_TAG = "#friend";

    private static final Name VALID_NAME = new Name(BENSON.getName().fullName);
    private static final Address VALID_ADDRESS = new Address(BENSON.getAddress().value);
    private static final String VALID_DATE = "1/1/2000";
    private static final String INVALID_DATE = "1/1/1";
    private static final String VALID_CONTENT = BENSON.getContent().toString();
    private static final String INVALID_CONTENT = "";
    private static final Integer VALID_RATING = BENSON.getRating().value;
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validReviewTags_returnsReview() throws Exception {
        JsonAdaptedReview review = new JsonAdaptedReview(BENSON);
        assertEquals(BENSON, review.toModelType(VALID_NAME, VALID_ADDRESS));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, VALID_RATING, new ArrayList<>());
        String expectedMessage =
                String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(null, VALID_ADDRESS));
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, VALID_RATING, new ArrayList<>());
        String expectedMessage =
                String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, null));
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, null, new ArrayList<>());
        String expectedMessage =
                String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, VALID_ADDRESS));
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, -1, new ArrayList<>());
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, VALID_ADDRESS));
        JsonAdaptedReview review2 = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, 6, new ArrayList<>());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review2.toModelType(VALID_NAME, VALID_ADDRESS));
    }

    @Test
    public void toModelType_nullContent_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, null, VALID_RATING, new ArrayList<>());
        String expectedMessage =
                String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT, Content.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, VALID_ADDRESS));
    }

    @Test
    public void toModelType_invalidContent_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, INVALID_CONTENT, VALID_RATING, new ArrayList<>());
        String expectedMessage = Content.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, VALID_ADDRESS));
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(null, VALID_CONTENT, VALID_RATING, new ArrayList<>());
        String expectedMessage =
                String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, VALID_ADDRESS));
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(INVALID_DATE, VALID_CONTENT, VALID_RATING, new ArrayList<>());
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, VALID_ADDRESS));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, VALID_RATING,
                invalidTags);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                review.toModelType(VALID_NAME, VALID_ADDRESS));
    }

}
