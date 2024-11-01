import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class DataTable extends JPanel
{
    DataController dataController;
    JTable table;
    JScrollPane scrollPane;
    JSplitPane splitPane;
    JPanel dataPane, detailPane;

    DataTable(DataController dataController)
    {
        // set the DataController
        this.setLayout(new BorderLayout());
        dataPane = new JPanel();
        dataPane.setLayout(new BorderLayout());
        this.dataController = dataController;
        // Generate table
        generateTable();

        // Add sorting for Table Headers
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        for (int i = 0; i < table.getColumnCount(); i++) {
            sorter.setComparator(i, Comparator.naturalOrder()); // Ascending order
            sorter.setComparator(i, Comparator.reverseOrder()); // Descending order
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String[] selectedRow = {};

                String[][] data = dataController.getData();
                for (String[] item: data)
                {
                    if (item[0] == (String) table.getValueAt(table.getSelectedRow() , 0) )
                    {
                        selectedRow = item;
                        break;
                    }
                }

                detailPane.removeAll();
                for (String entry: selectedRow)
                {
                    detailPane.add(new Label("| " + entry +" |"));
                }

                detailPane.revalidate();
                detailPane.repaint();
            }
        });


        scrollPane = new JScrollPane(table);
        detailPane = new JPanel();
        dataPane.add(scrollPane, BorderLayout.CENTER);
        splitPane = new JSplitPane(SwingConstants.HORIZONTAL, dataPane, detailPane);
        this.add(splitPane);

    }

    void generateTable() {
        table = new JTable(dataController.getData(), Arrays.copyOfRange(dataController.getHeader(), 0, 5));
    }

}

