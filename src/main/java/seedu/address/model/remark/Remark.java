package seedu.address.model.remark;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a remark for the client.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Remark {

    // Identity fields
    private final Text text;

    /**
     * Every field must be present and not null.
     */
    public Remark(Text text) {
        requireAllNonNull(text);
        this.text = text;

    }

    public Text getText() {
        return text;
    }

    /**
     * Returns true if both remarks have the same text.
     * This defines a weaker notion of equality between two remarks.
     */
    public boolean isSameRemark(Remark otherRemark) {
        if (otherRemark == this) {
            return true;
        }

        if (otherRemark == null) {
            return false;
        }

        // Remove all trailing white spaces, change all alphabet to lower case, and concatenate all characters.
        String remarkText = getText().value.trim().toLowerCase().replace(" ", "");
        String otherText = otherRemark.getText().value.trim().toLowerCase().replace(" ", "");

        return remarkText.equals(otherText);
    }

    /**
     * Returns true if both remarks have the same identity and data fields.
     * This defines a stronger notion of equality between two remarks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return otherRemark.getText().value.equals(getText().value);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getText());

        return builder.toString();
    }

}
