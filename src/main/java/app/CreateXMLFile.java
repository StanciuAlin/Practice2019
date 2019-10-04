package app;

import food.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateXMLFile {
    public Document createXML() {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            InitDBConnection initConnection = new InitDBConnection();
            Connection conn = initConnection.initConnectionDB();

//            ResultSet rs = null;
//            try {
//                Statement st = conn.createStatement();
//                // execute the query, and get a java resultset
//                rs = st.executeQuery(query);
//            } catch (Exception e) {
//            }

            // root element
            Element rootElement = doc.createElement("food");
            doc.appendChild(rootElement);

            // supercars element
            Element alcoholElement = doc.createElement("alcohol");
            rootElement.appendChild(alcoholElement);

            Alcohol alcoholBottle = new Alcohol();
            ArrayList<Alcohol> alcoholTable = null;

            String query = "SELECT * FROM alcohol";
            ResultSet rs = null;
            try {
                Statement st = conn.createStatement();
                // execute the query, and get a java resultset
                rs = st.executeQuery(query);
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            //rs.close();
            alcoholTable = alcoholBottle.createArrayListFromTable(rs);

            for (int i = 0; i <alcoholTable.size(); i++) {
                //;
                Element alcoholSubElement = doc.createElement("alcoholElement");
                alcoholElement.appendChild(alcoholSubElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(alcoholTable.get(i).getId())));
                alcoholSubElement.appendChild(id);

                Element productType = doc.createElement("productType");
                productType.appendChild(doc.createTextNode(alcoholTable.get(i).getProductType()));
                alcoholSubElement.appendChild(productType);

                Element quantity = doc.createElement("quantity");
                quantity.appendChild(doc.createTextNode(Integer.toString(alcoholTable.get(i).getQuantity())));
                alcoholSubElement.appendChild(quantity);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(Float.toString(alcoholTable.get(i).getPrice())));
                alcoholSubElement.appendChild(price);

                Element expirationDay = doc.createElement("expirationDay");
                expirationDay.appendChild(doc.createTextNode(alcoholTable.get(i).getExpirationDay()));
                alcoholSubElement.appendChild(expirationDay);

                Element alcoholLevel = doc.createElement("alcoholLevel");
                alcoholLevel.appendChild(doc.createTextNode(Float.toString(alcoholTable.get(i).getAlcoholLevel())));
                alcoholSubElement.appendChild(alcoholLevel);

                Element age = doc.createElement("age");
                age.appendChild(doc.createTextNode(Integer.toString(alcoholTable.get(i).getAge())));
                alcoholSubElement.appendChild(age);
            }
            Element butcheryElement = doc.createElement("butchery");
            rootElement.appendChild(butcheryElement);


            Butchery butchery = new Butchery();
            ArrayList<Butchery> butcheryTable = null;
            String query1 = "SELECT * FROM butchery";
            ResultSet rs1 = null;
            try {
                Statement st = conn.createStatement();
                // execute the query, and get a java resultset
                rs1 = st.executeQuery(query1);
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            butcheryTable = butchery.createArrayListFromTable(rs1);


            for (int i = 0; i < butcheryTable.size(); i++) {
                Element butcherySubElement = doc.createElement("butcheryElement");
                butcheryElement.appendChild(butcherySubElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(butcheryTable.get(i).getId())));
                butcherySubElement.appendChild(id);

                Element productType = doc.createElement("productType");
                productType.appendChild(doc.createTextNode(butcheryTable.get(i).getProductType()));
                butcherySubElement.appendChild(productType);

                Element quantity = doc.createElement("quantity");
                quantity.appendChild(doc.createTextNode(Integer.toString(butcheryTable.get(i).getQuantity())));
                butcherySubElement.appendChild(quantity);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(Float.toString(butcheryTable.get(i).getPrice())));
                butcherySubElement.appendChild(price);

                Element expirationDay = doc.createElement("expirationDay");
                expirationDay.appendChild(doc.createTextNode(butcheryTable.get(i).getExpirationDay()));
                butcherySubElement.appendChild(expirationDay);

                Element nutritionalValue = doc.createElement("nutritionalValue");
                nutritionalValue.appendChild(doc.createTextNode(butcheryTable.get(i).getNutritionalValue().toString()));
                butcherySubElement.appendChild(nutritionalValue);
            }

            Element dairyProductElement = doc.createElement("dairy_product");
            rootElement.appendChild(dairyProductElement);


            DairyProducts dairyProducts = new DairyProducts();
            ArrayList<DairyProducts> dairyProductsTable = null;
            String queryDairyProduct = "SELECT * FROM `dairy products`";
            ResultSet rsDairyProduct = null;
            try {
                Statement st = conn.createStatement();
                // execute the query, and get a java resultset
                rsDairyProduct = st.executeQuery(queryDairyProduct);
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            dairyProductsTable = dairyProducts.createArrayListFromTable(rsDairyProduct);


            for (int i = 0; i < dairyProductsTable.size(); i++) {
                Element dairyProductSubElement = doc.createElement("dairyProductsElement");
                dairyProductElement.appendChild(dairyProductSubElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(dairyProductsTable.get(i).getId())));
                dairyProductSubElement.appendChild(id);

                Element productType = doc.createElement("productType");
                productType.appendChild(doc.createTextNode(dairyProductsTable.get(i).getProductType()));
                dairyProductSubElement.appendChild(productType);

                Element quantity = doc.createElement("quantity");
                quantity.appendChild(doc.createTextNode(Integer.toString(dairyProductsTable.get(i).getQuantity())));
                dairyProductSubElement.appendChild(quantity);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(Float.toString(dairyProductsTable.get(i).getPrice())));
                dairyProductSubElement.appendChild(price);

                Element provenance = doc.createElement("provenance");
                provenance.appendChild(doc.createTextNode(dairyProductsTable.get(i).getProvenance()));
                dairyProductSubElement.appendChild(provenance);

                Element expirationDay = doc.createElement("expirationDate");
                expirationDay.appendChild(doc.createTextNode(dairyProductsTable.get(i).getExpirationDay().toString()));
                dairyProductSubElement.appendChild(expirationDay);
            }

            Element dinerElement = doc.createElement("diner");
            rootElement.appendChild(dinerElement);


            Diner dinerProduct = new Diner();
            ArrayList<Diner> dinerTable = null;
            String queryDiner = "SELECT * FROM diner";
            ResultSet rsDiner = null;
            try {
                Statement st = conn.createStatement();
                // execute the query, and get a java resultset
                rsDiner = st.executeQuery(queryDiner);
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            dinerTable = dinerProduct.createArrayListFromTable(rsDiner);


            for (int i = 0; i < dinerTable.size(); i++) {
                Element dinerSubElement = doc.createElement("dinerElement");
                dinerElement.appendChild(dinerSubElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(dinerTable.get(i).getId())));
                dinerSubElement.appendChild(id);

                Element productType = doc.createElement("productType");
                productType.appendChild(doc.createTextNode(dinerTable.get(i).getProductType()));
                dinerSubElement.appendChild(productType);

                Element quantity = doc.createElement("quantity");
                quantity.appendChild(doc.createTextNode(Integer.toString(dinerTable.get(i).getQuantity())));
                dinerSubElement.appendChild(quantity);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(Float.toString(dinerTable.get(i).getPrice())));
                dinerSubElement.appendChild(price);

                Element disponibility = doc.createElement("disponibility");
                disponibility.appendChild(doc.createTextNode(Integer.toString(dinerTable.get(i).getDisponibility())));
                dinerSubElement.appendChild(disponibility);

                Element nutritionalValue = doc.createElement("nutritionalValue");
                nutritionalValue.appendChild(doc.createTextNode(dinerTable.get(i).getNutritionalValue().toString()));
                dinerSubElement.appendChild(nutritionalValue);
            }

            Element petElement = doc.createElement("pets");
            rootElement.appendChild(petElement);


            Pet pet = new Pet();
            ArrayList<Pet> petTable = null;
            String queryPet = "SELECT * FROM pets";
            ResultSet rsPet = null;
            try {
                Statement st = conn.createStatement();
                // execute the query, and get a java resultset
                rsPet = st.executeQuery(queryPet);
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            petTable = pet.createArrayListFromTable(rsPet);


            for (int i = 0; i < petTable.size(); i++) {
                Element petSubElement = doc.createElement("petElement");
                petElement.appendChild(petSubElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(petTable.get(i).getId())));
                petSubElement.appendChild(id);

                Element productType = doc.createElement("productType");
                productType.appendChild(doc.createTextNode(petTable.get(i).getProductType()));
                petSubElement.appendChild(productType);

                Element quantity = doc.createElement("category");
                quantity.appendChild(doc.createTextNode(petTable.get(i).getCategory()));
                petSubElement.appendChild(quantity);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(Float.toString(petTable.get(i).getPrice())));
                petSubElement.appendChild(price);

                Element expirationDay = doc.createElement("expirationDay");
                expirationDay.appendChild(doc.createTextNode(petTable.get(i).getExpirationDay()));
                petSubElement.appendChild(expirationDay);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\Alin Stanciu\\Desktop\\Practica\\SP\\FoodSQLPractica\\food.xml"));
            transformer.transform(source, result);


            // Output to console for testing
//            StreamResult consoleResult = new StreamResult(System.out);
//            transformer.transform(source, consoleResult);

            //System.out.println(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
