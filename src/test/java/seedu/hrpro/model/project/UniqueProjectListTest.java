package seedu.hrpro.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalProjects.APPLE;
import static seedu.hrpro.testutil.TypicalProjects.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hrpro.model.project.exceptions.DuplicateProjectException;
import seedu.hrpro.model.project.exceptions.ProjectNotFoundException;
import seedu.hrpro.testutil.ProjectBuilder;

public class UniqueProjectListTest {

    private final UniqueProjectList uniqueProjectList = new UniqueProjectList();

    @Test
    public void contains_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.contains(null));
    }

    @Test
    public void contains_projectNotInList_returnsFalse() {
        assertFalse(uniqueProjectList.contains(APPLE));
    }

    @Test
    public void contains_projectInList_returnsTrue() {
        uniqueProjectList.add(APPLE);
        assertTrue(uniqueProjectList.contains(APPLE));
    }

    @Test
    public void contains_projectWithSameIdentityFieldsInList_returnsTrue() {
        uniqueProjectList.add(APPLE);
        Project editedApple = new ProjectBuilder(APPLE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueProjectList.contains(editedApple));
    }

    @Test
    public void add_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.add(null));
    }

    @Test
    public void add_duplicateProject_throwsDuplicateProjectException() {
        uniqueProjectList.add(APPLE);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.add(APPLE));
    }

    @Test
    public void setProject_nullTargetProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(null, APPLE));
    }

    @Test
    public void setProject_nullEditedProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(APPLE, null));
    }

    @Test
    public void setProject_targetProjectNotInList_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.setProject(APPLE, APPLE));
    }

    @Test
    public void setProject_editedProjectIsSameProject_success() {
        uniqueProjectList.add(APPLE);
        uniqueProjectList.setProject(APPLE, APPLE);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(APPLE);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasSameIdentity_success() {
        uniqueProjectList.add(APPLE);
        Project editedApple = new ProjectBuilder(APPLE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueProjectList.setProject(APPLE, editedApple);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(editedApple);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasDifferentIdentity_success() {
        uniqueProjectList.add(APPLE);
        uniqueProjectList.setProject(APPLE, BOB);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(BOB);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProject_editedProjectHasNonUniqueIdentity_throwsDuplicateProjectException() {
        uniqueProjectList.add(APPLE);
        uniqueProjectList.add(BOB);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProject(APPLE, BOB));
    }

    @Test
    public void remove_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.remove(null));
    }

    @Test
    public void remove_projectDoesNotExist_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.remove(APPLE));
    }

    @Test
    public void remove_existingProject_removesProject() {
        uniqueProjectList.add(APPLE);
        uniqueProjectList.remove(APPLE);
        UniqueProjectList expecteduniqueProjectList = new UniqueProjectList();
        assertEquals(expecteduniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nulluniqueProjectList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((UniqueProjectList) null));
    }

    @Test
    public void setProjects_uniqueProjectList_replacesOwnListWithProvideduniqueProjectList() {
        uniqueProjectList.add(APPLE);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(BOB);
        uniqueProjectList.setProjects(expectedUniqueProjectList);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((List<Project>) null));
    }

    @Test
    public void setProjects_list_replacesOwnListWithProvidedList() {
        uniqueProjectList.add(APPLE);
        List<Project> projectList = Collections.singletonList(BOB);
        uniqueProjectList.setProjects(projectList);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(BOB);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_listWithDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> listWithDuplicateProjects = Arrays.asList(APPLE, APPLE);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProjects(listWithDuplicateProjects));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProjectList.asUnmodifiableObservableList().remove(0));
    }
}
