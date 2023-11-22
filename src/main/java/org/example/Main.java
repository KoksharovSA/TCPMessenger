package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.print("Введите номер приложения (1 - Server, 2 - Client, 3 - выход): ");
            Integer num = scanner.nextInt();
            if (num != 3) {
                if (num == 1) {
                    System.out.print("Введите порт: ");
                    Integer port = scanner.nextInt();
                    Thread server = new Server(port);
                    server.setDaemon(true);
                    server.run();
                } else if (num == 2) {
                    System.out.print("Введите IP адрес и порт сервера через двоеточие: ");
                    String[] socket = scanner.next().split(":");
                    Client client = new Client(socket[0], Integer.parseInt(socket[1]));
                    client.clientConnect();
                    boolean more = true;
                    while (more){
                        System.out.print("Введите сообщение или знак '!', для выхода - 'q': ");
                        String message = scanner.next();
                        if (message.equals("q")){
                            client.sendMessage(message);
                            more = false;
                            client.clientDisconnect();
                        } else {
                            client.sendMessage(message);
                        }
                    }
                } else {
                    System.out.println("Неверный ввод!");
                }
            } else {
                run = false;
            }
        }
    }
}
