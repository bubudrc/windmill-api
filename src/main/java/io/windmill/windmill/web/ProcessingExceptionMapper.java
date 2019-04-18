package io.windmill.windmill.web;

import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import io.windmill.windmill.services.exceptions.MissingKeyException;
import io.windmill.windmill.services.exceptions.UnauthorizedAccountAccessException;

@Dependent
@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {

	private static final Logger LOGGER = Logger.getLogger(ProcessingExceptionMapper.class);
	
	@Override
	public Response toResponse(ProcessingException exception) {
		
		if (exception.getMessage().equals("RESTEASY008200: JSON Binding deserialization error") ) {
			LOGGER.debug(exception.getMessage(), Optional.ofNullable(exception.getCause()).orElse(exception));
			return Response.status(Status.BAD_REQUEST).entity("The request body was malformed JSON").build();
		} else if (exception instanceof UnauthorizedAccountAccessException) {
			LOGGER.warn(exception.getMessage());			
			return Response.status(Status.UNAUTHORIZED).build();
		} else if (exception instanceof SubscriptionAuthorizationTokenException) {
			LOGGER.warn(exception.getMessage());			
			return Response.status(Status.UNAUTHORIZED).build();
		} else if (exception instanceof MissingKeyException) {
			LOGGER.error(exception.getMessage(), exception.getCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} else { 
			LOGGER.error(exception.getMessage(), exception.getCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}