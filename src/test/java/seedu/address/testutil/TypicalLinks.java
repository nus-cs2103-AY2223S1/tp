package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_4_WITHOUT_HTTPS;

import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.link.Link;

/**
 * A utility class containing a list of {@code Link} objects to be used in tests.
 */
public class TypicalLinks {

    public static final Link VALID_LINK_1 = new Link(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_URL);
    public static final Link VALID_LINK_2 = new Link(VALID_MODULE_LINK_ALIAS_2, VALID_MODULE_LINK_URL_2);
    public static final Link VALID_LINK_2_DIFFERENT_ALIAS = new Link(
            VALID_MODULE_LINK_ALIAS_3, VALID_MODULE_LINK_URL_2);
    public static final Link VALID_LINK_2_DIFFERENT_URL = new Link(
            VALID_MODULE_LINK_ALIAS_2, VALID_MODULE_LINK_URL_3);
    public static final Link VALID_LINK_4 = new Link(VALID_MODULE_LINK_ALIAS_4, VALID_MODULE_LINK_URL_4);
    public static final Link VALID_LINK_4_NO_HTTPS_HEADER = new Link(
            VALID_MODULE_LINK_ALIAS_4, VALID_MODULE_LINK_URL_4_WITHOUT_HTTPS);

    private TypicalLinks() {} // prevents instantiation

    public static List<Link> getTypicalLinks() {
        return Arrays.asList(VALID_LINK_1, VALID_LINK_2, VALID_LINK_4);
    }
}
