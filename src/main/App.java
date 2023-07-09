package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application {

    private static App instance;
    private static Stage primaryStage;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/menu_page/menu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Green Global");

        Image icon = new Image("/Gambar/icon GG2.png");

        // Set the icon image
        stage.getIcons().add(icon);
       
        stage.setScene(scene);
        stage.show();
        primaryStage = stage;
        instance=this;
    }
    //method ganti page
    public void gantiScreen(String fxml, Button button){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent unggahMainRoot = loader.load();
            Scene unggahMainScene = new Scene(unggahMainRoot);
            // Stage currentStage = (Stage) button.getScene().getWindow();
            Stage currentStage;
            if (button != null) {
                currentStage = (Stage) button.getScene().getWindow();
            } else {
                currentStage = primaryStage;
            }
            currentStage.setScene(unggahMainScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoginPrompt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Required");
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Silahkan login untuk menggunakan ini");
        
        ButtonType loginButton = new ButtonType("Login");
        ButtonType cancelButton = new ButtonType("Kembali");
        
        alert.getButtonTypes().setAll(loginButton, cancelButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == loginButton) {
                gantiScreen("/login_page/loginPage.fxml", null);
            }
        });
    }




    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

