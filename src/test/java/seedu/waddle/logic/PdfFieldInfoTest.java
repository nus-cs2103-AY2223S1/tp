package seedu.waddle.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PdfFieldInfoTest {

    private String name = "item_1";
    private String value = "dinner";
    private PdfFieldInfo info = new PdfFieldInfo(name, value);

    @Test
    public void getName_same() {
        assertEquals(info.getName(), name);
    }

    @Test
    public void getValue_same() {
        assertEquals(info.getValue(), value);
    }

    @Test
    public void toString_same() {
        assertEquals(info.toString(), "Field name: " + this.name + " Value: " + this.value);
    }
}
