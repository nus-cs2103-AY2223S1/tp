package paymelah.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidHandle_throwsIllegalArgumentException() {
        String invalidHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidHandle));
    }

    @Test
    public void isValidHandle() {
        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidHandle(null));

        // blank telegram handle
        assertFalse(Telegram.isValidHandle("")); // empty string
        assertFalse(Telegram.isValidHandle(" ")); // spaces only

        // missing parts
        assertFalse(Telegram.isValidHandle("@")); // missing username

        // invalid parts
        assertFalse(Telegram.isValidHandle("@1L1zz_Trust_meIt5f1n3")); // username starts with a number
        assertFalse(Telegram.isValidHandle("@_L1zz_Trust_meIt5f1n3")); // username starts with an underscore
        assertFalse(Telegram.isValidHandle("@L1zTruss_")); // invalid end of username
        assertFalse(Telegram.isValidHandle("@Liz Truss")); // spaces in the username
        assertFalse(Telegram.isValidHandle(" @LizTruss")); // leading space
        assertFalse(Telegram.isValidHandle("@LizTruss ")); // trailing space
        assertFalse(Telegram.isValidHandle("@@LizTruss")); // double '@' symbol
        assertFalse(Telegram.isValidHandle("Liz@Truss")); // '@' symbol in username
        assertFalse(Telegram.isValidHandle("@1234")); // username has less than 5 chars

        // valid handle
        assertTrue(Telegram.isValidHandle("LizTruss")); // alphabets only
        assertTrue(Telegram.isValidHandle("L1zzTru55")); // alphanumerical
        assertTrue(Telegram.isValidHandle("L1zz_Tru55__4EVA")); // alphanumerical & underscore
        assertTrue(Telegram.isValidHandle("@L1zz_Tru55__4EVA")); // alphanumerical & underscore and At char
        assertTrue(Telegram.isValidHandle("f1_ve")); // minimal without At char
        assertTrue(Telegram.isValidHandle("@f1_ve")); // minimal with At char
        assertTrue(Telegram.isValidHandle("@LizzTRUSS______1111111______H12_2313JSJVSO")); // long username
    }
}
