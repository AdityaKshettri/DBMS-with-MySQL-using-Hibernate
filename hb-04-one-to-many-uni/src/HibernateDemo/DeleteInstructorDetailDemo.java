package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
            
            int theId = 3;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
            
            System.out.println("Instructor Detail : " + instructorDetail);
            
            System.out.println("Associated Instructor : " + instructorDetail.getInstructor());
            
            //lets delete the instructor detail
            System.out.println("Deleting Instructor Detail : " + instructorDetail);
            
            //remove associated object reference
            //break bi-directional link
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);
            
            //commit transaction
            session.getTransaction().commit();
            
            System.out.println("Done!!");
        } 
		catch(Exception e) {
			e.printStackTrace();
		}
        finally 
        {
        	//handle connection leak error
            session.close();
            sessionFactory.close();
        }
	}
	
}
