package login_page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.App;


public class loginPage implements Initializable {
    
    private static boolean isLoggedIn = false;
    
    public static String namauser=" ";
    
   @FXML
    private Button btnlogin;

    @FXML
    private Button btnDaftar;

    @FXML
    private Label labelTryAgain;
    
   @FXML
    private PasswordField TFpassword;

    @FXML
    private TextField TFuserName;

    @FXML
    private void Login_ButtonClick(ActionEvent event) {
        
        if (TFuserName.getText().isEmpty() == false && TFpassword.getText().isEmpty() == false) {
            boolean isValidCredentials = checkCredentials(TFuserName.getText(), TFpassword.getText());

            if (isValidCredentials) {
                isLoggedIn = true;
                App.getInstance().gantiScreen("/menu_page/menu.fxml", btnlogin);
            } else {
                labelTryAgain.setText("Username atau password salah!");
            }
        } else {
            labelTryAgain.setText("Masukkan username dan password!");
        }
        
    }
    
    @FXML
    private void daftar_ButtonClick(ActionEvent event) {
        App.getInstance().gantiScreen("/login_page/daftarPage.fxml", btnDaftar);    
    }

    //method buat cek akun
    private boolean checkCredentials(String username, String password) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);

        ArrayList<data_user> storedCredentials = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/data/datalogin.xml");
            storedCredentials = (ArrayList<data_user>) xstream.fromXML(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
        for (data_user userData : storedCredentials) {
            if (userData.getUsername().equals(username) && userData.getPassword().equals(password)) {
                namauser=userData.getUsername() ;
                return true;
            }
        }
        return false;
        
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
}
