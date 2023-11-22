package org.example;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
//        serverSocket.setSoTimeout(1000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Ожидание клиента на порт " +
                        serverSocket.getLocalPort() + "...");
                Socket socketSomething = serverSocket.accept();
                System.out.println("К серверу подключился : " + socketSomething.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(socketSomething.getInputStream());

                while (true){
//                    byte[] bytes = in.readAllBytes();
//                    byte[] bytes = in.readNBytes(57);
                    String mes = in.readUTF();
                    try {
//                        String mes = new String(bytes, "UTF-8");
                        if (mes.equals("q")) {
                            System.out.println("Клиент " + socketSomething.getRemoteSocketAddress() + " : " + mes);
                            DataOutputStream out = new DataOutputStream(socketSomething.getOutputStream());
                            out.writeUTF(" ");
                            System.out.println("Клиент " + socketSomething.getRemoteSocketAddress() + " отключился.");
                            break;
                        }
                        System.out.println("Клиент " + socketSomething.getRemoteSocketAddress() + " : " + mes);
                        DataOutputStream out = new DataOutputStream(socketSomething.getOutputStream());
                        String idMes = mes.substring(mes.length()-5, mes.length()-1);
                        String answer = "_000000099Fiscal          __20 IFC_GST_SEL_418_IFC_CHG_PST_2_418/Gavrilina, " +
                                "Yulia Mi_418/Zatsepina, Ksenia I_" + idMes + "_";
                        out.writeUTF(answer);
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
