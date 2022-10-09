package foodwhere.storage;

import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalReviews.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.stall.Name;

public class JsonAdaptedReviewTest {
    private static final String INVALID_DETAIL = "#friend";

    private static final Name VALID_NAME = new Name(BENSON.getName().fullName);
    private static final String VALID_DATE = "1/1/1";
    private static final String VALID_CONTENT = BENSON.getContent().toString();
    private static final List<JsonAdaptedDetail> VALID_DETAILS = BENSON.getDetails().stream()
            .map(JsonAdaptedDetail::new)
            .collect(Collectors.toList());

    /**
     * Test will be added back when Review class is added properly.
     */
    public void toModelType_validReviewDetails_returnsReview() throws Exception {
        JsonAdaptedReview review = new JsonAdaptedReview(BENSON);
        assertEquals(BENSON, review.toModelType(VALID_NAME));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT, new ArrayList<>());
        String expectedMessage =
                String.format(JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> review.toModelType(null));
    }

    @Test
    public void toModelType_invalidDetails_throwsIllegalValueException() {
        List<JsonAdaptedDetail> invalidDetails = new ArrayList<>(VALID_DETAILS);
        invalidDetails.add(new JsonAdaptedDetail(INVALID_DETAIL));
        JsonAdaptedReview review = new JsonAdaptedReview(VALID_DATE, VALID_CONTENT,
                invalidDetails);
        assertThrows(IllegalValueException.class, () -> review.toModelType(null));
    }

}
