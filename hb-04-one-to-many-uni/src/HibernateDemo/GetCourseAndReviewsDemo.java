package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Course;
import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;
import HibernateEntity.Review;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     	
            //start a transaction
            session.beginTransaction();
            
            //get the course
            int theId = 10;
            Course course = session.get(Course.class, theId);
            
            //print the course
            System.out.println("Course : " + course);
            
            //print the course reviews
            System.out.println(course.getReviews());
			
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
