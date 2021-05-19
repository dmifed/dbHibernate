package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dmifed
 */

@Transactional
@Repository("courseDAO")
public class HibernateCourseDAO implements CourseDAO {

    private static final Log LOG = LogFactory.getLog(HibernateCourseDAO.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id) {
        return (Course) getSessionFactory().getCurrentSession().byId(Course.class).load(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("from Course c").list(); //HQL
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findByTitle(String title) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria query = session.createCriteria(Course.class);
        query.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
        return query.list();

        /*
        return session.createQuery("from Course c where title LIKE :title")
                .setString("title", "%" + title.trim() + "%").list();
        */
    }

    @Override
    @Transactional
    public void insert(Course course) {
        getSessionFactory().getCurrentSession().save(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        getSessionFactory().getCurrentSession().update(course);
    }

    @Override
    @Transactional
    public void delete(int id) {
        getSessionFactory().getCurrentSession().delete(findById(id));
    }
}
