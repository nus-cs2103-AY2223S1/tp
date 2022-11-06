package seedu.condonery.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.condonery.testutil.TypicalClients.AMY_CLIENT;
import static seedu.condonery.testutil.TypicalPersons.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validClient_returnsTrue() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(AMY_CLIENT);
        assertEquals(AMY_CLIENT, client.toModelType());
    }

}
