package seedu.address.ui.theme;

/**
 * Theme represents the stylesheet being used for the GUI.
 */
public enum Theme {
    LIGHT("LightTheme.css"),
    DARK("DarkTheme.css"),
    EXTENSION("Extensions.css");

    public static final String SUCCESS_MESSAGE = "Theme switched to: %1$s.";
    public static final String ERROR_MESSAGE = "Theme cannot be found: %1$s.";

    public static final Theme DEFAULT_THEME = LIGHT;
    private static final String DIRECTORY = "/view/";
    private final String path;

    Theme(String path) {
        this.path = path;
    }

    /**
     * Creates a {@code Theme} object given a {@code String}.
     *
     * If given {@code String} does not match any of the themes,
     * default will be used.
     */
    public static Theme constructTheme(String themeSupplied) {
        Theme[] themes = Theme.values();
        for (Theme theme: themes) {
            if (themeSupplied.equals(theme.path)) {
                return theme;
            }
        }
        return DEFAULT_THEME;
    }

    public String getTheme() throws ThemeException {
        try {
            return getClass().getResource(DIRECTORY + path).toExternalForm();
        } catch (NullPointerException e) {
            throw new ThemeException(String.format(ERROR_MESSAGE, path));
        }
    }

    @Override
    public String toString() {
        return path;
    }
}
