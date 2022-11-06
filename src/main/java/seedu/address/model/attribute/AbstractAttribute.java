package seedu.address.model.attribute;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AccessDisplayFlags.BOLD;
import static seedu.address.model.AccessDisplayFlags.CENTER_JUSTIFY;
import static seedu.address.model.AccessDisplayFlags.DEFAULT;
import static seedu.address.model.AccessDisplayFlags.DEFAULT_STYLE;
import static seedu.address.model.AccessDisplayFlags.DISPLAY_OK;
import static seedu.address.model.AccessDisplayFlags.DROPSHADOW;
import static seedu.address.model.AccessDisplayFlags.FONT_SIZE_BIG;
import static seedu.address.model.AccessDisplayFlags.FONT_SIZE_NORMAL;
import static seedu.address.model.AccessDisplayFlags.FONT_SIZE_SMALL;
import static seedu.address.model.AccessDisplayFlags.HIDE_TYPE;
import static seedu.address.model.AccessDisplayFlags.ITALIC;
import static seedu.address.model.AccessDisplayFlags.LEFT_JUSTIFY;
import static seedu.address.model.AccessDisplayFlags.MENU_OK;
import static seedu.address.model.AccessDisplayFlags.RIGHT_JUSTIFY;
import static seedu.address.model.AccessDisplayFlags.STRIKETHROUGH;
import static seedu.address.model.AccessDisplayFlags.UNDERLINE;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Creates an Abstract class to handle repeated and overused methods when making Attributes.
 */
public abstract class AbstractAttribute<T> implements Attribute<T> {

    public static final String SAVE_KEY_TYPE_NAME = "type";
    public static final String SAVE_KEY_VALUE = "content";
    public static final String SAVE_KEY_DISPLAY_FORMAT = "display_format";
    public static final String SAVE_KEY_STYLE_FORMAT = "style_format";

    protected T value;
    protected String typeName;
    private int accessCtrl;
    private int styleFlag;

    /**
     * Creates an instance of an abstract attribute class
     */
    public AbstractAttribute(String typeName, T value, int accessCtrl, int styleFlag) {
        requireNonNull(typeName);

        this.typeName = typeName.trim();
        this.value = value;
        this.accessCtrl = accessCtrl;
        this.styleFlag = styleFlag;
    }

    public AbstractAttribute(String typeName, T value) {
        this(typeName, value, DEFAULT, DEFAULT_STYLE);
    }

    @Override
    public boolean isNameMatch(String name) {
        return typeName.equalsIgnoreCase(name);
    }

    @Override
    public boolean isAllFlagMatch(int flag) {
        return (accessCtrl & flag) == flag;
    }

    @Override
    public boolean isAnyFlagMatch(int flag) {
        return (accessCtrl & flag) > 0;
    }

    @Override
    public boolean isAnyStyleMatch(int flag) {
        return (styleFlag & flag) > 0;
    }

    @Override
    public boolean isAllStyleMatch(int flag) {
        return (styleFlag & flag) == flag;
    }

    @Override
    public T getAttributeContent() {
        return value;
    }

    @Override
    public String getAttributeType() {
        return typeName;
    }

    @Override
    public boolean isVisibleInMenu() {
        return isAllFlagMatch(MENU_OK);
    }

    @Override
    public boolean isDisplayable() {
        return isAllFlagMatch(DISPLAY_OK);
    }

    @Override
    public <U> boolean isSameType(Attribute<U> o) {
        return o.getAttributeType().toLowerCase().equals(typeName.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AbstractAttribute // instanceof handles nulls
                && typeName.equals(((AbstractAttribute<?>) other).typeName)
                && value.equals(((AbstractAttribute<?>) other).value)); // state check
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        if (isAllFlagMatch(HIDE_TYPE)) {
            return value.toString();
        }
        return String.format("%s: %s", typeName, value);
    }

    @Override
    public Node getJavaFxRepresentation() {
        String txt;
        if (isAllFlagMatch(HIDE_TYPE)) {
            txt = value.toString();
        } else {
            txt = String.format("%s: %s", typeName, value);
        }

        Label ret = new Label();
        ret.setText(txt);
        ret.setStyle(getFormatCss());
        return ret;
    }

    @Override
    public int hashCode() {
        return typeName.hashCode() ^ value.hashCode() ^ accessCtrl ^ styleFlag;
    }

    @Override
    public Map<String, Object> toSaveableData() {
        Map<String, Object> ret = new HashMap<>();
        ret.put(SAVE_KEY_TYPE_NAME, typeName);
        ret.put(SAVE_KEY_VALUE, value);
        ret.put(SAVE_KEY_DISPLAY_FORMAT, accessCtrl);
        ret.put(SAVE_KEY_STYLE_FORMAT, styleFlag);

        return ret;
    }

    protected String getFormatCss() {
        return getFormatCss(true);
    }

    protected String getFormatCss(boolean isInMenu) {
        StringBuilder sb = new StringBuilder("-fx-font: normal");
        double size = 12;

        if (isAllStyleMatch(BOLD)) {
            sb.append(" bold");
        }
        if (isAllStyleMatch(ITALIC)) {
            sb.append(" italic");
        }
        if (isAllStyleMatch(FONT_SIZE_SMALL)) {
            size = 8;
        }
        if (isAllStyleMatch(FONT_SIZE_BIG) && !isInMenu) {
            size = 32;
        }
        if (isAllStyleMatch(FONT_SIZE_NORMAL)) {
            size = 10;
        }

        sb.append(String.format(" %fpt 'Segoe UI';", size));

        if (isAllStyleMatch(UNDERLINE)) {
            sb.append(" -fx-underline: true;");
        }
        if (isAllStyleMatch(STRIKETHROUGH)) {
            sb.append(" -fx-strikethrough: true;");
        }
        if (isAllStyleMatch(DROPSHADOW)) {
            sb.append(" -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);");
        }
        if (isAllStyleMatch(LEFT_JUSTIFY)) {
            sb.append(" -fx-text-alignment: left;");
        }
        if (isAllStyleMatch(CENTER_JUSTIFY)) {
            sb.append(" -fx-text-alignment: center;");
        }
        if (isAllStyleMatch(RIGHT_JUSTIFY)) {
            sb.append(" -fx-text-alignment: right;");
        }
        return sb.toString();
    }

}
