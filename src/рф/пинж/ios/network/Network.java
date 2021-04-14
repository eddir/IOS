package рф.пинж.ios.network;

import рф.пинж.ios.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Network {

    private Server server;

    private ServerSocket listener;
    private static Set<NetworkThread> threads = new HashSet<>();

    public Network(Server server) {
        this.server = server;
    }

    public void run() {
        try {
            Server.getLogger().info(String.format("Запуск сервера по адресу %s:%s .%n", this.server.getIp(), this.server.getPort()));
            this.listener = new ServerSocket(this.server.getPort());
        } catch (IOException e) {
            e.printStackTrace();
            Server.getLogger().error("Ошибка сети.");
            return;
        }

        while (this.server.isRunning()) {
            try {
                Socket socket = this.listener.accept();
                Server.getLogger().debug("Соединение установлено.");

                NetworkThread networkThread = new NetworkThread(socket);
                threads.add(networkThread);
                networkThread.start();

            } catch (Exception e) {
                e.printStackTrace();
                Server.getLogger().debug("Ошибка соединения.");
            }
        }
    }

    public void stop() {
        for (NetworkThread thread : threads) {
            closeThread(thread);
        }
    }

    public static void closeThread(NetworkThread thread) {
        thread.close();
        threads.remove(thread);
    }

}
