/*
 * (swing1.1beta3)
 * 
 */

package statistic.main.ui.model;

import java.util.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import javax.swing.event.*;
import statistic.main.core.models.Solution;
import statistic.main.core.service.SolutionService;
import statistic.main.core.utility.ResultAverageValues;



public class AttributiveCellTableModel extends DefaultTableModel 
{

  protected CellAttribute cellAtt;
  private java.util.List<Solution> data = null;
  private ResultAverageValues result = null;
  
  public AttributiveCellTableModel() { super(); }
  
  public String isClassName() { return "AttributiveCellTableModel"; }

  
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
                Solution row = data.get(rowIndex);
                switch(colIndex) {
                    //case 0: return row.getId();
                    case 0: return row.getCourseDate().format(DateTimeFormatter.ofPattern("dd.MM.yyy"));
                    case 1: return row.getCourse();
                    case 2: return row.getFirstChain(); 
                    case 3: return row.getFirstBasic(); 
                    case 4: return row.getSecondChain();
                    case 5: return row.getSecondBasic();
                    case 6: return row.getThirdChain(); 
                    case 7: return row.getThirdBasic(); 
                    case 8: return row.getPercent(); 
                    default: return null;
                }
            }
            if(rowIndex == getRowCount()-1 && result != null)
            {
                switch(colIndex) {
                    //case 0: return row.getId();
                    case 0: return "Среднее значение:";
                    case 2: return result.getDeltaAbs();
                    case 4: return result.getRateInc();
                    case 6: return result.getRateGrowth();
                    case 8: return "-"; 
                    default: return null;
                } 
            }
        }
            
      return null;
    }
    
    public void setValueAt(Object value, int row, int col) { }
  
   
  public void load(ResultAverageValues resultAvg)
  {
    try {
        SolutionService solService = new SolutionService();
        data = (java.util.List<Solution>)solService.findAllSolution();  
        if(data == null)
            data = new java.util.ArrayList<>();
        
        cellAtt = new DefaultCellAttribute(getRowCount(), getColumnCount());
        newRowsAdded(new TableModelEvent(this, 0, getRowCount()-1,
		 TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
        
        result = resultAvg;
        
        CellSpan cellAtt =(CellSpan)getCellAttribute();
	int[] columns1 = new int[]{0, 1}; 
	int[] rows1    = new int[]{getRowCount()-1};
	cellAtt.combine(rows1, columns1);
        
        int[] columns2 = new int[]{2, 3}; 
	int[] rows2    = new int[]{getRowCount()-1};
	cellAtt.combine(rows2, columns2);
        
        int[] columns3 = new int[]{4, 5}; 
	int[] rows3    = new int[]{getRowCount()-1};
	cellAtt.combine(rows3, columns3);
        
        int[] columns4 = new int[]{6, 7}; 
	int[] rows4    = new int[]{getRowCount()-1};
	cellAtt.combine(rows4, columns4);
        
        
        int[] columns5 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}; 
        int[] rows5    = new int[]{getRowCount()-1};
        
        CellFont cellFont = (CellFont)getCellAttribute();
        ColoredCell ColorCell = (ColoredCell)getCellAttribute();
        
        Font font = new Font("Arial", Font.BOLD, 12);
        cellFont.setFont(font, rows1, columns5);
        
        Color color = new Color(174, 210, 183);
        ColorCell.setBackground(color, rows5, columns5);
        
        
    } catch (Exception ex) {
            Logger.getLogger(AttributiveCellTableModel.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }

  public CellAttribute getCellAttribute() {
    return cellAtt;
  }

  public void setCellAttribute(CellAttribute newCellAtt) {
    int numColumns = getColumnCount();
    int numRows    = getRowCount();
    if ((newCellAtt.getSize().width  != numColumns) ||
        (newCellAtt.getSize().height != numRows)) {
      newCellAtt.setSize(new Dimension(numRows, numColumns));
    }
    cellAtt = newCellAtt;
    fireTableDataChanged();
  }
  
  public void addColumn(Object columnName, Vector columnData) { }

  public void addRow(Vector rowData) { }

  public void insertRow(int row, Vector rowData) { }

  /*
  public void changeCellAttribute(int row, int column, Object command) {
    cellAtt.changeAttribute(row, column, command);
  }

  public void changeCellAttribute(int[] rows, int[] columns, Object command) {
    cellAtt.changeAttribute(rows, columns, command);
  }
  */
    
}

