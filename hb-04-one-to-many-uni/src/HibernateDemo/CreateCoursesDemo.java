package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Course;
import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     	
            //start a transaction
            session.beginTransaction();
            
            //get the instructor from db
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			//create some courses
			Course tempCourse1 = new Course("Bio");
			Course tempCourse2 = new Course("Math");
			
			//add course to instructor
			instructor.add(tempCourse1);
			instructor.add(tempCourse2);
            
			//save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
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
