/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package iklcbank;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
/**
 *
 * @author win11
 */
public class IKLCBank {
    public static ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int choice = 0;
        boolean regis = true;
        while (choice != 4) {
            System.out.println("+----------------------------+");
            System.out.println("   Wellcome to Bank of IKLC   ");
            System.out.println("+----------------------------+");
            System.out.println();
            System.out.println("Choose a menu:");
            System.out.println("1. Register Account");
            System.out.println("2. Transfer");
            System.out.println("3. Deposit money");
            System.out.println("4. Withdraw");
            System.out.println("0. Exit");
            System.out.println();
            
            System.out.print("Enter a menu: ");
            choice = scanner.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    System.out.println("+----------------- REGISTER ACCOUNT ---------------------+");
                    System.out.print("Enter your name    : ");
                    String name = scanner.next();
                    int accountNumber = generateAccountNumber();
                    System.out.println("Your account number: " + accountNumber);
                    System.out.print("Initialize balance : Rp");
                    double balance = scanner.nextDouble();
                    
                    BankAccount account = new BankAccount(name, accountNumber, balance, new Date());
                    bankAccounts.add(account);
                    
                    System.out.println("+--------------------------------------------------------+");
                    System.out.println("The account was successfully registered on the date of " + sdf.format(account.getRegistrationDate()));
                    System.out.println("Name           : " + name);
                    System.out.println("Account number : " + accountNumber);
                    System.out.println("Money Balance  : Rp" + balance);
                    System.out.println();
                    regis = false;
                    break;
                case 2:
                    if(!regis){
                        System.out.println("+----------------- TRANSFER ACCOUNT ---------------------+");
                        System.out.print("Enter the sender's account number   : ");
                        int senderAccountNumber = scanner.nextInt();
                        System.out.print("Enter the recipient's account number: ");
                        int receiverAccountNumber = scanner.nextInt();
                        System.out.print("Enter the amount of money to be sent: Rp");
                    
                        if (scanner.hasNextDouble()) {
                            double transferAmount = scanner.nextDouble();
                            if (isSufficientBalance(senderAccountNumber, transferAmount)) {
                                transferMoney(senderAccountNumber, receiverAccountNumber, transferAmount);
                                double newBalance = 0;   
                                if (newBalance < 0) {
                                    System.out.println("!Insufficient funds!");
                                } else {
                                    System.out.println("You have successfully transfer Rp" + transferAmount);
                                    System.out.println();
                                }
                            } else {
                                System.out.println();
                                System.out.println("!Insufficient balance!");
                                System.out.println();
                            }
                        } else {
                            System.out.println();
                            System.out.println("!Input must be a numeric!");
                            System.out.println();
                        }
                    }else {
                        System.out.println();
                        System.out.println("!Your registration invalid, please registration before!");
                        System.out.println();
                    }
                break;
                 
                case 3:
                    if(!regis){
                        System.out.println("+----------------- DEPOSIT MONEY ---------------------+");
                        System.out.print("Enter the account number  : ");
                        int accountNum = scanner.nextInt();
                        System.out.print("Enter the amount of money : Rp");
                        
                        if (scanner.hasNextDouble()) {
                            double depositAmount = scanner.nextDouble();
                            depositMoney(accountNum, depositAmount);

                            System.out.println("You have successfully added " + depositAmount + " in your account.");
                        } else {
                            System.out.println();
                            System.out.println("!Input must be a numeric!");
                            System.out.println();
                        }
                    }else{
                        System.out.println();
                        System.out.println("!Your registration invalid, please registration first!");
                        System.out.println();
                    }
                break;
                
                case 4:
                    if(!regis){
                        System.out.println("+----------------- WITHDRAW ---------------------+");
                        System.out.print("Enter the account number : ");
                        int accountNum = scanner.nextInt();
                        
                        System.out.print("Enter withdraw amount    : Rp");
                        
                        if (scanner.hasNextDouble()) {
                            double withdrawAmount = scanner.nextDouble();
                            double newBalance = 0; 
                            withdrawMoney(accountNum, withdrawAmount);
                            if (newBalance < 0) {
                                System.out.println("!Insufficient funds!");
                            } else {
                                System.out.println("You have successfully withdraw Rp" + withdrawAmount);
                            }
                        } else {
                            System.out.println();
                            System.out.println("!Input must be a numeric!");
                            System.out.println();
                        }

                    }else{
                        System.out.println();
                        System.out.println("!Your registration invalid, please registration before!");
                        System.out.println();
                    }
                break;
                    
                case 0:
                    System.out.println();
                    System.out.println("Thank you!.");
                    break;
                default:
                    System.out.println();
                    System.out.println("!Invalid option!");
                break;
            }
        }
    }

    private static int generateAccountNumber() {
        int accountNumber = 0;
        boolean isUnique = false;
        while (!isUnique) {
            Random rand = new Random();
            accountNumber = rand.nextInt(900000) + 100000;
            isUnique = isUniqueAccountNumber(accountNumber);
        }
        return accountNumber;
    }

    private static boolean isUniqueAccountNumber(int accountNumber) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber() == accountNumber) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isSufficientBalance(int accountNumber, double amount) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getBalance() >= amount) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private static void transferMoney(int senderAccountNumber, int receiverAccountNumber, double transferAmount) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber() == senderAccountNumber) { 
                double newBalance = account.getBalance() - transferAmount;
                if (newBalance < 0) {
                    System.out.println ("Insufficient funds.");
                }else {
                    account.setBalance(newBalance);
                    System.out.println("sender's new balance : Rp" + newBalance);
                }
            }
            if (account.getAccountNumber() == receiverAccountNumber) {
                //account.setBalance(account.getBalance() + transferAmount);
                double newBalance = account.getBalance() + transferAmount;
                if (newBalance < 0) {
                    System.out.println ("Insufficient funds.");
                }else {
                    account.setBalance(newBalance);
                    System.out.println("Recipient's new balance : Rp" + newBalance);
                }
            }
        }
    }

    private static void depositMoney(int accountNumber, double amount) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.setBalance(account.getBalance() + amount);
            }
        }
    }

    private static void withdrawMoney(int accountNumber, double withdrawAmount) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber() == accountNumber) {
                double newBalance = account.getBalance() - withdrawAmount;
                if (newBalance < 0) {
                    System.out.println ("Insufficient funds.");
                }else {
                    account.setBalance(newBalance);
                    System.out.println("New Balance: Rp" + newBalance);
                }
            }else {
                System.out.println("Account not found.");
            }
        }
    }

}
    


