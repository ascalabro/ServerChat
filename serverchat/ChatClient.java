package serverchat;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient implements Runnable {

    private Socket socket = null;
    private Thread thread = null;
    private String hostname;
    public String username;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread client = null;

    public ChatClient(String serverName, int serverPort) {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerChat_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Welcome to the ServerChat Client");
        System.out.println("Your hostname is: " + this.hostname);
        setUsername();
        System.out.println("Establishing connection to chat server. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }

    public void run() {
        while (thread != null) {
            try {
                streamOut.writeUTF(console.readLine());
                streamOut.flush();
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
                stop();
            }
        }
    }

    public void handle(String msg) {
        if (msg.equals(".bye")) {
            System.out.println("Good bye. Press RETURN to exit ...");
            stop();
        } else {
            System.out.println(msg);
        }
    }

    public void start() throws IOException {
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
        if (thread == null) {
            client = new ChatClientThread(this, socket);
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
        try {
            if (console != null) {
                console.close();
            }
            if (streamOut != null) {
                streamOut.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        client.close();
        client.stop();
    }

    public void setUsername() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your on-screen username: ");
        this.username = input.nextLine();
        System.out.println("Welcome " + this.username);
    }
}