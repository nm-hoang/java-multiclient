/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class Client_Client implements Runnable{
    public String IPAddress;
    public int port;
    
    public DatagramSocket socket;
    public DatagramPacket receivePacket;
    public DatagramPacket sendPacket;
    public byte[] receiveData;
    public byte[] sendData;
    public InetAddress IPAddressPartner;
    public int portPartner;
    Client_Client(){
        try {
            this.IPAddress = Client_Server.IPAddress;
            this.port = Client_Server.port;
            this.receiveData = new byte[1024];
            this.sendData = new byte[2048];
            socket = new DatagramSocket(port);
            System.out.println("initalize client_client");
            System.out.println(IPAddress);
            System.out.println(port);
        } catch (SocketException ex) {
            Logger.getLogger(Client_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Initialize(){
           
    }
    
    public void Receive(){
        try {
            System.out.println("reach receive method");
            receivePacket = 
                    new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            System.out.println("Receive from partner: ");
            System.out.println(sentence);
            //Get IP
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Send(){
        try {
            System.out.println("react Send method");
            sendData = "send from partner".getBytes();
            System.out.println(sendData.toString());
            System.out.println(sendData.length);
            System.out.println(IPAddressPartner.toString());
            System.out.println(portPartner);
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressPartner, portPartner);
            System.out.println(sendPacket.toString());
            socket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(Client_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Connect(String IPPartner, int portPartner){
        try {
            System.out.println("reach connected in client_client");
            this.IPAddressPartner = InetAddress.getByName(IPPartner);
            this.portPartner = portPartner;
            System.out.println("print info partner");
            System.out.println(this.IPAddressPartner);
            System.out.println(this.portPartner);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("Start client_Client");
         while(true){
                Receive();
            }
    }
    
}
