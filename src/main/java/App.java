import dao.Course;
import dao.CourseDAO;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dmifed
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        CourseDAO courseDAO = context.getBean(CourseDAO.class);
        System.out.println(courseDAO.findAll());
        System.out.println(courseDAO.findById(10));
        System.out.println(courseDAO.findByTitle("PHP base"));
        Course newCourse = new Course();
        newCourse.setTitle("Java base");
        newCourse.setLength(21);
        newCourse.setDescriptions("Java base descriptions");
        //courseDAO.insert(newCourse);
        System.out.println(courseDAO.findAll());
        System.out.println(courseDAO.findByTitle("base"));
        Course updateCourse = courseDAO.findById(2);
        updateCourse.setDescriptions(updateCourse.getDescriptions() + "-U");
        courseDAO.update(updateCourse);
        System.out.println(courseDAO.findAll());
        //courseDAO.delete(12);
        //System.out.println(courseDAO.findAll());


        context.close();

    }
}
