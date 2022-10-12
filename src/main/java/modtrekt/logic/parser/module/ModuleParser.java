package modtrekt.logic.parser.module;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;


/**
 * This class returns the module details based on the inputted module code.
 * Info obtained via NUSMods API.
 */
public class ModuleParser {


    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final String NUSMODS_ENDPOINT = "https://api.nusmods.com/v2/2022-2023/modules/";

    private static Module sendGet(String code) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(NUSMODS_ENDPOINT + code + ".json"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return null;
        }
        String res = response.body();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(res, JsonNode.class);
        String moduleCredit = (node.get("moduleCredit")).toString();
        String moduleName = (node.get("title")).toString();
        moduleCredit = moduleCredit.substring(1, moduleCredit.length() - 1);
        moduleName = moduleName.substring(1, moduleName.length() - 1)
                .replaceAll("[^a-zA-Z0-9\\s]", " ")
                .trim().replaceAll(" +", " ");

        return new Module(code, moduleName, moduleCredit);
    }

    public static Module fetchModule(ModCode code) throws IOException, InterruptedException {
        return sendGet(code.toString());
    }

}
