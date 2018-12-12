/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.dao;

import java.sql.SQLException;
import java.util.Collection;


public interface DAOInterface<RowType> 
{
    public boolean isOpenSession();
    public void save(RowType item) throws SQLException, Exception;
    public void update(RowType item) throws SQLException, Exception;
    public void delete(RowType item) throws SQLException, Exception;
    public RowType findById(Integer id) throws SQLException, Exception;
    public Collection<RowType> findAll() throws SQLException, Exception;
    public Collection<RowType> findAll(Integer skip, Integer maxshow) throws SQLException, Exception;
    public Integer count() throws SQLException, Exception;
    public Integer deleteAll() throws SQLException, Exception;
    public Integer getMaxId();
    public Integer getMinId(); 
    public RowType getLastRow();
}
