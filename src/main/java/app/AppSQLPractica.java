package app;

import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Position;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AppSQLPractica extends JPanel implements ActionListener {


    protected JButton bOpenFile;
    protected JButton bPdf;
    protected JButton bXml;
    protected JTextField textFieldSelectFilename;
    protected JTextArea textAreaOpenFile;
    protected JLabel labelChooseFilename;
    protected JLabel labelTitle;
    protected JLabel labelInfoButtons;
    protected JPanel jPanel;

    private BufferedImage image;

    public AppSQLPractica() {
        jPanel = new JPanel();
        jPanel.setMinimumSize(new Dimension(100, 400));

        textAreaOpenFile = new JTextArea(15, 10);
        textFieldSelectFilename = new JTextField("Write filename to view absolut path");

        bXml = new JButton("Generate XML");
        bPdf = new JButton("Generate PDF");
        bOpenFile = new JButton("View file location");

        labelChooseFilename = new JLabel("Write filename to view absolut path");
        labelTitle = new JLabel("Database to Pdf");
        labelInfoButtons = new JLabel("Options: ");

        loadImageDB();
        createButtons();
        createJTextField();
        //optionsLabelTitle();
        //optionsLabelInfoButtons();
        //optionsLabelChooseFilename();
        //createJTextArea();
    }

    /**
     * private void optionsLabelChooseFilename() {
     * labelChooseFilename.setHorizontalAlignment(JLabel.CENTER);
     * labelChooseFilename.setVerticalAlignment(JLabel.CENTER);
     * add(labelChooseFilename);
     * }
     * <p>
     * private void optionsLabelTitle() {
     * //labelInfoButtons.setHorizontalAlignment(JLabel.LEADING);
     * //labelInfoButtons.setVerticalAlignment(JLabel.TOP);
     * add(labelTitle, SpringLayout.VERTICAL_CENTER);
     * //add(labelInfoButtons);
     * }
     * <p>
     * private void optionsLabelInfoButtons() {
     * //labelInfoButtons.setHorizontalAlignment(JLabel.LEFT);
     * //labelChooseFilename.setVerticalAlignment(JLabel.CENTER);
     * jPanel.add(labelInfoButtons, SpringLayout.WEST);
     * }
     * <p>
     * private void createJTextArea() {
     * textAreaOpenFile.setAlignmentY(100);
     * textAreaOpenFile.setAlignmentX(400);
     * textAreaOpenFile.setMinimumSize(new Dimension(100, 100));
     * textAreaOpenFile.setMaximumSize(new Dimension(400, 200));
     * <p>
     * add(textAreaOpenFile);
     * }
     * private void optionsLabelChooseFilename() {
     * //        labelChooseFilename.setHorizontalAlignment(JLabel.CENTER);
     * //        labelChooseFilename.setVerticalAlignment(JLabel.CENTER);
     * add(labelChooseFilename, BorderLayout.WEST);
     * }
     * <p>
     * <p>
     * private void createJTextField() {
     * <p>
     * textFieldSelectFilename.setAlignmentX(140);
     * textFieldSelectFilename.setAlignmentY(200);
     * textFieldSelectFilename.setMinimumSize(new Dimension(100, 100));
     * textFieldSelectFilename.setMaximumSize(new Dimension(400, 200));
     * add(textFieldSelectFilename);
     * }
     */


    private void createJTextField() {

        textFieldSelectFilename.setMinimumSize(new Dimension(100, 100));
        textFieldSelectFilename.setMaximumSize(new Dimension(400, 200));
        add(textFieldSelectFilename, BorderLayout.EAST);
    }

    private void loadImageDB() {
        try {
            image = ImageIO.read(new File("src/main/resources/formImage.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Nonexistent image");
        }
    }


    private void createButtons() {


        //Box box = Box.createHorizontalBox();
        //bXml.setVerticalTextPosition(AbstractButton.BOTTOM);
        //bXml.setHorizontalTextPosition(JButton.LEFT); //aka LEFT, for left-to-right locales
        bXml.setMnemonic(KeyEvent.VK_D);
        bXml.addActionListener(this);
        bXml.setToolTipText("Click button to generate XML file from database");
        bXml.setActionCommand("enable");
        add(bXml);


        //bXml.setHorizontalTextPosition(JButton.CENTER);
        //bXml.setHorizontalAlignment(JButton.LEFT);
        //bXml.setVerticalTextPosition(JButton.BOTTOM);
        //bXml.setVerticalAlignment(JButton.BOTTOM);
        //jPanel.add(bXml, SpringLayout.WEST);


//        bPdf.setVerticalTextPosition(AbstractButton.BOTTOM);
//        bPdf.setHorizontalTextPosition(AbstractButton.CENTER);
        bPdf.setMnemonic(KeyEvent.VK_M);
        bPdf.addActionListener(this);
        bPdf.setToolTipText("Click button to generate PDF file from XML file");
        bPdf.setActionCommand("disable");
        bPdf.setEnabled(false);
        add(bPdf);
        //bPdf.setHorizontalAlignment(JButton.LEFT);
        //bPdf.setVerticalAlignment(JButton.BOTTOM);
        //jPanel.add(bPdf, SpringLayout.HORIZONTAL_CENTER);

        bOpenFile.setMnemonic(KeyEvent.VK_E);
        bOpenFile.addActionListener(this);
        bOpenFile.setToolTipText("Click button to open file location");
        add(bOpenFile);
        //bOpenFile.setHorizontalTextPosition(JButton.CENTER);
        //bOpenFile.setVerticalTextPosition(JButton.TOP);
        //jPanel.add(bOpenFile, SpringLayout.EAST);
        //box.add(jPanel);
        //add(box, SpringLayout.SOUTH);
    }


    public void actionPerformed(ActionEvent e) {
        if ("enable".equals(e.getActionCommand())) {
            actionXmlButton();
            bXml.setEnabled(false);
            bPdf.setEnabled(true);
            JOptionPane.showMessageDialog(null, "XML file generated from database");
        } else if (e.getSource() == bOpenFile) {
            if ( "food.xml".equals(textFieldSelectFilename.getText()) || "foodDatabase.pdf".equals(textFieldSelectFilename.getText())) {
                File file = new File("src/main/resources/" + textFieldSelectFilename.getText());
                String path = file.getAbsolutePath();
                textAreaOpenFile.append(path);
                JOptionPane.showMessageDialog(null, path);
            } else {
                JOptionPane.showMessageDialog(null, "Nonexistent file" + textFieldSelectFilename.getText() + "\\");
            }
        } else {
            CreatePdf pdf = new CreatePdf();
            pdf.create();
            JOptionPane.showMessageDialog(null, "PDF file generated from XML file");
            bPdf.setEnabled(false);
        }
    }

    private void actionXmlButton() {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            CreateXMLFile x = new CreateXMLFile();
            doc = x.createXML();
        } catch (Exception ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
        /**
         *
         * ----Code for obtain XML content in String object
         *
         * TransformerFactory tf = TransformerFactory.newInstance();
         *         Transformer transformer;
         *         try {
         *             transformer = tf.newTransformer();
         *             // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
         *             //A character stream that collects its output in a string buffer,
         *             //which can then be used to construct a string.
         *             StringWriter writer = new StringWriter();
         *             //transform document to string
         *             transformer.transform(new DOMSource(doc), new StreamResult(writer));
         *             String xmlString = writer.getBuffer().toString();
         *             textAreaXml.append(xmlString);
         *         } catch (TransformerException e) {
         *             e.printStackTrace();
         *         } catch (Exception e) {
         *             e.printStackTrace();
         *         }
         */
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.drawImage(image, 100, 100, this); // see javadoc for more info on the parameters
        g.drawImage(image, 20, 100, 700, 180, this);
    }

    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Database to PDF");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(760, 350));
        //Create and set up the content pane.
        AppSQLPractica newContentPane = new AppSQLPractica();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.getContentPane().setBackground(Color.WHITE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
