package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SocialTestUtil;
import seedu.address.model.social.Social;
import seedu.address.model.social.exceptions.SocialException;

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

    @Test
    public void checkDelete() {
        Social test = new Social();
        test.addWhatsapp("wa");
        test.addTelegram("tele");
        test.addEmail("test@gmail.com");
        test.addInstagram("insta");
        test.prefer("INSTAGRAM");
        test.deleteWhatsapp();
        test.deleteTelegram();
        test.deleteEmail();
        test.deleteInstagram();
        test.deletePreferred();

        assertEquals(test, new Social());
    }

    @Test
    public void checkGet() {
        Social test = new Social();
        assertTrue(test.getWhatsapp() == "<none>");
        assertTrue(test.getTelegram() == "<none>");
        assertTrue(test.getEmail() == "<none>");
        assertTrue(test.getInstagram() == "<none>");

        test.addWhatsapp("wa");
        test.addTelegram("tele");
        test.addEmail("test@gmail.com");
        test.addInstagram("insta");

        assertTrue(test.getWhatsapp() == "wa");
        assertTrue(test.getTelegram() == "tele");
        assertTrue(test.getEmail() == "test@gmail.com");
        assertTrue(test.getInstagram() == "insta");
        assertTrue(test.getPreferredLink() == "<none>");

        test.prefer("INSTAGRAM");
        assertTrue(test.getPreferred() == "INSTAGRAM");
        assertTrue(test.getPreferredLink() == "insta");

        test.prefer("WHATSAPP");
        assertTrue(test.getPreferred() == "WHATSAPP");
        assertTrue(test.getPreferredLink() == "wa");

        test.prefer("TELEGRAM");
        assertTrue(test.getPreferred() == "TELEGRAM");
        assertTrue(test.getPreferredLink() == "tele");

        test.prefer("EMAIL");
        assertTrue(test.getPreferred() == "EMAIL");
        assertTrue(test.getPreferredLink() == "test@gmail.com");
    }

    @Test
    public void checkOpen() {
        Social test = new Social();

        assertThrows(SocialException.class, () -> test.openWhatsapp());
        assertThrows(SocialException.class, () -> test.openTelegram());
        assertThrows(SocialException.class, () -> test.openEmail());
        assertThrows(SocialException.class, () -> test.openInstagram());
        assertThrows(SocialException.class, () -> test.openPreferred());

        test.prefer("WHATSAPP");
        assertThrows(SocialException.class, () -> test.openPreferred());
        test.prefer("TELEGRAM");
        assertThrows(SocialException.class, () -> test.openPreferred());
        test.prefer("EMAIL");
        assertThrows(SocialException.class, () -> test.openPreferred());
        test.prefer("INSTAGRAM");
        assertThrows(SocialException.class, () -> test.openPreferred());
    }
}
