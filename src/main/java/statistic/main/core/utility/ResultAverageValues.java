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
public class ResultAverageValues {
    
    private BigDecimal deltaAbs; 
    private BigDecimal rateInc; 
    private BigDecimal rateGrowth;
    
    private boolean flagInit;
    
    public ResultAverageValues()
    {
        this.deltaAbs = null;
        this.rateInc = null;
        this.rateGrowth = null;
        flagInit = false;
    }
    
    public ResultAverageValues(BigDecimal deltaAbs, BigDecimal rateInc, BigDecimal rateGrowth)
    {
        this.deltaAbs = deltaAbs;
        this.rateInc = rateInc;
        this.rateGrowth = rateGrowth;
        flagInit = true;
    }
    
    public boolean isInit() { return flagInit; } 
    
    public BigDecimal getDeltaAbs()
    {
        return deltaAbs;
    }
    
    public BigDecimal getRateInc()
    {
        return rateInc;
    }
        
    public BigDecimal getRateGrowth()
    {
        return rateGrowth;
    }
}
