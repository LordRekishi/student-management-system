package se.iths.rest;

import se.iths.entity.Student;
import se.iths.error.ErrorMessage;
import se.iths.error.StudentsNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Inject
    Validator validator;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (!violations.isEmpty())
            throw new WebApplicationException(ErrorMessage.createStudent(violations));

        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @Path("")
    @GET
    public Response getAll() {
        List<Student> foundStudents = studentService.getAll();
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @GET
    public Response getById(@PathParam("id") Long id) {
        Student foundStudent = studentService.getById(id);
        if (foundStudent == null)
            throw new StudentsNotFoundException(ErrorMessage.getByID(id));

        return Response.ok(foundStudent).build();
    }

    @Path("filter")
    @GET
    public Response getByLastName(@QueryParam("lastname") String lastName) {
        List<Student> foundStudents = studentService.getByLastName(lastName);
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        Student foundStudent = studentService.getById(id);
        if (foundStudent != student)
            throw new StudentsNotFoundException(ErrorMessage.updateStudent());

        studentService.updateStudent(student);
        return Response.status(Response.Status.ACCEPTED).entity(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        Student foundStudent = studentService.getById(id);
        if (foundStudent == null)
            return Response.ok().build();

        studentService.deleteStudent(id);
        return Response.ok().build();
    }

}
