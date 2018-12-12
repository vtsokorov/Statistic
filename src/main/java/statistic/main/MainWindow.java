/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic.main;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import statistic.main.chart.LineChart;
import statistic.main.core.service.AnalyticalAlignmentService;
import statistic.main.core.service.SolutionService;
import statistic.main.core.utility.ExcelFile;
import statistic.main.core.utility.FbProperty;
import statistic.main.core.utility.HibernateUtil;
import statistic.main.core.utility.MathPart;
import statistic.main.core.utility.ResultsDevelopmentTrend;
import statistic.main.ui.model.AnalyticalAlignmentTableModel;
import statistic.main.ui.model.AttributiveCellTableModel;
import statistic.main.ui.model.MultiSpanCellTable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import static java.util.concurrent.TimeUnit.DAYS;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import statistic.main.chart.BarChart;
import statistic.main.core.models.AnalyticalAlignment;
import statistic.main.core.models.SeasonalityIndices;
import statistic.main.core.models.Solution;
import statistic.main.core.service.SeasonalityIndicesService;
import statistic.main.core.utility.ResultAverageValues;
import statistic.main.core.utility.ResultCourseForecast;
import statistic.main.ui.model.SeasonalityIndicesTableModel;


/**
 *
 * @author serge
 */

//https://bank.gov.ua/control/uk/curmetal/currency/search/form/day
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    
    private ConnectionDataBase connect = new ConnectionDataBase(this, true);
    private LineChart chart;
    private BarChart  barChart; 
    
    
    public MainWindow() {
        initComponents();
        CenterFrame();
        settingsComponent();
    }
    
    private void CenterFrame()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }
    
    private void clearTables()
    {
        try {
            SolutionService service = new SolutionService();
            service.deleteAllSolution();
            
            AnalyticalAlignmentService analyticalService = new AnalyticalAlignmentService();
            analyticalService.deleteAllAnalyticalAlignment();
            
            SeasonalityIndicesService seasonalityService = new SeasonalityIndicesService();
            seasonalityService.deleteAllSeasonalityIndices();
            
        } catch (Exception ex) {
            showWarningMsg(ex.getMessage());
        }
    }
    
    private void settingsComponent()
    {
        menuItemOpenFile.setEnabled(false);
        menuItemSaveFile.setEnabled(false);
        menuItemSolution.setEnabled(false);
        menuItemSolution2.setEnabled(false);
        menuItemPlotCurrency.setEnabled(false);
        btnOpenFile.setEnabled(false);
        menuItemGetDataRestApi.setEnabled(false);
        menuItemClearAllTable.setEnabled(false);
        menuItemSolution3.setEnabled(false);
        menuItemSolution4.setEnabled(false);
        progress.setVisible(false);
        manuItemPlotSeasonalFluctuations.setEnabled(false);
        

        //mainMenuBar.add(Box.createHorizontalGlue()); 
        menuHelp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        timeValue.setEditable(false);
        spotForecast.setEditable(false);
        rsdFromTrend.setEditable(false);
        resultForecast.setEditable(false);
        numberFreedom.setEditable(false);
        
        mainTabPane.setEnabledAt(3, false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenu = new javax.swing.JMenu();
        contextMenu = new javax.swing.JPopupMenu();
        contextItemMenuSolve = new javax.swing.JMenuItem();
        mainTabPane = new javax.swing.JTabbedPane();
        firstPage = new javax.swing.JPanel();
        fpScrollPane = new javax.swing.JScrollPane();
        firstTable = new MultiSpanCellTable();
        secondPage = new javax.swing.JPanel();
        spScrollPane = new javax.swing.JScrollPane();
        secondTable = new MultiSpanCellTable();
        thirdPage = new javax.swing.JPanel();
        tpScrollPane = new javax.swing.JScrollPane();
        thirdTable = new MultiSpanCellTable();
        fourthPage = new javax.swing.JPanel();
        btnCourseForecast = new javax.swing.JButton();
        datePicker = new com.github.lgooddatepicker.components.DatePicker();
        confidenceCoefficient = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        timeValue = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        spotForecast = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rsdFromTrend = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultForecast = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        numberFreedom = new javax.swing.JTextField();
        mainToolBar = new javax.swing.JToolBar();
        btnDBConnection = new javax.swing.JButton();
        btnOpenFile = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        progress = new javax.swing.JProgressBar();
        mainMenuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemOpenFile = new javax.swing.JMenuItem();
        menuItemGetDataRestApi = new javax.swing.JMenuItem();
        menuItemSaveFile = new javax.swing.JMenuItem();
        menuDataBase = new javax.swing.JMenu();
        menuItemConnectDB = new javax.swing.JMenuItem();
        menuItemClearAllTable = new javax.swing.JMenuItem();
        menuSolution = new javax.swing.JMenu();
        menuItemSolution = new javax.swing.JMenuItem();
        menuItemSolution2 = new javax.swing.JMenuItem();
        menuItemSolution3 = new javax.swing.JMenuItem();
        menuItemSolution4 = new javax.swing.JMenuItem();
        menuPlotGraph = new javax.swing.JMenu();
        menuItemPlotCurrency = new javax.swing.JMenuItem();
        manuItemPlotSeasonalFluctuations = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuItemAbout = new javax.swing.JMenuItem();

        mainMenu.setText("jMenu1");

        contextItemMenuSolve.setText("Измерить колеблемость уровней около тренда");
        contextItemMenuSolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contextItemMenuSolveActionPerformed(evt);
            }
        });
        contextMenu.add(contextItemMenuSolve);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        mainTabPane.setPreferredSize(new java.awt.Dimension(900, 550));
        mainTabPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mainTabPaneStateChanged(evt);
            }
        });

        firstPage.setLayout(new javax.swing.BoxLayout(firstPage, javax.swing.BoxLayout.LINE_AXIS));

        firstTable.setModel(new AttributiveCellTableModel());
        firstTable.setName("firstTable"); // NOI18N
        fpScrollPane.setViewportView(firstTable);
        firstTable.getAccessibleContext().setAccessibleName("");
        firstTable.getAccessibleContext().setAccessibleDescription("");

        firstPage.add(fpScrollPane);

        mainTabPane.addTab(" Цепные и базисные показатели", firstPage);
        firstPage.getAccessibleContext().setAccessibleName("firstPage");

        secondPage.setLayout(new javax.swing.BoxLayout(secondPage, javax.swing.BoxLayout.LINE_AXIS));

        secondTable.setModel(new AnalyticalAlignmentTableModel());
        secondTable.setComponentPopupMenu(contextMenu);
        secondTable.setName("secondTable"); // NOI18N
        spScrollPane.setViewportView(secondTable);
        secondTable.getAccessibleContext().setAccessibleName("");
        secondTable.getAccessibleContext().setAccessibleDescription("");

        secondPage.add(spScrollPane);

        mainTabPane.addTab(" Тендеция развития", secondPage);
        secondPage.getAccessibleContext().setAccessibleName("secondPage");

        thirdPage.setLayout(new javax.swing.BoxLayout(thirdPage, javax.swing.BoxLayout.LINE_AXIS));

        thirdTable.setModel(new SeasonalityIndicesTableModel());
        thirdTable.setName("thirdTable"); // NOI18N
        tpScrollPane.setViewportView(thirdTable);
        thirdTable.getAccessibleContext().setAccessibleName("");
        thirdTable.getAccessibleContext().setAccessibleDescription("");

        thirdPage.add(tpScrollPane);

        mainTabPane.addTab("Индексы сезонности", thirdPage);

        btnCourseForecast.setText("Раситать");
        btnCourseForecast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCourseForecastActionPerformed(evt);
            }
        });

        confidenceCoefficient.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setLabelFor(datePicker);
        jLabel1.setText("Прогноз на дату:");

        jLabel3.setText("Табличное значение коэффициента доверия");
        jLabel3.setFocusable(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("по распределению Стьюдента (t- критерий):");

        jLabel2.setText("Показатель времени:");

        timeValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("Точечный прогноз:");

        spotForecast.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Остаточное среднее квадратическое ");

        jLabel7.setText("отклонение от тренда:");

        rsdFromTrend.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        resultForecast.setColumns(20);
        resultForecast.setLineWrap(true);
        resultForecast.setRows(5);
        resultForecast.setWrapStyleWord(true);
        resultForecast.setBackground(jLabel1.getBackground());
        resultForecast.setBorder(jLabel1.getBorder());
        resultForecast.setFont(jLabel1.getFont());
        jScrollPane1.setViewportView(resultForecast);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setText("Число степеней свободы:");

        numberFreedom.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout fourthPageLayout = new javax.swing.GroupLayout(fourthPage);
        fourthPage.setLayout(fourthPageLayout);
        fourthPageLayout.setHorizontalGroup(
            fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fourthPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fourthPageLayout.createSequentialGroup()
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCourseForecast, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confidenceCoefficient)
                            .addComponent(datePicker, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(numberFreedom)
                            .addComponent(timeValue)
                            .addComponent(spotForecast)
                            .addComponent(rsdFromTrend)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(fourthPageLayout.createSequentialGroup()
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(392, 392, 392))
        );
        fourthPageLayout.setVerticalGroup(
            fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fourthPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(fourthPageLayout.createSequentialGroup()
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(numberFreedom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(fourthPageLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(confidenceCoefficient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCourseForecast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timeValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(fourthPageLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spotForecast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(fourthPageLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGap(1, 1, 1)
                        .addComponent(jLabel6)
                        .addGap(3, 3, 3)
                        .addGroup(fourthPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rsdFromTrend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        mainTabPane.addTab("Прогноз курса", fourthPage);

        mainToolBar.setFloatable(false);
        mainToolBar.setRollover(true);

        btnDBConnection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connect.png"))); // NOI18N
        btnDBConnection.setBorder(null);
        btnDBConnection.setFocusable(false);
        btnDBConnection.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDBConnection.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDBConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDBConnectionActionPerformed(evt);
            }
        });
        mainToolBar.add(btnDBConnection);

        btnOpenFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open.png"))); // NOI18N
        btnOpenFile.setBorder(null);
        btnOpenFile.setFocusable(false);
        btnOpenFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpenFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileActionPerformed(evt);
            }
        });
        mainToolBar.add(btnOpenFile);

        statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusPanel.setLayout(new javax.swing.BoxLayout(statusPanel, javax.swing.BoxLayout.LINE_AXIS));

        progress.setIndeterminate(true);
        statusPanel.add(progress);

        menuFile.setText("Файл");
        menuFile.setToolTipText("");

        menuItemOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpenFile.setText("Открыть файл...");
        menuItemOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenFileActionPerformed(evt);
            }
        });
        menuFile.add(menuItemOpenFile);

        menuItemGetDataRestApi.setText("Загрузить данные с сайта НБУ");
        menuItemGetDataRestApi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGetDataRestApiActionPerformed(evt);
            }
        });
        menuFile.add(menuItemGetDataRestApi);

        menuItemSaveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSaveFile.setText("Сохранить результат как...");
        menuItemSaveFile.setToolTipText("");
        menuItemSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveFileActionPerformed(evt);
            }
        });
        menuFile.add(menuItemSaveFile);

        mainMenuBar.add(menuFile);

        menuDataBase.setText("База данных");

        menuItemConnectDB.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuItemConnectDB.setText("Подключение к БД...");
        menuItemConnectDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConnectDBActionPerformed(evt);
            }
        });
        menuDataBase.add(menuItemConnectDB);

        menuItemClearAllTable.setText("Очистить таблицы БД...");
        menuItemClearAllTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemClearAllTableActionPerformed(evt);
            }
        });
        menuDataBase.add(menuItemClearAllTable);

        mainMenuBar.add(menuDataBase);

        menuSolution.setText("Вычислить");

        menuItemSolution.setText("Вычислить цепные и базисные показатели...");
        menuItemSolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSolutionActionPerformed(evt);
            }
        });
        menuSolution.add(menuItemSolution);
        menuItemSolution.getAccessibleContext().setAccessibleName("menuItemSolution");

        menuItemSolution2.setText("Вычислить тендеции развития...");
        menuItemSolution2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSolution2ActionPerformed(evt);
            }
        });
        menuSolution.add(menuItemSolution2);

        menuItemSolution3.setText("Вычислить колеблемость уровней около тренда...");
        menuItemSolution3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSolution3ActionPerformed(evt);
            }
        });
        menuSolution.add(menuItemSolution3);

        menuItemSolution4.setText("Вычислить индексы  сезонности...");
        menuItemSolution4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSolution4ActionPerformed(evt);
            }
        });
        menuSolution.add(menuItemSolution4);

        mainMenuBar.add(menuSolution);

        menuPlotGraph.setText("Представление данных");

        menuItemPlotCurrency.setText("Построить график динамики курса...");
        menuItemPlotCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPlotCurrencyActionPerformed(evt);
            }
        });
        menuPlotGraph.add(menuItemPlotCurrency);

        manuItemPlotSeasonalFluctuations.setText("Построить гистограмму сезонного колебания курса...");
        manuItemPlotSeasonalFluctuations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuItemPlotSeasonalFluctuationsActionPerformed(evt);
            }
        });
        menuPlotGraph.add(manuItemPlotSeasonalFluctuations);

        mainMenuBar.add(menuPlotGraph);

        menuHelp.setText("Справка");

        menuItemAbout.setText("О программе");
        menuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(menuItemAbout);

        mainMenuBar.add(Box.createHorizontalGlue());

        mainMenuBar.add(menuHelp);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(mainTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainTabPane.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenFileActionPerformed
 
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Файл Microsoft office Excel", "xls"));
        
        AttributiveCellTableModel model1 = new AttributiveCellTableModel();
        AnalyticalAlignmentTableModel model2 = new AnalyticalAlignmentTableModel();
        SeasonalityIndicesTableModel model3 = new SeasonalityIndicesTableModel();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
         {
            @Override
            protected Void doInBackground() throws Exception 
            {
                File file = fileChooser.getSelectedFile();
                try {
                        clearTables();
                        ExcelFile.read(file.getPath());

                } catch (Exception ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
            
            protected void done()
            {
               model1.load(new ResultAverageValues());
               model2.load(new ResultsDevelopmentTrend());
               model3.load(new BigDecimal(0));

               firstTable.setModel(model1); 
               secondTable.setModel(model2);
               thirdTable.setModel(model3);

               firstTable.clearSelection();
               firstTable.revalidate();
               firstTable.repaint();
               secondTable.clearSelection();
               secondTable.revalidate();
               secondTable.repaint();
               thirdTable.clearSelection();
               thirdTable.revalidate();
               thirdTable.repaint();

               menuItemSolution.setEnabled(true);
               menuItemSolution2.setEnabled(true);
               
               menuItemPlotCurrency.setEnabled(false);
               manuItemPlotSeasonalFluctuations.setEnabled(false);
               menuItemSolution3.setEnabled(false);
               menuItemSolution4.setEnabled(false);
               mainTabPane.setEnabledAt(3, false);
            }
            
         };
        
        //Window win = SwingUtilities.getWindowAncestor((AbstractButton)evt.getSource());
        //final WaitDialog dialog = new WaitDialog(win, "Dialog", Dialog.ModalityType.APPLICATION_MODAL);

        worker.addPropertyChangeListener(new PropertyChangeListener() 
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                   if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                      //dialog.dispose();
                      progress.setVisible(false);
                      setWaitingState(false);
                   }
                }
             }
        });
        
        if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) 
        {
            worker.execute();
            progress.setVisible(true);
            setWaitingState(true);
            //dialog.setLocationRelativeTo(win);
            //dialog.setVisible(true);
        }
    }//GEN-LAST:event_menuItemOpenFileActionPerformed

    private void menuItemSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveFileActionPerformed
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Файл Microsoft office Excel", "xls"));
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground() throws Exception 
            {
                File file = fileChooser.getSelectedFile();
                try {
                    
                    if(mainTabPane.getSelectedIndex() == 0){
                        AttributiveCellTableModel model = (AttributiveCellTableModel)firstTable.getModel();
                        ExcelFile.write(file.getPath()+".xls", model);
                    }
                    if(mainTabPane.getSelectedIndex() == 1){
                        AnalyticalAlignmentTableModel model1 = (AnalyticalAlignmentTableModel)secondTable.getModel();
                        ExcelFile.simpleWrite(file.getPath()+".xls", model1);
                    }
                    if(mainTabPane.getSelectedIndex() == 2){
                        SeasonalityIndicesTableModel model = (SeasonalityIndicesTableModel)thirdTable.getModel();
                        ExcelFile.simpleWrite(file.getPath()+".xls", model);
                    }

                } catch (Exception ex) {
                    showWarningMsg("Error: "+ex.getStackTrace()[0].toString());
                }
                return null;
            }
         };

        if (fileChooser.showSaveDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) 
                worker.execute();
        
    }//GEN-LAST:event_menuItemSaveFileActionPerformed

    private void menuItemConnectDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConnectDBActionPerformed

        FbProperty property = new FbProperty();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
        {
            private boolean flag = false;
            @Override
            protected Void doInBackground() throws Exception 
            {
                try 
                {
                    if(property.readPropertyFromFile("settings.ini"))
                    {
                        connect.setVisible(true);
                        if(connect.isOk())
                        {
                            property.setUserName(connect.getUserName());
                            property.setPassword(connect.getPassword());
                            if(HibernateUtil.initConfiguration(property)) {                    
                                    flag = true;
                            }
                        }
                    }
                } catch (Exception ex) 
                {
                    showWarningMsg("Error: "+ex.getMessage());
                    menuItemConnectDBActionPerformed(evt);    
                } 
                return null;
            }
            
            protected void done()
            {
                if(flag == true)
                {
                    menuItemOpenFile.setEnabled(true);
                    btnOpenFile.setEnabled(true);
                    menuItemGetDataRestApi.setEnabled(true);
                    menuItemClearAllTable.setEnabled(true);
                }
            }
         };

        worker.execute();
    }//GEN-LAST:event_menuItemConnectDBActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        HibernateUtil.closeSession();
    }//GEN-LAST:event_formWindowClosed

    private void menuItemSolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSolutionActionPerformed
       
        AttributiveCellTableModel model = new AttributiveCellTableModel(); 
        SwingWorker<ResultAverageValues, Void> worker = new SwingWorker<ResultAverageValues, Void>()
        {
            @Override
            protected ResultAverageValues doInBackground() throws Exception 
            {
                try {
                    return MathPart.calculateChainAndBaselineIndicators();
                } catch (Exception ex) {
                    showWarningMsg("Error: "+ex.getMessage());
                }
                return null;
            }
            
            protected void done()
            {
                try { 
                    model.load(get());
                    firstTable.setModel(model);              
                    firstTable.clearSelection();
                    firstTable.revalidate();
                    firstTable.repaint();
                    menuItemSaveFile.setEnabled(true);
                } catch (InterruptedException | ExecutionException ex) 
                {
                    showWarningMsg("Error: "+ex.getMessage());
                }

            }
         };

        worker.execute();

    }//GEN-LAST:event_menuItemSolutionActionPerformed

    private void btnDBConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDBConnectionActionPerformed
        menuItemConnectDBActionPerformed(evt);
    }//GEN-LAST:event_btnDBConnectionActionPerformed

    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileActionPerformed
       menuItemOpenFileActionPerformed(evt);
    }//GEN-LAST:event_btnOpenFileActionPerformed

    private void menuItemSolution2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSolution2ActionPerformed

        AnalyticalAlignmentTableModel model = new AnalyticalAlignmentTableModel();
        SwingWorker<ResultsDevelopmentTrend, Void> worker = new SwingWorker<ResultsDevelopmentTrend, Void>()
        {
            @Override
            protected ResultsDevelopmentTrend doInBackground() throws Exception 
            {
                try {
                      return  MathPart.calculateT();
                } catch (Exception ex) {
                    showWarningMsg("Error: "+ex.getMessage());
                }
                return null;
            }
            
            protected void done()
            {
                try {    
                    model.load(get());
                    secondTable.setModel(model);
                    secondTable.clearSelection();
                    secondTable.revalidate();
                    secondTable.repaint();
                    menuItemPlotCurrency.setEnabled(true);
                    menuItemSolution3.setEnabled(true);
                    menuItemSolution4.setEnabled(true);
                    mainTabPane.setEnabledAt(3, true);
                } catch (InterruptedException | ExecutionException ex) {
                    showWarningMsg("Error: "+ex.getMessage());
                } 
            }
         };

        worker.execute();
    }//GEN-LAST:event_menuItemSolution2ActionPerformed

    private void menuItemPlotCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPlotCurrencyActionPerformed
  
            ShowChart dlgChart = new ShowChart(this, false);
            dlgChart.showLineChart();
    }//GEN-LAST:event_menuItemPlotCurrencyActionPerformed

    
    
    private void menuItemGetDataRestApiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGetDataRestApiActionPerformed
         
        GetCurencyDateRange obj = new GetCurencyDateRange(this, true);
        obj.setVisible(true);
        
        AttributiveCellTableModel model1 = new AttributiveCellTableModel();
        AnalyticalAlignmentTableModel model2 = new AnalyticalAlignmentTableModel();
        SeasonalityIndicesTableModel model3 = new SeasonalityIndicesTableModel();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
         {
            @Override
            protected Void doInBackground() throws Exception 
            {
                if(obj.isOk())
                {
                    try {
                        clearTables();
                        loadDataRestApi(obj.getFirstDate(), obj.getSecondDate(), obj.getCurencyType());  
                    } catch (Exception  ex) {
                        showWarningMsg("Error: "+ex.getMessage());
                    } 
                }
               return null;
            }
            
            protected void done()
            {
               if(obj.isOk())
               {
                    model1.load(new ResultAverageValues());
                    model2.load(new ResultsDevelopmentTrend());
                    model3.load(new BigDecimal(0));
                    firstTable.setModel(model1); 
                    secondTable.setModel(model2);
                    thirdTable.setModel(model3);

                    firstTable.clearSelection();
                    firstTable.revalidate();
                    firstTable.repaint();
                    secondTable.clearSelection();
                    secondTable.revalidate();
                    secondTable.repaint();
                    thirdTable.clearSelection();
                    thirdTable.revalidate();
                    thirdTable.repaint();

                    menuItemSolution.setEnabled(true);
                    menuItemSolution2.setEnabled(true);
                    menuItemPlotCurrency.setEnabled(false);
                    manuItemPlotSeasonalFluctuations.setEnabled(false);
                    menuItemSolution3.setEnabled(false);
                    menuItemSolution4.setEnabled(false);
                    mainTabPane.setEnabledAt(3, false);
               }
            }
         };
        
        //Window win = SwingUtilities.getWindowAncestor((AbstractButton)evt.getSource());
        //final WaitDialog dialog = new WaitDialog(win, "Dialog", Dialog.ModalityType.APPLICATION_MODAL);

        worker.addPropertyChangeListener(new PropertyChangeListener() 
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                   if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                      //dialog.dispose();
                      progress.setVisible(false);
                      setWaitingState(false);
                   }
                }
             }
        });

        worker.execute();
        progress.setVisible(true);
        setWaitingState(true);
        //dialog.setLocationRelativeTo(win);
        //dialog.setVisible(true); 
    }//GEN-LAST:event_menuItemGetDataRestApiActionPerformed

    private void menuItemClearAllTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemClearAllTableActionPerformed
        clearTables();
        AttributiveCellTableModel model1 = new AttributiveCellTableModel();
        AnalyticalAlignmentTableModel model2 = new AnalyticalAlignmentTableModel();
        SeasonalityIndicesTableModel model3 = new SeasonalityIndicesTableModel();
        model1.load(new ResultAverageValues());
        model2.load(new ResultsDevelopmentTrend());
        model3.load(new BigDecimal(0));
        
        firstTable.setModel(model1); 
        secondTable.setModel(model2);
        thirdTable.setModel(model3);

        firstTable.clearSelection();
        firstTable.revalidate();
        firstTable.repaint();
        secondTable.clearSelection();
        secondTable.revalidate();
        secondTable.repaint();
        thirdTable.clearSelection();
        thirdTable.revalidate();
        thirdTable.repaint();
        
        menuItemOpenFile.setEnabled(true);
        menuItemSaveFile.setEnabled(false);
        menuItemSolution.setEnabled(false);
        menuItemSolution2.setEnabled(false);
        menuItemPlotCurrency.setEnabled(false);
        btnOpenFile.setEnabled(true);
        menuItemGetDataRestApi.setEnabled(true);
        menuItemClearAllTable.setEnabled(true);
        mainTabPane.setEnabledAt(3, false);
    }//GEN-LAST:event_menuItemClearAllTableActionPerformed

    private void contextItemMenuSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contextItemMenuSolveActionPerformed
        
        AnalyticalAlignmentTableModel model = (AnalyticalAlignmentTableModel)secondTable.getModel();
        if(model.getRowCount() > 0 && model.getTrand().isInit())
        {            
            try {
               TrandSolve dlg = new TrandSolve(this, false);
               BigDecimal[] arr = MathPart.getTrendlevel(model.getTrand());
               dlg.setFirstItem(arr[0].toString());
               dlg.setSecondItem(arr[1].toString());
               dlg.setVisible(true);
            } catch (Exception ex) {
                showWarningMsg("Error: "+ex.getMessage());
            }
        }
        else showWarningMsg("Error: ");
            
    }//GEN-LAST:event_contextItemMenuSolveActionPerformed

    private void menuItemSolution3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSolution3ActionPerformed
        contextItemMenuSolveActionPerformed(evt);
    }//GEN-LAST:event_menuItemSolution3ActionPerformed

    private void menuItemSolution4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSolution4ActionPerformed
        
        AnalyticalAlignmentTableModel model = (AnalyticalAlignmentTableModel)secondTable.getModel();
        SeasonalityIndicesTableModel model2 = new SeasonalityIndicesTableModel();
        SwingWorker<BigDecimal, Void> worker = new SwingWorker<BigDecimal, Void>()
        {
            @Override
            protected BigDecimal doInBackground() throws Exception 
            {
                if(model.getRowCount() > 0 && model.getTrand().isInit())
                {
                    try {
                          return  MathPart.calcSeasonalityIndices(model.getTrand());
                    } catch (Exception ex) {
                        showWarningMsg("Error: "+ex.getMessage());
                    }
                }
                return null;
            }
            
            protected void done()
            {
                try {    
                    model2.load(get());
                    thirdTable.setModel(model2);
                    thirdTable.clearSelection();
                    thirdTable.revalidate();
                    thirdTable.repaint();
                    manuItemPlotSeasonalFluctuations.setEnabled(true);
                } catch (InterruptedException | ExecutionException ex) {
                    showWarningMsg("Error: "+ex.getMessage());
                } 
            }
         };

        worker.execute();  
    }//GEN-LAST:event_menuItemSolution4ActionPerformed

    private void manuItemPlotSeasonalFluctuationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuItemPlotSeasonalFluctuationsActionPerformed
            ShowChart dlgChart = new ShowChart(this, false);
            dlgChart.showBarChart(); 
    }//GEN-LAST:event_manuItemPlotSeasonalFluctuationsActionPerformed

    private void mainTabPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mainTabPaneStateChanged
        System.out.println("Tab: " + mainTabPane.getSelectedIndex());

        if(mainTabPane.getSelectedIndex() == 3){
            numberFreedom.setText(String.valueOf(secondTable.getModel().getRowCount()-1-2));
        }
    }//GEN-LAST:event_mainTabPaneStateChanged

    private void btnCourseForecastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCourseForecastActionPerformed
        AnalyticalAlignmentTableModel model = (AnalyticalAlignmentTableModel)secondTable.getModel();
        if(model.getRowCount() > 0 && model.getTrand().isInit())
        {
            try {
                
                LocalDate date = datePicker.getDate();
                LocalDate lastDate = LocalDate.parse(
                        model.getValueAt(model.getRowCount()-2, 0).toString(), DateTimeFormatter.ofPattern("dd.MM.yyy"));
                
                if(date != null && lastDate != null &&
                        lastDate.isBefore(date) && !confidenceCoefficient.getText().isEmpty())
                {
                    long days = ChronoUnit.DAYS.between(lastDate, date);
                    if(days > 0)
                    {
                        System.out.print("days = ");
                        System.out.println(days);
                        ResultCourseForecast rcf = MathPart.courseForecast(model.getTrand(), 
                                Double.valueOf(confidenceCoefficient.getText()), days);
                        timeValue.setText(rcf.getT().toString());
                        spotForecast.setText(rcf.getSpotForecast().toString());
                        rsdFromTrend.setText("±"+rcf.getRSDFromTrend().toString());
                        resultForecast.setText("При введенном коэффициенте доверия по распределению Стьюдента "+
                                "в соответствии с вычисленной степенью свободы и выбранной вероятностью, можно "+
                                "утверждать,  что  курс  валюты на дату "+date.format(DateTimeFormatter.ofPattern("dd.MM.yyy"))+" будет находится в диапазоне "+
                                "от "+rcf.getX1().toString()+" до "+rcf.getX2().toString()+" денежных единиц.");
                    }
                }
            } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }//GEN-LAST:event_btnCourseForecastActionPerformed

    private void menuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAboutActionPerformed
        ShowAbout dlg = new ShowAbout(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_menuItemAboutActionPerformed

    public static void showWarningMsg(String text)
    {
        JOptionPane optionPane = new JOptionPane(text, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    //
    public void loadDataRestApi(LocalDate first, LocalDate second, String type) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception 
    {
        SolutionService dao = new SolutionService();
        AnalyticalAlignmentService analyticalService = new AnalyticalAlignmentService();
        SeasonalityIndicesService  seasonalityService = new SeasonalityIndicesService();
        for (LocalDate date = first; date.isBefore(second.plusDays(1)); date = date.plusDays(1))
        {
            String str = date.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString();
             
            URL bank = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode="+type+"&date="+str);
            URLConnection yc = bank.openConnection();
            Scanner scanner = new Scanner(new InputStreamReader(yc.getInputStream()));
            String xml = new String();
            while (scanner.hasNextLine()) {
                xml += scanner.nextLine();
            }
            scanner.close();
            
            InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(stream);
                    
                    
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("currency");

            for (int temp = 0; temp < nList.getLength(); temp++) 
            {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                        Element eElement = (Element) nNode;
                        
                        Solution solItems = new Solution();
                        solItems.setCourseDate(LocalDate.parse(eElement.getElementsByTagName("exchangedate").item(0).getTextContent(),
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                        solItems.setCourse(new BigDecimal(String.valueOf(eElement.getElementsByTagName("rate").item(0).getTextContent())));
                        dao.saveSolution(solItems);
                        
                        AnalyticalAlignment analyticalItems = new AnalyticalAlignment();
                        analyticalItems.setCourseDate(LocalDate.parse(eElement.getElementsByTagName("exchangedate").item(0).getTextContent(),
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                        analyticalItems.setCourse(new BigDecimal(String.valueOf(eElement.getElementsByTagName("rate").item(0).getTextContent())));
                        analyticalService.saveAnalyticalAlignment(analyticalItems);
                        
                        SeasonalityIndices seasonalityItems = new SeasonalityIndices();
                        seasonalityItems.setCourseDate(LocalDate.parse(eElement.getElementsByTagName("exchangedate").item(0).getTextContent(),
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                        seasonalityItems.setCourse(new BigDecimal(String.valueOf(eElement.getElementsByTagName("rate").item(0).getTextContent())));
                        seasonalityService.saveSeasonalityIndices(seasonalityItems);
                }
            }
         } 
    }
    
    void setWaitingState (boolean waitingState) 
    {
        boolean enabled = !waitingState;
           
        Component parent = getParent ();
        Component rootPane = getRootPane ();
        if (parent != null) {
            parent.setEnabled (enabled);
        }
        if (rootPane != null) {
            if (enabled) {
                rootPane.setCursor (null);
            } else {
                rootPane.setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
            }
        }
    }
    
    public static void main(String args[]) 
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCourseForecast;
    private javax.swing.JButton btnDBConnection;
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JTextField confidenceCoefficient;
    private javax.swing.JMenuItem contextItemMenuSolve;
    private javax.swing.JPopupMenu contextMenu;
    private com.github.lgooddatepicker.components.DatePicker datePicker;
    private javax.swing.JPanel firstPage;
    private javax.swing.JTable firstTable;
    private javax.swing.JPanel fourthPage;
    private javax.swing.JScrollPane fpScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenu mainMenu;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JTabbedPane mainTabPane;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JMenuItem manuItemPlotSeasonalFluctuations;
    private javax.swing.JMenu menuDataBase;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemAbout;
    private javax.swing.JMenuItem menuItemClearAllTable;
    private javax.swing.JMenuItem menuItemConnectDB;
    private javax.swing.JMenuItem menuItemGetDataRestApi;
    private javax.swing.JMenuItem menuItemOpenFile;
    private javax.swing.JMenuItem menuItemPlotCurrency;
    private javax.swing.JMenuItem menuItemSaveFile;
    private javax.swing.JMenuItem menuItemSolution;
    private javax.swing.JMenuItem menuItemSolution2;
    private javax.swing.JMenuItem menuItemSolution3;
    private javax.swing.JMenuItem menuItemSolution4;
    private javax.swing.JMenu menuPlotGraph;
    private javax.swing.JMenu menuSolution;
    private javax.swing.JTextField numberFreedom;
    private javax.swing.JProgressBar progress;
    private javax.swing.JTextArea resultForecast;
    private javax.swing.JTextField rsdFromTrend;
    private javax.swing.JPanel secondPage;
    private javax.swing.JTable secondTable;
    private javax.swing.JScrollPane spScrollPane;
    private javax.swing.JTextField spotForecast;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPanel thirdPage;
    private javax.swing.JTable thirdTable;
    private javax.swing.JTextField timeValue;
    private javax.swing.JScrollPane tpScrollPane;
    // End of variables declaration//GEN-END:variables
}
