package isi_daftar_relawan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import login_page.data_user;
import login_page.loginPage;
import main.App;
import unggah_artikelPage.unggah_artikel;
import unggah_laporkegiatan.Data_LK;

public class isi_daftar_relawan implements Initializable{

    unggah_artikel get=new unggah_artikel();

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
    private Button btnUnggahSK;

    @FXML
    private TextField txtNoTel;

    @FXML
    private TextField txtSyarat;

    @FXML
    void Login_ButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/login_page/loginPage.fxml", btnLogin);

    }

    @FXML
    void handleHomeButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/menu_page/menu.fxml", btnHome);

    }

    @FXML
    void handleUnggahButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/Unggah_page/unggah_main.fxml", btnUnggah);

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
    void klik_btnUnggah(ActionEvent event){
        if(txtSyarat.getText().isEmpty()==false && txtNoTel.getText().isEmpty()==false){
            UnggahDR(txtNoTel.getText(), txtSyarat.getText(), loginPage.namauser);
            get.sukses("kriteria pencarian sukarelawan sudah diunggah!!");
        }else{
        }
    }

    @FXML
    private void UnggahDR(String NOtelpon, String Syarat, String pembuat) {
        
        ArrayList<dataDR> newData = new ArrayList<>();
        File file = new File("src/data/DataCariRelawan.xml");
        if (file.exists()) {
            // File XML sudah ada, baca datanya
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.processAnnotations(ArrayList.class);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                newData = (ArrayList<dataDR>) xstream.fromXML(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                System.err.println("Perhatian: " + e.getMessage());
            }
        }
        dataDR dataUser = new dataDR(NOtelpon, Syarat, pembuat);
        newData.add(dataUser);

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