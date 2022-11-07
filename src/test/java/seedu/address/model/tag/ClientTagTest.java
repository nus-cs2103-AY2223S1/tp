package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.tag.ClientTag.isClientTag;

import org.junit.jupiter.api.Test;

public class ClientTagTest {

    @Test
    public void isValidClientTagName_validInput_success() {
        assertEquals(isClientTag("CURRENT"), true);
        assertEquals(isClientTag("POTENTIAL"), true);
    }

    @Test
    public void isValidClientTagName_invalidInput_success() {
        assertEquals(isClientTag("FUTURE"), false);
        assertEquals(isClientTag("PREVIOUS"), false);
    }

    @Test
    public void getType() {
        ClientTag currentClientTag = new ClientTag("CURRENT");
        assertEquals(1, currentClientTag.getType());

        ClientTag potentialClientTag = new ClientTag("POTENTIAL");
        assertEquals(2, potentialClientTag.getType());
    }
}
