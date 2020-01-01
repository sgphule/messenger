package org.sgphule.javamaven.messenger.resources;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sgphule.javamaven.messenger.model.Comment;
import org.sgphule.javamaven.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) { //, @PathParam("commentId") long )
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id, Comment comment) {
		comment.setId(id);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId")
	public Comment getMessgae(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
	/*
	@GET
	public String test() {
		return "new sub resource";
	}
	
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("messageId") long messageID, @PathParam("commentId") long commentID) {
		return "method to return comment ID:"+ commentID + " for message " +messageID;
	}
	*/
}
