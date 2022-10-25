package nus.climods.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.ModulesApi;
import org.openapitools.client.model.ModuleInformation;
import org.openapitools.client.model.SemestersEnum;

class ModuleTest {

    private static final String TEST_ACADEMIC_YEAR = "2022-2023";
    private final List<ModuleInformation> moduleInformationList =
        ModulesApi.getInstance().acadYearModuleInfoJsonGet(TEST_ACADEMIC_YEAR);

    ModuleTest() throws ApiException {
    }

    private ModuleInformation getModuleInformation(String moduleCode) {
        Optional<ModuleInformation> moduleInfo =
            moduleInformationList.stream().filter(info -> info.getModuleCode().equals(moduleCode)).findFirst();
        assert moduleInfo.isPresent();

        return moduleInfo.get();
    }

    @Test
    public void test_moduleGetLessonTypes_success() throws ApiException {
        Module module = new Module(getModuleInformation("CS1101S"), TEST_ACADEMIC_YEAR);
        module.loadMoreData();
        Set<LessonType> lessonTypes = module.getLessonTypes(SemestersEnum.S1);

        assertEquals(Set.of(LessonType.LEC, LessonType.REC, LessonType.TUT), lessonTypes);
    }

    @Test
    public void test_moduleHasLessonType_success() throws ApiException {
        Module module = new Module(getModuleInformation("CS2103"), TEST_ACADEMIC_YEAR);
        module.loadMoreData();

        assertTrue(module.hasLessonType(LessonType.TUT));
        assertTrue(module.hasLessonType(LessonType.LEC));
        assertFalse(module.hasLessonType(LessonType.LAB));
    }

    @Test
    public void test_moduleSelectableLessonTypes_correctLessonTypesFound() throws ApiException {
        Module module = new Module(getModuleInformation("CS1101S"), TEST_ACADEMIC_YEAR);
        module.loadMoreData();
        Set<LessonType> selectableLessonTypes = module.getSelectableLessonTypes(SemestersEnum.S1);

        // Note: CS1101S has 2 lecture slots, but it comes as a set, therefore it is not selectable
        assertEquals(Set.of(LessonType.TUT, LessonType.REC), selectableLessonTypes);
    }

    @Test
    public void test_moduleGetLessons_correctNumber() throws ApiException {
        Module module = new Module(getModuleInformation("CS1101S"), TEST_ACADEMIC_YEAR);
        module.loadMoreData();
        HashMap<LessonType, Module.ModuleLessonIdMap> lessonsMap1 = module.getLessons(SemestersEnum.S1);
        HashMap<LessonType, Module.ModuleLessonIdMap> lessonsMap2 = module.getLessons(SemestersEnum.S2);

        assertEquals(106, lessonsMap1.get(LessonType.TUT).size());
        assertEquals(6, lessonsMap2.get(LessonType.TUT).size());
        assertEquals(35, lessonsMap1.get(LessonType.REC).size());
        assertEquals(3, lessonsMap2.get(LessonType.REC).size());
        // Note: There are 2 lecture slots in S1, but they are under the same lesson id,
        //       therefore they are considered as one lesson
        assertEquals(1, lessonsMap1.get(LessonType.LEC).size());
        assertEquals(1, lessonsMap2.get(LessonType.LEC).size());
    }
}
