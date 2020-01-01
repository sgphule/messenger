package org.sgphule.javamaven.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.sgphule.javamaven.messenger.model.ErrorMessage;
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
	
		@Override
		public Response toResponse(Throwable e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 500, "http://sgphule.org");
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errorMessage)
					.build();
		}

	}