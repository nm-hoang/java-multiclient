/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public Server_Client runnable;
    public static ArrayList<Server_Client> listClient;
    
    
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
        listClient = new ArrayList<Server_Client>();
        System.out.println("Server setup");
        System.out.println("Client can stop by typing exit");
        
        while(true){
            client = server.accept();
//          add new client that connected to list
            clientcount++;
           runnable = new Server_Client(client,clientcount,this);
            listClient.add(runnable);
           SendListClient();
           pool.execute(runnable);
           
       }
    }
    public void SendListClient(){
        System.out.println("test");
        String sts = "listclient:";
        for(Server_Client sc : listClient){
            DetailClient dc = new DetailClient(sc.client);
               sts = sts.concat(dc.getIPAddress()+ "-" + dc.getPort()+";");
        }
        System.out.println(sts);
        runnable.SendMessage(sts); 
    }   
    
//    public void SendMessageToAll() {
//       for(Server_Client sc : listClient){
//           sc.SendMessage("send multi");   
//       }
//    }
}
