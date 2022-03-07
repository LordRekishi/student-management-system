package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.error.EntitysNotFoundException;
import se.iths.error.ErrorMessage;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Inject
    StudentService studentService;

    @Inject
    TeacherService teacherService;

    @Inject
    Validator validator;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);
        if (!violations.isEmpty())
            throw new WebApplicationException(ErrorMessage.createSubject(violations));

        subjectService.createSubject(subject);
        return Response.status(Response.Status.CREATED).entity(subject).build();
    }

    @Path("")
    @GET
    public Response getAll() {
        List<Subject> foundSubjects = subjectService.getAll();
        return Response.ok(foundSubjects).build();
    }

    @Path("{id}")
    @GET
    public Response getById(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.getById(id);
        if (foundSubject == null)
            throw new EntitysNotFoundException(ErrorMessage.getSubjectByID(id));

        return Response.ok(foundSubject).build();
    }

    @Path("filter")
    @GET
    public Response getByName(@QueryParam("name") String name) {
        List<Subject> foundSubjects = subjectService.getByName(name);
        return Response.ok(foundSubjects).build();
    }

    @Path("{id}")
    @PUT
    public Response updateSubject(@PathParam("id") Long id, Subject subject) {
        Subject foundSubject = subjectService.getById(id);
        if (foundSubject == null)
            throw new EntitysNotFoundException(ErrorMessage.updateSubject());

        subjectService.updateSubject(subject);
        return Response.status(Response.Status.ACCEPTED).entity(subject).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.getById(id);
        if (foundSubject == null)
            return Response.ok().build();

        subjectService.deleteSubject(id);
        return Response.ok().build();
    }

    @Path("{id}/student")
    @PUT
    public Response addStudent(@PathParam("id") Long id, @QueryParam("student-id") Long studentId) {
        Subject foundSubject = subjectService.getById(id);
        Student foundStudent = studentService.getById(studentId);

        if (foundSubject == null || foundStudent == null)
            throw new EntitysNotFoundException(ErrorMessage.updateSubject());

        foundSubject.addStudent(foundStudent);
        subjectService.updateSubject(foundSubject);

        return Response.status(Response.Status.ACCEPTED).entity(foundSubject).build();
    }

    @Path("{id}/teacher")
    @PUT
    public Response addTeacher(@PathParam("id") Long id, @QueryParam("teacher-id") Long teacherId) {
        Subject foundSubject = subjectService.getById(id);
        Teacher foundTeacher = teacherService.getById(teacherId);

        if (foundSubject == null || foundTeacher == null)
            throw new EntitysNotFoundException(ErrorMessage.updateSubject());

        foundSubject.setTeacher(foundTeacher);
        subjectService.updateSubject(foundSubject);

        return Response.status(Response.Status.ACCEPTED).entity(foundSubject).build();
    }

    @Path("{id}/student")
    @PUT
    public Response removeStudent(@PathParam("id") Long id, @QueryParam("student-id") Long studentId) {
        Subject foundSubject = subjectService.getById(id);
        Student foundStudent = studentService.getById(studentId);

        if (foundSubject == null || foundStudent == null)
            throw new EntitysNotFoundException(ErrorMessage.updateSubject());

        foundSubject.removeStudent(foundStudent);
        subjectService.updateSubject(foundSubject);

        return Response.status(Response.Status.ACCEPTED).entity(foundSubject).build();
    }

    @Path("{id}/teacher")
    @PUT
    public Response removeTeacher(@PathParam("id") Long id, @QueryParam("teacher-id") Long teacherId) {
        Subject foundSubject = subjectService.getById(id);
        Teacher foundTeacher = teacherService.getById(teacherId);

        if (foundSubject == null || foundTeacher == null)
            throw new EntitysNotFoundException(ErrorMessage.updateSubject());

        if (foundSubject.getTeacher() == foundTeacher)
            foundSubject.setTeacher(null);
        else
            throw new EntitysNotFoundException(ErrorMessage.getTeacherByID(teacherId));

        subjectService.updateSubject(foundSubject);

        return Response.status(Response.Status.ACCEPTED).entity(foundSubject).build();
    }

}
