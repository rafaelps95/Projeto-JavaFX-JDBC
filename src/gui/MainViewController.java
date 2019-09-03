package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import gui.util.Alerts;
import gui.util.Pages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	@FXML
	private VBox mainFrame;
	
	@FXML
	private void onMenuItemSellerAction() {
		loadView(Pages.SELLER_LIST, (SellerListController controller) -> {
			controller.setSellerService(SellerService.getInstance());
			controller.updateTableView();
		});
	}

	@FXML
	private void onMenuItemDepartmentAction() {
		loadView(Pages.DEPARTMENT_LIST, (DepartmentListController controller) -> {
			controller.setDepartmentService(DepartmentService.getInstance());
			controller.updateTableView();
		});
	}

	@FXML
	private void onMenuItemAboutAction() {
		loadView(Pages.ABOUT, x -> {});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			mainFrame.getChildren().clear();
			mainFrame.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
