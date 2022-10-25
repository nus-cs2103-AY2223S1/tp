package seedu.address.model.attribute;

import java.util.Map;

public class Description extends AbstractAttribute<String> {

    public Description(String string) {
        super("Description", string);
    }

    @Override
    public Map<String, Object> toSaveableData() {
        // TODO Auto-generated method stub
        return null;
    }

}
