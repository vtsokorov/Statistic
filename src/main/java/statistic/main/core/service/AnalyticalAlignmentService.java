/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.service;

import java.util.Collection;
import statistic.main.core.dao.AnalyticalAlignmentDAOImpl;
import statistic.main.core.models.AnalyticalAlignment;

/**
 *
 * @author serge
 */
public class AnalyticalAlignmentService 
{
    private AnalyticalAlignmentDAOImpl dao = new AnalyticalAlignmentDAOImpl();

    public AnalyticalAlignmentService() {
    }
    
    public boolean isOpenSession()
    {
        return dao.isOpenSession();
    }

    public AnalyticalAlignment findAnalyticalAlignment(Integer id) throws Exception {
        return dao.findById(id);
    }

    public void saveAnalyticalAlignment(AnalyticalAlignment object) throws Exception {
        dao.save(object);
    }

    public void deleteAnalyticalAlignment(AnalyticalAlignment object) throws Exception {
        dao.delete(object);
    }

    public void updateAnalyticalAlignment(AnalyticalAlignment object) throws Exception {
        dao.update(object);
    }

    public Collection<AnalyticalAlignment> findAllAnalyticalAlignment() throws Exception {
        return dao.findAll();
    }
    
    public Collection<AnalyticalAlignment> findAllAnalyticalAlignment(Integer skip, Integer maxshow) throws Exception {   
         return dao.findAll(skip, maxshow);  
    }
    
    public Integer countAnalyticalAlignment() throws Exception
    {
        return dao.count();
    }
    
    public void deleteAllAnalyticalAlignment() throws Exception
    {
        dao.deleteAll();
    }
    
    public Integer getMaxId() throws Exception
    {
        return dao.getMaxId();
    }
    
    public Integer getMinId() throws Exception
    {
        return dao.getMinId();
    } 
    
    public AnalyticalAlignment getLastRow()
    {
        return dao.getLastRow();
    }
}
