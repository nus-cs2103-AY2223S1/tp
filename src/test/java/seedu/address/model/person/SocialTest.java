package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.social.Social;

public class SocialTest {
    @Test
    public void isValidSocial() {
        assertThrows(NullPointerException.class, () -> Social.isValidSocial(null));

        //invalid input
        assertFalse(Social.isValidSocial(" ")); // spaces only

        //valid input
        assertTrue(Social.isValidSocial("whatsapp"));
        assertTrue(Social.isValidSocial("telegram"));
        assertTrue(Social.isValidSocial("email"));
        assertTrue(Social.isValidSocial("instagram"));
        assertTrue(Social.isValidSocial("preferred"));
        assertTrue(Social.isValidSocial("WHATSAPP"));
        assertTrue(Social.isValidSocial("TELEGRAM"));
        assertTrue(Social.isValidSocial("EMAIL"));
        assertTrue(Social.isValidSocial("INSTAGRAM"));
        assertTrue(Social.isValidSocial("PREFERRED"));

        //Almost all the input are valid as this isValidSocial only checks
        //to see if the inputs are characters
        //There are other isValidSocials specific to each commands
        //As each command have different valid social inputs


    }
}
