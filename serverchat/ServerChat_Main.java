/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchat;

import java.lang.reflect.Constructor;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.*;

/**
 *
 * @author Angelo
 */
public class ServerChat_Main {

    /**
     * @param args the command line arguments
     */
    private String hostname;
    public String username;
    
    
    public static void main(String[] args) {
        ServerChat_Main program = new ServerChat_Main();
    }
    
    
    public ServerChat_Main(){
        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerChat_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Welcome to my chat program..");
            System.out.println("Your hostname is: " + this.hostname);
            this.setUsername();
    }
    
    public void setUsername(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your on-screen username: ");
        this.username = input.nextLine();
        System.out.println("Welcome " + this.username);
    }
}
