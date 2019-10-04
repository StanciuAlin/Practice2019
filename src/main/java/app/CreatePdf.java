package app;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;

public class CreatePdf {
    public static final String RESULT
            = "foodDatabase.pdf";


    public void create() {
        try {

            com.itextpdf.text.Document document = new com.itextpdf.text.Document(new Rectangle(1000, 1000));
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
            // changes the user unit
            writer.setUserunit(10000f);
            // step 3
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.TIMES, 40f, Font.BOLD);
            Paragraph title = new Paragraph("Summer practice project 2019", fontTitle);
            title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            title.setSpacingAfter(30);
            document.add(title);
            Font fontDbName = FontFactory.getFont(FontFactory.TIMES, 20f, Font.BOLD);
            Paragraph dbName = new Paragraph("Database: food", fontDbName);
            dbName.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            document.add(dbName);
            //Get Document Builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

//Build Document
            Document documentFromXml = builder.parse(new File("food.xml"));
            //System.out.println("\n\n\n" + documentFromXml.getClass());
//Normalize the XML Structure; It's just too important !!
            documentFromXml.getDocumentElement().normalize();

//Here comes the root node
//            Element root = documentFromXml.getDocumentElement();
//            System.out.println(root.getNodeName());

            PdfPTable alcoholTable = new PdfPTable(7);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Alcohol"));
            cell.setColspan(7);
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            alcoholTable.addCell(cell);
            alcoholTable.addCell(new Phrase("Alcohol id : "));
            alcoholTable.addCell(new Phrase("Product Type : "));
            alcoholTable.addCell(new Phrase("Quantity : "));
            alcoholTable.addCell(new Phrase("Price : "));
            alcoholTable.addCell(new Phrase("Expiration day : "));
            alcoholTable.addCell(new Phrase("Alcohol level : "));
            alcoholTable.addCell(new Phrase("Age : "));
//Get all employees
            NodeList alcoholList = documentFromXml.getElementsByTagName("alcoholElement");

            for (int temp = 0; temp < alcoholList.getLength(); temp++) {
                Node node = alcoholList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Print each employee's detail
                    Element eElement = (Element) node;
//                    document.add(new Paragraph("Alcohol id : "
//                            + eElement.getElementsByTagName("id").item(0).getTextContent()));

                    alcoholTable.addCell(new Phrase(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    alcoholTable.addCell(new Phrase(eElement.getElementsByTagName("productType").item(0).getTextContent()));
                    alcoholTable.addCell(new Phrase(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    alcoholTable.addCell(new Phrase(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    alcoholTable.addCell(new Phrase(eElement.getElementsByTagName("expirationDay").item(0).getTextContent()));
                    alcoholTable.addCell(new Phrase(eElement.getElementsByTagName("alcoholLevel").item(0).getTextContent()));
                    alcoholTable.addCell(new Phrase(eElement.getElementsByTagName("age").item(0).getTextContent()));
                }
            }
            alcoholTable.setSpacingBefore(50);
            alcoholTable.setSpacingAfter(100);

            document.add(alcoholTable);


            PdfPTable butcheryTable = new PdfPTable(6);
            PdfPCell cellBut;
            cellBut = new PdfPCell(new Phrase("Butchery"));
            cellBut.setColspan(6);
            cellBut.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            butcheryTable.addCell(cellBut);
            butcheryTable.addCell(new Phrase("Id : "));
            butcheryTable.addCell(new Phrase("Product Type : "));
            butcheryTable.addCell(new Phrase("Quantity : "));
            butcheryTable.addCell(new Phrase("Price/Unit : "));
            butcheryTable.addCell(new Phrase("Expiration day : "));
            butcheryTable.addCell(new Phrase("Nutritional Value : "));

            NodeList butcheryList = documentFromXml.getElementsByTagName("butcheryElement");

            for (int temp = 0; temp < butcheryList.getLength(); temp++) {
                Node node = butcheryList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Print each employee's detail
                    Element eElement = (Element) node;
//                    document.add(new Paragraph("Alcohol id : "
//                            + eElement.getElementsByTagName("id").item(0).getTextContent()));

                    butcheryTable.addCell(new Phrase(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    butcheryTable.addCell(new Phrase(eElement.getElementsByTagName("productType").item(0).getTextContent()));
                    butcheryTable.addCell(new Phrase(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    butcheryTable.addCell(new Phrase(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    butcheryTable.addCell(new Phrase(eElement.getElementsByTagName("expirationDay").item(0).getTextContent()));
                    butcheryTable.addCell(new Phrase(eElement.getElementsByTagName("nutritionalValue").item(0).getTextContent()));
                }
            }
            butcheryTable.setSpacingAfter(100);
            document.add(butcheryTable);

            PdfPTable dairyProductTable = new PdfPTable(6);
            PdfPCell celldP;
            celldP = new PdfPCell(new Phrase("Dairy Products"));
            celldP.setColspan(6);
            celldP.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            dairyProductTable.addCell(celldP);
            dairyProductTable.addCell(new Phrase("Id : "));
            dairyProductTable.addCell(new Phrase("Product Type : "));
            dairyProductTable.addCell(new Phrase("Quantity : "));
            dairyProductTable.addCell(new Phrase("Price/litre : "));
            dairyProductTable.addCell(new Phrase("Provenance : "));
            dairyProductTable.addCell(new Phrase("Expiration day : "));

            NodeList dairyProdcutList = documentFromXml.getElementsByTagName("dairyProductsElement");

            for (int temp = 0; temp < dairyProdcutList.getLength(); temp++) {
                Node node = dairyProdcutList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Print each employee's detail
                    Element eElement = (Element) node;
//                    document.add(new Paragraph("Alcohol id : "
//                            + eElement.getElementsByTagName("id").item(0).getTextContent()));

                    dairyProductTable.addCell(new Phrase(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    dairyProductTable.addCell(new Phrase(eElement.getElementsByTagName("productType").item(0).getTextContent()));
                    dairyProductTable.addCell(new Phrase(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    dairyProductTable.addCell(new Phrase(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    dairyProductTable.addCell(new Phrase(eElement.getElementsByTagName("provenance").item(0).getTextContent()));
                    dairyProductTable.addCell(new Phrase(eElement.getElementsByTagName("expirationDate").item(0).getTextContent()));
                }
            }
            dairyProductTable.setSpacingAfter(100);
            document.add(dairyProductTable);


            PdfPTable dinerTable = new PdfPTable(6);
            PdfPCell cellDiner;
            cellDiner = new PdfPCell(new Phrase("Diner"));
            cellDiner.setColspan(6);
            cellDiner.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            dinerTable.addCell(cellDiner);
            dinerTable.addCell(new Phrase("Id : "));
            dinerTable.addCell(new Phrase("Product Type : "));
            dinerTable.addCell(new Phrase("Quantity : "));
            dinerTable.addCell(new Phrase("Price : "));
            dinerTable.addCell(new Phrase("Disponibility : "));
            dinerTable.addCell(new Phrase("Nutritional Value : "));

            NodeList dinerList = documentFromXml.getElementsByTagName("dinerElement");

            for (int temp = 0; temp < dinerList.getLength(); temp++) {
                Node node = dinerList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Print each employee's detail
                    Element eElement = (Element) node;
//                    document.add(new Paragraph("Alcohol id : "
//                            + eElement.getElementsByTagName("id").item(0).getTextContent()));

                    dinerTable.addCell(new Phrase(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    dinerTable.addCell(new Phrase(eElement.getElementsByTagName("productType").item(0).getTextContent()));
                    dinerTable.addCell(new Phrase(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    dinerTable.addCell(new Phrase(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    dinerTable.addCell(new Phrase(eElement.getElementsByTagName("disponibility").item(0).getTextContent()));
                    dinerTable.addCell(new Phrase(eElement.getElementsByTagName("nutritionalValue").item(0).getTextContent()));
                }
            }
            dinerTable.setSpacingAfter(100);
            document.add(dinerTable);
            //

            PdfPTable petTable = new PdfPTable(5);
            PdfPCell cellPet;
            cellPet = new PdfPCell(new Phrase("Pet"));
            cellPet.setColspan(5);
            cellPet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            petTable.addCell(cellPet);
            petTable.addCell(new Phrase("Id : "));
            petTable.addCell(new Phrase("Product Type : "));
            petTable.addCell(new Phrase("Category : "));
            petTable.addCell(new Phrase("Price : "));
            petTable.addCell(new Phrase("Expiration day : "));

            NodeList petList = documentFromXml.getElementsByTagName("petElement");

            for (int temp = 0; temp < petList.getLength(); temp++) {
                Node node = petList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Print each employee's detail
                    Element eElement = (Element) node;
//                    document.add(new Paragraph("Alcohol id : "
//                            + eElement.getElementsByTagName("id").item(0).getTextContent()));

                    petTable.addCell(new Phrase(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    petTable.addCell(new Phrase(eElement.getElementsByTagName("productType").item(0).getTextContent()));
                    petTable.addCell(new Phrase(eElement.getElementsByTagName("category").item(0).getTextContent()));
                    petTable.addCell(new Phrase(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    petTable.addCell(new Phrase(eElement.getElementsByTagName("expirationDay").item(0).getTextContent()));
                }
            }
            petTable.setSpacingAfter(100);
            document.add(petTable);

            Font fontPeople = FontFactory.getFont(FontFactory.TIMES, 15f, Font.BOLD);
            Paragraph teacher = new Paragraph("Coordinator: University Assistant Popescu Paul Stefan", fontPeople);
            teacher.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            teacher.setSpacingAfter(5);
            document.add(teacher);

            Paragraph people1 = new Paragraph("Student: Alexe Octavian Alexandru", fontPeople);
            people1.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            people1.setSpacingAfter(5);
            document.add(people1);

            Paragraph people2 = new Paragraph("Student: Moraru Valentin Teodor", fontPeople);
            people2.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            people2.setSpacingAfter(5);
            document.add(people2);

            Paragraph people3 = new Paragraph("Student: Stanciu Alin Marian", fontPeople);
            people3.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            people3.setSpacingAfter(5);
            document.add(people3);

            document.close();
        } catch (Exception e) {
            System.out.println("Err");
        }
    }


}
