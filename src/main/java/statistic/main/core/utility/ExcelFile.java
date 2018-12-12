/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main.core.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import statistic.main.core.models.AnalyticalAlignment;
import statistic.main.core.models.SeasonalityIndices;

import statistic.main.core.models.Solution;
import statistic.main.core.service.AnalyticalAlignmentService;
import statistic.main.core.service.SeasonalityIndicesService;
import statistic.main.core.service.SolutionService;
import statistic.main.ui.model.AnalyticalAlignmentTableModel;
import statistic.main.ui.model.AttributiveCellTableModel;




public class ExcelFile 
{
//    private HSSFWorkbook workbook; 
//    
//    public ExcelFile(String filename)
//    { 
//        try (FileInputStream file = new FileInputStream(new File(filename))){
//            workbook = new HSSFWorkbook(file);
//        }
//        catch(Exception e)
//        {
//        
//        }
//    }
    
    public static void read(String filename) throws IOException, Exception 
    {
        try (FileInputStream file = new FileInputStream(new File(filename))) {
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            SolutionService solutionService = new SolutionService(); 
            AnalyticalAlignmentService analyticalService = new AnalyticalAlignmentService();
            SeasonalityIndicesService seasonalityService = new SeasonalityIndicesService();
            
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                boolean parseDate    = false; 
                boolean initCurrency = false;
                BigDecimal units = new BigDecimal("0.0000");
                Solution solItems = new Solution();
                AnalyticalAlignment analyticalItems = new AnalyticalAlignment();
                SeasonalityIndices  indicesItem = new SeasonalityIndices();
                while (cellIterator.hasNext())
                {
                    
                    Cell cell = cellIterator.next();
                    if(cell.getColumnIndex() > 0 && cell.getColumnIndex() != 2 
                            && cell.getRowIndex() > 0)
                    {
                        switch (cell.getCellType())
                        {
                           case STRING: 
                           { 
                                if(cell.getColumnIndex() == 1){
                                    solItems.setCourseDate(LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                                    //analyticalItems.setCourseDate(LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                                    parseDate = true;
                                }
                                break;  
                           }
                           
                            case NUMERIC: 
                            {
                                if(cell.getColumnIndex() == 3){
                                    units = new BigDecimal(String.valueOf(cell.getNumericCellValue()));
                                }
                                
                                if(cell.getColumnIndex() == 4){
                                    BigDecimal temp = new BigDecimal(String.valueOf(cell.getNumericCellValue()));
                                    BigDecimal currency = temp.divide(units, 4, BigDecimal.ROUND_HALF_EVEN)
                                            .setScale(4, BigDecimal.ROUND_HALF_EVEN);
                                    solItems.setCourse(currency);
                                    //analyticalItems.setCourse(currency);
                                    initCurrency = true;
                                }
                                break;
                            }
                        }
                    }      
                }
                

                if(parseDate && initCurrency)
                {    
                    solutionService.saveSolution(solItems);
                    
                    indicesItem.setCourseDate(solItems.getCourseDate());
                    indicesItem.setCourse(solItems.getCourse());
                    seasonalityService.saveSeasonalityIndices(indicesItem);
                    
                    analyticalItems.setCourseDate(solItems.getCourseDate());
                    analyticalItems.setCourse(solItems.getCourse());
                    analyticalService.saveAnalyticalAlignment(analyticalItems);
                }
            }
        }
    }
        
    private static void setStyleTitleForWite(Cell item)
    {
        CellStyle cellStyle = item.getCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        item.setCellStyle(cellStyle);      
    }
    
    private static void setColorCell(HSSFWorkbook workbook, Cell item)
    {
        HSSFCellStyle style = workbook.createCellStyle();  
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        item.setCellStyle(style);

    }
    
    private static void setRegionBorder(CellRangeAddress region, Sheet sheet) 
    {
        RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
        
    }
    
    public static void write(String filename, AttributiveCellTableModel model) throws IOException, Exception 
    {
        if(model != null)
        {
            if(model.getRowCount() > 0)
            {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Solve");

                sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 5));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 7));
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 8, 8));


                Row title = sheet.createRow(0);
                title.createCell(0).setCellValue(model.getColumnName(0));
                setStyleTitleForWite(title.getCell(0));

                title.createCell(1).setCellValue(model.getColumnName(1));
                setStyleTitleForWite(title.getCell(1));

                title.createCell(2).setCellValue("Абсолютный прирост");
                setStyleTitleForWite(title.getCell(2));

                title.createCell(4).setCellValue("Темп роста");
                setStyleTitleForWite(title.getCell(4));

                title.createCell(6).setCellValue("Темп прироста");
                setStyleTitleForWite(title.getCell(6));

                title.createCell(8).setCellValue(model.getColumnName(8));
                setStyleTitleForWite(title.getCell(8));

                Row subTitle = sheet.createRow(1);
                subTitle.createCell(2).setCellValue(model.getColumnName(2));
                setStyleTitleForWite(subTitle.getCell(2));
                subTitle.createCell(3).setCellValue(model.getColumnName(3));
                setStyleTitleForWite(subTitle.getCell(3));

                subTitle.createCell(4).setCellValue(model.getColumnName(4));
                setStyleTitleForWite(subTitle.getCell(4));
                subTitle.createCell(5).setCellValue(model.getColumnName(5));
                setStyleTitleForWite(subTitle.getCell(5));

                subTitle.createCell(6).setCellValue(model.getColumnName(6));
                setStyleTitleForWite(subTitle.getCell(6));
                subTitle.createCell(7).setCellValue(model.getColumnName(7));
                setStyleTitleForWite(subTitle.getCell(7));

                sheet.addMergedRegion(new CellRangeAddress(model.getRowCount()+1, model.getRowCount()+1, 0, 1));
                sheet.addMergedRegion(new CellRangeAddress(model.getRowCount()+1, model.getRowCount()+1, 2, 3));
                sheet.addMergedRegion(new CellRangeAddress(model.getRowCount()+1, model.getRowCount()+1, 4, 5));
                sheet.addMergedRegion(new CellRangeAddress(model.getRowCount()+1, model.getRowCount()+1, 6, 7));


                for(int i = 0; i < model.getRowCount(); ++i)
                {
                    Row row = sheet.createRow(i + 2);
                    for(int j = 0; j < model.getColumnCount(); ++j)
                    {
                        Object data = model.getValueAt(i, j);
                        if(data != null)
                            row.createCell(j).setCellValue(String.valueOf(data));
                    }
                }
                for(int j = 0; j < model.getColumnCount(); j+=2){
                    setColorCell(workbook, sheet.getRow(model.getRowCount()+1).getCell(j));
                    setStyleTitleForWite(sheet.getRow(model.getRowCount()+1).getCell(j));
                }

                for(int i = 0; i < model.getRowCount() + 2; ++i) {
                    for(int j = 0; j < model.getColumnCount(); ++j)
                      setRegionBorder(new CellRangeAddress(i, i, j, j), sheet);  
                }

                FileOutputStream outputStream = new FileOutputStream(filename);
                workbook.write(outputStream);
                workbook.close();
            }
        }
    }
    
    public static void simpleWrite(String filename, DefaultTableModel model) throws IOException
    {
        if(model != null)
        {
            if(model.getRowCount() > 0)
            {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Solve");


                Row title = sheet.createRow(0);
                for(int i = 0; i < model.getColumnCount(); ++i){
                    title.createCell(i).setCellValue(model.getColumnName(i));
                    setStyleTitleForWite(title.getCell(i));
                }
                
                for(int i = 0; i < model.getRowCount(); ++i)
                {
                    Row row = sheet.createRow(i + 1);
                    for(int j = 0; j < model.getColumnCount(); ++j)
                    {
                        Object data = model.getValueAt(i, j);
                        if(data != null)
                            row.createCell(j).setCellValue(String.valueOf(data));
                    }
                }
                for(int j = 0; j < model.getColumnCount(); ++j){
                    setColorCell(workbook, sheet.getRow(model.getRowCount()).getCell(j));
                    setStyleTitleForWite(sheet.getRow(model.getRowCount()).getCell(j));
                }

                for(int i = 0; i < model.getRowCount() + 1; ++i) {
                    for(int j = 0; j < model.getColumnCount(); ++j)
                      setRegionBorder(new CellRangeAddress(i, i, j, j), sheet);  
                }

                FileOutputStream outputStream = new FileOutputStream(filename);
                workbook.write(outputStream);
                workbook.close();
            }
        }
    }
    
}
