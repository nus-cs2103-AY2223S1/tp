package seedu.address.model.attribute;

import java.util.Map;

/**
 * Creates an attribute to represent a description of a display Item
 */
public class Description extends AbstractAttribute<String> {

    public static final String TYPE = "Description";

    public Description(String string) {
        super(TYPE, string);
    }

    @Override
    public Map<String, Object> toSaveableData() {
        // TODO Auto-generated method stub
        return null;
    }

}
