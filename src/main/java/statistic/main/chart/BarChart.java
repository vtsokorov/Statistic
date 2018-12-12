/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.chart;

import java.awt.Color;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import statistic.main.core.models.SeasonalityIndices;
import statistic.main.core.service.SeasonalityIndicesService;

/**
 *
 * @author Progress
 */

public class BarChart 
{
    private double []arr;
    public BarChart(JPanel parent) throws ParseException, Exception {

        initUI(parent);
    }

    private void initUI(JPanel parent) throws ParseException, Exception {

        parent.removeAll();
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        if(chart != null){
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.white);
            parent.add(chartPanel);
        }
    }

    private DefaultCategoryDataset createDataset() throws ParseException, Exception 
    {
        SeasonalityIndicesService analyticalService = new SeasonalityIndicesService();
        if(analyticalService.isOpenSession())
        {
            List<SeasonalityIndices> data = (List<SeasonalityIndices>) 
                    analyticalService.findAllSeasonalityIndices();
            
            int size = data.size();
            arr = new double[size];
            for(int i = 0; i < size; ++i)
                arr[i] = data.get(i).getIndices().doubleValue();
            Arrays.sort(arr);
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(SeasonalityIndices row : data)
            {
                String dateStr = row.getCourseDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
                dataset.addValue(row.getIndices().doubleValue(), "Индекс сезонности", dateStr);
            }

            return dataset;
        }
        return null;
    }

    private JFreeChart createChart(final DefaultCategoryDataset dataset) 
    {
        if(dataset != null)
        {
		final JFreeChart chart = ChartFactory.createBarChart("Сезонные колебания курса",																	// title
				"Дата", 
				"Значение показателя", 
				dataset, 
				PlotOrientation.VERTICAL, 
				true, // include legend
				true, // tooltips
				false // urls
		);

		final CategoryPlot plot = chart.getCategoryPlot();
		final CategoryAxis axis = plot.getDomainAxis();
		axis.setCategoryLabelPositions(CategoryLabelPositions
                        .createUpRotationLabelPositions(/*dataset.getRowCount() <= 15 ? Math.PI / 8.0 : */Math.PI / 2.0));

		final CategoryItemRenderer renderer = plot.getRenderer();
		final BarRenderer r = (BarRenderer) renderer;
                
                chart.setBackgroundPaint(Color.white);  
                chart.getTitle().setPaint(Color.DARK_GRAY);
                chart.setBorderVisible(true);

                plot.getRangeAxis().setLowerBound(arr[0]-0.5);
                plot.getRangeAxis().setUpperBound(arr[arr.length-1]+0.5);

                plot.setBackgroundPaint(Color.white);     
                plot.setRangeGridlinePaint(Color.blue);
                CategoryItemRenderer rendererItem = plot.getRenderer();
                rendererItem.setSeriesPaint(0, Color.decode("#00008B"));
                return chart;
        }
        return null;
    }
}

