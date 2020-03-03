package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Course;
import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;
import HibernateEntity.Review;
import HibernateEntity.Student;

public class CreateStudentAndCoursesDemo {

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
            
            //create a course
            Course course = new Course("Maths");
            
            //save the course
            System.out.println("Saving the course");
            session.save(course);
            System.out.println("Saved the course : " + course);
            
            //create the students
            Student student1 = new Student("Adi", "Ksh", "adi@gmail.com");
            Student student2 = new Student("Sri", "Nidhi", "sri@gmail.com");
            
            //add some students
            course.addStudent(student1);
            course.addStudent(student2);
            
            //save the students
            System.out.println("Saving the students");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved the students : " + course.getStudents());
			
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
