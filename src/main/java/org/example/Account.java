package org.example;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//getter setter pom.xml
public class Account {
    private static int count = 0;
    int id;
    int account_id;
    String name;
    double balance;

    public Account(int account_id, String name, Double balance){
        id = ++count;
        this.name = name;
        this.account_id = account_id;
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "Account Id: " + account_id + ", Name: " + name + ", Balance: " + balance;
    }
}
