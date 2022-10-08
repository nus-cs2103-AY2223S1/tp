package modtrekt.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import modtrekt.model.ModuleList;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.module.Module;
import modtrekt.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ModuleList} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModCode("CS2109S"), new ModName("Intro to AI and machine learning"), new ModCredit("4")),
            new Module(new ModCode("CS2040S"), new ModName("Data Structures and Algorithms"), new ModCredit("4"))
        };
    }

    public static ReadOnlyModuleList getSampleModuleList() {
        ModuleList sampleAb = new ModuleList();
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
