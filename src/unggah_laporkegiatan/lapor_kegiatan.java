package unggah_laporkegiatan;

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
import javafx.scene.control.TextArea;
import login_page.data_user;
import login_page.loginPage;
import main.App;
import unggah_artikelPage.data_artikel;
import unggah_artikelPage.unggah_artikel;

public class lapor_kegiatan implements Initializable {


    unggah_artikel get=new unggah_artikel();
    @FXML
    private Button btnSukarelawan;

    @FXML
    private Button btnUnggah;

    @FXML
    private Button btnDonasi;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnJadwal;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnUnggahLK;
    
    @FXML
    private TextArea TFIsiLaporan;

    @FXML
    private TextArea TFJudulLaporan;
    
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
    private void klikUnggah(ActionEvent event){
        if(TFJudulLaporan.getText().isEmpty()==false && TFJudulLaporan.getText().isEmpty()==false){
            UnggahLK(TFJudulLaporan.getText(), TFIsiLaporan.getText(), loginPage.namauser);
            get.sukses("Laporan berhasil diunggah!!");
        }
    }

    @FXML
    private void UnggahLK(String judulLaporan, String laporanLK, String pembuat) {
    
        ArrayList<Data_LK> newData = new ArrayList<>();
        File file = new File("src/data/DataLaporanKegiatan.xml");
        if (file.exists()) {
            // File XML sudah ada, baca datanya
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.processAnnotations(ArrayList.class);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                newData = (ArrayList<Data_LK>) xstream.fromXML(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                System.err.println("Perhatian: " + e.getMessage());
            }
        }
        Data_LK dataUser = new Data_LK(judulLaporan, laporanLK, pembuat);
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

