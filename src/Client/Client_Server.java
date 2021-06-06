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

/**
 *
 * @author H
 */
public class Client_Server {
    public void Initialize() throws IOException{
        Socket socket = new Socket("127.0.0.1", 5000);
        BufferedReader receive=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream send =new PrintStream(socket.getOutputStream());
        BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
        String text;
        
        while(true){
            System.out.println("Client");
            text = stdin.readLine();
            send.println(text);
            if(text.equalsIgnoreCase("EXIT")){
                break;
            }
            
            text = receive.readLine();
            System.out.println("Server:" + text +"\n");
            
        }
        socket.close();
        receive.close();
        send.close();
        stdin.close();
        
    }
}
