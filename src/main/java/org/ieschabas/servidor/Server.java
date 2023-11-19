package org.ieschabas.servidor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.List;

public class Server extends WebSocketServer {
   private final Request request = new Request();
    public Server(InetSocketAddress address) {super(address);}

    @Override
    public void onOpen(WebSocket ws, ClientHandshake clientHandshake) {
        System.out.println("Conexion establecida");
    }

    @Override
    public void onClose(WebSocket ws, int i, String s, boolean b) {
        System.out.println("Conexión cerrada");
        ws.send("El servidor ha cerrado la conexión");
    }

    @Override
    public void onMessage(WebSocket ws, String message)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(message);
            JsonNode action = root.get("action");
            JsonNode type = root.get("type");
            JsonNode data = root.get("data");
            switch (action.asText()){
                case "get":
                    List<?> lista = request.get(type.asText());
                    for(var obj: lista){
                        ws.send(obj.toString());
                    }
                    break;
                case "post":
                    if(request.post(type.asText(), data)) System.out.println("Objeto insertado correctamente");
                    break;
                case "delete":
                    if(request.delete(type.asText(), data)) System.out.println("Objeto eliminado correctamente");
                    break;
                default:
                    System.out.println("protocolo incorrecto");
                    ws.send("Error de protcolo");
                    break;
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(WebSocket ws, Exception ex) {
       ex.printStackTrace();
    }

    @Override
    public void onStart() {

    }
}
