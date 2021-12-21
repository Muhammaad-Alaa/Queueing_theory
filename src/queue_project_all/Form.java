/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue_project_all;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Mohammed Mashal
 */

public class Form extends javax.swing.JFrame {

    /**
     * Creates new form Form
    
     */
    public Form(String title) {
    super(title);
    // Create dataset
    XYDataset dataset = createDataset();
    // Create chart
    JFreeChart chart = ChartFactory.createXYStepChart(
        "XY Step Chart", // Chart title
        "X-Axis", // X-Axis Label
        "Y-Axis", // Y-Axis Label
        dataset
        );
    XYPlot plot=(XYPlot)chart.getPlot();
    NumberAxis domain = new NumberAxis("xaxis");
    domain.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    plot.setDomainAxis(domain);
    plot.setBackgroundPaint(new Color(229, 150, 97, 60));
    
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }
  public XYDataset createDataset() {
     XYSeriesCollection dataset = new XYSeriesCollection();

     XYSeries series1 = new XYSeries("Series1");
     series1.add(2, 4);
     series1.add(3, 6);
     series1.add(5, 2);
     series1.add(8, 5);
     series1.add(1, 8);
   
     // Add series to dataset
     dataset.addSeries(series1);
     return dataset;
  }
    Border default_border = BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(46,49,49));
    Border yellow_border = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.YELLOW);
    public double fact(double x){
         double sum=1;
         for(int i = 1 ; i <= x ; i++)  sum*=i;
     return sum;
     }
public double p0_mck(double r, double p, double c, double k, double arrival, double service) {
        if (p == 1) {
            double sum = 0;
            for (int n = 0; n < c; n++) {
                sum += ((Math.pow(r, n)) / (fact(n)));
            }
            return 1 / (sum + (((Math.pow(r, c)) / ((fact(c)))) * (k - c + 1)));
        } else {
            double sum = 0;
            for (int n = 0; n < c; n++) {
                sum += ((Math.pow(r, n)) / (fact(n)));
            }
            return 1 / (sum + (((Math.pow(r, c)) / ((fact(c)))) * ((1 - Math.pow(p, k - c + 1)) / (1 - p))));
        }
    }

    public double pn_mck(double r, double n, double p, double c, double k, double arrival, double service) {
        if (n >= 0 && n < c) {
            return ((Math.pow(r, n)) / (fact(n))) * (p0_mck(r, p, c, k, arrival, service));
        } else {
            return ((Math.pow(r, n)) / ((Math.pow(c, n - c)) * fact(c))) * (p0_mck(r, p, c, k, arrival, service));
        }
    }
         public double p0_s3(double r ,double p , double c , double arrival , double service ){
        if(p<1){
            double sum = 0;
            for(int n=0 ;n<c; n++){
               sum += ((Math.pow(r,n))/(fact(n))); 
            }
            return 1/(sum+((c*(Math.pow(r,c)))/((fact(c))*(c-r))));
        }else{
            double sum = 0;
            for(int n=0 ;n<c; n++){
               sum += ((1/fact(n))*(Math.pow(r,n)));
            }
            return 1/(sum+((1/fact(c))*(Math.pow(r,c))*((c*service) / (c*service - arrival))));
        }
    }
     public void setLabelBackround(JLabel label){
         for(JLabel menulabel : menuitems){
             menulabel.setBackground(new Color(46,49,49));
             menulabel.setForeground(Color.white);
         }
         label.setBackground(Color.white);
         label.setForeground(Color.blue);
     }
     JLabel[] menuitems = new JLabel[5];
     JPanel[] menupanels = new JPanel[5];
    public Form() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setBackground(Color.lightGray);
        MatteBorder panelBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY);
        jPanel5.setBorder(panelBorder);
        menuitems[0]=jlabelD;
        menuitems[1]=jlabelM1;
        menuitems[2]=jLabelM1k;
        menuitems[3]=jLabelMc;
        menuitems[4]=jLabelMck;
        menupanels[0]=jPanel_d;
        menupanels[1]=jPanel_mm1;
        menupanels[2]=jPanel_mm1k;
        menupanels[3]=jPanel_mmc;
        menupanels[4]=jPanel_mmck;
        for(JPanel menupanel : menupanels ){
            menupanel.setVisible(false);
        }
        addActionToMenuLables();
    }
    public void showPanel(JPanel panel){
        jPanel4.setVisible(false);
        for(JPanel menupanel : menupanels ){
            menupanel.setVisible(false);
        }
        panel.setVisible(true);
    }
    public void addActionToMenuLables(){
        Component[] components = jPanel2.getComponents();
        for(Component compmonet : components){
            if(compmonet instanceof JLabel){
                JLabel labe1 = (JLabel)compmonet;
                labe1.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                    setLabelBackround(labe1);   
                    switch (labe1.getText().trim()){
                        case "D/D/1/k-1" : showPanel(jPanel_d);
                                           txt13.setText("");
                                           txt14.setText("");
                                           txt15.setText("");
                                           txt16.setText("");
                                           txt17.setText("");
                                           txt18.setText("");
                                           lbl_l4.setVisible(false);
                                           lbl_wq4.setVisible(false);
                                           number5.setText("");
                                           waiting5.setText("");
                                           break;
                        case "M/M/1"     : showPanel(jPanel_mm1); 
                                           lbl_l3.setVisible(false);
                                           lbl_lq3.setVisible(false);
                                           lbl_w3.setVisible(false);
                                           lbl_wq3.setVisible(false);
                                           txt11.setText("");
                                           txt12.setText("");
                                           number1.setText("");
                                           numberQ1.setText("");
                                           waitingSystem1.setText("");
                                           waitingQ1.setText("");
                                           break;
                        case "M/M/1/k"   : showPanel(jPanel_mm1k);
                                           txt8.setText("");
                                           txt9.setText("");
                                           txt10.setText("");
                                           number.setText("");
                                           numberQ.setText("");
                                           waitingSystem.setText("");
                                           waitingQ.setText("");
                                           lbl_l2.setVisible(false);
                                           lbl_lq2.setVisible(false);
                                           lbl_w2.setVisible(false);
                                           lbl_wq2.setVisible(false);
                                           break;
                        case "M/M/c"     : showPanel(jPanel_mmc);
                                           txt5.setText("");
                                           txt6.setText("");
                                           txt7.setText("");
                                           lbl_l1.setVisible(false);
                                           lbl_lq1.setVisible(false);
                                           lbl_w1.setVisible(false);
                                           lbl_wq1.setVisible(false);
                                           lbl5.setText("");
                                           lbl6.setText("");
                                           lbl7.setText("");
                                           lbl8.setText("");
                                           break;
                        case "M/M/c/k"   : showPanel(jPanel_mmck);
                                           txt1.setText("");
                                           txt2.setText("");
                                           txt3.setText("");
                                           txt4.setText("");
                                           lbl_l.setVisible(false);
                                           lbl_lq.setVisible(false);
                                           lbl_w.setVisible(false);
                                           lbl_wq.setVisible(false);
                                           lbl1.setText("");
                                           lbl2.setText("");
                                           lbl3.setText("");
                                           lbl4.setText("");
                                           break;
                    }
                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                   labe1.setBorder(yellow_border);
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                        labe1.setBorder(default_border);
                   }
                });
            }
        }
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlabelD = new javax.swing.JLabel();
        jlabelM1 = new javax.swing.JLabel();
        jLabelM1k = new javax.swing.JLabel();
        jLabelMc = new javax.swing.JLabel();
        jLabelMck = new javax.swing.JLabel();
        jPanel_mm1 = new javax.swing.JPanel();
        btn4 = new javax.swing.JButton();
        txt12 = new javax.swing.JTextField();
        lbl_w3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_wq3 = new javax.swing.JLabel();
        lbl_l3 = new javax.swing.JLabel();
        waitingQ1 = new javax.swing.JLabel();
        number1 = new javax.swing.JLabel();
        lbl_lq3 = new javax.swing.JLabel();
        waitingSystem1 = new javax.swing.JLabel();
        numberQ1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt11 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel_mm1k = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txt9 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lbl_l2 = new javax.swing.JLabel();
        waitingQ = new javax.swing.JLabel();
        number = new javax.swing.JLabel();
        lbl_lq2 = new javax.swing.JLabel();
        waitingSystem = new javax.swing.JLabel();
        numberQ = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt8 = new javax.swing.JTextField();
        btn3 = new javax.swing.JButton();
        lbl_w2 = new javax.swing.JLabel();
        lbl_wq2 = new javax.swing.JLabel();
        txt10 = new javax.swing.JTextField();
        jPanel_mmck = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        lbl_wq = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        lbl4 = new javax.swing.JLabel();
        btn0 = new javax.swing.JButton();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        lbl_l = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl_lq = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl_w = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel_mmc = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        txt7 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lbl_l1 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl_lq1 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        lbl_w1 = new javax.swing.JLabel();
        lbl_wq1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        jPanel_d = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txt15 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txt16 = new javax.swing.JTextField();
        lbl_l4 = new javax.swing.JLabel();
        number5 = new javax.swing.JLabel();
        txt13 = new javax.swing.JTextField();
        lbl_wq4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt14 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        waiting5 = new javax.swing.JLabel();
        txt17 = new javax.swing.JTextField();
        txt18 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(772, 405));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(635, 237));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel1.setText("WELCOME !");

        jLabel2.setText("Choose any of these models you want");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1))
                    .addComponent(jLabel2))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(49, 49, 49));

        jPanel5.setBackground(new java.awt.Color(49, 49, 49));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 102));
        jLabel6.setText("     Queue Models");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(49, 49, 49));

        jlabelD.setBackground(new java.awt.Color(46, 49, 49));
        jlabelD.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jlabelD.setForeground(new java.awt.Color(255, 255, 255));
        jlabelD.setText("   D/D/1/k-1");
        jlabelD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlabelD.setOpaque(true);

        jlabelM1.setBackground(new java.awt.Color(46, 49, 49));
        jlabelM1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jlabelM1.setForeground(new java.awt.Color(255, 255, 255));
        jlabelM1.setText("   M/M/1");
        jlabelM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlabelM1.setOpaque(true);

        jLabelM1k.setBackground(new java.awt.Color(46, 49, 49));
        jLabelM1k.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabelM1k.setForeground(new java.awt.Color(255, 255, 255));
        jLabelM1k.setText("   M/M/1/k");
        jLabelM1k.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelM1k.setOpaque(true);

        jLabelMc.setBackground(new java.awt.Color(46, 49, 49));
        jLabelMc.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabelMc.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMc.setText("   M/M/c");
        jLabelMc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMc.setOpaque(true);

        jLabelMck.setBackground(new java.awt.Color(46, 49, 49));
        jLabelMck.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabelMck.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMck.setText("   M/M/c/k");
        jLabelMck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMck.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlabelD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlabelM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelM1k, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelMc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelMck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jlabelD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelM1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelM1k, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelMc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelMck, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        btn4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btn4.setText("Calculate");
        btn4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        txt12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt12.setPreferredSize(new java.awt.Dimension(2, 18));

        lbl_w3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_w3.setText("W = ");

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setText("Service Rate(μ)=");

        lbl_wq3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_wq3.setText("Wq = ");

        lbl_l3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_l3.setText("L = ");

        waitingQ1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        number1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        lbl_lq3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_lq3.setText("Lq = ");

        waitingSystem1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        numberQ1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setText("Arrival Rate(λ)=");

        txt11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton4.setText("Reset");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_mm1Layout = new javax.swing.GroupLayout(jPanel_mm1);
        jPanel_mm1.setLayout(jPanel_mm1Layout);
        jPanel_mm1Layout.setHorizontalGroup(
            jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mm1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mm1Layout.createSequentialGroup()
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_l3)
                            .addComponent(lbl_lq3))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberQ1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(number1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_w3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_wq3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(waitingQ1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(waitingSystem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_mm1Layout.createSequentialGroup()
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mm1Layout.createSequentialGroup()
                                .addComponent(txt11, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mm1Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mm1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(txt12, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addGap(68, 68, 68)))
                .addGap(81, 81, 81))
        );
        jPanel_mm1Layout.setVerticalGroup(
            jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mm1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt11, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt12, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_mm1Layout.createSequentialGroup()
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(number1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl_l3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_lq3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(numberQ1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(jPanel_mm1Layout.createSequentialGroup()
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(waitingSystem1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl_w3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_wq3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(waitingQ1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))))
                .addGap(81, 81, 81))
        );

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setText("Arrival Rate(λ)=");

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton3.setText("Reset");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txt9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt9.setPreferredSize(new java.awt.Dimension(2, 18));

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel19.setText("Service Rate(μ)=");

        lbl_l2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_l2.setText("L = ");

        waitingQ.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        number.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        lbl_lq2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_lq2.setText("Lq = ");

        waitingSystem.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        numberQ.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setText("System Capicty(k)=");

        txt8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btn3.setText("Calculate");
        btn3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        lbl_w2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_w2.setText("W = ");

        lbl_wq2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_wq2.setText("Wq = ");

        txt10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt10.setPreferredSize(new java.awt.Dimension(2, 18));

        javax.swing.GroupLayout jPanel_mm1kLayout = new javax.swing.GroupLayout(jPanel_mm1k);
        jPanel_mm1k.setLayout(jPanel_mm1kLayout);
        jPanel_mm1kLayout.setHorizontalGroup(
            jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mm1kLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mm1kLayout.createSequentialGroup()
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_l2)
                            .addComponent(lbl_lq2))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_w2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_wq2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(waitingQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(waitingSystem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_mm1kLayout.createSequentialGroup()
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mm1kLayout.createSequentialGroup()
                                .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(txt9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt8, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mm1kLayout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)))))
                .addGap(81, 81, 81))
        );
        jPanel_mm1kLayout.setVerticalGroup(
            jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mm1kLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt8, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt9, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt10, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_mm1kLayout.createSequentialGroup()
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl_l2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_lq2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(numberQ, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(jPanel_mm1kLayout.createSequentialGroup()
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(waitingSystem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl_w2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mm1kLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_wq2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(waitingQ, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))))
                .addGap(81, 81, 81))
        );

        lbl3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txt1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lbl_wq.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_wq.setText("Wq = ");

        txt2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt2.setPreferredSize(new java.awt.Dimension(2, 18));

        lbl4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        btn0.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btn0.setText("Calculate");
        btn0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        txt3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt3.setPreferredSize(new java.awt.Dimension(2, 18));

        txt4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt4.setPreferredSize(new java.awt.Dimension(2, 18));

        lbl_l.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_l.setText("L = ");

        lbl1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        lbl_lq.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_lq.setText("Lq = ");

        lbl2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        lbl_w.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_w.setText("W = ");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Arrival Rate(λ)=");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel13.setText("Service Rate(μ)=");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setText("Number of Servers(c)=");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setText("System Capicty(k)=");

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton1.setText("Reset");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_mmckLayout = new javax.swing.GroupLayout(jPanel_mmck);
        jPanel_mmck.setLayout(jPanel_mmckLayout);
        jPanel_mmckLayout.setHorizontalGroup(
            jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mmckLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mmckLayout.createSequentialGroup()
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_l)
                            .addComponent(lbl_lq))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_mmckLayout.createSequentialGroup()
                                .addComponent(lbl_wq)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_mmckLayout.createSequentialGroup()
                                .addComponent(lbl_w)
                                .addGap(18, 18, 18)
                                .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_mmckLayout.createSequentialGroup()
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mmckLayout.createSequentialGroup()
                                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mmckLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)))))
                .addGap(81, 81, 81))
        );
        jPanel_mmckLayout.setVerticalGroup(
            jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mmckLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt4, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btn0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_mmckLayout.createSequentialGroup()
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbl_l, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_lq, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                    .addGroup(jPanel_mmckLayout.createSequentialGroup()
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbl_w, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mmckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_wq, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setText("Number of Servers(c)=");

        btn2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btn2.setText("Calculate");
        btn2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        txt7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt7.setPreferredSize(new java.awt.Dimension(2, 18));

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton2.setText("Reset");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbl_l1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_l1.setText("L = ");

        lbl5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        lbl_lq1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_lq1.setText("Lq = ");

        lbl6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        lbl7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txt5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lbl_w1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_w1.setText("W = ");

        lbl_wq1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_wq1.setText("Wq = ");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setText("Arrival Rate(λ)=");

        txt6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt6.setPreferredSize(new java.awt.Dimension(2, 18));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel18.setText("Service Rate(μ)=");

        lbl8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel_mmcLayout = new javax.swing.GroupLayout(jPanel_mmc);
        jPanel_mmc.setLayout(jPanel_mmcLayout);
        jPanel_mmcLayout.setHorizontalGroup(
            jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mmcLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mmcLayout.createSequentialGroup()
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_l1)
                            .addComponent(lbl_lq1))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_w1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_wq1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_mmcLayout.createSequentialGroup()
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mmcLayout.createSequentialGroup()
                                .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(txt6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt5, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mmcLayout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)))))
                .addGap(81, 81, 81))
        );
        jPanel_mmcLayout.setVerticalGroup(
            jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mmcLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt5, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt6, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_mmcLayout.createSequentialGroup()
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl_l1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_lq1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(jPanel_mmcLayout.createSequentialGroup()
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl_w1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_mmcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_wq1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))))
                .addGap(81, 81, 81))
        );

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel21.setText("Time(t)=");

        btn1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btn1.setText("Calculate");
        btn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel22.setText("System Capicty(k)=");

        txt15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt15.setPreferredSize(new java.awt.Dimension(2, 18));

        jButton5.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton5.setText("Reset");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txt16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt16.setPreferredSize(new java.awt.Dimension(2, 18));

        lbl_l4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_l4.setText("N(t)=");

        number5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txt13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt13ActionPerformed(evt);
            }
        });

        lbl_wq4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lbl_wq4.setText("Wq = ");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setText("Arrival Rate(λ)=");

        txt14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt14.setPreferredSize(new java.awt.Dimension(2, 18));

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel23.setText("Service Rate(μ)=");

        waiting5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txt17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt17.setPreferredSize(new java.awt.Dimension(2, 18));

        txt18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txt18.setPreferredSize(new java.awt.Dimension(2, 18));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Number of Customer(n)=");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("Intial Customers(M)=");

        jButton6.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton6.setText("Draw");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_dLayout = new javax.swing.GroupLayout(jPanel_d);
        jPanel_d.setLayout(jPanel_dLayout);
        jPanel_dLayout.setHorizontalGroup(
            jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_dLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_dLayout.createSequentialGroup()
                        .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel_dLayout.createSequentialGroup()
                        .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_dLayout.createSequentialGroup()
                                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt14, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt15, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt16, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt13, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt17, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt18, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel_dLayout.createSequentialGroup()
                                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_dLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(lbl_l4)
                                        .addGap(18, 18, 18)
                                        .addComponent(number5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(lbl_wq4))
                                    .addGroup(jPanel_dLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(waiting5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 98, Short.MAX_VALUE))))
        );
        jPanel_dLayout.setVerticalGroup(
            jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_dLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt13, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt16, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt18, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_dLayout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel_dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_l4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(number5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_wq4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(waiting5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 300, Short.MAX_VALUE)
                    .addComponent(jPanel_mm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 300, Short.MAX_VALUE)
                    .addComponent(jPanel_mm1k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 305, Short.MAX_VALUE)
                    .addComponent(jPanel_mmck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 299, Short.MAX_VALUE)
                    .addComponent(jPanel_mmc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 286, Short.MAX_VALUE)
                    .addComponent(jPanel_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_mm1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_mm1k, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_mmck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_mmc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object a = engine.eval(txt1.getText());
            Object b = engine.eval(txt2.getText());
            Object c = engine.eval(txt3.getText());
            Object d = engine.eval(txt4.getText());
            String y = String.valueOf(a);
            String z = String.valueOf(b);
            String x = String.valueOf(c);
            String xx = String.valueOf(d);
            
            
            if(y=="null"||z=="null"||xx=="null"||x=="null"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(y=="Infinity"||z=="Infinity"||x=="Infinity"||xx=="Infinity"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                double y1 = Double.valueOf(y);
                double y2 = Double.valueOf(z);
                double y3 = Double.valueOf(x);
                double y4 = Double.valueOf(xx);
                    if(y1<=0||y2<=0||y3<=0||y4<=0){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    else{
                double Lambda = Double.valueOf(y);
                double Mue = Double.valueOf(z);
                double r = Lambda/Mue;
                double Row = r/Double.valueOf(x);
                double cc= Double.valueOf(x);
                double k = Double.valueOf(xx);
                double Lq=((Row*Math.pow(r,cc)*p0_mck(r,Row,cc,k,Lambda,Mue))/(fact(cc)*Math.pow((1-Row),2)))*(1-(Math.pow(Row,k-cc+1))-((1-Row)*(k-cc+1)*(Math.pow(Row,k-cc))));
                double summ=0;
                for(int i=0;i<=cc-1;i++){
                    summ+=((cc-i)*((Math.pow(r, i))/fact(i)));
                }
                double L = Lq+cc-p0_mck(r,Row,cc,k,Lambda,Mue)*(summ);
                double pk=((Math.pow(Lambda, k))/(Math.pow(cc, k))*(fact(cc))*(Math.pow(Mue, k)))*p0_mck(r,Row,cc,k,Lambda,Mue);
                double Lambda_dash=Lambda*(1-pn_mck(r, k, Row, cc, k, Lambda, Mue));
                double W = L/Lambda_dash;
                double Wq=Lq/Lambda_dash;
                L=Math.round(L * 100.0) / 100.0;
                Lq=Math.round(Lq * 100.0) / 100.0;
                W=Math.round(W * 100.0) / 100.0;
                Wq=Math.round(Wq * 100.0) / 100.0;
                lbl_l.setVisible(true);
                lbl_lq.setVisible(true);
                lbl_w.setVisible(true);
                lbl_wq.setVisible(true);
                lbl1.setText(String.valueOf(L));
                lbl2.setText(String.valueOf(Lq));
                lbl3.setText(String.valueOf(W));
                lbl4.setText(String.valueOf(Wq));
            }
           }
        } catch (ScriptException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn0ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        lbl1.setText("");
        lbl2.setText("");
        lbl3.setText("");
        lbl4.setText(""); 
        lbl_l.setVisible(false);
        lbl_lq.setVisible(false);
        lbl_w.setVisible(false);
        lbl_wq.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object result = engine.eval(txt5.getText());
            Object result1 = engine.eval(txt6.getText());
            Object time = engine.eval(txt7.getText());
            String arr = String.valueOf(result);
            String ser = String.valueOf(result1);
            String nser = String.valueOf(time);
            if(arr=="null"||ser=="null"||nser=="null"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(arr=="Infinity"||ser=="Infinity"||nser=="Infinity"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                double y1 = Double.valueOf(arr);
                double y2 = Double.valueOf(ser);
                double y3 = Double.valueOf(nser);
                    if(y1<=0||y2<=0||y3<=0){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    else{
                double r_m = Double.valueOf(arr)/Double.valueOf(ser);
                    double row_m = r_m/Double.valueOf(nser);
                    double arriver_m = Double.valueOf(arr);
                    double service_m = Double.valueOf(ser);
                    double nService_m = Double.valueOf(nser);
                    
                    double p0_m = p0_s3(r_m,row_m,Double.valueOf(nser),Double.valueOf(arr),Double.valueOf(ser)) ;
                    double Lq_m = (((Math.pow(r_m,nService_m))*arriver_m*service_m)/((fact(nService_m-1))*Math.pow(nService_m*service_m-arriver_m,2)))*p0_m;
                    double L_m = Lq_m +r_m ;
                    double W_m = (Lq_m/arriver_m)+(1/service_m) ;
                    double Wq_m = Lq_m/arriver_m;
                L_m=Math.round(L_m * 100.0) / 100.0;
                Lq_m=Math.round(Lq_m * 100.0) / 100.0;
                W_m=Math.round(W_m * 100.0) / 100.0;
                Wq_m=Math.round(Wq_m * 100.0) / 100.0;
                lbl_l1.setVisible(true);
                lbl_lq1.setVisible(true);
                lbl_w1.setVisible(true);
                lbl_wq1.setVisible(true);
                lbl5.setText(String.valueOf(L_m));
                lbl7.setText(String.valueOf(Lq_m));
                lbl6.setText(String.valueOf(W_m));
                lbl8.setText(String.valueOf(Wq_m));
            }
           }
        } catch (ScriptException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");
        lbl5.setText("");
        lbl6.setText("");
        lbl7.setText("");
        lbl8.setText("");
        lbl_l1.setVisible(false);
        lbl_lq1.setVisible(false);
        lbl_w1.setVisible(false);
        lbl_wq1.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        txt8.setText("");
        txt9.setText("");
        txt10.setText("");
        waitingQ.setText("");
        number.setText("");
        waitingSystem.setText("");
        numberQ.setText("");
        lbl_l2.setVisible(false);
        lbl_lq2.setVisible(false);
        lbl_w2.setVisible(false);
        lbl_wq2.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object r_m1k = engine.eval(txt8.getText());
            Object s_m1k = engine.eval(txt9.getText());
            Object t_m1k = engine.eval(txt10.getText());
            String ar = String.valueOf(r_m1k);
            String wa = String.valueOf(s_m1k);
            String tim = String.valueOf(t_m1k);
            if(ar=="null"||wa=="null"||tim=="null"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(ar=="Infinity"||wa=="Infinity"||tim=="Infinity"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                double y1 = Double.valueOf(ar);
                double y2 = Double.valueOf(wa);
                double y3 = Double.valueOf(tim);
                    if(y1<=0||y2<=0||y3<=0){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    else{
                if(Double.valueOf(ar)/Double.valueOf(wa)==1){
                    double pn_m1k=(1/(Double.valueOf(tim)+1));
                    double L_m1k =(Double.valueOf(tim)/2) ;
                    double lamda2_m1k =Double.valueOf(ar)*(1-pn_m1k);
                    double Lq_m1k = L_m1k-(lamda2_m1k/Double.valueOf(wa));
                    double W_m1k = L_m1k/lamda2_m1k;
                    double Wq_m1k= W_m1k-(1/Double.valueOf(wa));
                    number.setText(String.valueOf((int)(Math.round(L_m1k * 100))/100.0));
                    numberQ.setText(String.valueOf((int)(Math.round(Lq_m1k * 100))/100.0));
                    waitingSystem.setText(String.valueOf((int)(Math.round(W_m1k * 100))/100.0));
                    waitingQ.setText(String.valueOf((int)(Math.round(Wq_m1k * 100))/100.0));
                    lbl_l2.setVisible(true);
                    lbl_lq2.setVisible(true);
                    lbl_w2.setVisible(true);
                    lbl_wq2.setVisible(true);
                }
                    else {
                    double Row_m1k=Double.valueOf(ar)/Double.valueOf(wa);
                    double pn_m1k=Math.pow(Row_m1k,Double.valueOf(tim))*((1-Row_m1k)/(1-Math.pow(Row_m1k,(Double.valueOf(tim)+1))));
                    double L_m1k =Row_m1k*((1-(Double.valueOf(tim)+1)*Math.pow(Row_m1k,Double.valueOf(tim))+Double.valueOf(tim)*Math.pow(Row_m1k,(Double.valueOf(tim)+1)))/ ((1-Row_m1k)*(1-Math.pow(Row_m1k,(Double.valueOf(tim)+1)))));
                    double lamda2_m1k =Double.valueOf(ar)*(1-pn_m1k);
                    double Lq_m1k = L_m1k-(lamda2_m1k/Double.valueOf(wa));
                    double W_m1k = L_m1k/lamda2_m1k;
                    double Wq_m1k= W_m1k-(1/Double.valueOf(wa));
                    number.setText(String.valueOf((int)(Math.round(L_m1k * 100))/100.0));
                    numberQ.setText(String.valueOf((int)(Math.round(Lq_m1k * 100))/100.0));
                    waitingSystem.setText(String.valueOf((int)(Math.round(W_m1k * 100))/100.0));
                    waitingQ.setText(String.valueOf((int)(Math.round(Wq_m1k * 100))/100.0));
                    lbl_l2.setVisible(true);
                    lbl_lq2.setVisible(true);
                    lbl_w2.setVisible(true);
                    lbl_wq2.setVisible(true);
                }
            }
           }
        } catch (ScriptException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object a = engine.eval(txt11.getText());
            Object b = engine.eval(txt12.getText());
            String y = String.valueOf(a);
            String z = String.valueOf(b);
            
            if(y=="null"||z=="null"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(y=="Infinity"||z=="Infinity"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                double ar = Double.valueOf(y);
                double wa = Double.valueOf(z);
   
                    if(ar<=0||wa<=0){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    else{
                double L = (ar) / ((wa - ar));
                double Lq = (Math.pow(ar, 2)) / (wa * (wa - ar));
                double W = (1 / (wa - ar)) ;
                double x = wa - ar;
                double Wq = ar / (wa * x);
                L=Math.round(L * 100.0) / 100.0;
                Lq=Math.round(Lq * 100.0) / 100.0;
                W=Math.round(W * 100.0) / 100.0;
                Wq=Math.round(Wq * 100.0) / 100.0;
                lbl_l.setVisible(true);
                lbl_lq.setVisible(true);
                lbl_w.setVisible(true);
                lbl_wq.setVisible(true);
                number1.setText(String.valueOf(L));
                numberQ1.setText(String.valueOf(Lq));
                waitingSystem1.setText(String.valueOf(W));
                waitingQ1.setText(String.valueOf(Wq));
            }
           }
        } catch (ScriptException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txt11.setText("");
        txt12.setText("");
        lbl_l3.setVisible(false);
        lbl_lq3.setVisible(false);
        lbl_w3.setVisible(false);
        lbl_wq3.setVisible(false);
        number1.setText("");
        numberQ1.setText("");
        waitingSystem1.setText("");
        waitingQ1.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
        Pattern p = Pattern.compile("\\d{1,6}|\\d{1,6}+[/*.+-]+\\d{1,6}|\\d{1,6}+[/*.+-]+\\d{1,6}+[/*.+-]+\\d{1,6}");
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object result = engine.eval(txt13.getText());
            Object result1 = engine.eval(txt14.getText());
            Object time = engine.eval(txt15.getText());
            Object numbe = engine.eval(txt17.getText());
            Object capacty = engine.eval(txt16.getText());
            String r = String.valueOf(result);
            String s = String.valueOf(result1);
            String t = String.valueOf(time);
            String n = String.valueOf(numbe);
            String k = String.valueOf(capacty);
            
            
            if(r=="null"||s=="null"||t=="null"||n=="null"||k=="null"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(r=="Infinity"||s=="Infinity"||t=="Infinity"||n=="Infinity"||k=="Infinity"){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                double y1 = Double.valueOf(r);
                double y2 = Double.valueOf(s);
                double y3 = Double.valueOf(t);
                double y4 = Double.valueOf(n);
                double y5 = Double.valueOf(k);
                    if(y1<=0||y2<=0||y3<=0||y4<=0||y5<=0){
                JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    else{
                lbl_l4.setVisible(true);
                lbl_wq4.setVisible(true);
                double ar = Double.parseDouble(String.valueOf(result));
                double wa = Double.parseDouble(String.valueOf(result1));
                double timee = Double.parseDouble(String.valueOf(time));
                double numb = Double.parseDouble(String.valueOf(numbe));
                double ca = Double.parseDouble(String.valueOf(capacty));
                if (ar > wa) {
                    double ti = ((ca - (wa / ar) )/ (ar - wa));
                ti=Math.round(ti);
                for(int i=0;i<10;)
                {
                    if (ca <= Math.round((ti * ar) - ((ti*wa)-(wa/ar)))) {
                      ti -= (1 / ar);
                }
                    else {
                        break;
                    }
                }
                    
                    if (timee < 1 / ar) {
                        number5.setText("0");
                    } else if (timee > 1 / ar && timee < ti) {
                        number5.setText(String.valueOf((int)((ar * timee) - (wa * timee) + (wa / ar))));
                    } else if (timee >= ti) {
                        number5.setText(String.valueOf((int)ca - 1) + "/" + String.valueOf((int)ca - 2));
                    }
                    if (numb == 0) {
                        waiting5.setText("0");
                    } else if (numb < (ar * ti)) {
                        waiting5.setText(String.valueOf((int)(((1 / wa) - (1 / ar)) * (numb - 1))));
                    } else if (numb >= (ar * ti)) {
                        double wait1 = ((1 / wa) - (1 / ar)) * ((ar * ti) - 2);
                        double wait2 = ((1 / wa) - (1 / ar)) * ((ar * ti) - 3);
                        waiting5.setText(String.valueOf((int)wait1 + "/" + (int)wait2));
                    }
                }else if(ar<=wa)
                {
                     String m1=txt18.getText();
                     Matcher init=p.matcher(m1);
                     Object initia=engine.eval(m1);
                      double me = Double.parseDouble(String.valueOf(initia));
                    if( !init.matches() || initia.toString().matches("Infinity"))
                    {
                       JOptionPane.showMessageDialog(null, "Invalid input\nPlease Enter a valid input", "Error", JOptionPane.ERROR_MESSAGE);
                       txt18.setText("");
                    }
                  double ti=(me)/(wa-ar);
                  for(int i=0;i<10;)
                {
                    if (me <=(ti*wa)-(ti * ar)) {
                      ti -= (1 / ar);
                }
                    else {
                        break;
                    }
                }
                  ti=(int)ti;
                  if(timee<ti)
                  {
                      number5.setText(String.valueOf((int)(me+(ar*timee)-(wa*timee))));
                  }
                  else if(timee>=ti)
                  {
                      number5.setText("0/1");
                  }
                  
                  if(numb==0)
                  {
                      waiting5.setText(String.valueOf((int)((me-1)/(2*wa))));
                  }
                  else if(numb<(int)(ar*ti))
                  {
                      waiting5.setText(String.valueOf((int)(((me-1+numb)*(1/wa))-(numb*(1/ar)))));
                  }
                  else if(numb>=(int)(ar*ti))
                  {
                      waiting5.setText("0");
                  }
                }
            }
           }
        } catch (ScriptException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input\nPlease enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        txt13.setText("");
        txt14.setText("");
        txt15.setText("");
        txt16.setText("");
        txt17.setText("");
        txt18.setText("");
        lbl_l4.setVisible(false);
        lbl_wq4.setVisible(false);
        number5.setText("");
        waiting5.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt13ActionPerformed


    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    Form f = new Form("example");
    f.setVisible(true);
    f.setLocationRelativeTo(null);
    f.setSize(800, 400);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelM1k;
    private javax.swing.JLabel jLabelMc;
    private javax.swing.JLabel jLabelMck;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_d;
    private javax.swing.JPanel jPanel_mm1;
    private javax.swing.JPanel jPanel_mm1k;
    private javax.swing.JPanel jPanel_mmc;
    private javax.swing.JPanel jPanel_mmck;
    private javax.swing.JLabel jlabelD;
    private javax.swing.JLabel jlabelM1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl_l;
    private javax.swing.JLabel lbl_l1;
    private javax.swing.JLabel lbl_l2;
    private javax.swing.JLabel lbl_l3;
    private javax.swing.JLabel lbl_l4;
    private javax.swing.JLabel lbl_lq;
    private javax.swing.JLabel lbl_lq1;
    private javax.swing.JLabel lbl_lq2;
    private javax.swing.JLabel lbl_lq3;
    private javax.swing.JLabel lbl_w;
    private javax.swing.JLabel lbl_w1;
    private javax.swing.JLabel lbl_w2;
    private javax.swing.JLabel lbl_w3;
    private javax.swing.JLabel lbl_wq;
    private javax.swing.JLabel lbl_wq1;
    private javax.swing.JLabel lbl_wq2;
    private javax.swing.JLabel lbl_wq3;
    private javax.swing.JLabel lbl_wq4;
    private javax.swing.JLabel number;
    private javax.swing.JLabel number1;
    private javax.swing.JLabel number5;
    private javax.swing.JLabel numberQ;
    private javax.swing.JLabel numberQ1;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt10;
    private javax.swing.JTextField txt11;
    private javax.swing.JTextField txt12;
    private javax.swing.JTextField txt13;
    private javax.swing.JTextField txt14;
    private javax.swing.JTextField txt15;
    private javax.swing.JTextField txt16;
    private javax.swing.JTextField txt17;
    private javax.swing.JTextField txt18;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    private javax.swing.JTextField txt7;
    private javax.swing.JTextField txt8;
    private javax.swing.JTextField txt9;
    private javax.swing.JLabel waiting5;
    private javax.swing.JLabel waitingQ;
    private javax.swing.JLabel waitingQ1;
    private javax.swing.JLabel waitingSystem;
    private javax.swing.JLabel waitingSystem1;
    // End of variables declaration//GEN-END:variables
}
