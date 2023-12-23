

import org.example.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Program
{
/*


     *    * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
*/


    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()){

            // Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Course courses = Course.create();
            session.save(courses);
            System.out.println("Object courses save successfully");

            // Чтение объекта из базы данных
            Course retrievedCourse = session.get(Course.class, courses.getId());
            System.out.println("Object course retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.updateTitle();
            retrievedCourse.updateDuration();
            session.update(retrievedCourse);
            System.out.println("Object courses update successfully");

            // Удаление объекта
           //session.delete(retrievedCourse);
            //System.out.println("Object courses delete successfully");


            session.getTransaction().commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}


