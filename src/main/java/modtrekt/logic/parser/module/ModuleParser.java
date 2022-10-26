package modtrekt.logic.parser.module;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import modtrekt.commons.util.FileUtil;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;


/**
 * This class returns the module details based on the inputted module code.
 * Info obtained via NUSMods API.
 */
public class ModuleParser {

    private static final Path backupFilePath = Paths
            .get("src/main/java/modtrekt/logic/parser/module/data/modules.json");

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final String NUSMODS_ENDPOINT = "https://api.nusmods.com/v2/2022-2023/modules/";

    private static Module sendGet(String code) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(NUSMODS_ENDPOINT + code + ".json"))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            /* A non-200 response code indicates that our request has failed, hence we use fallback data files. */
            if (response.statusCode() != 200) {
                try {
                    return parseModuleFromFile(code);
                } catch (IOException e) {
                    /* Error reading data file with code */
                    return null;
                }
            }
            String res = response.body();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(res, JsonNode.class);
            Module module = parseJsonNodeFromResponse(node, code);
            return module;
        } catch (ConnectException | InterruptedException e) {
            /* Handle connection-related errors */
            return parseModuleFromFile(code);
        }
    }

    private static Module parseJsonNodeFromResponse(JsonNode node, String code) {
        String moduleCredit = (node.get("moduleCredit")).toString();
        String moduleName = (node.get("title")).toString();
        moduleCredit = moduleCredit.substring(1, moduleCredit.length() - 1);
        moduleName = moduleName.substring(1, moduleName.length() - 1)
                .replaceAll("[^a-zA-Z0-9\\s]", " ")
                .trim().replaceAll(" +", " ");

        return new Module(code, moduleName, moduleCredit, "0");
    }

    private static Module parseModuleFromFile(String code) throws IOException {
        JsonNode fallbackData = new ObjectMapper().readValue(
                FileUtil.readFromFile(backupFilePath), JsonNode.class);
        String moduleData = fallbackData.get(code).toString();
        JsonNode moduleNode = new ObjectMapper().readValue(moduleData, JsonNode.class);
        String moduleName = moduleNode.get("title").toString();
        moduleName = moduleName.substring(1, moduleName.length() - 1)
                .replaceAll("[^a-zA-Z0-9\\s]", " ")
                .trim().replaceAll(" +", " ");
        String moduleCredit = moduleNode.get("moduleCredit").toString();
        moduleCredit = moduleCredit.substring(1, moduleCredit.length() - 1);
        return new Module(code, moduleName, moduleCredit, "0");
    }

    public static Module fetchModule(ModCode code) throws IOException, InterruptedException {
        return sendGet(code.toString());
    }

}
