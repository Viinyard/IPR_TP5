package test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

public class TestApi {

    //TODO : Adapter l'URL en fonction de votre resource :
    public static final String URL = "http://localhost:8080/rest";

    @XmlRootElement()
    public static class ChatMessage {
        public Long id;
        public String content;
        public String date;
        @Override
        public String toString() {
            return id + ":" + content+ " at "+ date;
        }
    }

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        //Post a message :
        ChatMessage m = new ChatMessage();
        m.content="ok ?";
        Response response = client.target(URL).path("messages").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(m, MediaType.APPLICATION_XML));
        System.out.println("Response "+ response.getStatus());

        //Get all the messages
        ChatMessage[] messages = client.target(URL).path("messages").request(MediaType.APPLICATION_JSON_TYPE).get(ChatMessage[].class);
        for (ChatMessage chatMessage : messages) {
            System.out.println(chatMessage);
        }
    }
}