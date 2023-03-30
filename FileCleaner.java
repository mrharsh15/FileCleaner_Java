// WAP to design a cleaner app which should provide an interface to the user for providing the path of the folder
// to  be cleaned and ption to choose the file extensions to be deleted. At a time, user should be allowed to delete the files of 
// multiple tye such as .exe, .class, .zip etc



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;

public class FileCleaner implements ActionListener {
    private JButton chooseFolderButton, deleteButton;
    private JCheckBox exeCheckbox, classCheckbox, zipCheckbox;
    private JTextField folderPathField;
    JFrame frame = new JFrame();

    public FileCleaner() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        
        JLabel folderPathLabel = new JLabel("Folder Path:");
        folderPathField = new JTextField(20);


        folderPathLabel.setBounds(25,50, 80, 25);
        folderPathField.setBounds(120,50, 100, 25);
        frame.add(folderPathLabel);
        frame.add(folderPathField);

        chooseFolderButton = new JButton("Browse");
        chooseFolderButton.addActionListener(this);

        chooseFolderButton.setBounds(250, 50, 80,25);
        frame.add(chooseFolderButton);

        JLabel fileTypesLabel = new JLabel("File Types to Delete:");

        exeCheckbox = new JCheckBox(".exe");
        classCheckbox = new JCheckBox(".class");
        zipCheckbox = new JCheckBox(".zip");

        exeCheckbox.setBounds(50, 160, 20, 20);
        classCheckbox.setBounds(150, 160, 20, 20);
        zipCheckbox.setBounds(250, 160, 20, 20);

        JLabel exeLabel = new JLabel("exe ");
        JLabel classLabel = new JLabel("class");
        JLabel zipLabel = new JLabel("zip");
        exeLabel.setBounds(80, 160, 80, 20);
        classLabel.setBounds(180, 160, 80, 20);
        zipLabel.setBounds(280, 160, 80, 20);


        frame.add(exeCheckbox);
        frame.add(classCheckbox);
        frame.add(zipCheckbox);

        frame.add(exeLabel);
        frame.add(classLabel);
        frame.add(zipLabel);


        deleteButton = new JButton("Delete");
        deleteButton.setBounds(90, 250, 100,25);
        frame.add(deleteButton);
        deleteButton.addActionListener(this);
        
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == chooseFolderButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showDialog(fileChooser, null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                folderPathField.setText(selectedFile.getAbsolutePath());
            }
        } else if (event.getSource() == deleteButton) {
            File folder = new File(folderPathField.getText());
            
            if (exeCheckbox.isSelected()) {
                deleteFiles(folder, ".exe");
            }
            if (classCheckbox.isSelected()) {
                deleteFiles(folder, ".class") ;
            }
            if (zipCheckbox.isSelected()) {
                deleteFiles(folder, ".zip");
            }

        }
    }

    private boolean deleteFiles(File folder, String extension) {
        File[] files = folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(extension);
            }
        });

        if (files == null || files.length == 0) {
            return false;
        }

        for (File file : files) {
            if (!file.delete()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        new FileCleaner();
    }
}