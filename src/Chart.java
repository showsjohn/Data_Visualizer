import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Chart extends JPanel {

    String chartTitle;
    DataController dataController;
    public Chart(DataController dataController)
    {
        this.dataController = dataController;
        JFreeChart pieChart = ChartFactory.createPieChart("Most Common Genres", createDataset() );

        // Percentage label details code courtest of Stack Overflow
        // https://stackoverflow.com/questions/52314116/percentage-accuracy-issue-in-jfreechart-pie-chart
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        PiePlot plot = (PiePlot) pieChart.getPlot();


        ChartPanel chartPanel = new ChartPanel(pieChart);
        plot.setLabelGenerator(gen);
        this.add(chartPanel);
    }


    private PieDataset createDataset( ) {
        String[][] data = dataController.getData();
        ArrayList<String> genres = new ArrayList<>();


        for (int i = 0; i < data.length; i++) {
            genres.add(data[i][1]);

        }

        Map<String, Long> genreCount = genres.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        final DefaultPieDataset dataset =
                new DefaultPieDataset();

        for (var key: genreCount.keySet())
        {
            dataset.setValue(key, genreCount.get(key));
        }



        return dataset;
    }
}
