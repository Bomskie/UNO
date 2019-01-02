package Client.Controllers;

import Shared.Logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowController {

    private static WindowController windowController = new WindowController();
    private Stage mainStage = new Stage();

    private static Stage loginStage;
    private static Stage menuStage;

    private static LoginController loginController;


    public static WindowController getWindowController(){
        return windowController;
    }

    public void LoadStages(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
            Parent rootLogin = loader.load();
            loginStage = new Stage();
            loginStage.setTitle("UNO Login");
            loginStage.setResizable(false);
            loginStage.setScene(new Scene(rootLogin, 600, 300));
            loginController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("/FXML/Menu.fxml"));
            Parent rootRegister = loader.load();
            menuStage = new Stage();
            menuStage.setTitle("UNO Menu");
            menuStage.setResizable(false);
            menuStage.setScene(new Scene(rootRegister, 400, 400));
            menuStage = loader.getController();
        } catch (Exception e) {
            Logger.getInstance().log(e);
        }
    }


    public void showLogin() {
        setStage(loginStage);
        showScene();
    }

    public void showMenu(){
        setStage(menuStage);
        showScene();
    }

    private void setStage(Stage stage) {
        Platform.runLater(() -> {
            try {
                if (mainStage != stage) {
                    mainStage.close();
                }
                mainStage = stage;
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    private void showScene(){
        Platform.runLater(() -> {
            mainStage.show();
        });
    }

    public void showMsg(String header, String msg){
        Platform.runLater(() ->
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(header);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }



    /*
    private static WindowController windowController = new WindowController();
    private Stage primaryStage;
    private FXMLLoader loader;

    public WindowController(){
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public static WindowController getWindowController() {
        return windowController;
    }

    public void ShowLogin() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("UNO");
        primaryStage.setScene(new Scene(root, 600, 300));


        //geef geen resize
        primaryStage.resizableProperty().setValue(false);

        primaryStage.show();
    }

    public void showMenu() throws IOException {

        loader = new FXMLLoader(getClass().getResource("/FXML/Menu.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("UNO");
        primaryStage.setScene(new Scene(root, 600, 300));


        //geef geen resize
        primaryStage.resizableProperty().setValue(false);

        primaryStage.show();
    }
    */
}