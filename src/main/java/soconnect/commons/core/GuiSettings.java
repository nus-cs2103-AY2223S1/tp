package soconnect.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final String DEFAULT_ORDER = "TAGS>PHONE>EMAIL>ADDRESS";
    private static final String DEFAULT_HIDDEN_ATTRIBUTES = "NONE";

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final String attributeOrder;
    private final String hiddenAttributes;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
        attributeOrder = DEFAULT_ORDER;
        hiddenAttributes = DEFAULT_HIDDEN_ATTRIBUTES;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     *
     * @param windowWidth The width of the window.
     * @param windowHeight The height of the window.
     * @param xPosition The x-coordinate of the window coordinates.
     * @param yPosition The y-coordinate of the window coordinates.
     * @param attributeOrder The order of the attributes displayed in string format.
     * @param hiddenAttributes The attributes that are hidden in string format.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition,
            String attributeOrder, String hiddenAttributes) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.attributeOrder = attributeOrder;
        this.hiddenAttributes = hiddenAttributes;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public String getAttributeOrder() {
        return attributeOrder;
    }

    public String getHiddenAttributes() {
        return hiddenAttributes;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { //this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && Objects.equals(windowCoordinates, o.windowCoordinates)
                && attributeOrder.equals(o.attributeOrder)
                && hiddenAttributes.equals(o.hiddenAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, attributeOrder, hiddenAttributes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates + "\n");
        sb.append("Attribute Order : " + attributeOrder + "\n");
        sb.append("Hidden Attributes : " + hiddenAttributes);
        return sb.toString();
    }
}
