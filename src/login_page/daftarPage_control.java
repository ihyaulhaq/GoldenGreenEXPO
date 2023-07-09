package login_page;


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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.App;


public class daftarPage_control implements Initializable {

    @FXML
    private TextField TFnama;

    @FXML
    private TextField TFusername;
    
    @FXML
    private TextField TFpass;

    @FXML
    private Button btnDaftar;

    @FXML
    private Label labeltryAgain;

    
    
    @FXML
    private void daftar_click(ActionEvent event) {
       
        if(TFusername.getText().isEmpty()==false && TFpass.getText().isEmpty()==false && TFnama.getText().isEmpty()==false){
            boolean userExists =checkUserExists(TFusername.getText(), TFnama.getText());

            if(userExists){
                labeltryAgain.setText("Username atau nama sudah ada silahkan pilih yang lain");
            }else{
                App.getInstance().gantiScreen("/login_page/loginPage.fxml", btnDaftar);                
                saveData(TFnama.getText(),TFusername.getText(),TFpass.getText());
            }
        }else{
            labeltryAgain.setText("semua harus diisi!!");
        }
        
    }

    private boolean checkUserExists(String username, String name) {
        ArrayList<data_user> newData = new ArrayList<>();
        File file = new File("src/data/datalogin.xml");
    
        if (file.exists()) {
            // File XML sudah ada, baca datanya
            XStream xstream = new XStream(new StaxDriver());
            xstream.processAnnotations(ArrayList.class);
            xstream.addPermission(AnyTypePermission.ANY);
    
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                newData = (ArrayList<data_user>) xstream.fromXML(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                System.err.println("Perhatian: " + e.getMessage());
            }
        }
    
        for (data_user user : newData) {
            if (user.getUsername().equals(username) || user.getName().equals(name)) {
                return true;
            }
        }
    
        return false;
    }
    
    //method buat simpen user sama pass
    @FXML
    private void saveData(String name, String username, String password) {

        ArrayList<data_user> newData = new ArrayList<>();
        File file = new File("src/data/datalogin.xml");
        if (file.exists()) {
            // File XML sudah ada, baca datanya
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.processAnnotations(ArrayList.class);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                newData = (ArrayList<data_user>) xstream.fromXML(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                System.err.println("Perhatian: " + e.getMessage());
            }
        }
        data_user dataUser = new data_user(name, username, password);
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

