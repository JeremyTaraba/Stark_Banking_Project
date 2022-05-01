import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;



public class afterLogin implements Initializable{

    private Boolean accountStatus = false; // currently no way to make it false besides hard coding it as false.
    private String accountName = "";
    private BigDecimal accountBalance = BigDecimal.valueOf(-1.00);
    private String username = "";

    @FXML
    private Label welcomeText;

    @FXML
    private Label balance;

    @FXML
    private TextField depositAmount;

    @FXML
    private TextField withdrawalAmount;

    @FXML
    private Label errorDeposit;

    @FXML
    private Label errorWithdrawal;



    public void depositCheck(){
        String depositStr = depositAmount.getText();

        if(isNumber(depositStr)){
            BigDecimal depositDec = BigDecimal.valueOf(Double.valueOf(depositStr));
            if(depositDec.scale() > 2){
                //System.out.println("Input up to 2 decimal points");
                errorDeposit.setText("Input up to 2 decimal points");
            }
            else{
                deposit(depositDec);  
            }
        }
        else{
           // System.out.println("Please enter a number");
            errorDeposit.setText("Please enter a number");
        }
        

    }

    

    
    private void deposit(BigDecimal depositDec){
        BigDecimal total = this.accountBalance.add(depositDec);

        try{
            String basePath = new File("").getAbsolutePath();
            FileWriter myWriter = new FileWriter(basePath.concat("/src/LoginInformation/Accounts/" + this.username + ".txt"));
            myWriter.write(this.accountStatus + "\n" + this.accountName + "\n" + total);
            this.accountBalance = total;
            myWriter.close();
            //System.out.println("Successfully depositted. Your new balance is: " + this.getBalance() + "\n");
           
            balance.setText(this.accountBalance.toString());
            errorDeposit.setText("");
            depositAmount.setText("");

        }
        catch(IOException e){
            System.out.println("An error occurred while depositting.");
            errorDeposit.setText("Error occurred while depositting");
            e.printStackTrace();
        }
    }


    public void withdrawalCheck(){
        String withdrawalStr = withdrawalAmount.getText();
        if(isNumber(withdrawalStr)){
            BigDecimal withdrawalDec = BigDecimal.valueOf(Double.valueOf(withdrawalStr));
            if(withdrawalDec.scale() > 2){
                //System.out.println("Please only input up to 2 decimal points");
                errorWithdrawal.setText("Input up to 2 decimal points");
            }
            else{
                withdrawal(withdrawalDec);
            }
        }
        else{
            //System.out.println("Please enter a number");
            errorWithdrawal.setText("Please enter a number");
        }
        
    }

   
    private void withdrawal(BigDecimal withdrawalDec){
        BigDecimal total = this.accountBalance.subtract(withdrawalDec);
        int result = total.compareTo(BigDecimal.valueOf(0.00));
        if(result == -1 ){
            //System.out.println("Error. Cannot withdrawal balance too low\n");
            errorWithdrawal.setText("Cannot withdrawal balance too low");
           // System.out.println("Your current balance is: " + this.getBalance() + "\n");
            return;
        }
        try{
            String basePath = new File("").getAbsolutePath();
            FileWriter myWriter = new FileWriter(basePath.concat("/src/LoginInformation/Accounts/" + this.username + ".txt"));
            myWriter.write(this.accountStatus + "\n" + this.accountName + "\n" + total);
            this.accountBalance = total;
            myWriter.close();
            //System.out.println("Successfully withdrew. Your new balance is: " + this.getBalance() + "\n");
            balance.setText(this.accountBalance.toString());
            errorWithdrawal.setText("");
            withdrawalAmount.setText("");
        }
        catch(IOException e){
            System.out.println("An error occurred while withdrawaling.");
            errorWithdrawal.setText("Error occurred while withdrawaling");
            e.printStackTrace();
        }
    }

    @FXML
    public void logout(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("MainScene.fxml");
    }

    private static boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public void setData(String uName){
        username = uName;
    }

    public BigDecimal getBalance(){
        return this.accountBalance;
    }

    public void getData(){
        try{
            String basePath = new File("").getAbsolutePath();
            File accountFile = new File(basePath.concat("/src/LoginInformation/Accounts/" + this.username + ".txt"));
            Scanner scAccount = new Scanner(accountFile);
            if(scAccount.hasNextLine()){    
                if(scAccount.nextLine().equals("true")){
                    accountStatus = true;
                } 
                if(scAccount.hasNextLine()){
                    accountName = scAccount.nextLine();
                }
                if(scAccount.hasNextLine()){
                    accountBalance = BigDecimal.valueOf(Double.valueOf(scAccount.nextLine()));
                    String bal = accountBalance.toString();
                    balance.setText(bal);
                }
            }  
            scAccount.close();
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        welcomeText.setText("Hello " + accountName + ", what would you like to do today?");
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       
       
        
    }


    
}
