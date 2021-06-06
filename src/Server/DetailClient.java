/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.Socket;

/**
 *
 * @author H
 */
public class DetailClient {
    private String IPAddress;
    private int port;
    
    public DetailClient(Socket client){
        this.IPAddress = client.getInetAddress().toString().replace("/","");
        this.port = client.getPort();
    }
     public void RemoveClient(Socket client){
        
    }
    
    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
