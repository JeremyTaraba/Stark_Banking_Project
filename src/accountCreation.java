import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;




public class accountCreation {

    Hashtable<String, String> logins;
    String username = "";
    String password = "";
    private String hashPassword = "";

    @FXML
    private Label createdText;

    @FXML
    private Label errorName;

    @FXML
    private TextField personalName;


    @FXML
    void accountCreate(ActionEvent event){
        
        if(personalName.getText().length() > 1){
            errorName.setText("");

            try {
                passwordHash p = new passwordHash(password);
                hashPassword = p.getHash();

                try{
                    String basePath = new File("").getAbsolutePath();

                    FileWriter myWriter = new FileWriter(basePath.concat("/src/LoginInformation/logins.txt"), true);
                    myWriter.write(username + "\n" + hashPassword + "\n");
                    myWriter.close();
        
                    FileWriter myWriter2 = new FileWriter(basePath.concat("/src/LoginInformation/Accounts/" + this.username + ".txt"));
                    myWriter2.write("true\n" + personalName.getText() + "\n0");
                    myWriter2.close();
                    
                    App a = new App();
                    a.changeScene("MainScene.fxml");

                }
                catch(IOException e){
                    createdText.setText("An error occurred");
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } 

            catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }



        }
        else{
            errorName.setText("Please enter a name");
        }
    }

    @FXML
    void cancelAccount(ActionEvent event) {
        App a = new App();
        try {
            a.changeSceneLogins("createAccount.fxml", logins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(String uName, String pWord, Hashtable<String, String> log){
        username = uName;
        password = pWord;
        logins = log;
    }

}
