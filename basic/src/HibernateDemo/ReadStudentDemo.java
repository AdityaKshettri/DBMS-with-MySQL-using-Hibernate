package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{
			//Creating a new entity
            System.out.println("Creating new Student object...");
            Student tempStudent = new Student("Sri", "Nidhi", "sri@gmail.com");
            
            session.beginTransaction();
            
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            session.getTransaction().commit();
            
            //Reading the entity with its id
            System.out.println("Saved student. Generated id : " + tempStudent.getId());
            
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            
            System.out.println("Getting student with id : " + tempStudent.getId());
            
            Student myStudent = session.get(Student.class, tempStudent.getId());
            
            System.out.println("Get Complete. Student : " + myStudent);
            
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
