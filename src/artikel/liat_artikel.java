package artikel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import main.App;
import unggah_artikelPage.data_artikel;

public class liat_artikel implements Initializable{
    
    private data_artikel artikelTerpilih;

    @FXML
    private Button btnDonasi;

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
    private Text lblJudul1;

    @FXML
    private Text lblJudul2;

    @FXML
    private Text lblJudul3;

    @FXML
    private Text lblJudul4;
    
    @FXML
    private Button btnArtikel1;

    @FXML
    private Button btnArtikel2;

    @FXML
    private Button btnArtikel3;

    @FXML
    private Button btnArtikel4;
    
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

    private static ArrayList<data_artikel> DataArtikelList =null;
    
    @FXML
    private void pindahKeArtikel(ActionEvent event) {
        
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(ArrayList.class);
        
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream file = null;
        try {
            file = new FileInputStream("src/data/DataArtikel.xml");
            DataArtikelList = (ArrayList<data_artikel>) xstream.fromXML(file);

            if (event.getSource() == btnArtikel1) {
                if (!DataArtikelList.isEmpty()) {
                    DataArtikelList.get(0).setKlik(true);
                }else{

                    return;
                }

            } else if (event.getSource() == btnArtikel2) {
                if (DataArtikelList.size() > 1) {
                    DataArtikelList.get(1).setKlik(true);
                }else{

                    return;
                }

            } else if (event.getSource() == btnArtikel3) {
                if (DataArtikelList.size() > 2) {
                    DataArtikelList.get(2).setKlik(true);
                }else{

                    return;
                }
            } else if (event.getSource() == btnArtikel4) {
                if (DataArtikelList.size() > 3) {
                    DataArtikelList.get(3).setKlik(true);   
                }else{

                    return;
                }
            }
            
        App.getInstance().gantiScreen("/artikel/isi_artikel/isi_artikel.fxml", btnArtikel4);

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

    public static ArrayList<data_artikel> panggilarray(){
        return DataArtikelList;
    }

    @FXML
    private void load() {

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(ArrayList.class);
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream file = null;
    try {
        file = new FileInputStream("src/data/DataArtikel.xml");
        ArrayList<data_artikel> dataArtikelList = (ArrayList<data_artikel>) xstream.fromXML(file);
        
        if (!dataArtikelList.isEmpty()) {
            lblJudul1.setText(dataArtikelList.get(0).getJudul());
        }
        if (dataArtikelList.size() > 1) {
            lblJudul2.setText(dataArtikelList.get(1).getJudul());
        }
        if (dataArtikelList.size() > 2) {
            lblJudul3.setText(dataArtikelList.get(2).getJudul());
        }
        if (dataArtikelList.size() > 3) {
            lblJudul4.setText(dataArtikelList.get(3).getJudul());
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

    public data_artikel getArtikelTerpilih() {
        return artikelTerpilih;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      load();
    } 
    
}