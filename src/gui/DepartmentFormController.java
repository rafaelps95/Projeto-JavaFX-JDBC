package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {

	private Department department;

	public void setDepartment(Department department) {
		this.department = department;
	}

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private Label labelError;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnCancel;

	@FXML
	private void onBtnSaveAction() {
		try {
			Integer id = Integer.parseInt(txtId.getText());
			String name = txtName.getText();
			department.setId(id);
			department.setName(name);
//			DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
//			departmentDao.insert(dep);
		} catch (NumberFormatException e) {
			labelError.setText(e.getMessage());
		}

	}

	@FXML
	private void onBtnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

	public void updateFormData() {
		if (department == null) {
			throw new IllegalStateException("Department was null");
		}
		txtId.setText(String.valueOf(department.getId()));
		txtName.setText(department.getName());
	}
}
