package plt;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static plt.CreatLineChart.CreateNewLineChartForPng;

public class Plot {
    public void plot(List<String> categorie,List<Double> doubleList ,String fileName,String chartTitle,String xLabel, String yLabel){
        try {
//            List<String> categorie = null;
            Double[] doubles = new Double[doubleList.size()];
            int cnt = 0;
            for (double d: doubleList)
                doubles[cnt++] = d;
            List<Serie> series = null;
            //横坐标
//            String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
//            categorie = Arrays.asList(categories);
            series = new Vector<Serie>();
            // 柱子名称：柱子所有的值集合
            //纵坐标
//            Double[] doubles1 = new Double[] { 49.9,50.0,41.0,52.0,54.0,57.0,54.0,53.2,47.6 ,54.0,53.2,47.6 };
            series.add(new Serie("Player Name", doubles));

            ChartPanel chartPanel = new CreatLineChart().createChart(fileName, xLabel, yLabel, categorie,series);
//            frame.getContentPane().add(chartPanel);
//            frame.setVisible(true);
            //将图片保存为png格式
            //saveAsFile(chartPanel.getChart(),"D:\\1\\lol2.png",900,500);
            CreateNewLineChartForPng(chartTitle, xLabel, yLabel, "img\\output\\" + fileName, categorie, series, 900, 500);


            new gui.Plot(fileName).setVisible(true);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //swing 运行
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建图形
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
