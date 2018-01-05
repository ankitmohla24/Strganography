import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
 
import java.awt.*;
 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
import java.io.File;
import javax.swing.JLabel;
 
import javazoom.jl.converter.Converter;
 
public class MainClass extends JFrame implements ActionListener
{
    JButton mainButton=new JButton ("Click Here To Select MP3 Files To Convert");
    JTextArea myLabel=new JTextArea("\n\tConverted file has name as the original \n\tfile with .wav extension and located in the same \n\tdirectory with original mp3 file."
            + "\n\n\tYou can convert multiple files at a time.");
    JLabel informPanel=new JLabel("STATUS : No File To Convert",SwingConstants.CENTER);
    JPanel testingPanel=new JPanel();
    JFileChooser jfc;
 
    SwingWorker worker;
 
    File selectedFile;
 
    public MainClass()
    {
        super("MP3 To Wav Converter");
        setLayout(new BorderLayout());
 
        mainButton.addActionListener(this);
        myLabel.setEditable(false);
        myLabel.setSize(300, 400);
        testingPanel.add(informPanel);
        add(testingPanel,BorderLayout.SOUTH);
        add(myLabel,BorderLayout.CENTER);
        add(mainButton,BorderLayout.NORTH);
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    void updateJlabel(final String jlabelText)
    {
        Runnable doSetProgressBarValue = new Runnable() {
            public void run() {
                informPanel.setText(jlabelText);
            }
        };
        SwingUtilities.invokeLater(doSetProgressBarValue);
    }
 
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==mainButton)
        {
            mainButton.setEnabled(false);
            jfc=new JFileChooser();
            jfc.setMultiSelectionEnabled(true);
            jfc.setFileFilter(new MainClass.TypeOfFile());
            jfc.setDialogTitle("Select File To Convert");
            int a=jfc.showDialog(this, "Convert");
            if(a==JFileChooser.APPROVE_OPTION)
            {
                worker=new SwingWorker()
                {
                    public Object construct()
                    {
                        return doWork();
                    }
                    public void finished()
                    {
                        mainButton.setEnabled(true);
                        updateJlabel("STATUS : No File To Convert");
                        
                    }
                };
                worker.start();
            }
            else
            {
                mainButton.setEnabled(true);
            }
        }
    }
 
    Object doWork()
    {
        File [] groupOfFiles=jfc.getSelectedFiles();
        int numberOfFiles=groupOfFiles.length;
        for(int i=0;i<numberOfFiles;i++)
        {
            mainButton.enable(false);
            selectedFile=groupOfFiles[i];
            if(selectedFile.getName().toLowerCase().endsWith(".mp3"))
            {
                try
                {
                    updateJlabel("Please Wait : "+selectedFile.getName()+" converting...");
                    Converter myConverter = new Converter();
                    myConverter.convert(selectedFile.getPath(), selectedFile.getPath()+".wav");
                }
                catch(Exception error)
                {
                    JOptionPane.showMessageDialog(null, "Converting Process Fail", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, selectedFile.getName()+" not a MP3 File", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return new Object();
    }
 
    
    class TypeOfFile extends FileFilter
    {
        public boolean accept(File f)
        {
            return f.getName().toLowerCase().endsWith(".mp3")||f.isDirectory();
        }
 
        public String getDescription()
        {
            return ".mp3 files";
        }
    }
 
    public static void main(String[]args)
    {
        MainClass myObject=new MainClass();
    }
}
