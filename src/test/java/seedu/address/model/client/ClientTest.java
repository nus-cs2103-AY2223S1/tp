package seedu.address.model.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

public class ClientTest {
    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Client(null, "12345678", "abc@gmail.com"));
    }

    @Test
    public void constructor_invalidArguments_throwsIllegalArgumentException() {

        String invalidEmail = "_abc@gmail.com";
        assertThrows(IllegalArgumentException.class, () -> new Client("Harry", "12345678", invalidEmail));

        String invalidPhone = "123";
        assertThrows(IllegalArgumentException.class, () -> new Client("Harry", invalidPhone, "ac@cd.com"));
    }

    @Test
    public void constructor_generates_defaultValues() {
        Client client = new Client("Harry", "12345678", "abc@gmail.com");
        assertNotNull(client.getClientId());
        assertEquals(0, client.getProjectCount());
    }
}
