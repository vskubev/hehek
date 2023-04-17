package org.db;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final InMemoryStorage storage;

    public ClientHandler(Socket socket, InMemoryStorage storage) {
        this.clientSocket = socket;
        this.storage = storage;
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
                    storage.save(query[1], query[2]);
                    out.println(storage.find(query[1]));
                } else if ("get".equals(command)) {
                    out.println(storage.find(query[1]));
                } else if ("delete".equals(command)) {
                    storage.delete(query[1]);
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
