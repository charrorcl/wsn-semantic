/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wsn.jena.ontology;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author reols
 */
public class readOntology {

    public void read() throws IOException {

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.read("file:" + getDataForReading("readOntology"), "RDF/XML-ABBREV");

        //Output System.out.println or Shell
        //System.out.println("\nFirst Output:\n");
        //model.write(System.out);
        //System.out.println("\nSecond Output:\n");
        //model.write(System.out, "N-TRIPLE");
        System.out.println("\nThird Output:\n");
        model.write(System.out, "N3");
        //System.out.println("\nFourth Output:\n");
        //model.write(System.out, "RDF/XML-ABBREV");

    }

    public void insertMsg() throws IOException {
        String NS = "wsn";
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.read("file:" + getDataForReading("readOntology"), "RDF/XML-ABBREV");

        //Output System.out.println or Shell
        //System.out.println("\nFirst Output:\n");
        //model.write(System.out);
        //System.out.println("\nSecond Output:\n");
        //model.write(System.out, "N-TRIPLE");
        System.out.println("\nThird Output:\n");
        model.write(System.out, "N3");
        //System.out.println("\nFourth Output:\n");
        //model.write(System.out, "RDF/XML-ABBREV");

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

        DatatypeProperty id_topic = model.createDatatypeProperty(NS + ":" + "id_topic");
        DatatypeProperty id_topic_gateway = model.createDatatypeProperty(NS + ":" + "id_topic_gateway");
        DatatypeProperty id_topic_publisher = model.createDatatypeProperty(NS + ":" + "id_topic_publisher");
        DatatypeProperty id_topic_subscriber = model.createDatatypeProperty(NS + ":" + "id_topic_subscriber");
        DatatypeProperty id_gateway = model.createDatatypeProperty(NS + ":" + "id_gateway");
        DatatypeProperty id_publisher = model.createDatatypeProperty(NS + ":" + "id_publisher");
        DatatypeProperty id_subscriber = model.createDatatypeProperty(NS + ":" + "id_subscriber");
        DatatypeProperty status_topic = model.createDatatypeProperty(NS + ":" + "status_topic");
        DatatypeProperty name_topic = model.createDatatypeProperty(NS + ":" + "name_topic");
        DatatypeProperty value_topic = model.createDatatypeProperty(NS + ":" + "value_topic");
        DatatypeProperty time = model.createDatatypeProperty(NS + ":" + "time");
        DatatypeProperty time_update = model.createDatatypeProperty(NS + ":" + "time_update");
        DatatypeProperty time_received = model.createDatatypeProperty(NS + ":" + "time_received");
        DatatypeProperty time_emission = model.createDatatypeProperty(NS + ":" + "time_emission");
        DatatypeProperty delay_msg_received = model.createDatatypeProperty(NS + ":" + "delay_msg_received");
        DatatypeProperty address_ip = model.createDatatypeProperty(NS + ":" + "address_ip");
        DatatypeProperty address_port = model.createDatatypeProperty(NS + ":" + "address_port");
        DatatypeProperty address_ip_local_msg = model.createDatatypeProperty(NS + ":" + "address_ip_local_msg");
        DatatypeProperty address_port_local_msg = model.createDatatypeProperty(NS + ":" + "address_port_local_msg");
        DatatypeProperty msg_size = model.createDatatypeProperty(NS + ":" + "msg_size");
        DatatypeProperty msg_number = model.createDatatypeProperty(NS + ":" + "msg_number");
        DatatypeProperty msg_type = model.createDatatypeProperty(NS + ":" + "msg_type");

        ExtendedIterator iteratorClasses = model.listClasses();


        // Add - Classes
        int ii = 0;
        while (iteratorClasses.hasNext()) {

            OntClass ontclass = (OntClass) iteratorClasses.next();
            if ("wsn:WSN".equalsIgnoreCase(ontclass.toString())) {
                Wsn = ontclass;
            } else if ("wsn:topic".equalsIgnoreCase(ontclass.toString())) {
                topic = ontclass;
            } else if ("wsn:topic_publisher".equalsIgnoreCase(ontclass.toString())) {
                topic_publisher = ontclass;
            }else if ("wsn:topic_subscriber".equalsIgnoreCase(ontclass.toString())) {
                topic_subscriber = ontclass;
            } else if ("wsn:topic_publisher_gateway".equalsIgnoreCase(ontclass.toString())) {
                topic_publisher_gateway = ontclass;
            } else if ("wsn:topic_subscriber_gateway".equalsIgnoreCase(ontclass.toString())) {
                topic_subscriber_gateway = ontclass;
            } else if ("wsn:gateway".equalsIgnoreCase(ontclass.toString())) {
                gateway = ontclass;
            } else if ("wsn:publisher".equalsIgnoreCase(ontclass.toString())) {
                publisher = ontclass;
            } else if ("wsn:subscriber".equalsIgnoreCase(ontclass.toString())) {
                subscriber = ontclass;
            } else if ("wsn:msg_gateway_received".equalsIgnoreCase(ontclass.toString())) {
                msg_gateway_received = ontclass;
            } else if ("wsn:msg_gateway_send".equalsIgnoreCase(ontclass.toString())) {
                msg_gateway_send = ontclass;
            } else if ("wsn:msg_publisher_received".equalsIgnoreCase(ontclass.toString())) {
                msg_publisher_received = ontclass;
            } else if ("wsn:msg_publisher_send".equalsIgnoreCase(ontclass.toString())) {
                msg_publisher_send = ontclass;
            } else if ("wsn:msg_subscriber_received".equalsIgnoreCase(ontclass.toString())) {
                msg_subscriber_received = ontclass;
            } else if ("wsn:msg_subscriber_send".equalsIgnoreCase(ontclass.toString())) {
                msg_subscriber_send = ontclass;
            }

            ii++;
        }
        System.out.println("ii ListClasses Model:" + ii);


        



    }

    public String getDataForReading(String functionSelect) throws IOException {

        File fileA = null;
        File fileB = null;
        Long lastModifiedA = null;
        Long lastModifiedB = null;

        if (functionSelect.equalsIgnoreCase("readOntology")) {
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

        if (functionSelect.equalsIgnoreCase("readOntology")) {
            if (lastModifiedB < lastModifiedA) {
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

    public static void main(String[] args) {
        try {
            System.out.println(" -> Run Read Ontology");
            new readOntology().read();
        } catch (Exception e) {
            System.out.println("\nError Exception: \n" + e);
        }
    }
}
