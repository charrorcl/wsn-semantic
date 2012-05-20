/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wsn.jena.ontology;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.XSD;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author reols
 */
public class createOntology {

    public void create() {

        try {
            OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            String NS = "wsn";
            // http://www.semanticweb.org/ontologies/2011/3/Ontology_Plugwise.owl
            // http://www.owl-ontologies.com/OntologyPlugwise.owl
            model.setNsPrefix(NS, "http://www.semanticweb.org/ontologies/2012/0/WsnMqtts.owl#");

            //Clases. Hay que crearlas con el NameSpace definido
            OntClass Wsn = model.createClass(NS + ":" + "WSN");
            OntClass topic = model.createClass(NS + ":" + "topic");
            OntClass publisher = model.createClass(NS + ":" + "publisher");
            OntClass topic_publisher = model.createClass(NS + ":" + "topic_publisher");
            OntClass subscriber = model.createClass(NS + ":" + "subscriber");
            OntClass topic_subscriber = model.createClass(NS + ":" + "topic_subscriber");
            OntClass gateway = model.createClass(NS + ":" + "gateway");
            OntClass topic_publisher_gateway = model.createClass(NS + ":" + "topic_publisher_gateway");
            OntClass topic_subscriber_gateway = model.createClass(NS + ":" + "topic_subscriber_gateway");

            //Clases - Messages Received and Send
            OntClass msg_gateway_received = model.createClass(NS + ":" + "msg_gateway_received");
            OntClass msg_gateway_send = model.createClass(NS + ":" + "msg_gateway_send");
            OntClass msg_publisher_received = model.createClass(NS + ":" + "msg_publisher_received");
            OntClass msg_publisher_send = model.createClass(NS + ":" + "msg_publisher_send");
            OntClass msg_subscriber_received = model.createClass(NS + ":" + "msg_subscriber_received");
            OntClass msg_subscriber_send = model.createClass(NS + ":" + "msg_subscriber_send");

            topic.setDisjointWith(publisher);
            topic.setDisjointWith(subscriber);
            topic.setDisjointWith(gateway);

            publisher.setDisjointWith(subscriber);
            publisher.setDisjointWith(gateway);


            subscriber.setDisjointWith(gateway);


            topic_publisher_gateway.setDisjointWith(topic_subscriber_gateway);


            msg_gateway_received.setDisjointWith(msg_gateway_send);
            msg_gateway_received.setDisjointWith(topic_publisher_gateway);
            msg_gateway_received.setDisjointWith(topic_subscriber_gateway);
            msg_gateway_send.setDisjointWith(topic_publisher_gateway);
            msg_gateway_send.setDisjointWith(topic_subscriber_gateway);

            msg_publisher_received.setDisjointWith(msg_publisher_send);
            msg_publisher_received.setDisjointWith(topic_publisher);
            msg_publisher_send.setDisjointWith(topic_publisher);

            msg_subscriber_received.setDisjointWith(msg_subscriber_send);
            msg_subscriber_received.setDisjointWith(topic_subscriber);
            msg_subscriber_send.setDisjointWith(topic_subscriber);

            Wsn.addSubClass(publisher);
            Wsn.addSubClass(subscriber);
            Wsn.addSubClass(gateway);
            Wsn.addSubClass(topic);

            publisher.addSubClass(topic_publisher);
            subscriber.addSubClass(topic_subscriber);
            gateway.addSubClass(topic_publisher_gateway);
            gateway.addSubClass(topic_subscriber_gateway);

            gateway.addSubClass(msg_gateway_received);
            gateway.addSubClass(msg_gateway_send);
            publisher.addSubClass(msg_publisher_received);
            publisher.addSubClass(msg_publisher_send);
            subscriber.addSubClass(msg_subscriber_received);
            subscriber.addSubClass(msg_subscriber_send);


            // Create Properties (Object Properties)------------------------------- //

            ObjectProperty hasGateway = model.createObjectProperty(NS + ":" + "hasGateway");
            ObjectProperty hasPublisher = model.createObjectProperty(NS + ":" + "hasPublisher");
            ObjectProperty hasSubscriber = model.createObjectProperty(NS + ":" + "hasSubscriber");
            ObjectProperty hasTopic = model.createObjectProperty(NS + ":" + "hasTopic");
            ObjectProperty isTopicOf = model.createObjectProperty(NS + ":" + "isTopicOf");
            ObjectProperty hasTopicSubscriber = model.createObjectProperty(NS + ":" + "hasTopicSubscriber");

            hasGateway.addDomain(publisher);//Clase a la que pertenece Gateway
            hasGateway.addDomain(subscriber);//Clase a la que pertenece Gateway
            hasPublisher.addDomain(gateway);//Clase a la que pertenece Publisher
            hasSubscriber.addDomain(gateway);
            hasTopic.addDomain(publisher);
            hasTopic.addDomain(subscriber);
            hasTopic.addDomain(gateway);
            hasTopic.addDomain(topic_publisher);
            hasTopic.addDomain(topic_subscriber);
            hasTopic.addDomain(topic_publisher_gateway);
            hasTopic.addDomain(topic_subscriber_gateway);
            isTopicOf.addDomain(topic);
            hasTopicSubscriber.addDomain(topic_subscriber_gateway);

            // Create Properties (Data Properties)------------------------------- //

            DatatypeProperty id_topic = model.createDatatypeProperty(NS + ":" + "id_topic");
            id_topic.addDomain(topic);
            id_topic.addDomain(msg_gateway_received);
            id_topic.addDomain(msg_gateway_send);
            id_topic.addDomain(msg_publisher_received);
            id_topic.addDomain(msg_publisher_send);
            id_topic.addDomain(msg_subscriber_received);
            id_topic.addDomain(msg_subscriber_send);


            DatatypeProperty id_topic_gateway = model.createDatatypeProperty(NS + ":" + "id_topic_gateway");
            id_topic_gateway.addDomain(topic_publisher_gateway);
            id_topic_gateway.addDomain(topic_subscriber_gateway);

            DatatypeProperty id_topic_publisher = model.createDatatypeProperty(NS + ":" + "id_topic_publisher");
            id_topic_publisher.addDomain(topic_publisher);

            DatatypeProperty id_topic_subscriber = model.createDatatypeProperty(NS + ":" + "id_topic_subscriber");
            id_topic_subscriber.addDomain(topic_subscriber);

            DatatypeProperty id_gateway = model.createDatatypeProperty(NS + ":" + "id_gateway");
            id_gateway.addDomain(gateway);
            id_gateway.addDomain(topic_publisher_gateway);
            id_gateway.addDomain(topic_subscriber_gateway);

            DatatypeProperty id_publisher = model.createDatatypeProperty(NS + ":" + "id_publisher");
            id_publisher.addDomain(publisher);
            id_publisher.addDomain(topic_publisher);

            DatatypeProperty id_subscriber = model.createDatatypeProperty(NS + ":" + "id_subscriber");
            id_subscriber.addDomain(subscriber);
            id_subscriber.addDomain(topic_subscriber);

            DatatypeProperty status_topic = model.createDatatypeProperty(NS + ":" + "status_topic");
            status_topic.addDomain(topic);
            status_topic.addDomain(msg_gateway_received);
            status_topic.addDomain(msg_gateway_send);
            status_topic.addDomain(msg_publisher_received);
            status_topic.addDomain(msg_publisher_send);
            status_topic.addDomain(msg_subscriber_received);
            status_topic.addDomain(msg_subscriber_send);


            DatatypeProperty name_topic = model.createDatatypeProperty(NS + ":" + "name_topic");
            name_topic.addDomain(topic);
            //name_topic.addDomain(topic_subscriber);
            name_topic.addDomain(msg_gateway_received);
            name_topic.addDomain(msg_gateway_send);
            name_topic.addDomain(msg_publisher_received);
            name_topic.addDomain(msg_publisher_send);
            name_topic.addDomain(msg_subscriber_received);
            name_topic.addDomain(msg_subscriber_send);


            DatatypeProperty value_topic = model.createDatatypeProperty(NS + ":" + "value_topic");
            value_topic.addDomain(topic);
            value_topic.addDomain(msg_gateway_received);
            value_topic.addDomain(msg_gateway_send);
            value_topic.addDomain(msg_publisher_received);
            value_topic.addDomain(msg_publisher_send);
            value_topic.addDomain(msg_subscriber_received);
            value_topic.addDomain(msg_subscriber_send);


            DatatypeProperty time = model.createDatatypeProperty(NS + ":" + "time");
            time.addDomain(Wsn);

            DatatypeProperty time_update = model.createDatatypeProperty(NS + ":" + "time_update");
            time_update.addDomain(Wsn);

            DatatypeProperty time_received = model.createDatatypeProperty(NS + ":" + "time_received");
            //time_received.addDomain(topic_publisher_gateway);
            time_received.addDomain(msg_gateway_received);
            //time_received.addDomain(msg_gateway_send);
            time_received.addDomain(msg_publisher_received);
            //time_received.addDomain(msg_publisher_send);
            time_received.addDomain(msg_subscriber_received);
            //time_received.addDomain(msg_subscriber_send);

            DatatypeProperty time_emission = model.createDatatypeProperty(NS + ":" + "time_emission");
            //time_emission.addDomain(topic_publisher_gateway);
            time_emission.addDomain(msg_gateway_received);
            time_emission.addDomain(msg_gateway_send);
            time_emission.addDomain(msg_publisher_received);
            time_emission.addDomain(msg_publisher_send);
            time_emission.addDomain(msg_subscriber_received);
            time_emission.addDomain(msg_subscriber_send);

            DatatypeProperty delay_msg_received = model.createDatatypeProperty(NS + ":" + "delay_msg_received");
            //delay_msg_received.addDomain(topic_publisher_gateway);
            //delay_msg_received.addDomain(topic_publisher_gateway);
            delay_msg_received.addDomain(msg_gateway_received);
            //delay_msg_received.addDomain(msg_gateway_send);
            delay_msg_received.addDomain(msg_publisher_received);
            //delay_msg_received.addDomain(msg_publisher_send);
            delay_msg_received.addDomain(msg_subscriber_received);
            //delay_msg_received.addDomain(msg_subscriber_send);

            DatatypeProperty address_ip = model.createDatatypeProperty(NS + ":" + "address_ip");
            address_ip.addDomain(gateway);
            address_ip.addDomain(publisher);
            address_ip.addDomain(subscriber);
            address_ip.addDomain(msg_gateway_received);
            address_ip.addDomain(msg_gateway_send);
            address_ip.addDomain(msg_publisher_received);
            address_ip.addDomain(msg_publisher_send);
            address_ip.addDomain(msg_subscriber_received);
            address_ip.addDomain(msg_subscriber_send);


            DatatypeProperty address_port = model.createDatatypeProperty(NS + ":" + "address_port");
            address_port.addDomain(gateway);
            address_port.addDomain(publisher);
            address_port.addDomain(subscriber);
            address_port.addDomain(msg_gateway_received);
            address_port.addDomain(msg_gateway_send);
            address_port.addDomain(msg_publisher_received);
            address_port.addDomain(msg_publisher_send);
            address_port.addDomain(msg_subscriber_received);
            address_port.addDomain(msg_subscriber_send);


            DatatypeProperty address_ip_local_msg = model.createDatatypeProperty(NS + ":" + "address_ip_local_msg");

            address_ip_local_msg.addDomain(msg_gateway_received);
            address_ip_local_msg.addDomain(msg_gateway_send);
            address_ip_local_msg.addDomain(msg_publisher_received);
            address_ip_local_msg.addDomain(msg_publisher_send);
            address_ip_local_msg.addDomain(msg_subscriber_received);
            address_ip_local_msg.addDomain(msg_subscriber_send);


            DatatypeProperty address_port_local_msg = model.createDatatypeProperty(NS + ":" + "address_port_local_msg");
            address_port_local_msg.addDomain(msg_gateway_received);
            address_port_local_msg.addDomain(msg_gateway_send);
            address_port_local_msg.addDomain(msg_publisher_received);
            address_port_local_msg.addDomain(msg_publisher_send);
            address_port_local_msg.addDomain(msg_subscriber_received);
            address_port_local_msg.addDomain(msg_subscriber_send);

            DatatypeProperty msg_size = model.createDatatypeProperty(NS + ":" + "msg_size");
            msg_size.addDomain(msg_gateway_received);
            msg_size.addDomain(msg_gateway_send);
            msg_size.addDomain(msg_publisher_received);
            msg_size.addDomain(msg_publisher_send);
            msg_size.addDomain(msg_subscriber_received);
            msg_size.addDomain(msg_subscriber_send);

            DatatypeProperty msg_number = model.createDatatypeProperty(NS + ":" + "msg_number");
            msg_number.addDomain(msg_gateway_received);
            msg_number.addDomain(msg_gateway_send);
            msg_number.addDomain(msg_publisher_received);
            msg_number.addDomain(msg_publisher_send);
            msg_number.addDomain(msg_subscriber_received);
            msg_number.addDomain(msg_subscriber_send);

            DatatypeProperty msg_type = model.createDatatypeProperty(NS + ":" + "msg_type");
            msg_type.addDomain(msg_gateway_received);
            msg_type.addDomain(msg_gateway_send);
            msg_type.addDomain(msg_publisher_received);
            msg_type.addDomain(msg_publisher_send);
            msg_type.addDomain(msg_subscriber_received);
            msg_type.addDomain(msg_subscriber_send);

            // Tipo de Propiedad ------------------------------------------------ //

            // Id
            id_topic.addRange(XSD.xint);//Tipo de la propiedad
            id_topic.convertToFunctionalProperty();//Para que solo acepte un valor.

            id_topic_gateway.addRange(XSD.xint);//Tipo de la propiedad
            id_topic_gateway.convertToFunctionalProperty();//Para que solo acepte un valor.

            id_topic_publisher.addRange(XSD.xint);//Tipo de la propiedad
            id_topic_publisher.convertToFunctionalProperty();//Para que solo acepte un valor.

            id_topic_subscriber.addRange(XSD.xint);//Tipo de la propiedad
            id_topic_subscriber.convertToFunctionalProperty();//Para que solo acepte un valor.

            id_gateway.addRange(XSD.xint);//Tipo de la propiedad
            id_gateway.convertToFunctionalProperty();//Para que solo acepte un valor.

            id_publisher.addRange(XSD.xint);//Tipo de la propiedad
            id_publisher.convertToFunctionalProperty();//Para que solo acepte un valor.

            id_subscriber.addRange(XSD.xint);//Tipo de la propiedad
            id_subscriber.convertToFunctionalProperty();//Para que solo acepte un valor.

            // Status Topic
            status_topic.addRange(XSD.xstring);//Tipo de la propiedad
            status_topic.convertToFunctionalProperty();//Para que solo acepte un valor.

            // Name Topic
            name_topic.addRange(XSD.xstring);//Tipo de la propiedad
            name_topic.convertToFunctionalProperty();//Para que solo acepte un valor.

            // Value Topic
            value_topic.addRange(XSD.xstring);//Tipo de la propiedad
            value_topic.convertToFunctionalProperty();//Para que solo acepte un valor.

            // Time : Wsn
            time.addRange(XSD.xlong);//Tipo de la propiedad
            time.convertToFunctionalProperty();//Para que solo acepte un valor.

            // Time Update : Wsn
            time_update.addRange(XSD.xlong);//Tipo de la propiedad
            time_update.convertToFunctionalProperty();//Para que solo acepte un valor.


            // MSG - time_received / time_emission / delay_msg_received / address_ip / address_port / msg_number / msg_size

            time_received.addRange(XSD.xstring);
            time_received.convertToFunctionalProperty();

            time_emission.addRange(XSD.xstring);
            time_emission.convertToFunctionalProperty();

            delay_msg_received.addRange(XSD.xstring);
            delay_msg_received.convertToFunctionalProperty();

            address_ip.addRange(XSD.xstring);
            address_ip.convertToFunctionalProperty();

            address_port.addRange(XSD.xlong);
            address_port.convertToFunctionalProperty();

            msg_size.addRange(XSD.xlong);
            msg_size.convertToFunctionalProperty();

            msg_number.addRange(XSD.xlong);
            msg_number.convertToFunctionalProperty();

            msg_type.addRange(XSD.xstring);
            msg_type.convertToFunctionalProperty();


            Individual time_wsn = model.createIndividual(NS + ":" + "time", Wsn);

            //timeUpdatePlugwise.setPropertyValue(time_plugwise_update, model.createTypedLiteral(new java.sql.Timestamp(new java.util.Date().getTime())));
            time_wsn.setPropertyValue(time, model.createTypedLiteral(new java.util.Date().getTime()));

            int contadorMsg = 0;
            File fFile = new File("/home/reols/Omnet/omnetpp-4.1/samples/AppPubSub/src/file.txt");
            Scanner scannerFile = new Scanner(new FileReader(fFile));
            //System.out.println("Entra Contador");
            //while (scannerFile.hasNextLine()) {
            //  contadorMsg++;
            //  System.out.println("Contador:" + contadorMsg);
            // }

            //scannerFile = new Scanner(new FileReader(fFile));
            //System.out.println("Sale Contador");

            try {
                //first use a Scanner to get each line
                while (scannerFile.hasNextLine()) {
                    //processLineText(scanner.nextLine());

                    Scanner scanner = new Scanner(scannerFile.nextLine());
                    scanner.useDelimiter("_");
                    if (scanner.hasNext()) {
                        String numberPacket = scanner.next();
                        if (numberPacket.equalsIgnoreCase("publisher") || numberPacket.equalsIgnoreCase("subscriber")) {
                            String topicID = scanner.next();
                            scanner.next();
                            String timeData = scanner.next();
                            System.out.println(numberPacket + " " + topicID + " " + timeData);
                        } else {
                            String receivedOrSendPacket = scanner.next();
                            String typePacket = scanner.next();
                            scanner.next();
                            String addressIP = scanner.next();
                            scanner.next();
                            String portIP = scanner.next();
                            scanner.next();
                            String timeReceived = scanner.next();
                            scanner.next();
                            String timeEmision = scanner.next();
                            scanner.next();
                            String delayMsgReceived = scanner.next();

                            System.out.println("" + numberPacket + " " + receivedOrSendPacket + " " + typePacket + " " + addressIP + " " + portIP + " " + timeReceived + " " + timeEmision + " " + delayMsgReceived);
                            Individual packetGatewayReceived = model.createIndividual(NS + ":" + "msg_gateway_received_" + numberPacket, msg_gateway_received);
                            packetGatewayReceived.setPropertyValue(msg_number, model.createTypedLiteral(Long.parseLong(numberPacket)));
                            packetGatewayReceived.setPropertyValue(msg_type, model.createTypedLiteral(typePacket));
                            packetGatewayReceived.setPropertyValue(address_ip, model.createTypedLiteral(addressIP));
                            packetGatewayReceived.setPropertyValue(address_port, model.createTypedLiteral(Long.parseLong(portIP)));
                            packetGatewayReceived.setPropertyValue(time_emission, model.createTypedLiteral(timeEmision));
                            packetGatewayReceived.setPropertyValue(time_received, model.createTypedLiteral(timeReceived));
                            packetGatewayReceived.setPropertyValue(delay_msg_received, model.createTypedLiteral(delayMsgReceived));

                        }
                    } else {
                        System.out.println("Empty or invalid line. Unable to process.");
                    }
                    scanner.close();


                }
            } finally {
                //ensure the underlying stream is always closed
                //this only has any effect if the item passed to the Scanner
                //constructor implements Closeable (which it does in this case).

                scannerFile.close();
            }

            //Almacenamos la ontología en un fichero OWL (Opcional)
            File file = new File(getDataForWriting("createOntology"));
            //File file = new File("/Users/" + new rlaino.ftw.plugwise.action.DefineNameUserPlugwise().getUserName() + "/Documents/NetBeansProjects/PlugwiseDB2/Ontologies/Plugwise_Onto.owl");
            //File file = new File("/Users/Raúl/Documents/NetBeansProjects/PlugwiseDB2/Ontologies/Plugwise_Onto.owl");

            //Hay que capturar las Excepciones
            if (!file.exists()) {
                file.createNewFile();
            }

            model.validate();

            model.write(new PrintWriter(file));

            //Output System.out.println or Shell
            //System.out.println("\nFirst Output:\n");
            //model.write(System.out);
            //System.out.println("\nSecond Output:\n");
            //model.write(System.out, "N-TRIPLE");
            //System.out.println("\nThird Output:\n");
            //model.write(System.out, "N3");
            //System.out.println("\nFourth Output:\n");
            //model.write(System.out, "RDF/XML-ABBREV");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String getDataForWriting(String functionSelect) throws IOException {

        File fileA = null;
        File fileB = null;
        Long lastModifiedA = null;
        Long lastModifiedB = null;

        if (functionSelect.equalsIgnoreCase("createOntology")) {
            fileA = new File("ontologies/WsnMqtts_A.owl");
            fileB = new File("ontologies/WsnMqtts_B.owl");
            //fileA = new File("C:/Users/Raúl/Documents/NetBeansProjects/PlugwiseDB2/Ontologies/Plugwise_Onto_A.owl");
            //fileB = new File("C:/Users/Raúl/Documents/NetBeansProjects/PlugwiseDB2/Ontologies/Plugwise_Onto_B.owl");
        }

        if (!fileB.exists()) {
            fileB.createNewFile();
        }
        lastModifiedA = fileA.lastModified();
        if (!fileA.exists()) {
            fileA.createNewFile();
        }
        lastModifiedB = fileB.lastModified();

        if (functionSelect.equalsIgnoreCase("createOntology")) {
            if (lastModifiedB > lastModifiedA) {
                return "ontologies/WsnMqtts_A.owl";
                //return "C:/Users/Raúl/Documents/NetBeansProjects/PlugwiseDB2/Ontologies/Plugwise_Onto_A.owl";
            } else {
                return "ontologies/WsnMqtts_B.owl";
                //return "C:/Users/Raúl/Documents/NetBeansProjects/PlugwiseDB2/Ontologies/Plugwise_Onto_B.owl";
            }
        } else {
            return null;
        }

    }

    public void insertIndividuals() throws IOException {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.read("file:" + new readOntology().getDataForReading("readOntology"), "RDF/XML-ABBREV");


    }

    public static void main(String[] args) {
        try {
            System.out.println(" -> Run Ontology");
            new createOntology().create();
        } catch (Exception e) {
            System.out.println("\nError Exception: \n" + e);
        }
    }
}
