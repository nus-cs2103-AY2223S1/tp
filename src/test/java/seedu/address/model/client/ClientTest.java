package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Client(null, "12345678", "abc@gmail.com",
                Type.UNSPECIFIED));
    }

    @Test
    public void constructor_invalidArguments_throwsIllegalArgumentException() {

        String invalidEmail = "_abc@gmail.com";
        assertThrows(IllegalArgumentException.class, () -> new Client("Harry", "12345678", invalidEmail,
                Type.INDIVIDUAL));

        String invalidPhone = "12";
        assertThrows(IllegalArgumentException.class, () -> new Client("Harry", invalidPhone, "ac@cd.com",
                Type.EMPLOYER));
    }

    @Test
    public void constructor_generates_defaultValues() {
        Client client = new Client("Harry", "12345678", "abc@gmail.com");
        assertEquals("none", client.getClientProjectList());
        assertEquals("UNSPECIFIED", client.getClientType());
    }
}
