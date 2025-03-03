import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import SortingApp.*;

public class Main_WindowsApp extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton shellSortButton;
    private JButton introSortButton;

    public Main_WindowsApp() {
    	
    	double wwdasafa = 0;
    	
        setTitle("Sorting App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input area
        inputArea = new JTextArea();
        inputArea.setBorder(BorderFactory.createTitledBorder("Input Pairs (key(int):value(str) , one per line)"));
        add(new JScrollPane(inputArea), BorderLayout.NORTH);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Sorted Pairs"));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        shellSortButton = new JButton("Shell Sort");
        introSortButton = new JButton("Introspective Sort");
        buttonPanel.add(shellSortButton);
        buttonPanel.add(introSortButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        shellSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortPairs("Shell");
            }
        });

        introSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortPairs("Intro");
            }
        });
    }

    private void sortPairs(String sortType) {
        String input = inputArea.getText();
        String[] lines = input.split("\n");

        Pair<Integer, String>[] pairs = new Pair[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(":");
            if (parts.length == 2) {
                try {
                    Integer key = Integer.parseInt(parts[0].trim());
                    String value = parts[1].trim();
                    pairs[i] = new Pair<>(key, value);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid key format: " + parts[0], "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input format: " + lines[i], "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (sortType.equals("Shell")) {
        	SortUtils.shellSort(pairs);
        } else if (sortType.equals("Intro")) {
        	SortUtils.introspectiveSort(pairs);
        }

        StringBuilder sortedOutput = new StringBuilder();
        for (Pair<Integer, String> pair : pairs) {
            sortedOutput.append(pair.getKey()).append(":").append(pair.getValue()).append("\n");
        }
        outputArea.setText(sortedOutput.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main_WindowsApp().setVisible(true);
            }
        });
    }
}