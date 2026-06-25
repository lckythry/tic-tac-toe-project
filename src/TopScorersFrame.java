import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {

    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitle = new JLabel("TOP 5 SCORERS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(200, 150, 0));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        String[] columns = {"#", "Username", "Menang", "Kalah", "Seri", "Skor"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return false; // tabel tidak bisa diedit
            }
        };

        ArrayList<Player> topPlayers = playerService.getTopFiveScorers();
        for (int i = 0; i < topPlayers.size(); i++) {
            Player p = topPlayers.get(i);
            Object[] row = {
                (i + 1),
                p.getUsername(),
                p.getWins(),
                p.getLosses(),
                p.getDraws(),
                p.getScore()
            };
            model.addRow(row);
        }

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setBackground(new Color(45, 45, 45));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(80, 80, 80));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(60, 60, 60));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(0, 150, 200));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(30);  // #
        table.getColumnModel().getColumn(1).setPreferredWidth(120); // username
        table.getColumnModel().getColumn(2).setPreferredWidth(70);  // menang
        table.getColumnModel().getColumn(3).setPreferredWidth(70);  // kalah
        table.getColumnModel().getColumn(4).setPreferredWidth(70);  // seri
        table.getColumnModel().getColumn(5).setPreferredWidth(70);  // skor

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(30, 30, 30));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnClose = new JButton("Tutup");
        btnClose.setFont(new Font("Arial", Font.BOLD, 13));
        btnClose.setBackground(new Color(100, 100, 100));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClose.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        bottomPanel.add(btnClose);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> dispose());

        add(mainPanel);
    }
}