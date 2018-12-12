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
@Table (name = "SOLUTION")
public class Solution implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    private Integer id;
    
    @Column(name = "COURSE_DATE", nullable = false, insertable = true, updatable = true)
    private LocalDate courseDate;
    
    @Column(name = "COURSE", nullable = false, insertable = true, updatable = true)
    private BigDecimal course;
    
    @Column(name = "FIRST_CHAIN", nullable = true, insertable = true, updatable = true)        
    private BigDecimal firstChain;
    
    @Column(name = "FIRST_BASIC", nullable = true, insertable = true, updatable = true)        
    private BigDecimal firstBasic;
    
    @Column(name = "SECOND_CHAIN", nullable = true, insertable = true, updatable = true)        
    private BigDecimal secondChain;
    
    @Column(name = "SECOND_BASIC", nullable = true, insertable = true, updatable = true)        
    private BigDecimal secondBasic;
    
    @Column(name = "THIRD_CHAIN", nullable = true, insertable = true, updatable = true)        
    private BigDecimal thirdChain;
    
    @Column(name = "THIRD_BASIC", nullable = true, insertable = true, updatable = true)        
    private BigDecimal thirdBasic;
    
    @Column(name = "PERCENT", nullable = true, insertable = true, updatable = true) 
    private BigDecimal percent;
    
    @Transient 
    private final String [] columnName = new String[]{
       "ID", "Дата", "Курс валюты", "Цепной", "Базисный", "Цепной",
       "Базисный", "Цепной", "Базисный", "|%|"
    };
    
    @Transient 
    private final Class[] columnClass = new Class[] {
        Integer.class, LocalDate.class, BigDecimal.class,
        BigDecimal.class, BigDecimal.class, BigDecimal.class,
            BigDecimal.class, BigDecimal.class, BigDecimal.class,
            BigDecimal.class
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
     
    public Solution() {
    }
    
    public Solution(LocalDate date, BigDecimal value) { 
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
    
    public BigDecimal getFirstChain() {
        return this.firstChain;
    }

    public void setFirstChain(BigDecimal value) {
        this.firstChain = value;
    }
 
    public BigDecimal getFirstBasic() {
        return this.firstBasic;
    }

    public void setFirstBasic(BigDecimal value) {
        this.firstBasic = value;
    }
    
//-
    
    public BigDecimal getSecondChain() {
        return this.secondChain;
    }

    public void setSecondChain(BigDecimal value) {
        this.secondChain = value;
    }
    
    public BigDecimal getSecondBasic() {
        return this.secondBasic;
    }

    public void setSecondBasic(BigDecimal value) {
        this.secondBasic = value;
    }
    
//-
    
    public BigDecimal getThirdChain() {
        return this.thirdChain;
    }

    public void setThirdChain(BigDecimal value) {
        this.thirdChain = value;
    }
    
    public BigDecimal getThirdBasic() {
        return this.thirdBasic;
    }

    public void setThirdBasic(BigDecimal value) {
        this.thirdBasic = value;
    }

//-    
    
    public BigDecimal getPercent() {
        return this.percent;
    }
    
    public void setPercent(BigDecimal value) {
        this.percent = value;
    }
}
