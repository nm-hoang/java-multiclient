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
import java.util.ArrayList;
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
    public static String text;
    public static ArrayList<String[]> listClient;
    public static String IPAddress;
    public static int port;
    
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
       
        
        IPAddress = this.GetIPAddress();
        port = this.GetPort();
        while(true){
            ReceiveMessage();
//            text = stdin.readLine();
//            
//            if(text.equalsIgnoreCase("EXIT")){
//                break;
//            }
        }
//        socket.close();
//        receive.close();
//        send.close();
//        stdin.close();
        
    }
    
    public void SendMessage(String message){
        send.println(message);
    }
    
    public void ReceiveMessage() throws IOException{
        text = receive.readLine();
        System.out.println("Server:" + text +"\n");
        listClient = new ArrayList<String[]>();
        //listclient:127.0.0.1-5000;
        if(text.contains("listclient")){
            String[] message = text.split(":");
            String[] lc = message[1].split(";");
            for(String s : lc){
                  if(s.length()>0){
                    String[] client = s.split("-");
                    listClient.add(client);
                  }
            }
        }
    }
    
    public void GetInfo(){
        System.out.println(socket.getInetAddress());
        System.out.println(socket.getPort());
        System.out.println(socket.toString());
        System.out.println(socket.getLocalPort());
    }
    
    public String GetIPAddress(){
        return(socket.getInetAddress().toString().replace("/", ""));
    }
    
    public int GetPort(){
        return socket.getLocalPort();
    }
    
   
  
    
}
