/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.utility;

import java.math.BigDecimal;

/**
 *
 * @author serge
 */
public class ResultCourseForecast 
{
    BigDecimal spotforecast;
    BigDecimal rsdFromTrend;
    BigDecimal X1;
    BigDecimal X2;
    Long T;
    
    private boolean flagInit;
    
    public ResultCourseForecast() { flagInit = false; }
    public ResultCourseForecast(BigDecimal spotforecas, 
            BigDecimal rsdFromTrend, BigDecimal X1, 
            BigDecimal X2, Long t)
    {
        this.spotforecast = spotforecas;
        this.rsdFromTrend = rsdFromTrend;
        this.X1 = X1;
        this.X2 = X2;
        this.T = t;
        flagInit = true;
    }
    
    public boolean isInit() { return flagInit; } 
    
    public BigDecimal getSpotForecast()
    {
        return this.spotforecast;
    }
    
    public BigDecimal getRSDFromTrend()
    {
        return this.rsdFromTrend;
    }
        
    public BigDecimal getX1()
    {
        return this.X1;
    }
            
    public BigDecimal getX2()
    {
        return this.X2;
    }
    
    public Long getT()
    {
        return this.T;
    }

}
