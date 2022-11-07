package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertCommandFailure;
import static coydir.logic.commands.CommandTestUtil.assertViewDepartmentCommandSuccess;
import static coydir.testutil.TypicalPersons.getTypicalDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import coydir.logic.parser.ViewDepartmentCommandParser;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;

public class ViewDepartmentCommandTest {

    private Model model;
    private ModelManager expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void viewGeneralManagementDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("General Management");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "General Management");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "General Management");
    }

    @Test
    public void viewFinanceDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Finance");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Finance");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Finance");
    }

    @Test
    public void viewAdministrationDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Administration");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Administration");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Administration");
    }

    @Test
    public void viewBoardOfDirectorsDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Board of Directors");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Board of Directors");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Board of Directors");
    }

    @Test
    public void viewCustomerServiceDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Customer Service");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Customer Service");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Customer Service");
    }

    @Test
    public void viewHumanResourcesDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Human Resources");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Human Resources");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Human Resources");
    }

    @Test
    public void viewInformationTechnologyDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Information Technology");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Information Technology");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Information Technology");
    }

    @Test
    public void viewLegalDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Legal");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Legal");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Legal");
    }

    @Test
    public void viewMarketingDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Marketing");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Marketing");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Marketing");
    }

    @Test
    public void viewOperationsDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Operations");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Operations");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Operations");
    }

    @Test
    public void viewProductManagementDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Product Management");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Product Management");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Product Management");
    }

    @Test
    public void viewProductionDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Production");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Production");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Production");
    }

    @Test
    public void viewQualityAssuranceDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Quality Assurance");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Quality Assurance");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Quality Assurance");
    }

    @Test
    public void viewResearchAndDevelopmentDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Research and Development");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Research and Development");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Research and Development");
    }

    @Test
    public void viewSalesDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Sales");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Sales");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Sales");
    }

    @Test
    public void viewTechnologyDepartmentSuccess() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Technology");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Technology");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Technology");
    }

    @Test
    public void viewInvalidDepartmentTest() {
        ViewDepartmentCommand viewDepartmentCommand = new ViewDepartmentCommand("Tech");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_UNKNOWN_DEPARTMENT);
        assertCommandFailure(viewDepartmentCommand, model, expectedMessage);
    }

    @Test
    public void viewDepartmentCaseInsensitiveTest() throws ParseException {
        ViewDepartmentCommandParser viewDepartmentCommandParser = new ViewDepartmentCommandParser();
        ViewDepartmentCommand viewDepartmentCommand = viewDepartmentCommandParser.parse("TeChNoLogY");
        String expectedMessage = String.format(
                ViewDepartmentCommand.MESSAGE_VIEW_DEPARTMENT_SUCCESS, "Technology");
        assertViewDepartmentCommandSuccess(viewDepartmentCommand, model, expectedMessage,
                expectedModel, "Technology");
    }
}
