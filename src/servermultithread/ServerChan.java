/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermultithread;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pogliani.mattia
 */
public class ServerChan implements Runnable {

    private Socket clientSocket;

    public ServerChan(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Serverino  partito: "
                + clientSocket.getInetAddress());
        try {

            PrintWriter out
                    = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String richiesta = "";
            while (!richiesta.equals("exit")) {
                System.out.println("serverino in ascolto...");
                richiesta = in.readLine();
                System.out.println("stringa dal client: " + richiesta);
                out.println("Numero di caratteri: " + richiesta.length());
            }

            out.close();
            clientSocket.close();

            System.out.println("chiusura connessione effettuata");

        } catch (IOException ex) {
            Logger.getLogger(ServerChan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}