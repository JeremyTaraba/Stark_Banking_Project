import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class createAccount implements Initializable{
    //Note: encrypting passwords happens after it is stored in the logins.txt file. 
    //ecryption done in MainSceneController.java for login in and in accountCreation.java

    Hashtable<String, String> logins;
    private boolean usernameAvailable = false;
    private boolean passwordMatch = false;
    private boolean captchaCorrect = false;
    

    @FXML
    private TextField captchaEntered;

    @FXML
    private Label captchaError;

    @FXML
    private Label captchaPrompt;

    @FXML
    private PasswordField firstPassword;

    @FXML
    private Label passwordError;

    @FXML
    private PasswordField secondPassword;

    @FXML
    private TextField username;

    @FXML
    private Label usernameError;

    @FXML
    void backButton(ActionEvent event) {
        App a = new App();
        try {
            a.changeScene("MainScene.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void checkUsername(ActionEvent event) {
        if(username.getText().length() > 2){
        
            if(logins.containsKey(username.getText())){   //hashtables: <key> : <value>    -->  <username> : <password>
                //System.out.println("Username is already taken, please choose another");
                usernameError.setTextFill(Color.web("#f0c71a"));
                usernameError.setText("Username is taken");
                usernameAvailable = false;
            }
            else{
                usernameError.setTextFill(Color.web("#09fa71"));
                usernameError.setText("Username available");
                usernameAvailable = true;
            }
        }
        else{
            usernameError.setTextFill(Color.web("#f0c71a"));
            usernameError.setText("Username too short");
            usernameAvailable = false;
        }
    }

    @FXML
    void confirmPassword(KeyEvent event) {
        if(firstPassword.getText().length() > 2){
            if(firstPassword.getText().equals(secondPassword.getText())){
                passwordMatch = true;
                passwordError.setText("");
            }
            else{
                passwordMatch = false;
                passwordError.setText("Passwords don't match");
            }
        }
        else{
            passwordError.setText("Password too short");
            passwordMatch = false;
        }
        
    }

    @FXML
    void submitAccount(ActionEvent event) {
        checkUsername(event);   //check username incase they dont press the button

        //check captcha here

        if(captchaEntered.getText().equals(captchaPrompt.getText())){
            captchaCorrect = true;
            captchaError.setText("");
        }
        else{
            captchaCorrect = false;
            captchaError.setText("letters don't match");
        }


        if(captchaCorrect && passwordMatch && usernameAvailable){
            System.out.println("Account Success");
            App a = new App();
            try {
                a.changeSceneAccount("nameAccount.fxml", username.getText(), firstPassword.getText(), logins);
            } catch (IOException e) {
                System.out.println("Error account not created");
                e.printStackTrace();
            }

        }
        else{
            // if they tried to submit without anything correct
            if(!passwordMatch){
                passwordError.setText("Password Error");    //either didn't enter password or failed password check
            }
        }

    }

    public void setData(Hashtable<String, String> loginsDictionary){
        logins = loginsDictionary;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ArrayList<Character> captcha = new ArrayList<Character>();
        Random random = new Random();
        String correctCaptcha = "";
        
        for(int i = 0; i < 5; i++){ //for loop is used to initilize the arraylist to size 5
            captcha.add('a');
        } 
        for(int i = 0; i < 5; i++){ //used to randomize the captcha every time
            captcha.set(i, (char)(random.nextInt(26)+'a'));
        }
        for(int i = 0; i < 5; i++){
            //System.out.print(captcha.get(i));
            correctCaptcha += captcha.get(i);   //used to store the captcha into a string
        }
        captchaPrompt.setText(correctCaptcha);
        
    }

}

