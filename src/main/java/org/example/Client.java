package org.example;

import java.net.*;
import java.io.*;

public class Client {
    private String lastMessage = "<LinkStart Date=\"131123\" Time=\"112600\"/>";
    private String serverName;
    int port;
    private Socket client;

    public Client(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }
    public void clientConnect() throws IOException {
        System.out.println("Подключение к " + serverName + " на порт " + port);
        this.client = new Socket(this.serverName, this.port);
        System.out.println("Подкючились к " + client.getRemoteSocketAddress());
    }

    public void clientDisconnect() throws IOException {
        this.client.close();
    }

    public void sendMessage(String message){
        try {
            if (!message.equals("!")){
                this.lastMessage = message;
            }

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

//            out.writeUTF("Привет из " + client.getLocalSocketAddress());
            out.writeUTF(lastMessage);
            System.out.println("Отправили сообщение : " + lastMessage);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String mes = in.readUTF();
            if (!mes.equals(" ")){
                System.out.println("Сервер ответил : " + mes);
            }
        } catch (IOException e) {
            System.out.println(e);;
        }
    }
}
