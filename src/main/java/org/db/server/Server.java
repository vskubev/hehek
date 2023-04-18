package org.db.server;

import org.db.client.ClientHandler;
import org.db.storage.DataBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {
    private static ServerSocket serverSocket;
    private static int port;

    public static void start(String[] args) throws IOException {
        port = args.length > 0 ? Integer.parseInt(args[0]) : 4444;
        serverSocket = new ServerSocket(port);
        Runnable runnable = new RunTask();
        Executors.newSingleThreadExecutor().submit(runnable);
    }

    private static class RunTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    new ClientHandler(serverSocket.accept(), new DataBase()).start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void stop() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Server.start(args);
    }
}
