/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.utility;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import statistic.main.core.models.AnalyticalAlignment;
import statistic.main.core.models.SeasonalityIndices;
import statistic.main.core.models.Solution;
import statistic.main.core.service.AnalyticalAlignmentService;
import statistic.main.core.service.SeasonalityIndicesService;
import statistic.main.core.service.SolutionService;


public class MathPart 
{
    
   public static ResultAverageValues calculateChainAndBaselineIndicators() throws Exception
   {
        SolutionService service = new SolutionService();
        int size = service.countSolution();
        List<Solution> table = (List<Solution>) service.findAllSolution(0, 2);
        Solution basic = table.get(0);
                    
        BigDecimal sb = (basic.getCourse().divide(basic.getCourse(), new MathContext(20)))
                                .multiply(new BigDecimal("100", MathContext.UNLIMITED));
        BigDecimal tp = sb.subtract(new BigDecimal("100", MathContext.UNLIMITED));
        basic.setSecondBasic(sb);
        basic.setThirdBasic(tp);
        service.updateSolution(basic);
                    
        for(int i = 0, skip = 0; i < size-1; ++i)
        {
            Solution row1 = table.get(0);
            Solution row2 = table.get(1); 

            BigDecimal firstChain = row2.getCourse().subtract(row1.getCourse(), new MathContext(20));              
            BigDecimal firstBasic = row2.getCourse().subtract(basic.getCourse(), new MathContext(20));
                        
            BigDecimal secondChain = (row2.getCourse().divide(row1.getCourse(), new MathContext(20)))
                      .multiply(new BigDecimal("100", MathContext.UNLIMITED));
                        
            BigDecimal secondBasic = (row2.getCourse().divide(basic.getCourse(), new MathContext(20)))
                      .multiply(new BigDecimal("100", MathContext.UNLIMITED));
                  
            row2.setFirstChain(firstChain);
            row2.setFirstBasic(firstBasic);
            row2.setSecondChain(secondChain);
            row2.setSecondBasic(secondBasic);
            row2.setThirdChain(secondChain.subtract(new BigDecimal("100", MathContext.UNLIMITED)));
            row2.setThirdBasic(secondBasic.subtract(new BigDecimal("100", MathContext.UNLIMITED)));
                        
            row2.setPercent(row1.getCourse().multiply(new BigDecimal("0.01", MathContext.UNLIMITED)));
                        
            service.updateSolution(row2);
                        
            table = (List<Solution>) service.findAllSolution(++skip, 2);
        }
        
        Integer N = size-1;
        BigDecimal Y0 = service.findSolution(service.getMinId()).getCourse();
        BigDecimal Yn = service.findSolution(service.getMaxId()).getCourse();
        BigDecimal deltaAbs = (Yn.subtract(Y0, MathContext.UNLIMITED)).divide(new BigDecimal(N), 4, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal rateInc  = new BigDecimal(
                Math.pow(Yn.divide(Y0, 4, BigDecimal.ROUND_HALF_EVEN).doubleValue(), 1.0 / N)*100)
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal rateGrowth = rateInc.subtract(new BigDecimal(100));

        return new ResultAverageValues(deltaAbs, rateInc, rateGrowth);
   }
   

   public static ResultsDevelopmentTrend calculateT() throws Exception
   {
       AnalyticalAlignmentService analytical = new AnalyticalAlignmentService();
       int size = analytical.countAnalyticalAlignment();
       
       BigDecimal ySum = new BigDecimal("0.0000");
       BigDecimal tMultiplyYSum = new BigDecimal("0.0000");
       BigDecimal tSquareSum = new BigDecimal("0.0000");
       for(int i = -size/2, j = 0; i < (size/2)+1 && j < size; ++i, ++j)
       {
           if(i == 0 && size % 2 == 0) i++;
           
           List<AnalyticalAlignment> item = (List<AnalyticalAlignment>) 
                   analytical.findAllAnalyticalAlignment(j, 1);
           AnalyticalAlignment row  = item.get(0);
           
           row.setTValue(i);
           row.setTSquareValue((new Double(Math.pow(i, 2))).intValue());
           
           BigDecimal ty = row.getCourse().multiply(new BigDecimal(i, MathContext.UNLIMITED));  
           row.setTMultiplyYValue(ty);
           
           analytical.updateAnalyticalAlignment(row);
           
           ySum = ySum.add(row.getCourse(), MathContext.UNLIMITED);
           tMultiplyYSum = tMultiplyYSum.add(ty, MathContext.UNLIMITED);
           tSquareSum = tSquareSum.add(new BigDecimal(row.getTSquareValue()), MathContext.UNLIMITED);        
       }
       
       BigDecimal a0 = ySum.divide(new BigDecimal(size), 4, BigDecimal.ROUND_HALF_EVEN);
       BigDecimal a1 = tMultiplyYSum.divide(tSquareSum, 4, BigDecimal.ROUND_HALF_EVEN);
               
       for(int i = 0; i < size; ++i)
       {
            List<AnalyticalAlignment> item = (List<AnalyticalAlignment>) 
                   analytical.findAllAnalyticalAlignment(i, 1);
            AnalyticalAlignment row  = item.get(0);
            
            //subtract???.
            BigDecimal yAvgT = a0.add(a1.multiply(new BigDecimal(row.getTValue()), MathContext.UNLIMITED), MathContext.UNLIMITED);
            row.setYAvgTValue(yAvgT);

            BigDecimal yMinisYAvgT = row.getCourse().subtract(yAvgT, MathContext.UNLIMITED); 
            row.setYMinusYAvgTValue(yMinisYAvgT);
            
            
            BigDecimal yMinisYAvgTSquare = new BigDecimal(Math.pow(yMinisYAvgT.doubleValue(), 2));
            row.setYMinusYAvgTSquareValue(yMinisYAvgTSquare);
            
            analytical.updateAnalyticalAlignment(row);
       }

       BigDecimal yAvgTSum = new BigDecimal("0.0000");
       BigDecimal yMinusYAvgTSum = new BigDecimal("0.0000");
       BigDecimal yMinusYAvgTSquareSum = new BigDecimal("0.0000");
       for(int i = 0; i < size; ++i)
       {
            List<AnalyticalAlignment> item = (List<AnalyticalAlignment>) 
                   analytical.findAllAnalyticalAlignment(i, 1);
            AnalyticalAlignment row  = item.get(0);
            yAvgTSum = yAvgTSum.add(row.getYAvgTValue(), MathContext.UNLIMITED);
            yMinusYAvgTSum = yMinusYAvgTSum.add(row.getYMinusYAvgTValue(), MathContext.UNLIMITED);
            yMinusYAvgTSquareSum = yMinusYAvgTSquareSum.add(row.getYMinusYAvgTSquareValue(), MathContext.UNLIMITED)
                    .setScale(4, BigDecimal.ROUND_HALF_EVEN);
       }
       
       ResultsDevelopmentTrend result = 
               new ResultsDevelopmentTrend(ySum, tSquareSum, tMultiplyYSum,
                       yAvgTSum, yMinusYAvgTSum, yMinusYAvgTSquareSum);
       
       return result;
   }
   
   public static BigDecimal[] getTrendlevel(ResultsDevelopmentTrend trend) throws Exception
   {
       AnalyticalAlignmentService analytical = new AnalyticalAlignmentService();
       int size = analytical.countAnalyticalAlignment();

       BigDecimal x = trend.getYMinusYAvgTSquareSum().divide(new BigDecimal(size), 4, BigDecimal.ROUND_HALF_EVEN);
       double temp = Math.sqrt(x.doubleValue());
       x = new BigDecimal(temp).setScale(4, BigDecimal.ROUND_HALF_EVEN);
       
       BigDecimal a0 = trend.getYSum().divide(new BigDecimal(size), 4, BigDecimal.ROUND_HALF_EVEN);
       BigDecimal y = (x.divide(a0, 4, BigDecimal.ROUND_HALF_EVEN)).multiply(new BigDecimal(100))
               .setScale(2, BigDecimal.ROUND_HALF_EVEN);
       
       BigDecimal [] arr = new BigDecimal[2];
       arr[0] = x;
       arr[1] = y;
       
       return arr;
   }
   
   public static BigDecimal calcSeasonalityIndices(ResultsDevelopmentTrend trend) throws Exception
   {
       SeasonalityIndicesService seasonalityService = new SeasonalityIndicesService();
       int size = seasonalityService.countSeasonalityIndices();
       
       BigDecimal a0 = trend.getYSum().divide(new BigDecimal(size), 4, BigDecimal.ROUND_HALF_EVEN);
       BigDecimal sumY = new BigDecimal("0.0000");
       for(int i = 0; i < size; ++i)
       {
           List<SeasonalityIndices> item = (List<SeasonalityIndices>) 
                   seasonalityService.findAllSeasonalityIndices(i, 1);
            SeasonalityIndices row  = item.get(0);
            BigDecimal si = (row.getCourse().divide(a0, 4, BigDecimal.ROUND_HALF_EVEN)).multiply(new BigDecimal(100))
               .setScale(2, BigDecimal.ROUND_HALF_EVEN);
            row.setIndices(si);
            
            sumY = sumY.add(row.getCourse(),  MathContext.UNLIMITED)
                    .setScale(4, BigDecimal.ROUND_HALF_EVEN);
            
            seasonalityService.updateSeasonalityIndices(row); 
       }
       
       return sumY;
   }
   
   public static ResultCourseForecast courseForecast(ResultsDevelopmentTrend trend, double confidenceCoefficient, long days) throws Exception
   {
       AnalyticalAlignmentService analytical = new AnalyticalAlignmentService();
       int size = analytical.countAnalyticalAlignment();
       
       double ta = confidenceCoefficient;
       final Integer m = 2;
       final Integer n = size;
       BigDecimal x =  trend.getYMinusYAvgTSquareSum();
       BigDecimal a0 = trend.getYSum().divide(new BigDecimal(size), 4, BigDecimal.ROUND_HALF_EVEN);
       BigDecimal a1 = trend.getTMultiplyYSum().divide(trend.getTSquareSum(), 4, BigDecimal.ROUND_HALF_EVEN);
       
       Long t = analytical.getLastRow().getTValue() + days;
       BigDecimal Yt = a0.add(a1.multiply(new BigDecimal(t), MathContext.UNLIMITED));
       System.out.print("Точечный прогноз(Yt) = ");
       System.out.println(Yt);
       
       BigDecimal Syt = new BigDecimal(
               Math.sqrt(x.divide(new BigDecimal(size-m), 4, BigDecimal.ROUND_HALF_EVEN).doubleValue()))
               .setScale(4, BigDecimal.ROUND_HALF_EVEN);
       System.out.print("Остаточное среднее квадратическое отклонение от тренда(Syt) = ");
       System.out.println(Syt);
       
       BigDecimal x1 = Yt.subtract((new BigDecimal(ta)).multiply(Syt, MathContext.UNLIMITED))
               .setScale(4, BigDecimal.ROUND_HALF_EVEN);
       BigDecimal x2 = Yt.add((new BigDecimal(ta)).multiply(Syt, MathContext.UNLIMITED))
               .setScale(4, BigDecimal.ROUND_HALF_EVEN);
       
//       System.out.print("x1 = ");
//       System.out.println(x1);
//       System.out.print("x2 = ");
//       System.out.println(x2);
//       System.out.print("t = ");
//       System.out.println(t);
       
       
       return new ResultCourseForecast(Yt, Syt, x1, x2, t);
   }
   
 }
