package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.Name;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PHONE = "#ab";
    private static final String INVALID_EMAIL = "  ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedRemark> VALID_REMARKS = BENSON.getRemarkList().stream()
            .map(JsonAdaptedRemark::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTransaction> VALID_TRANSACTIONS = BENSON.getTransactionList().stream()
            .map(JsonAdaptedTransaction::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(INVALID_NAME, VALID_ADDRESS, VALID_PHONE,
                        VALID_EMAIL, VALID_TAGS, VALID_REMARKS, VALID_TRANSACTIONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_ADDRESS, VALID_PHONE,
                VALID_EMAIL, VALID_TAGS, VALID_REMARKS, VALID_TRANSACTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, INVALID_ADDRESS,
                        VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_REMARKS, VALID_TRANSACTIONS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, null,
                VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_REMARKS, VALID_TRANSACTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_ADDRESS,
                null, VALID_EMAIL, VALID_TAGS, VALID_REMARKS, VALID_TRANSACTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClientPhone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_ADDRESS,
                VALID_PHONE, null, VALID_TAGS, VALID_REMARKS, VALID_TRANSACTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClientEmail.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_ADDRESS, VALID_PHONE,
                        VALID_EMAIL, invalidTags, VALID_REMARKS, VALID_TRANSACTIONS);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

    @Test
    public void toModelType_duplicateRemarks_throwsIllegalValueException() {
        List<JsonAdaptedRemark> duplicateRemarks = Stream.concat(VALID_REMARKS.stream(),
                VALID_REMARKS.stream()).collect(Collectors.toList());
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_ADDRESS, VALID_PHONE,
                        VALID_EMAIL, VALID_TAGS, duplicateRemarks, VALID_TRANSACTIONS);

        assertThrows(IllegalValueException.class, client::toModelType);
    }
}
