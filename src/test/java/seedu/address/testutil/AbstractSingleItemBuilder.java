package seedu.address.testutil;

import java.util.List;
import java.util.Set;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.tag.Tag;

abstract class AbstractSingleItemBuilder extends AbstractDisplayItemBuilder {

    protected AbstractSingleItem parent;

    protected AbstractSingleItemBuilder(Name name, List<Attribute<?>> attributes,
                                        Set<Tag> tags) {
        super(name, attributes, tags);
    }
}
