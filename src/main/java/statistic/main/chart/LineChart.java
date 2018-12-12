/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import statistic.main.core.models.AnalyticalAlignment;
import statistic.main.core.service.AnalyticalAlignmentService;


public class LineChart 
{
    public LineChart(JPanel parent) throws ParseException, Exception {

        initUI(parent);
    }

    private void initUI(JPanel parent) throws ParseException, Exception {

        parent.removeAll();
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        if(chart != null){
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.white);
            parent.add(chartPanel);
        }
    }

    private XYDataset createDataset() throws ParseException, Exception 
    {
        AnalyticalAlignmentService analyticalService = new AnalyticalAlignmentService();
        if(analyticalService.isOpenSession())
        {
            List<AnalyticalAlignment> data = (List<AnalyticalAlignment>) 
                    analyticalService.findAllAnalyticalAlignment();
            TimeSeries series1 = new TimeSeries("Фактические данные");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            for(AnalyticalAlignment row : data)
            {
                //LocalDate date = row.getCourseDate();
                String dateStr = row.getCourseDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
                Date date = dateFormat.parse(dateStr);
                //Date date = Date.from(row.getCourseDate().atStartOfDay(ZoneId.systemDefault())
                //        .toInstant());
                series1.addOrUpdate(new Day(date), row.getCourse().doubleValue());
                //Date.from(Instant.MIN)
            }

            TimeSeries series2 = new TimeSeries("Теоретические данные");
            for(AnalyticalAlignment row : data)
            {
                //LocalDate date = row.getCourseDate();
                String dateStr = row.getCourseDate().format(DateTimeFormatter.ofPattern("dd.MM.yyy")).toString();
                Date date = dateFormat.parse(dateStr);
                //Date date = Date.from(row.getCourseDate().atStartOfDay(ZoneId.systemDefault())
                //        .toInstant());
                series2.addOrUpdate(new Day(date), row.getYAvgTValue().doubleValue());
                //Date.from(Instant.MIN)
            }

            TimeSeriesCollection  dataset = new TimeSeriesCollection ();
            dataset.addSeries(series2);
            dataset.addSeries(series1);


            return dataset;
        }
        return null;
    }

    private JFreeChart createChart(final XYDataset dataset) 
    {
        if(dataset != null)
        {
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Динамика курса валюты.",  // title
            "Дата",             // x-axis label
            "Курс валюты",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
            );
            
            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            
            
            DateAxis axis = (DateAxis) plot.getDomainAxis();
            axis.setDateFormatOverride(new SimpleDateFormat("dd.MM.yyyy"));

            //XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            XYSplineRenderer renderer = new XYSplineRenderer();

            renderer.setSeriesPaint(0, Color.RED);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));

            renderer.setSeriesPaint(1, Color.BLUE);
            renderer.setSeriesStroke(1, new BasicStroke(2.0f));        

            plot.setRenderer(renderer);
            //plot.setBackgroundPaint(Color.white);

            plot.setRangeGridlinesVisible(true);
            plot.setDomainGridlinesVisible(true);

            chart.getLegend().setFrame(BlockBorder.NONE);

            chart.setTitle(new TextTitle("Динамика курса валюты.", new Font("Serif", Font.BOLD, 18)));
                    
            return chart;
        }
        return null;
    }
}
