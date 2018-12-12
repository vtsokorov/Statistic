/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Progress
 */


@Entity
@Table (name = "SEASONALITY_INDICES")
public class SeasonalityIndices implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    private Integer id;
    
    @Column(name = "COURSE_DATE", nullable = false, insertable = true, updatable = true)
    private LocalDate courseDate;
    
    @Column(name = "COURSE", nullable = false, insertable = true, updatable = true)
    private BigDecimal course;
    
    @Column(name = "INDICES", nullable = true, insertable = true, updatable = true)        
    private BigDecimal indices;
    

    @Transient 
    private final String [] columnName = new String[]{"ID", "Дата", "Курс валюты", "Индекс сезонности"};
    
    @Transient 
    private final Class[] columnClass = new Class[] 
    { Integer.class, LocalDate.class, BigDecimal.class, BigDecimal.class};
    
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
     
    public SeasonalityIndices() {
    }
    
    public SeasonalityIndices(LocalDate date, BigDecimal value) { 
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
    
    public BigDecimal getIndices() {
        return this.indices;
    }

    public void setIndices(BigDecimal value) {
        this.indices = value;
    }
}