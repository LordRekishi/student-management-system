package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Name can not be Empty")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "SUB_STUD",
            joinColumns = @JoinColumn(name = "SUB_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "STUD_ID", referencedColumnName = "id"))
    private Set<Student> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.addSubject(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.removeSubject(this);
    }
}
