package seedu.address.storage;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Custom Deserializer that deserialize person into Student or Professor or Teaching Assistant.
 */
public class JsonAdaptedPersonDeserializer extends JsonDeserializer<JsonAdaptedPerson> {

    @Override
    public JsonAdaptedPerson deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        Root root = p.readValueAs(Root.class);
        if (root.type.equals("p")) {
            return new JsonAdaptedProfessor(root.type, root.name, root.moduleCode, root.phone,
                    root.email, root.gender, root.tagged, root.location);
        } else if (root.type.equals("s")) {
            return new JsonAdaptedStudent(root.type, root.name, root.name, root.moduleCodes, root.phone,
                    root.email, root.gender, root.tagged, root.location);
        } else if (root.type.equals("t")) {
            return new JsonAdaptedTeachingAssistant(root.type, root.name, root.moduleCode, root.phone,
                    root.email, root.gender, root.tagged, root.location);
        } else {
            throw new IOException("Invalid type");
        }

    }

    private static class Root {
        private String type;
        private List<JsonAdaptedModuleCode> moduleCodes;
        private String moduleCode;
        private String name;
        private String phone;
        private String email;
        private String gender;
        private List<JsonAdaptedTag> tagged;
        private String location;
    }
}
