package рф.пинж.ios.network;

import рф.пинж.ios.Client;
import рф.пинж.ios.Server;
import рф.пинж.ios.network.protocol.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetworkThread extends Thread {
    private final Socket socket;

    private String line;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public NetworkThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(this.socket.getOutputStream());

            InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            String address = socketAddress.getAddress().toString().split("/")[1];

            Server.getLogger().info("Регистрация клиента.");

            Client client = new Client(Server.getInstance(), this, address);
            Server.getInstance().addClient(client);

            Server.getLogger().debug("Подключено!");
            boolean firstInput = true;

            while (Server.getInstance().isRunning()) {
                String input = this.bufferedReader.readLine();
                if (firstInput && input.length() > 0) {
                    // Удаление BOM символов из первого сообщения
                    input = input.replaceAll("[^а-яА-Яa-zA-Z0-9:;.?!/ ]","").trim();
                    firstInput = false;
                }
                DataPacket packet;
                if (client.getAction().equals("!menu")) {
                    packet = new MenuPacket(input);
                } else if (client.getAction().equals("!input")) {
                    packet = new InputPacket(input);
                } else {
                    if (input.length() != 0 && input.startsWith("/")) {
                        packet = new CommandPacket(input.substring(1));
                    } else {
                        packet = new ViewPacket(input);
                    }
                }
                client.handlePacket(packet);
            }

        } catch (IOException e) {
            this.line = this.getName(); //reused String line for getting thread name
            Server.getLogger().info("Ошибка ввода-вывода: клиент " + this.line + " резко оборвал соединение.");

        } catch (NullPointerException e) {
            e.printStackTrace();
            this.line = this.getName(); //reused String line for getting thread name
            Server.getLogger().info("Клиент " + this.line + " разорвал связь.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Network.closeThread(this);
        }
    }

    public void close() {
        try {
            Server.getLogger().debug("Закрытие соединения..");

            if (bufferedReader != null) {
                this.bufferedReader.close();
                Server.getLogger().debug("Socket Input Stream закрыт.");
            }

            if (printWriter != null) {
                this.printWriter.close();
                Server.getLogger().debug("Socket Out закрыт.");
            }
            if (this.socket != null) {
                this.socket.close();
                Server.getLogger().debug("Socket закрыт.");
            }

        } catch (IOException ie) {
            Server.getLogger().debug("Ошибка при закрытии сокета.");
        }
    }

    public void send(String line) {
        printWriter.println(line);
        printWriter.flush();
    }

    /**
     * Очищает консоль и выводит заданный текст
     * @param text текст для вывода
     */
    public void show(String text) {
        printWriter.print("\u001B[2J");
        printWriter.print(text);
        printWriter.flush();
    }
}
