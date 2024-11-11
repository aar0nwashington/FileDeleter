import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//works but has perrmision issues? 
        SwingUtilities.invokeLater(() -> {
        String largeFile = findRandomLargeFile("/Users/joshuawashington");
        if(largeFile != null){
            System.out.println("Large file found: " + largeFile);
            createdDeletePrompt(largeFile);
        }else{
            System.out.println("No large files found.");
            JOptionPane.showMessageDialog(null, "No large files found");
        }
            
        });

        }

    private static String findRandomLargeFile(String directoryPath){
        File directory = new File(directoryPath);
        List<File> largeFiles = new ArrayList<>();

        findLargeFiles(directory, largeFiles, 1 * 1024 * 1024);

        if (!largeFiles.isEmpty()){
            Random random = new Random();
            File randomFile = largeFiles.get(random.nextInt(largeFiles.size()));
            return randomFile.getAbsolutePath();
        }else{
            return null;
        }
        }
        private static void findLargeFiles(File directory, List<File> largeFiles, long sizeThreshold){
            File[] files = directory.listFiles();
            if(files != null){
                for(File file : files){
                    if(file.isFile() && file.length() > sizeThreshold){
                        largeFiles.add(file);
                        System.out.println("added large file: "+ file.getAbsolutePath());
                    }else if (file.isDirectory()){
                        findLargeFiles(file, largeFiles, sizeThreshold);
                    }

                    }
                    }
                }
            private static void createdDeletePrompt(String filePath){
                JFrame frame = new JFrame("File Deletion Prompt");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JDialog dialog = new JDialog(frame, "Delete File?", true);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(300, 150);
                dialog.setLocationRelativeTo(null);

                JLabel message = new JLabel("<html>This file is taking up space:<br>" + filePath + "<html>");
                message.setHorizontalAlignment(SwingConstants.CENTER);
                dialog.add(message, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel();
                JButton yesButton = new JButton("Yes, Delete it!");
                yesButton.setBackground(new Color(255, 102, 102));
                yesButton.setForeground(Color.WHITE);


                JButton noButton = new JButton("No, Keep it");
                buttonPanel.add(yesButton);
                buttonPanel.add(noButton);
                dialog.add(buttonPanel, BorderLayout.SOUTH);

                yesButton.addActionListener(e -> {
                    File file = new File(filePath);
                    if( file.delete()){
                        JOptionPane.showMessageDialog(frame, "File deleted succesfully!");
                    }else{
                        JOptionPane.showMessageDialog(frame, "Failed to delete file");
                    }
                    dialog.dispose();

                });
                noButton.addActionListener(e -> dialog.dispose());

                dialog.setVisible(true);
                    }
    
            }

    

