/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.ui.model;

import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import statistic.main.core.models.SeasonalityIndices;
import statistic.main.core.service.SeasonalityIndicesService;

/**
 *
 * @author Progress
 */
public class SeasonalityIndicesTableModel extends AttributiveCellTableModel 
{
       
    private java.util.List<SeasonalityIndices> data = null;
    private BigDecimal SumY = null;
    
    public SeasonalityIndicesTableModel() { super(); }
    
    @Override
    public String isClassName() { return "SeasonalityIndicesTableModel"; }
    
    @Override
    public boolean isCellEditable(int row, int column){  
          return false;  
    }
   
    @Override
    public int getColumnCount() {
        if(data != null)
            return !data.isEmpty()? data.get(0).getColumnCount() - 1 : 0;
        else 
            return 0;
    }

    @Override
    public int getRowCount() {
        if(data != null)
            return !data.isEmpty()? data.size() + 1 : data.size();
        else 
            return 0;
    }
    public String getColumnName(int index) 
    {
        if(!data.isEmpty()) {
            if(index >= 0 && index <= data.get(0).getColumnCount())
                return data.get(0).getColumnName(index+1);
        }
      return null;
    }
    
    public Class getColumnClass(int index) 
    {
        if(!data.isEmpty()) {
            if(index >= 0 && index <= data.get(0).getColumnCount())
                return data.get(0).getColumnClass(index+1);
        }
        return Object.class;
        //return getValueAt(0, c).getClass();
    }
    
    public Object getValueAt(int rowIndex, int colIndex) 
    {
        if(!data.isEmpty()) {
            if(rowIndex < data.size())
            {
                SeasonalityIndices row = data.get(rowIndex);
                switch(colIndex) {
                    case 0: return row.getCourseDate().format(DateTimeFormatter.ofPattern("dd.MM.yyy"));
                    case 1: return row.getCourse();
                    case 2: return row.getIndices(); 
                    default: return null;
                }
            }
            if(rowIndex == getRowCount()-1)
            {
                switch(colIndex) {
                    case 0: return "Итого:";
                    case 1: return SumY.doubleValue() == 0 ? " " : SumY;
                    case 2: return "-";
                    default: return null;
                } 
            }
        }
            
      return null;
    }
    
    public void load(BigDecimal sumY)
    {
        try 
        {
            SeasonalityIndicesService  seasonalityService = new SeasonalityIndicesService();
            data = (java.util.List<SeasonalityIndices>)seasonalityService.findAllSeasonalityIndices();
            if(data == null)
                data = new java.util.ArrayList<>();

            cellAtt = new DefaultCellAttribute(getRowCount(), getColumnCount());
            newRowsAdded(new TableModelEvent(this, 0, getRowCount()-1,
                     TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));

            int[] columns = new int[]{0, 1, 2}; 
            int[] rows   = new int[]{getRowCount()-1};

            CellFont cellFont = (CellFont)getCellAttribute();
            ColoredCell ColorCell = (ColoredCell)getCellAttribute();

            Font font = new Font("Arial", Font.BOLD, 12);
            cellFont.setFont(font, rows, columns);

            Color color = new Color(174, 210, 183);
            ColorCell.setBackground(color, rows, columns);
            
            this.SumY = sumY;
        }
        catch (Exception ex) {
            Logger.getLogger(AnalyticalAlignmentTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
