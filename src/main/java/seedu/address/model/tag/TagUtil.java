package seedu.address.model.tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class that contains tag shortcuts.
 */
public class TagUtil {
    private static final Map<String, String> SHORTCUTS = new HashMap<>() {{
            put("tut", "tutorial");
            put("assm", "assignment");
            put("pres", "presentation");
            put("lec", "lecture");
            put("prof", "professor");
            put("ta", "tutor");
            put("rec", "recitation");
        }};

    // to prevent instantiation
    private TagUtil() {}

    /**
     * Retrieves the expanded string if shortcut is in variable, else returns the original string
     */
    public static String retrieveTagString(String string) {
        for (String key : SHORTCUTS.keySet()) {
            if (string.toLowerCase().equals(key)) {
                return SHORTCUTS.get(key);
            }
        }
        return string;
    }

}
