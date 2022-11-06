package hobbylist.commons.core;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the theme settings.
 * Guarantees: immutable.
 */
public class ThemeSettings implements Serializable {
    /**
     * Enum for theme.
     */
    public enum Theme {
        DARK,
        LIGHT,
        STAR,
        SKY,
        TREE
    }

    private final Theme theme;

    /**
     * Constructs a {@code ThemeSettings} with the default theme.
     */
    public ThemeSettings() {
        theme = Theme.STAR;
    }

    /**
     * Constructs a {@code ThemeSettings} with the specified theme.
     */
    public ThemeSettings(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ThemeSettings)) { //this handles null as well.
            return false;
        }

        ThemeSettings o = (ThemeSettings) other;

        return theme == o.theme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Theme : " + theme.toString());
        return sb.toString();
    }
}
