package Unggah_page;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import login_page.loginPage;
import main.App;

public class unggah_main implements Initializable  {

    @FXML
    private Button btnHapusLK;

    @FXML
    private Button btnHapusSukarelawan;

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
    private Button btnSukarelawan;
    
    @FXML
    private Button btnCariSukarelawan;

    @FXML
    private Button btnLaporKegiatan;

    @FXML
    private Button btnUnggahJadwal;
    
    @FXML
    private Button btnUnggahArtikel;

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
    void klikArtikel(ActionEvent event) {
        if(loginPage.isLoggedIn()){
            App.getInstance().gantiScreen("/unggah_artikelPage/unggah_artikel.fxml", btnUnggahArtikel);
        }else{
            App.getInstance().showLoginPrompt();
        }
    }

    @FXML
    void klikLaporKegiatan(ActionEvent event) {
        if(loginPage.isLoggedIn()){
            App.getInstance().gantiScreen("/unggah_laporkegiatan/lapor_kegiatan.fxml", btnLaporKegiatan);
        }else{
            App.getInstance().showLoginPrompt();
        }
    }
    
    @FXML
    void Klik_unggahJadwal(ActionEvent event) {
        if(loginPage.isLoggedIn()){
            App.getInstance().gantiScreen("/Unggah_Jadwal/UnggahJadwal.fxml", btnUnggahJadwal);
        }else{
            App.getInstance().showLoginPrompt();
        }      
    }
    
    @FXML
    void klikCariSukarelawan(ActionEvent event) {
        if(loginPage.isLoggedIn()){
            App.getInstance().gantiScreen("/isi_daftar_relawan/isi_daftar_relawan.fxml", btnCariSukarelawan);
        }else{
            App.getInstance().showLoginPrompt();
        }
    }

    @FXML
    void KlikHapusLK(ActionEvent event) {
        if(loginPage.isLoggedIn()){
            App.getInstance().gantiScreen("/hapus_laporan/hapusLaporan.fxml", btnHapusLK);
        }else{
            App.getInstance().showLoginPrompt();
        }
    }

    @FXML
    void KlikHapusSukarelawan(ActionEvent event) {
        if(loginPage.isLoggedIn()){
            App.getInstance().gantiScreen("/hapus_sukarelawan/hapusSukarelawan.fxml", btnHapusSukarelawan);
        }else{
            App.getInstance().showLoginPrompt();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

}
