/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
    public class Server_Client implements Runnable {

       Server server = null;
       Socket client = null;
       BufferedReader receive;
       public static PrintStream send;
       Scanner scanner = new Scanner(System.in);
       int id;
       String text;

       Server_Client(Socket client, int count, Server server) throws IOException{
           this.client = client;
           this.server = server;
           this.id = count;
           System.out.println("ID: " + id + "Client: "+ client);
           receive =new BufferedReader(new InputStreamReader(client.getInputStream()));
           send = new PrintStream(client.getOutputStream());
       }

       @Override
       public void run() {
           int x = 1;
           SendMessage("Send from server");
           while(true){
               try {
                   ReceiveMessage();

               } catch (IOException ex) {
                   Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }   

       public void ReceiveMessage() throws IOException{
           text = receive.readLine();
          System.out.println("Client"+id +": " + text + "\n");
       }

       public static void SendMessage(String message){
           System.out.println("Sending: " + message);
           send.println(message);
       }



   }    
