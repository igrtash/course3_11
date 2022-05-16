import java.util.Objects;

public class Student implements Comparable<Student>{
    String name;
    int age;
    int numclass;
    FavoriteLesson favoriteLesson;

    public Student(String name, int age, int numclass, FavoriteLesson favoriteLesson) {
        this.name = name;
        this.age = age;
        this.numclass = numclass;
        this.favoriteLesson = favoriteLesson;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", numclass=" + numclass +
                ", favoriteLesson=" + favoriteLesson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name) + (age - o.age);
    }

}
