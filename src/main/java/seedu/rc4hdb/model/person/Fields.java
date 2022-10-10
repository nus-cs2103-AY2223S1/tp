package seedu.rc4hdb.model.person;

import java.util.List;

/**
 *
 */
public class Fields {
    public static final String INDEX_IDENTIFIER = "Index";
    public static final String ADDRESS_FIELD = "Address";
    public static final String NAME_FIELD = "Name";
    public static final String PHONE_FIELD = "Phone";
    public static final String EMAIL_FIELD = "Email";
    public static final String TAG_FIELD = "Tag";

    public static final List<String> fields = List.of(INDEX_IDENTIFIER, ADDRESS_FIELD, NAME_FIELD,
            PHONE_FIELD, EMAIL_FIELD, TAG_FIELD);
}
