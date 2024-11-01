import javax.swing.*;
import java.awt.*;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws IOException {


        DataController dataController = new DataController(
                new CSVReader("src/movies.csv")
        );

        JFrame mainWindow = new JFrame();
        mainWindow.setSize(new Dimension(1280, 720));
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DataTable dataTable = new DataTable(dataController);
        StatsPanel statsPanel = new StatsPanel(dataController);
        Chart chart = new Chart(dataController);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Data Table", dataTable);
        tabbedPane.add("Statistics", statsPanel);
        tabbedPane.add("Pie Chart", chart);
        mainWindow.add(tabbedPane);
        mainWindow.pack();
        mainWindow.revalidate();
        mainWindow.repaint();
    }
}