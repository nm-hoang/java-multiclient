/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class Client_Client {
    public String IPAddress;
    public int port;
    
    public DatagramSocket socket;
    public DatagramPacket receivePacket;
    Client_Client(){
            this.IPAddress = Client_Server.IPAddress;
            this.port = Client_Server.port;
    }
    
    public void Initialize(){
        try {
            socket = new DatagramSocket(port);
            while(true){
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[2048];
                
                
            }
        } catch (SocketException ex) {
            Logger.getLogger(Client_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
