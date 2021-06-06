/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class Client_Server {
    public Socket socket;
    public BufferedReader receive;
    public PrintStream send;
    public BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
    public String text;

    public Client_Server() {
        try {
            this.socket = new Socket("127.0.0.1", 5000);
            this.receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.send = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Initialize() throws IOException{
       
        
            System.out.println("Client");
        while(true){
            ReceiveMessage();
            text = stdin.readLine();
            
            if(text.equalsIgnoreCase("EXIT")){
                break;
            }
        }
        socket.close();
        receive.close();
        send.close();
        stdin.close();
        
    }
    
    public void SendMessage(String message){
        System.out.println("Sending: "+message);
        send.println(message);
    }
    public void ReceiveMessage() throws IOException{
        text = receive.readLine();
        System.out.println("Server:" + text +"\n");
    }
}
