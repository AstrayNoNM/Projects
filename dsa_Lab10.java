import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class dsa_Lab10 {
    private static ArrayList<String> diaryLogs = new ArrayList<>();
    private static JFrame chatFrame;
    private static JTextArea chatArea;
    private static JTextField messageField;

    private static void sendLogs(String dlogs) {
        if (!dlogs.trim().isEmpty()) {
            diaryLogs.add(dlogs);
            chatArea.append("You: " + dlogs + "\n");
            messageField.setText("");
        } else {
            JOptionPane.showMessageDialog(chatFrame, "Log cannot be empty!");
        }
    }

    private static void viewDlogs(JFrame parent) {
        if (diaryLogs.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No logs available.");
            return;
        }

        StringBuilder logsText = new StringBuilder("Diary Logs:\n\n");
        for (String logs : diaryLogs) {
            logsText.append(logs).append("\n\n");
        }

        JTextArea textArea = new JTextArea(logsText.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 400));

        JOptionPane.showMessageDialog(parent, scrollPane, "Diary Logs", JOptionPane.PLAIN_MESSAGE);
    }

    private static void showChatWindow(JFrame parent) {
        chatFrame = new JFrame("Diary Chat");
        chatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chatFrame.setSize(500, 500);
        chatFrame.setLocationRelativeTo(parent);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messageField.addActionListener(e -> sendLogs(messageField.getText()));

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendLogs(messageField.getText()));

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatFrame.add(chatScrollPane, BorderLayout.CENTER);
        chatFrame.add(inputPanel, BorderLayout.SOUTH);

        if (!diaryLogs.isEmpty()) {
            chatArea.append("=== Previous Entries ===\n");
            for (String log : diaryLogs) {
                chatArea.append("You: " + log + "\n");
            }
            chatArea.append("\n=== New Entries ===\n");
        } else {
            chatArea.append("=== Start Your Diary ===\n");
            chatArea.append("Type your thoughts (insert the date) and press Send or Enter\n\n");
        }

        chatFrame.setVisible(true);
        messageField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Diary Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JButton createLogButton = new JButton("Create Diary Log");
        JButton viewLogsButton = new JButton("View Past Logs");
        JButton exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        createLogButton.setFont(buttonFont);
        viewLogsButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        buttonPanel.add(createLogButton);
        buttonPanel.add(viewLogsButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        createLogButton.addActionListener(e -> showChatWindow(frame));
        viewLogsButton.addActionListener(e -> viewDlogs(frame));
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to exit?", "Exit",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Exiting.......");
                System.exit(0);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
