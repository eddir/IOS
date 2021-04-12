package рф.пинж.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkThread extends Thread{
    private Socket socket;

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

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода в NetworkThread.");
        }

        try {
            this.line = this.bufferedReader.readLine();
            while (this.line.compareTo("QUIT") != 0) {

                printWriter.println(this.line);
                printWriter.flush();
                System.out.println("Response to Client  :  " + this.line);
                this.line = this.bufferedReader.readLine();
            }

        } catch (IOException e) {
            this.line = this.getName(); //reused String line for getting thread name
            System.out.println("Ошибка ввода-вывода: клиент " + this.line + " резко оборвал соединение.");

        } catch (NullPointerException e) {
            this.line = this.getName(); //reused String line for getting thread name
            System.out.println("Клиент " + this.line + " разорвал связь.");

        } finally {
            Network.closeThread(this);
        }
    }

    public void close() {
        try {
            System.out.println("Закрытие соединения..");

            if (bufferedReader != null) {
                this.bufferedReader.close();
                System.out.println("Socket Input Stream закрыт.");
            }

            if (printWriter != null) {
                this.printWriter.close();
                System.out.println("Socket Out закрыт.");
            }
            if (this.socket != null) {
                this.socket.close();
                System.out.println("Socket закрыт.");
            }

        } catch (IOException ie) {
            System.out.println("Ошибка при закрытии сокета.");
        }
    }
}
