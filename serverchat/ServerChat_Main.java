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
    private final static int DEFAULT_SERVER_PORT = 15750;
    private final static String SERVER_IP = "192.168.0.9";
    private ChatClient client = null;

    public static void main(String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("server") && (ServerChat_Main.isInt(args[1]) && Integer.parseInt(args[1]) > 1023)) {
                new ChatServer(Integer.parseInt(args[1]));
            } else {
                System.out.println("Usage: java ServerChat_Main server port(>1023)");
                System.out.println("or...java ServerChat_Main without arguments for chat client");
                System.exit(0);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("server")) {
                /* User indicated that this computer
                 * should be used as a server with
                 * the default port 15750 used
                 */
                new ChatServer(DEFAULT_SERVER_PORT);
            } else {
                System.out.println("Usage: java ServerChat_Main server port(>1023)");
                System.out.println("or...java ServerChat_Main without arguments for chat client");
                System.exit(0);
            }
        } else {
            // open the client for user
            new ServerChat_Main();
        }
//        program.initServer(15750);
    }

    public ServerChat_Main() {
        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerChat_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Welcome to the ServerChat Client");
        System.out.println("Your hostname is: " + this.hostname);
        this.setUsername();
        client = new ChatClient(SERVER_IP, DEFAULT_SERVER_PORT);
    }

    public void setUsername() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your on-screen username: ");
        this.username = input.nextLine();
        System.out.println("Welcome " + this.username);
    }

    private void initServer(int port) {
        new ChatServer(port);
    }

    public static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException nFE) {
            return false;
        }
        return true;
    }
}
