package foodwhere.storage;

import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalReviews.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.commons.Name;

public class JsonAdaptedReviewTest {
    private static final String INVALID_TAG = "#friend";

    private static final Name VALID_NAME = new Name(BENSON.getName().fullName);
    private static final String VALID_DATE = "1/1/1";
    private static final String VALID_CONTENT = BENSON.getContent().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validReviewTags_returnsReview() throws Exception {
        JsonAdaptedReview review = new JsonAdaptedReview(BENSON);
        assertEquals(BENSON, review.toModelType(VALID_NAME));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, new ArrayList<>());
        String expectedMessage =
                String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> review.toModelType(null));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT,
                invalidTags);
        assertThrows(IllegalValueException.class, () -> review.toModelType(null));
    }

}
