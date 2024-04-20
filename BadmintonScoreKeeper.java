import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BadmintonScoreKeeper {
    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;
    private String setWinner;
    private DefaultTableModel tableModel;

    public BadmintonScoreKeeper() {
        player1Score = 0;
        player2Score = 0;
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Set Number");
        tableModel.addColumn(player1Name);
        tableModel.addColumn(player2Name);
    }

    public void player1Scores() {
        player1Score++;
        checkWinner();
    }

    public void player2Scores() {
        player2Score++;
        checkWinner();
    }

    public void player1Subtract() {
        player1Score--;
        checkWinner();
    }

    public void player2Subtract() {
        player2Score--;
        checkWinner();
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    private void checkWinner() {
        if (player1Score >= 21 && player1Score - player2Score >= 2) {
            setWinner = player1Name;
            JOptionPane.showMessageDialog(null, player1Name + " wins the set!");
            recordSetScore();
        } else if (player2Score >= 21 && player2Score - player1Score >= 2) {
            setWinner = player2Name;
            JOptionPane.showMessageDialog(null, player2Name + " wins the set!");
            recordSetScore();
        } else if (player1Score == 20 && player2Score == 20) {
            JOptionPane.showMessageDialog(null, "Deuce! It's a tie at 20-20! First to 2 points advantage wins!");
        }
    }

    private void recordSetScore() {
        Object[] rowData = { tableModel.getRowCount() + 1, player1Score, player2Score };
        tableModel.addRow(rowData);
    }

    public void resetScores() {
        player1Score = 0;
        player2Score = 0;
    }

    public String getScore() {
        return player1Name + ": " + player1Score + " - " + player2Name + ": " + player2Score;
    }

    // Method to display the GUI
    public void displayGUI() {
        JFrame frame = new JFrame("Badminton Score Keeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel scoreLabel = new JLabel(getScore());
        panel.add(scoreLabel);

        JTextField player1Field = new JTextField(10);
        panel.add(player1Field);

        JTextField player2Field = new JTextField(10);
        panel.add(player2Field);

        JButton player1Button = new JButton(player1Name + " ++");
        player1Button.addActionListener(e -> {
            player1Scores();
            scoreLabel.setText(getScore());
        });
        panel.add(player1Button);

        JButton player2Button = new JButton(player2Name + " ++");
        player2Button.addActionListener(e -> {
            player2Scores();
            scoreLabel.setText(getScore());
        });
        panel.add(player2Button);

        JButton player1SubtractButton = new JButton(player1Name + " --");
        player1SubtractButton.addActionListener(e -> {
            player1Subtract();
            scoreLabel.setText(getScore());
        });
        panel.add(player1SubtractButton);

        JButton player2SubtractButton = new JButton(player2Name + " --");
        player2SubtractButton.addActionListener(e -> {
            player2Subtract();
            scoreLabel.setText(getScore());
        });
        panel.add(player2SubtractButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            resetScores();
            scoreLabel.setText(getScore());
        });
        panel.add(resetButton);

        JButton setNameButton = new JButton("Set Names");
        setNameButton.addActionListener(e -> {
            player1Name = player1Field.getText();
            player2Name = player2Field.getText();
            tableModel.setColumnIdentifiers(new Object[] { "Set Number", player1Name, player2Name });
            player1Button.setText(player1Name + " ++");
            player2Button.setText(player2Name + " ++");
            player1SubtractButton.setText(player1Name + " --");
            player2SubtractButton.setText(player2Name + " --");
            scoreLabel.setText(getScore());
        });
        panel.add(setNameButton);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        frame.setVisible(true);
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        BadmintonScoreKeeper scoreKeeper = new BadmintonScoreKeeper();
        scoreKeeper.displayGUI();
    }
}
