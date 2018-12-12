/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.math.BigDecimal;
import javax.persistence.*;



@Entity
@Table (name = "ANALYTICAL_ALIGNMENT")
public class AnalyticalAlignment implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    private Integer id;
    
    @Column(name = "COURSE_DATE", nullable = false, insertable = true, updatable = true)
    private LocalDate courseDate;
    
    @Column(name = "COURSE", nullable = false, insertable = true, updatable = true)
    private BigDecimal course;
    
    
    @Column(name = "T_VALUE", nullable = true, insertable = true, updatable = true)        
    private Integer tValue;
    
    @Column(name = "T_SQUARE_VALUE", nullable = true, insertable = true, updatable = true)        
    private Integer tSquareValue;
    
    
    @Column(name = "T_MULTIPLY_Y_VALUE", nullable = true, insertable = true, updatable = true)        
    private BigDecimal tMultiplyYValue;
    
    @Column(name = "Y_AVG_T_VALUE", nullable = true, insertable = true, updatable = true)        
    private BigDecimal yAvgTValue;
    
    @Column(name = "Y_MINUS_Y_AVG_T_VALUE", nullable = true, insertable = true, updatable = true)        
    private BigDecimal yMinusYAvgTValue;
    
    @Column(name = "Y_MINUS_Y_AVG_T_SQUARE_VALUE", nullable = true, insertable = true, updatable = true)        
    private BigDecimal yMinusYAvgTSquareValue;
    

    
    @Transient 
    private final String [] columnName = new String[]{
       "ID", "Дата", "Курс валюты", "t", "t^2", "t*y",
       "yt", "y - yt", "(y-yt)^2"
    };
    
    @Transient 
    private final Class[] columnClass = new Class[] {
        Integer.class, LocalDate.class, BigDecimal.class,
        BigDecimal.class, BigDecimal.class, BigDecimal.class,
            BigDecimal.class, BigDecimal.class, BigDecimal.class
    };
    
    public Class getColumnClass(int indexColumn)
    {
        return columnClass[indexColumn];
    }
    
    public String getColumnName(int indexColumn)
    {
        return columnName[indexColumn];
    }
    
    public int getColumnCount()
    {
        return columnName.length;
    }
     
    public AnalyticalAlignment() {
    }
    
    public AnalyticalAlignment(LocalDate date, BigDecimal value) { 
        this.courseDate = date;  
        this.course = value; 
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer value) {
        this.id = value;
    }
    
//-
    
    public LocalDate getCourseDate() {
        return this.courseDate;
    }

    public void setCourseDate(LocalDate date) {
        this.courseDate = date;
    }
    
//-    

    public BigDecimal getCourse() {
        return this.course;
    }

    public void setCourse(BigDecimal value) {
        this.course = value;
    }
    
//-
    
    public Integer getTValue() {
        return this.tValue;
    }

    public void setTValue(Integer value) {
        this.tValue = value;
    }
 
    public Integer getTSquareValue() {
        return this.tSquareValue;
    }

    public void setTSquareValue(Integer value) {
        this.tSquareValue = value;
    }
    
//-
    
    public BigDecimal getTMultiplyYValue() {
        return this.tMultiplyYValue;
    }

    public void setTMultiplyYValue(BigDecimal value) {
        this.tMultiplyYValue = value;
    }
    
    public BigDecimal getYAvgTValue() {
        return this.yAvgTValue;
    }

    public void setYAvgTValue(BigDecimal value) {
        this.yAvgTValue = value;
    }
    
//-
    
    public BigDecimal getYMinusYAvgTValue() {
        return this.yMinusYAvgTValue;
    }

    public void setYMinusYAvgTValue(BigDecimal value) {
        this.yMinusYAvgTValue = value;
    }
    
    public BigDecimal getYMinusYAvgTSquareValue() {
        return this.yMinusYAvgTSquareValue;
    }

    public void setYMinusYAvgTSquareValue(BigDecimal value) {
        this.yMinusYAvgTSquareValue = value;
    }
}
