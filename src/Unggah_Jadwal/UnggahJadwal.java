package Unggah_Jadwal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import login_page.loginPage;
import main.App;
import unggah_artikelPage.unggah_artikel;
import unggah_laporkegiatan.Data_LK;
import unggah_laporkegiatan.lapor_kegiatan;

public class UnggahJadwal implements Initializable {
    unggah_artikel get = new unggah_artikel();

    @FXML
    private TextField TFwaktu;

    @FXML
    private TextField TFlokasi;

    @FXML
    private TextField TFkegiatan;

    @FXML
    private DatePicker datepicker;

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
    private Button buttonUnggahJadwal;
    

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
    void buttonUnggahJadwal_klik(ActionEvent event) {

        if (!TFkegiatan.getText().isEmpty() && !TFlokasi.getText().isEmpty() && !TFwaktu.getText().isEmpty() && datepicker.getValue() != null) {
            if (TFwaktu.getTextFormatter().getValue() != null) {
                int waktu = (int) TFwaktu.getTextFormatter().getValue();
                UnggahJADWAL(datepicker.getValue(), waktu, TFkegiatan.getText(), TFlokasi.getText(), loginPage.namauser);
                get.sukses("Jadwal berhasil diunggah");
            } else {
                get.sukses("waktu hanya bisa angka");
            }
        } else {
            get.warning();
        }
        
    }

    @FXML
    private void UnggahJADWAL(LocalDate tanggal, int waktu, String kegiatan, String lokasiKegiatan,String pembuat) {
    
        ArrayList<Data_jadwal> newData = new ArrayList<>();
        File file = new File("src/data/DataJadwal.xml");
        if (file.exists()) {
            // File XML sudah ada, baca datanya
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.processAnnotations(ArrayList.class);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                newData = (ArrayList<Data_jadwal>) xstream.fromXML(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                System.err.println("Perhatian: " + e.getMessage());
            }
        }
        Data_jadwal dataUser = new Data_jadwal(tanggal, waktu, kegiatan, lokasiKegiatan,pembuat);
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
    public void initialize(URL location, ResourceBundle resources) {
            // Menggunakan TextFormatter untuk memvalidasi input hanya berupa angka
    TextFormatter<Integer> integerTextFormatter = new TextFormatter<>(new IntegerStringConverter(), null, c -> {
        if (c.getControlNewText().matches("\\d*")) {
            return c;
        }
        return null;
    });
    TFwaktu.setTextFormatter(integerTextFormatter);
    }


}

    