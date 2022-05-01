import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainSceneController implements Initializable{

    Hashtable<String, String> logins;
    private String hashPassword = "";
    

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

  
    public void userLogin() throws IOException{
        if (logins.containsKey(username.getText())){
            passwordHash p;
            try {
                p = new passwordHash(password.getText());
                hashPassword = p.getHash();
            
            
                if (logins.get(username.getText()).equals(hashPassword)){
                    //System.out.println("login succesful\n");
                    loginSuccess();
                }
                else{
                    //System.out.println("Incorrect Username or Password");
                    wrongLogin.setText("Incorrect Username or Password!");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        else{
            // System.out.println("Incorrect Username or Password");
            wrongLogin.setText("Incorrect Username or Password!");
        }

    }

    @FXML
    public void loginKey(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            try {
                userLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    
    @FXML
    void createAccountKey(ActionEvent event) {
        App a = new App();
        try {
            a.changeSceneLogins("createAccount.fxml", logins);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginSuccess() throws IOException{
        App a = new App();
        a.changeSceneUsername("afterLogin.fxml", username.getText());;
    }

   



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        logins = new Hashtable<String, String>();
        try{
            String basePath = new File("").getAbsolutePath();
            File file = new File(basePath.concat("/src/LoginInformation/logins.txt"));
            Scanner sc = new Scanner(file); 
            while(sc.hasNextLine()){
                logins.put(sc.nextLine(), sc.nextLine());
            }
            sc.close();
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
           // System.out.println("Loading Data...");
        
    }

   

}
