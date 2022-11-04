package seedu.address.logic.commands;

import seedu.address.model.social.Social;

/**
 * Contains helper methods and fields for testing social commands.
 */
public class SocialTestUtil {
    public static final String VALID_WHATSAPP = "WHATSAPP";
    public static final String VALID_TELEGRAM = "TELEGRAM";
    public static final String VALID_EMAIL = "EMAIL";
    public static final String VALID_INSTAGRAM = "INSTAGRAM";
    public static final String VALID_PREFERRED = "PREFERRED";

    public static final String VALID_WHATSAPP_AMY = "88881111";
    public static final String VALID_WHATSAPP_BOB = "11118888";
    public static final String VALID_TELEGRAM_AMY = "amy";
    public static final String VALID_TELEGRAM_BOB = "bob";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_INSTAGRAM_AMY = "amy123";
    public static final String VALID_INSTAGRAM_BOB = "bob321";
    public static final String VALID_PREFERRED_AMY = "WHATSAPP";
    public static final String VALID_PREFERRED_BOB = "TELEGRAM";

    public static final String INVALID_SOCIALS = "WHATELEMAILGRAM";

    public static Social getAmySocial() {
        Social s = new Social();
        s.addWhatsapp(VALID_WHATSAPP_AMY);
        s.addTelegram(VALID_TELEGRAM_AMY);
        s.addEmail(VALID_EMAIL_AMY);
        s.addInstagram(VALID_INSTAGRAM_AMY);
        return s;
    }

    public static Social getBobSocial() {
        Social s = new Social();
        s.addWhatsapp(VALID_WHATSAPP_BOB);
        s.addTelegram(VALID_TELEGRAM_BOB);
        s.addEmail(VALID_EMAIL_BOB);
        s.addInstagram(VALID_INSTAGRAM_BOB);
        return s;
    }

}
