package jadwal_page;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Unggah_Jadwal.Data_jadwal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.App;

public class jadwal implements Initializable {

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
    private TableView<Data_jadwal> TableView_Jadwal;

    ObservableList<Data_jadwal> data = FXCollections.observableArrayList();

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
    
    private void loadDataFromXML() {
       
        // Baca data dari file XML
        File file = new File("src/data/DataJadwal.xml");
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Data_jadwal", Data_jadwal.class);
        List<Data_jadwal> dataList = new ArrayList<>();
        if (file.exists()) {
            dataList = (List<Data_jadwal>) xstream.fromXML(file);
        }

        // Tambahkan data ke ObservableList
        data.addAll(dataList);

        // Atur kolom tabel
        TableColumn<Data_jadwal, String> kolomKomunitas = new TableColumn<>("Komunitas");
        kolomKomunitas.setCellValueFactory(new PropertyValueFactory<>("pembuat"));

        TableColumn<Data_jadwal, LocalDate> kolomTanggal = new TableColumn<>("Tanggal");
        kolomTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        TableColumn<Data_jadwal, String> kolomKegiatan = new TableColumn<>("Kegiatan");
        kolomKegiatan.setCellValueFactory(new PropertyValueFactory<>("kegiatan"));

        TableColumn<Data_jadwal, String> kolomLokasi = new TableColumn<>("Lokasi Kegiatan");
        kolomLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiKegiatan"));

        TableColumn<Data_jadwal, Integer> kolomWaktu = new TableColumn<>("Waktu");
        kolomWaktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));

        // Set kolom ke TableView
        TableView_Jadwal.getColumns().clear();
        TableView_Jadwal.getColumns().addAll(kolomKomunitas, kolomTanggal, kolomKegiatan, kolomLokasi, kolomWaktu);

        // Set data ke TableView
        TableView_Jadwal.setItems(data);
    
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataFromXML();
        // setupTableView();
    }

    
}
