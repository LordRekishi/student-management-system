package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {
        Student student1 = new Student("Patrik", "Fallqvist", "patrik@gmail.com", "01");
        Student student2 = new Student("Börje", "Hedin", "borje@gmail.com", "02");
        Student student3 = new Student("Felix", "Jacobsen", "felix@gmail.com", "03");
        Student student4 = new Student("Helena", "Halldin", "helena@gmail.com", "04");
        Student student5 = new Student("Lum", "Shabani", "lum@gmail.com", "05");
        Student student6 = new Student("Greger", "Gregori", "greger@gmail.com", "06");

        Teacher teacher1 = new Teacher("Martin", "Blomberg");
        Teacher teacher2 = new Teacher("Pontus", "Redig");
        Teacher teacher3 = new Teacher("Eddie", "Neuman");

        Subject subject1 = new Subject("Java");
        Subject subject2 = new Subject("Java EE");
        Subject subject3 = new Subject("Java Byggmiljö");
        Subject subject4 = new Subject("Databas");
        Subject subject5 = new Subject("Webb");
        Subject subject6 = new Subject("Spring Framework");

        subject1.addStudent(student1);
        subject1.addStudent(student2);
        subject2.addStudent(student3);
        subject2.addStudent(student4);
        subject3.addStudent(student5);
        subject3.addStudent(student6);
        subject4.addStudent(student1);
        subject4.addStudent(student2);
        subject5.addStudent(student3);
        subject5.addStudent(student4);
        subject6.addStudent(student5);
        subject6.addStudent(student6);

        subject1.setTeacher(teacher1);
        subject2.setTeacher(teacher1);
        subject3.setTeacher(teacher2);
        subject4.setTeacher(teacher2);
        subject5.setTeacher(teacher3);
        subject6.setTeacher(teacher3);

        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.persist(subject3);
        entityManager.persist(subject4);
        entityManager.persist(subject5);
        entityManager.persist(subject6);
    }
}
