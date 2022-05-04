# Stark_Banking_Project
## One Sentence Summary
Banking Application that lets you create an account, deposit fake money and withdrawal fake money within that account.


## Technologies Used
- JavaFX and Scene Builder for the GUI
- SHA-256 for password encryption
- File reading and writting to store information locally since there is no server
- Simple captcha check for bots


## Description
I wanted to make an application similar to a bank. Obviously I can't connect to a real bank so instead it will be more like a Banking Simulation.
You can create an account just like a real bank. You can deposit money (fake money) into your account just like a real bank.
You can pretend to withdrawal this money. Basically you can think of it as a way to keep track of how much money you have similar
to applications like Mint which tracks your spending history. The only difference is that mint links to your bank so all deposits
and withdrawals are done automatically whereas in this application that I made you have to manually type all of that in. There is 
also no graph to show spending history, atleast not yet.


## How to run it
Currently there is no executable file so there are a couple of steps you can take to run it.
1. Download repo and open it in vscode
2. Modify the launch.json file, specifically the "vmArgs" to point towards your downloaded version of javafx
3. Open settings.json and modify each .jar to point to the correct location on your machine
4. Run App.java



## What it looks like

Login Screen:
![alt text](https://github.com/JeremyTaraba/Stark_Banking_Project/blob/main/ReadMe_Images/StarkBankLogin.jpg?raw=true)
