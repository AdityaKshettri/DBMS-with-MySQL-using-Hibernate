package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{
			//Creating the entity student
            System.out.println("Creating new Student object...");
            Student tempStudent = new Student("Adi", "Kshettri", "adi@gmail.com");
            
            //start a transaction
            session.beginTransaction();
            
            //Saving the entity student
            System.out.println("Saving the student...");
            session.save(tempStudent);
            
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
