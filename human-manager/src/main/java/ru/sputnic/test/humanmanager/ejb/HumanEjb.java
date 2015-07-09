package ru.sputnic.test.humanmanager.ejb;


import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.sputnic.test.humanmanager.model.Human;
import ru.sputnic.test.humanmanager.service.HumanServiceException;
import ru.sputnic.test.humanmanager.service.IHumanService;
import ru.sputnic.test.humanmanager.service.ResourceNotFoundException;

@Path("actions")
@Singleton
public class HumanEjb {
	@Inject
	private IHumanService service;
	
	@Inject
    private Validator validator;
	
	private static final String UTF8 = ";charset=utf-8";
	
	public HumanEjb() {}
	
	@GET
	@Path("showall")
	@Produces(MediaType.APPLICATION_JSON + UTF8)
	public List<Human> showAllHuman() throws HumanServiceException {
		return service.showAllHuman();
	}
	
	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_JSON + UTF8)
	@Produces(MediaType.APPLICATION_JSON + UTF8)
	public Response saveHuman(Human human) {
		try {
			if(validator.validate(human).size() != 0){
				return Response.status(403).entity("Не прошло валидацию ").build();
			}
			service.saveHuman(human);
			return Response.ok().build();
		} catch (HumanServiceException e) {
			return Response.status(403).entity("Запрещено срохранять").build();
		}
	}

	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON + UTF8 )
	@Produces(MediaType.APPLICATION_JSON + UTF8)
	public Response updateHuman(Human human) {
		try {
			if(validator.validate(human).size() != 0){
				return Response.status(403).entity("Не прошло валидацию").build();
			}
			service.updateHuman(human);
			return Response.ok().build();
		} catch (HumanServiceException e) {
			return Response.status(403).entity("Пользователь отсутствует").build();
		}
	}
	
	@GET
	@Path("remove/{id}")
	@Consumes(MediaType.APPLICATION_JSON + UTF8)
	@Produces(MediaType.APPLICATION_JSON + UTF8)
	public Response removeHuman(@PathParam("id") Long id) {
		try {
			service.removeHuman(id);
			return Response.ok().build();
		} catch (ResourceNotFoundException e) {
			return Response.status(403).entity("Пользователь с id " + id + " отсутствует").build();
		} 
	}
}
