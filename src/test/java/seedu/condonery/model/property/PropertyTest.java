package seedu.condonery.model.property;

import static seedu.condonery.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class PropertyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Property(
                null,
                null,
                null,
                null,
                null,
                null,
                null)
        );
    }

}
