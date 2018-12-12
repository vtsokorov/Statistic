/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.service;

import java.util.Collection;
import statistic.main.core.dao.SeasonalityIndicesDAOImpl;
import statistic.main.core.models.SeasonalityIndices;

/**
 *
 * @author Progress
 */
public class SeasonalityIndicesService 
{
    private SeasonalityIndicesDAOImpl dao = new SeasonalityIndicesDAOImpl();

    public SeasonalityIndicesService() {
    }
    
    public boolean isOpenSession()
    {
        return dao.isOpenSession();
    }

    public SeasonalityIndices findSeasonalityIndices(Integer id) throws Exception {
        return dao.findById(id);
    }

    public void saveSeasonalityIndices(SeasonalityIndices object) throws Exception {
        dao.save(object);
    }

    public void deleteSeasonalityIndices(SeasonalityIndices object) throws Exception {
        dao.delete(object);
    }

    public void updateSeasonalityIndices(SeasonalityIndices object) throws Exception {
        dao.update(object);
    }

    public Collection<SeasonalityIndices> findAllSeasonalityIndices() throws Exception {
        return dao.findAll();
    }
    
    public Collection<SeasonalityIndices> findAllSeasonalityIndices(Integer skip, Integer maxshow) throws Exception {   
         return dao.findAll(skip, maxshow);  
    }
    
    public Integer countSeasonalityIndices() throws Exception
    {
        return dao.count();
    }
    
    public void deleteAllSeasonalityIndices() throws Exception
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
}
