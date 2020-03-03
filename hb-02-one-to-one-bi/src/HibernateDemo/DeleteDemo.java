package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     
            //start a transaction
            session.beginTransaction();
            
            //get instructor by primary key/id
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);
            
            System.out.println("Found Instructor : " + instructor);
            
            //delete the instructors
            if(instructor!=null) {
            	System.out.println("Deleting : " + instructor);
            	session.delete(instructor);
            }
            
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
