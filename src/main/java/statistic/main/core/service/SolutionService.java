/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.service;

import java.util.Collection;
import statistic.main.core.dao.SolutionDAOImpl;
import statistic.main.core.models.Solution;


public class SolutionService {
    
    private SolutionDAOImpl dao = new SolutionDAOImpl();

    public SolutionService() {
    }

    public Solution findSolution(Integer id) throws Exception {
        return dao.findById(id);
    }

    public void saveSolution(Solution object) throws Exception {
        dao.save(object);
    }

    public void deleteSolution(Solution object) throws Exception {
        dao.delete(object);
    }

    public void updateSolution(Solution object) throws Exception {
        dao.update(object);
    }

    public Collection<Solution> findAllSolution() throws Exception {
        return dao.findAll();
    }
    
    public Collection<Solution> findAllSolution(Integer skip, Integer maxshow) throws Exception {   
         return dao.findAll(skip, maxshow);  
    }
    
    public Integer countSolution() throws Exception
    {
        return dao.count();
    }
    
    public void deleteAllSolution() throws Exception
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
