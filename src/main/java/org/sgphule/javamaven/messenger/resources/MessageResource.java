package org.sgphule.javamaven.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.sgphule.javamaven.messenger.model.Message;
import org.sgphule.javamaven.messenger.resources.beans.MessageFilterBean;
import org.sgphule.javamaven.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		
		if(filterBean.getYear() > 0) {
			return messageService.getAllmessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		
		return messageService.getAllMessages();
		
	}
	@POST
	public Response addMessage(Message message) {
		Message newMessage = messageService.addMessage(message);
		return Response.status(Status.CREATED)
				.entity(newMessage)
				.build();
		//return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		
		return messageService.getMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	
}
