package org.sgphule.javamaven.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.sgphule.javamaven.messenger.model.ErrorMessage;
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "http://sgphule.org");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
