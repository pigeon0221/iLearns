import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class CreatePDF{
    void Convert(){
        JOptionPane.showMessageDialog(null,
                "Select the library you want to save as PDF.",
                "ILearns",
                JOptionPane.PLAIN_MESSAGE);
        File workingDirectory = new File(System.getProperty("user.dir")+File.separator+"Libraries");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(workingDirectory);
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancel was selected");
            return;
        }
        File f = chooser.getSelectedFile();
        Font fontsmall = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLACK);
        Paragraph words = new Paragraph();
        words.setFont(fontsmall);
        words.setAlignment(Paragraph.ALIGN_LEFT);
        try (BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()))) {
            int level = 1;
            for (String line; (line = br.readLine()) != null; ) {
                words.add("\nLevel "+level+": "+line.trim() +"\n");
                level+=1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try{
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 35.0f, Font.BOLD, BaseColor.BLACK);
            Document document = new com.itextpdf.text.Document(PageSize.LETTER, 20, 20, 20, 20);
            PdfWriter.getInstance(document,new FileOutputStream("Library PDF's/"+f.getName().replaceAll(".txt",".pdf")));
            String topText = f.getName().replaceAll(".txt","")+" Library";
            document.open();
            Image image = Image.getInstance("Images/logo.png");
            image.scalePercent(20);
            image.setAlignment(Image.MIDDLE);
            document.add(image);
            Paragraph p = new Paragraph();
            p.setFont(font);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.add("\n");
            p.add(topText);
            p.add("\n");
            document.add(p);
            document.add(words);
            document.close();
            final ImageIcon icon = new ImageIcon("Images/cute-ghost.png");
            int reply = JOptionPane.showConfirmDialog(null, "PDF saved! Would you like to view the PDF?", "ILearns", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,icon);
            if (reply == JOptionPane.YES_OPTION) {
                if(Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("Library PDF's/" + f.getName().replaceAll(".txt", ".pdf"));
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Could not open PDF, please view it in the \"Library PDF's\" Folder.",
                                "ILearns: ERROR",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "PDF is viewable in the \"Library PDF's\" Folder.",
                        "ILearns",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
} 