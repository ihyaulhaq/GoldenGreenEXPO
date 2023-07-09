package artikel.isi_artikel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import artikel.liat_artikel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import main.App;
import unggah_artikelPage.data_artikel;

public class isi_artikel implements Initializable{


    @FXML
    private Button btnHome;

    @FXML
    private Button btnJadwal;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSukarelawan;

    @FXML
    private Button btnUnggah;

    @FXML
    private Label labelJudul;

    @FXML
    private Label labelPembuat;

    @FXML
    private Text txtArtikel;

    @FXML
    private void handleUnggahButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/Unggah_page/unggah_main.fxml", btnUnggah);
        
    }

    @FXML
    private void Login_ButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/login_page/loginPage.fxml", btnLogin);
        
    }

    @FXML
    private void handleHomeButtonClick(ActionEvent event) {
    
        App.getInstance().gantiScreen("/menu_page/menu.fxml", btnHome);
    }
    @FXML
    private void tombol_sukarelawan(ActionEvent event) {
        App.getInstance().gantiScreen("/sukarelawan_page/liat_sukarelawan.fxml", btnSukarelawan);
    }
    
    @FXML
    private void tombol_jadwal(ActionEvent event) {
        App.getInstance().gantiScreen("/jadwal_page/jadwal.fxml", btnJadwal);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        for (int i = 0; i < liat_artikel.panggilarray().size(); i++) {
            if (liat_artikel.panggilarray().get(i).getklik()==true) {
                labelJudul.setText(liat_artikel.panggilarray().get(i).getJudul());
                txtArtikel.setText(liat_artikel.panggilarray().get(i).getArtikel());
                labelPembuat.setText(liat_artikel.panggilarray().get(i).getPembuat());
                break;
            }
        }

    } 
    
}
