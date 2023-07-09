package hapus_sukarelawan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import isi_daftar_relawan.dataDR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import login_page.loginPage;
import main.App;
import menu_page.AppStatus;
import unggah_laporkegiatan.Data_LK;

public class hapusSukarelawan implements Initializable {

    private Set<StackPane> storedStackPanes;
    
    @FXML
    private Label lblPeringatan;

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
    private VBox VboxhapusRelawan;

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
    private void loadDR() {

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(dataDR.class);
        xstream.addPermission(AnyTypePermission.ANY); 
        FileInputStream file = null;

        storedStackPanes = AppStatus.getStackPane();
        try {
            file = new FileInputStream("src/data/DataCariRelawan.xml");
            ArrayList<dataDR> dataList = (ArrayList<dataDR>) xstream.fromXML(file);
            boolean data_ada = false;
            for (dataDR data : dataList){

                if(data.getPembuat().equals(loginPage.namauser)){

                    StackPane newstack = new StackPane();
                    newstack.setPrefHeight(121.0);
                    newstack.setPrefWidth(242.0);
                    newstack.setStyle("-fx-background-color: #036666; -fx-background-radius: 10;");
                    newstack.setAlignment(Pos.TOP_LEFT);
                    
                    Text label1 = new Text(data.getPembuat());
                    label1.setFill(Color.WHITE);
                    label1.setFont(new Font(14.0));
                    StackPane.setMargin(label1, new Insets(10, 40, 0, 40));
                
                    Text label2 = new Text(data.getNoTelpon());
                    label2.setFill(Color.WHITE);
                    label2.setFont(new Font(12.0));
                    StackPane.setMargin(label2, new Insets(30, 40, 0, 40));
    
        
                    Text label3 = new Text(data.getSyarat());
                    label3.setFill(Color.WHITE);
                    StackPane.setMargin(label3, new Insets(50, 0, 0, 40));

                    Button button = new Button("Hapus");
                    button.setStyle("-fx-background-color: white; -fx-background-radius: 30;");
                    StackPane.setAlignment(button, Pos.BOTTOM_CENTER);
                    StackPane.setMargin(button, new Insets(0, 0, 10, 0));
                    button.setOnAction(event -> {
                        hapusUnggahan(newstack);
                    });
        
                    newstack.getChildren().addAll(label1, label2,label3,button);

                    newstack.setId(data.getIdentifier());
        

                    VboxhapusRelawan.getChildren().add(newstack);
                    VBox.setMargin(newstack, new Insets(20, 0, 0, 0));
        
                    AppStatus.setStackPane(newstack);
    
                    if (!storedStackPanes.contains(newstack)) {
                        VboxhapusRelawan.getChildren().add(newstack);
                        VBox.setMargin(newstack, new Insets(20, 0, 0, 0));
                        AppStatus.setStackPane(newstack);
                    }
                    data_ada=true;
                }
            }

            if (!data_ada) {
                lblPeringatan.setText("kamu belum pernah mengunggah apapun");
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

    private void hapusUnggahan(StackPane stackPaneToDelete) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(dataDR.class);
        xstream.addPermission(AnyTypePermission.ANY); 
        FileInputStream file = null;
    
        try {
            file = new FileInputStream("src/data/DataCariRelawan.xml");
            ArrayList<dataDR> dataList = (ArrayList<dataDR>) xstream.fromXML(file);
            boolean data_ada = false;
    
            for (int i = 0; i < dataList.size(); i++) {
                dataDR data = dataList.get(i);
                if (data.getPembuat().equals(loginPage.namauser)) 
                {
                    // Menghapus data yang sesuai dengan data yang ditampilkan pada stackPaneToDelete
                    // if (i < VboxhapusRelawan.getChildren().size() && VboxhapusRelawan.getChildren().get(i) == stackPaneToDelete) {
                    //     dataList.remove(i);
                    //     VboxhapusRelawan.getChildren().remove(i);
                    //     data_ada = true;
                    //     break;
                    // }
                    if (data.getIdentifier().equals(stackPaneToDelete.getId())) { // Membandingkan identifier unik
                        dataList.remove(data);
                        VboxhapusRelawan.getChildren().remove(stackPaneToDelete);
                        data_ada = true;
                        break;
                    }
                }
            }
    
            if (data_ada) {
                // Menyimpan data yang telah dihapus kembali ke file XML
                xstream.toXML(dataList, new FileOutputStream("src/data/DataCariRelawan.xml"));
            } else {
                lblPeringatan.setText("kamu belum pernah mengunggah apapun");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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
       loadDR();
    }
}
