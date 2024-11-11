import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    public static void main(String [] args) {

        SwingUtilities.invokeLater(() -> {
            String filePath = "/Users/joshuawasington/sampleLargeFile.txt";
            createDeletePrompt(filePath);
        });

        }
private static void createDeletePrompt(String filePath){
    JFrame frame = new JFrame("File Deletion Prompt");
    frame.setDefualtCloseOperation(JFrame.EXIT_ON_CLOSE);


    JDialog dialog = new JDialog(frame, "Delete File?", true);
    dialog.setLayout(new BorderLayout());
dialog.setSize(300, 150);
dialog.setLocationRelativeTo(null);

JLabel message = new JLabel("This file is taking up space:\n " + filePath);
message.setHorizontalAlignment(SwingConstants.CENTER);
dialog.add(message, BorderLayout.CENTER);

Jpanel buttonPanel = new JPanel();
JButton yesButton = new JButton("yes, Delete it! ");
yesButton.setBackground(new Color(255, 102, 102));
yesButtonsetForeground(Color.WHITE);

buttonPanel.add(yesButton);
buttonPanel.add(noButton);
dialog.add(buttonPanel, BorderLayout.SOUTH);

yesButton.addActionListener(e -> {
    File file = new File(filePath);
    if (file.delete()){
        JOptionPane.showMessageDialog(frame, "File deleted successfully!");
    }else{
        JOptionPane.showMessageDialog(frame, "Failed to delete file. ");

    }
    dialog.dispose();
});

noButton.addActionListener(e -> dialog.dispose());

dialog.setVisible(true);



    }
}
