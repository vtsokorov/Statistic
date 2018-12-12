/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import statistic.main.core.models.Solution;
import statistic.main.core.utility.HibernateUtil;


public class SolutionDAOImpl implements DAOInterface<Solution> 
{
    private Session session  = null;
    
    public SolutionDAOImpl()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        if(sf != null)
            session = sf.openSession();
    }
    
    
    @Override
    public boolean isOpenSession() {
        return session != null;
    }

    @Override
    public void save(Solution item) throws SQLException, Exception {
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        //session.close();
        //HibernateUtil.closeSession();
    }

    @Override
    public void update(Solution item) throws SQLException, Exception {
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        //session.close();
        //HibernateUtil.closeSession();
    }
    
    
    @Override
    public void delete(Solution item) throws SQLException, Exception {
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        //session.close();
        //HibernateUtil.closeSession();
    }
    

    @Override
    public Solution findById(Integer id) throws SQLException, Exception {
        return session.get(Solution.class, id);
        //HibernateUtil.getSessionFactory().close();
    }

    @Override
    public Collection<Solution> findAll() throws SQLException, Exception {   
         return session.createQuery("from Solution").list();
        //HibernateUtil.getSessionFactory().close();    
    }
    
    @Override
    public Collection<Solution> findAll(Integer skip, Integer maxshow) throws SQLException, Exception {   
         List<Solution> list = session.createQuery("from Solution")
                 .setFirstResult(skip).setMaxResults(maxshow).list();
         return list;
        //HibernateUtil.getSessionFactory().close();    
    }
    
    @Override
    public Integer count() throws SQLException, Exception
    {
        return ((Long)session.createQuery("select count(*) from Solution").uniqueResult()).intValue();
    }
     
    @Override
    public Integer deleteAll() 
    {
       session.beginTransaction();
       Query query = session.createQuery("DELETE FROM Solution");
       Integer rows = query.executeUpdate();
       session.getTransaction().commit();
       return rows;
    }
    
    @Override
    public Integer getMaxId()
    {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(Solution.class).setProjection(Projections.max("id"));
        return (Integer) criteria.getExecutableCriteria(session).list().get(0);
    }
    
    @Override
    public Integer getMinId()
    {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(Solution.class).setProjection(Projections.min("id"));
        return (Integer) criteria.getExecutableCriteria(session).list().get(0);
    }

    @Override
    public Solution getLastRow() {
               DetachedCriteria criteria = DetachedCriteria
                .forClass(Solution.class).setProjection(Projections.max("id"));
        Integer id = (Integer) criteria.getExecutableCriteria(session).list().get(0);
        
        return session.get(Solution.class, id);
    }

}
