package org.ieschabas;

import org.ieschabas.servidor.Server;

import java.net.InetSocketAddress;

public class Main{
    public static void main(String[] args) {
        int port = 3500;
        Server server = new Server(new InetSocketAddress(port));
        server.start();
        System.out.println("Servidor WebSocket iniciado en el puerto: " + server.getPort());
    }
}