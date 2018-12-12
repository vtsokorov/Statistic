/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import statistic.main.core.models.AnalyticalAlignment;
import statistic.main.core.utility.HibernateUtil;

/**
 *
 * @author serge
 */
public class AnalyticalAlignmentDAOImpl implements DAOInterface<AnalyticalAlignment> 
{
    private Session session = null;
    
    public AnalyticalAlignmentDAOImpl() {
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        if(sf != null)
            session = sf.openSession();
        
    }
    
    @Override
    public boolean isOpenSession() {
        return session != null;
    }

    @Override
    public void save(AnalyticalAlignment item) throws SQLException, Exception {
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
    }

    @Override
    public void update(AnalyticalAlignment item) throws SQLException, Exception {
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
    }

    @Override
    public void delete(AnalyticalAlignment item) throws SQLException, Exception {
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
    }

    @Override
    public AnalyticalAlignment findById(Integer id) throws SQLException, Exception {
        return session.get(AnalyticalAlignment.class, id);
    }

    @Override
    public Collection<AnalyticalAlignment> findAll() throws SQLException, Exception {
        return session.createQuery("from AnalyticalAlignment").list();
    }

    @Override
    public Collection<AnalyticalAlignment> findAll(Integer skip, Integer maxshow) throws SQLException, Exception 
    {
        List<AnalyticalAlignment> list = session.createQuery("from AnalyticalAlignment")
                 .setFirstResult(skip).setMaxResults(maxshow).list();
         return list;
    }

    @Override
    public Integer count() throws SQLException, Exception {
        return ((Long)session.createQuery("select count(*) from AnalyticalAlignment").uniqueResult()).intValue();
    }

    @Override
    public Integer deleteAll() throws SQLException, Exception 
    {
       session.beginTransaction();
       Query query = session.createQuery("DELETE FROM AnalyticalAlignment");
       Integer rows = query.executeUpdate();
       session.getTransaction().commit();
       return rows;
    }

    @Override
    public Integer getMaxId() 
    {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(AnalyticalAlignment.class).setProjection(Projections.max("id"));
        return (Integer) criteria.getExecutableCriteria(session).list().get(0);
    }

    @Override
    public Integer getMinId() 
    {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(AnalyticalAlignment.class).setProjection(Projections.min("id"));
        return (Integer) criteria.getExecutableCriteria(session).list().get(0);
    }

    @Override
    public AnalyticalAlignment getLastRow() {
               DetachedCriteria criteria = DetachedCriteria
                .forClass(AnalyticalAlignment.class).setProjection(Projections.max("id"));
        Integer id = (Integer) criteria.getExecutableCriteria(session).list().get(0);
        
        return session.get(AnalyticalAlignment.class, id);
    }
    
}
