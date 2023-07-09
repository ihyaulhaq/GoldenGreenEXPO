package unggah_artikelPage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login_page.data_user;
import login_page.loginPage;
import main.App;


public class unggah_artikel implements Initializable {

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
    private Button btnSave;

    @FXML
    private TextArea txtArtikel;

    @FXML
    private TextArea txtJudulArtikel;

    @FXML
    private void handleUnggahButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/Unggah_page/unggah_main.fxml", btnUnggah);
    }
    
    @FXML
    private void handleHomeButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/menu_page/menu.fxml", btnHome);
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
    void btnkliksave(ActionEvent event) {
        if(txtArtikel.getText().isEmpty()==false&&txtJudulArtikel.getText().isEmpty()==false){

            saveData(txtJudulArtikel.getText(),txtArtikel.getText(),loginPage.namauser);
            sukses("Artikel berhasil diunggah");
        }else{
            warning();
        }

    }

    public void warning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Field tidak boleh kosong!");

        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }
    public void sukses(String konteks) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText(konteks);

        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    @FXML
    private void saveData(String judul, String artikel, String pembuat) {
        ArrayList<data_artikel> newData = new ArrayList<>();
        File file = new File("src/data/DataArtikel.xml");
        if (file.exists()) {
            // File XML sudah ada, baca datanya
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.processAnnotations(ArrayList.class);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                newData = (ArrayList<data_artikel>) xstream.fromXML(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                System.err.println("Perhatian: " + e.getMessage());
            }
        }
        data_artikel dataartikel = new data_artikel(judul, artikel, pembuat, false);
        newData.add(dataartikel);

        // Tulis kembali ArrayList ke file XML
        XStream xstream2 = new XStream(new StaxDriver());
        xstream2.processAnnotations(ArrayList.class);
        String xml = xstream2.toXML(newData);
        xstream2.addPermission(AnyTypePermission.ANY);
        FileOutputStream coba = null;
        try {
            coba = new FileOutputStream(file);
            byte[] bytes = xml.getBytes("UTF-8");
            coba.write(bytes);
        } catch (IOException e) {
            System.err.println("Perhatian: " + e.getMessage());
        } finally {
            if (coba != null) {
                try {
                    coba.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

}