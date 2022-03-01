package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public List<Teacher> getAll() {
        return entityManager.createQuery("SELECT s FROM Teacher s", Teacher.class).getResultList();
    }

    public Teacher getById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> getByLastName(String lastName) {
        TypedQuery<Teacher> query = entityManager.createQuery("SELECT t FROM Teacher t WHERE t.lastName = :lastName", Teacher.class);
        query.setParameter("lastName", lastName);

        return query.getResultList();
    }

    public void updateTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }
}
