package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class FieldPrefixes {

    public List<Prefix> prefixes;
    public HashMap<Prefix, String> map;

    public FieldPrefixes() {
        prefixes = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean contains(Prefix prefix) {
        return prefixes.contains(prefix);
    }

    public void addPrefix(Prefix prefix, String name, Model model) throws ParseException {
        if (prefixes.contains(prefix)) {
            throw new ParseException("Prefix has been stored previously. Enter a different prefix");
        }
        if (map.values().contains(name)) {
            throw new ParseException("Field has been stored previously. Enter a different field");
        }
        prefixes.add(prefix);
        model.addField(name);
        map.put(prefix, name);
    }

    public void removePrefix(Prefix prefix) throws ParseException {
        if (!prefixes.contains(prefix)) {
            throw new ParseException("Field not found");
        }
        prefixes.remove(prefix);
        map.remove(prefix);
    }
}
