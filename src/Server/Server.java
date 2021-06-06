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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class Server {
    int port;
    ServerSocket server = null;
    Socket client = null;
    ExecutorService pool = null;
    int clientcount=0;
    public static ArrayList<DetailClient> clients;
    public Server_Client runnable;
    
    Server(int port){
        this.port = port;
        pool = Executors.newFixedThreadPool(5);
    }
    
    public void Initalize() throws IOException{
        Server serverobj = new Server(5000);
        serverobj.StartConnection();
    }
    public void StartConnection() throws IOException{
        server = new ServerSocket(5000);
        clients = new ArrayList<DetailClient>();
        System.out.println("Server setup");
        System.out.println("Client can stop by typing exit");
        
        while(true){
           client = server.accept();
//          add new client that connected to list
            DetailClient cl = new DetailClient(client);
            
            clients.add(cl);
            System.out.println(clients.size());
           runnable = new Server_Client(client,clientcount,this);
           pool.execute(runnable);
           
       }
    }
    public void SendListClient(){
        String sts = "";
        for(int i=0; i<clients.size(); i++){
            System.out.println(clients.get(i).getIPAddress());
            sts = sts.concat(clients.get(i).getIPAddress()+ ":" + clients.get(i).getPort()+";");
        }
        runnable.SendMessage(sts);
    }   
   
    
    public static class Server_Client implements Runnable {

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
}
