package java;

import java.util.*;

public class JavaPriorityQueue {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}

class StudentComparator implements Comparator<Student> {
    @Override

    public int compare(Student a, Student b) {

        if (a.getCgpa() > b.getCgpa()) {
            return -1;
        } else if(b.getCgpa() > a.getCgpa()) {
            return 1;
        }

        if(!a.getName().equals(b.getName())) {
            return a.getName().compareTo(b.getName());
        }

        return a.getId() > b.getId() ? -1 : a.getId() == b.getId() ? 0 : 1;

    }
}


class Student {
    int id;
    String name;
    double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }
}

class Priorities {

    List<Student> getStudents(List<String> events) {

        Queue<Student> students = new PriorityQueue<>(50, new StudentComparator());

        int nrEvents = events.size();

        for (String event : events) {
            String[] eventData = event.split(" ");
            String eventType = eventData[0];

            if("SERVED".equals(eventType)) {
                Student s = students.poll();
                //System.out.print("polled " + s);

            } else {
                String name = eventData[1];
                double cgpa = Double.parseDouble(eventData[2]);
                int id = Integer.parseInt(eventData[3]);

                Student student = new Student(id, name, cgpa);

                students.add(student);

                //System.out.println("added " + student);
            }
        }

        List<Student> s = new ArrayList<>();

        while(!students.isEmpty()) {
            s.add(students.poll());
        }

        return s;
    }
}
