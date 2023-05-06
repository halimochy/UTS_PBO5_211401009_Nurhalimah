
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iklcbank;

import java.util.Date;
/**
 *
 * @author win11
 */
public class BankAccount {
    private String name;
    private int accountNumber;
    private double balance;
    private Date registrationDate;
    
    // constructor
    public BankAccount(String name, int accountNumber, double balance, Date registrationDate) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.registrationDate = registrationDate;
    }
    
    // getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}

