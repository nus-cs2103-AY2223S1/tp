package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;

class OrderConverterTest {
    private OrderConverter converter = new OrderConverter();

    @Test
    public void convert_validOrder_success() throws Exception {
        String validOrder = "asc";
        Order order = converter.convert(validOrder);
        assertEquals(Order.ASCENDING, order);
    }

    @Test
    public void convert_invalidOrder_throwsTypeConversionException() {
        String invalidOrder = "idk";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(invalidOrder));
    }
}