package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.error.EntitysNotFoundException;
import se.iths.error.ErrorMessage;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Inject
    Validator validator;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        if (!violations.isEmpty())
            throw new WebApplicationException(ErrorMessage.createTeacher(violations));

        teacherService.createTeacher(teacher);
        return Response.status(Response.Status.CREATED).entity(teacher).build();
    }

    @Path("")
    @GET
    public Response getAll() {
        List<Teacher> foundTeachers = teacherService.getAll();
        return Response.ok(foundTeachers).build();
    }

    @Path("{id}")
    @GET
    public Response getById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.getById(id);
        if (foundTeacher == null)
            throw new EntitysNotFoundException(ErrorMessage.getTeacherByID(id));

        return Response.ok(foundTeacher).build();
    }

    @Path("filter")
    @GET
    public Response getByLastName(@QueryParam("lastname") String lastName) {
        List<Teacher> foundTeachers = teacherService.getByLastName(lastName);
        return Response.ok(foundTeachers).build();
    }

    @Path("{id}")
    @PUT
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        Teacher foundTeacher = teacherService.getById(id);
        if (foundTeacher == null)
            throw new EntitysNotFoundException(ErrorMessage.updateTeacher());

        teacherService.updateTeacher(teacher);
        return Response.status(Response.Status.ACCEPTED).entity(teacher).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.getById(id);
        if (foundTeacher == null)
            return Response.ok().build();

        teacherService.deleteTeacher(id);
        return Response.ok().build();
    }

}
