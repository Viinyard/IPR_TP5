package fr.istic.rest;

import mri.message.jersey.Message;
import mri.message.jersey.MessageList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import java.util.List;


@Path("/messages")
public class MessageRessource {

    //En partant du principe que l'annotation Path sur la classe contient "messages"
    @Path("/after/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Message> getMessagesAfter(@PathParam("id") Long id){
        System.out.println("message after" + id);
        return MessageList.getInstance().getMessagesAfter(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Message> getMessages() {
        System.out.println("all messages");
        return MessageList.getInstance().getMessages();
    }

    @Path("/{id}")
    @DELETE
    public void deleteMessage(@PathParam("id") Long id) {
        System.out.println("delete message of id " + id);
        MessageList.getInstance().delMessage(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postMessage(JAXBElement<Message> message) {
        MessageList.getInstance().createMessage(message.getValue());
        return Response.status(201).entity(message).build();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessageId(@PathParam("id") Long id) {
        System.out.println("message of id " + id);
        Message m = MessageList.getInstance().getMessage(id);
        if(m == null) {
            System.out.println("204 returned");
            return Response.noContent().build();
        }
        System.out.println("200 returned");
        return Response.ok(m).build();
    }
}
