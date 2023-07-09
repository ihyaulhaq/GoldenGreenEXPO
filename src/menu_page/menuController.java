package menu_page;
import main.App;
import unggah_artikelPage.data_artikel;
import unggah_laporkegiatan.Data_LK;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class menuController implements Initializable {
    private Set<StackPane> storedStackPanes;
    
    @FXML
    private Button btnUnggah;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnJadwal;
    
    @FXML
    private Button btnArtikel;

    @FXML
    private VBox vboxLaporan;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSukarelawan;

    @FXML
    private StackPane stackPane;

    @FXML
    private void handleUnggahButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/Unggah_page/unggah_main.fxml", btnUnggah);
    }
    @FXML
    private void handleArtikelButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/artikel/Liat_artikel.fxml", btnArtikel);

    }
    @FXML
    private void Login_ButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/login_page/loginPage.fxml", btnLogin);
    }
    @FXML
    private void tombol_sukarelawan(ActionEvent event) {
        App.getInstance().gantiScreen("/sukarelawan_page/liat_sukarelawan.fxml", btnSukarelawan);
    }
    
    @FXML
    private void tombol_jadwal(ActionEvent event) {
        App.getInstance().gantiScreen("/jadwal_page/jadwal.fxml", btnJadwal);
    }

    @FXML
    private void loadLK() {

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Data_LK.class);
        xstream.addPermission(AnyTypePermission.ANY); 
        FileInputStream file = null;

        storedStackPanes = AppStatus.getStackPane();
        try {
            file = new FileInputStream("src/data/DataLaporanKegiatan.xml");
            ArrayList<Data_LK> dataList = (ArrayList<Data_LK>) xstream.fromXML(file);

            for (Data_LK data : dataList){

                StackPane newstack = new StackPane();
                newstack.setPrefHeight(121.0);
                newstack.setPrefWidth(180.0);
                newstack.setStyle("-fx-background-color: #036666; -fx-background-radius: 40;");
                newstack.setAlignment(Pos.TOP_LEFT);
                
                Text label1 = new Text(data.getJudul_LK());
                label1.setFill(Color.WHITE);
                label1.setFont(new Font(14.0));
                StackPane.setMargin(label1, new Insets(10, 40, 0, 40));
            
                Text label2 = new Text(data.getPembuat_LK());
                label2.setFill(Color.WHITE);
                label2.setFont(new Font(11.0));
                StackPane.setMargin(label2, new Insets(30, 40, 0, 40));

    
                Text label3 = new Text(data.getIsi_LK());
                label3.setFill(Color.WHITE);
                StackPane.setMargin(label3, new Insets(50, 0, 0, 40));

                
    
                newstack.getChildren().addAll(label1, label2,label3);
    
                vboxLaporan.getChildren().add(newstack);
                VBox.setMargin(newstack, new Insets(20, 0, 0, 0));
    
                AppStatus.setStackPane(newstack);

                if (!storedStackPanes.contains(newstack)) {
                    vboxLaporan.getChildren().add(newstack);
                    VBox.setMargin(newstack, new Insets(20, 0, 0, 0));
                    AppStatus.setStackPane(newstack);
                }
            }
        
        
       
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadLK();
    }    
    
}
