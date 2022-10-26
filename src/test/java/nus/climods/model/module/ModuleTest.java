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

// TODO Add negative test cases
class ModuleTest {

    private static final String TEST_ACADEMIC_YEAR = "2022-2023";
    private final List<ModuleInformation> moduleInformationList =
        ModulesApi.getInstance().acadYearModuleInfoJsonGet(TEST_ACADEMIC_YEAR);
    private final Module testModuleCS1101S = new Module(getModuleInformation("CS1101S"), TEST_ACADEMIC_YEAR);
    private final Module testModuleCS2103 = new Module(getModuleInformation("CS2103"), TEST_ACADEMIC_YEAR);

    ModuleTest() throws ApiException {
        testModuleCS1101S.loadMoreData();
        testModuleCS2103.loadMoreData();
    }

    private ModuleInformation getModuleInformation(String moduleCode) {
        Optional<ModuleInformation> moduleInfo =
            moduleInformationList.stream().filter(info -> info.getModuleCode().equals(moduleCode)).findFirst();
        assert moduleInfo.isPresent();

        return moduleInfo.get();
    }

    @Test
    public void test_moduleGetLessonTypes_success() {
        Set<LessonType> lessonTypes = testModuleCS1101S.getLessonTypes(SemestersEnum.S1);

        assertEquals(Set.of(LessonType.LEC, LessonType.REC, LessonType.TUT), lessonTypes);
    }

    @Test
    public void test_moduleHasLessonType_success() {
        assertTrue(testModuleCS2103.hasLessonType(LessonType.TUT));
        assertTrue(testModuleCS2103.hasLessonType(LessonType.LEC));
        assertFalse(testModuleCS2103.hasLessonType(LessonType.LAB));
    }

    @Test
    public void test_moduleSelectableLessonTypes_correctLessonTypesFound() {
        Set<LessonType> selectableLessonTypes = testModuleCS1101S.getSelectableLessonTypes(SemestersEnum.S1);

        // Note: CS1101S has 2 lecture slots, but it comes as a set, therefore it is not selectable
        assertEquals(Set.of(LessonType.TUT, LessonType.REC), selectableLessonTypes);
    }

    @Test
    public void test_moduleUnselectableLessonTypes_correctLessonTypesFound() {
        Set<LessonType> unselectableLessonTypes = testModuleCS1101S.getUnselectableLessonTypes(SemestersEnum.S1);

        assertEquals(Set.of(LessonType.LEC), unselectableLessonTypes);
    }

    @Test
    public void test_moduleGetLessons_correctNumber() {
        HashMap<LessonType, Module.ModuleLessonIdMap> lessonsMap1 = testModuleCS1101S.getLessons(SemestersEnum.S1);
        HashMap<LessonType, Module.ModuleLessonIdMap> lessonsMap2 = testModuleCS1101S.getLessons(SemestersEnum.S2);

        assertEquals(106, lessonsMap1.get(LessonType.TUT).size());
        assertEquals(6, lessonsMap2.get(LessonType.TUT).size());
        assertEquals(35, lessonsMap1.get(LessonType.REC).size());
        assertEquals(3, lessonsMap2.get(LessonType.REC).size());
        // Note: There are 2 lecture slots in S1, but they are under the same lesson id,
        //       therefore they are considered as one lesson
        assertEquals(1, lessonsMap1.get(LessonType.LEC).size());
        assertEquals(1, lessonsMap2.get(LessonType.LEC).size());
    }

    @Test
    public void test_moduleHasLessonId() {
        assertTrue(testModuleCS2103.hasLessonId("06", SemestersEnum.S1, LessonType.TUT));
        assertFalse(testModuleCS2103.hasLessonId("0X", SemestersEnum.S1, LessonType.TUT));
    }
}
