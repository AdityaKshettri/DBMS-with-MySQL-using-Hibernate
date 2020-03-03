package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{
			//Creating the entity students
            System.out.println("Creating 3 new Student object...");
            Student tempStudent1 = new Student("Aditya", "Kshettri", "adi@gmail.com");
            Student tempStudent2 = new Student("Aditya", "Ksh", "adi@gmail.com");
            Student tempStudent3 = new Student("Adi", "Kshettri", "adi@gmail.com");
            
            //start a transaction
            session.beginTransaction();
            
            //Saving the students
            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            
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
