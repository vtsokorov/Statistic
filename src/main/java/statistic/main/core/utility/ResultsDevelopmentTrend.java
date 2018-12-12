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
public class ResultsDevelopmentTrend 
{
    private BigDecimal ySum;
    private BigDecimal tSquareSum;
    private BigDecimal tMultiplyYSum;
    private BigDecimal yAvgTSum;
    private BigDecimal yMinusYAvgTSum;
    private BigDecimal yMinusYAvgTSquareSum;
    private boolean flagInit;
    
    public ResultsDevelopmentTrend() { flagInit = false; }
    
    public ResultsDevelopmentTrend(BigDecimal ySum, 
            BigDecimal tSquareSum,
            BigDecimal tMultiplyYSum,
            BigDecimal yAvgTSum, 
            BigDecimal yMinusYAvgTSum,
            BigDecimal yMinusYAvgTSquareSum)
    {
        this.ySum = ySum;
        this.tSquareSum = tSquareSum;
        this.tMultiplyYSum = tMultiplyYSum;
        this.yAvgTSum = yAvgTSum;
        this.yMinusYAvgTSum = yMinusYAvgTSum;
        this.yMinusYAvgTSquareSum = yMinusYAvgTSquareSum;
        flagInit = true;
    }
    
    public boolean isInit() { return flagInit; } 
    
    public BigDecimal getYSum()
    {
        return ySum;
    }
    
    public BigDecimal getTSquareSum()
    {
        return tSquareSum;
    }
        
    public BigDecimal getTMultiplyYSum()
    {
        return tMultiplyYSum;
    }
            
    public BigDecimal getYAvgTSum()
    {
        return yAvgTSum;
    }
    
    public BigDecimal getYMinusYAvgTSum()
    {
        return yMinusYAvgTSum;
    }
    
    public BigDecimal getYMinusYAvgTSquareSum()
    {
        return yMinusYAvgTSquareSum;
    }
}
