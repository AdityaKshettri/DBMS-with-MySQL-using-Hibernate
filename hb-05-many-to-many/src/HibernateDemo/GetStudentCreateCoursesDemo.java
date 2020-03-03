package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Course;
import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;
import HibernateEntity.Review;
import HibernateEntity.Student;

public class GetStudentCreateCoursesDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     	
            //start a transaction
            session.beginTransaction();
            
            //get student from database
            int theId = 1;
            Student student = session.get(Student.class, theId);
            
            System.out.println("Student : " + student);
            System.out.println("Courses : " + student.getCourses());
			
            //create more courses
            Course course1 = new Course("Bio");
            Course course2 = new Course("CSE");
            
            //add student to courses
            course1.addStudent(student);
            course2.addStudent(student);
            
            //save the courses
            System.out.println("Saving the courses");
            session.save(course1);
            session.save(course2);
            
            //commit transaction
            session.getTransaction().commit();
            
            System.out.println("Done!!");
        } 
        finally 
        {
            session.close();
            sessionFactory.close();
        }
	}
	
}
