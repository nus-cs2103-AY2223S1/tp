package seedu.address.testutil;

import java.util.Map;

public class DisplayItemUtil {

    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_ADDRESS_ALICE = "123, Jurong West Ave 6, #08-111";
    public static final String VALID_EMAIL_ALICE = "alice@example.com";
    public static final String VALID_PHONE_ALICE = "94351253";
    public static final String VALID_TAG_FRIENDS = "friends";
    public static final String VALID_NAME_BENSON = "Benson Meier";
    public static final String VALID_ADDRESS_BENSON = "311, Clementi Ave 2, #02-25";
    public static final String VALID_EMAIL_BENSON = "johnd@example.com";
    public static final String VALID_PHONE_BENSON = "98765432";
    public static final String VALID_TAG_OWES_MONEY = "owesMoney";

    public static final String INVALID_NAME_RACHEL = "R@chel";
    public static final String INVALID_TAG_HASH = "#tag";
    public static final String INVALID_TAG_SPECIAL = "%)*^";
    public static final Map<String, Object> INVALID_SAVE_ATTRIBUTE = Map.of(
        "test", "none",
        "content", "nothing",
        "display_format", "23",
        "style_format", "35"
    );

}
