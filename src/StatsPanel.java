import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatsPanel extends JPanel
{
    DataController datacontroller;

    public StatsPanel(DataController dataController)
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.datacontroller = dataController;
        String[][] data = dataController.getData();

        double avgAudienceScore, avgRottenTomatoesScore, avgRevenue;
        int totalFilms = data.length;


        ArrayList<Double> audienceScores = new ArrayList<>();
        ArrayList<Double> RottenTomatoesScore = new ArrayList<>();
        ArrayList<Double> Revenue = new ArrayList<>();


        for (int i = 0; i < data.length; i++) {
            audienceScores.add(Double.parseDouble(data[i][3]));
            RottenTomatoesScore.add(Double.parseDouble(data[i][5]));
            Revenue.add(Double.parseDouble(data[i][6].substring(1)));
        }

        avgAudienceScore = audienceScores.stream().reduce(0.0, Double::sum) / totalFilms;
        avgRottenTomatoesScore = RottenTomatoesScore.stream().reduce(0.0, Double::sum) / totalFilms;
        avgRevenue = Revenue.stream().reduce(0.0, Double::sum) / totalFilms;


       JLabel audienceLabel = new JLabel("Average Audience Score:  " + Math.round(avgAudienceScore * 100.0) / 100.0 + "/100");
       audienceLabel.setFont(new Font("Ariel",Font.PLAIN, 24));

       JLabel rtLabel = new JLabel("Average RT Score:  " +  Math.round(avgRottenTomatoesScore * 100.0) / 100.0+ "/100");
       rtLabel.setFont(new Font("Ariel",Font.PLAIN, 24));

       JLabel revenueLabel = new JLabel("Average Gross Revenue (millions):  $" +  Math.round(avgRevenue * 100.0) / 100.0);
       revenueLabel.setFont(new Font("Ariel",Font.PLAIN, 24));

        this.add(audienceLabel);
        this.add(rtLabel);
        this.add(revenueLabel);

    }
}
