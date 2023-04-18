package org.db.client;

import org.db.storage.DataBase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final DataBase dataBase;

    public ClientHandler(Socket socket, DataBase dataBase) {
        this.clientSocket = socket;
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        PrintWriter out;
        BufferedReader in;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] query = inputLine.split(" ");
                if (query.length == 0) {
                    out.println("empty command is not a valid command");
                }
                String command = query[0];
                if ("exit".equals(command)) {
                    out.println("terminated");
                    break;
                } else if ("put".equals(command)) {
                    dataBase.save(query[1], query[2]);
                    out.println(dataBase.find(query[1]));
                } else if ("get".equals(command)) {
                    out.println(dataBase.find(query[1]));
                } else if ("delete".equals(command)) {
                    dataBase.delete(query[1]);
                    out.println();
                } else {
                    out.println(query[0] + " is not a valid command");
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("something went wrong: " + e);
        }
    }
}
