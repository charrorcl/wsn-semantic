/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wsn.omnetpp.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author raul
 */
public class parserTextOmnetpp {

    public static void main(String... aArgs) throws FileNotFoundException {
        //parserTextOmnetpp parser = new parserTextOmnetpp("C:\\Users\\raul\\Documents\\NetBeansProjects\\WSN_MQTTS\\file_in\\file.txt");

        //Windows 7
        //parserTextOmnetpp parser = new parserTextOmnetpp("file_in\\file.txt");

        //Linux
        //parserTextOmnetpp parser = new parserTextOmnetpp("file_in/file.txt");

        //Linux Omnetpp /home/reols/Omnet/omnetpp-4.1/samples/AppPubSub/src
        parserTextOmnetpp parser = new parserTextOmnetpp("/home/reols/Omnet/omnetpp-4.1/samples/AppPubSub/src/file.txt");


        parser.processLineByLineText();
        log("Done.");
    }

    /**
    Constructor.
    @param aFileName full name of an existing, readable file.
     */
    public parserTextOmnetpp(String aFileName) {
        fFile = new File(aFileName);
    }

    /** Template method that calls {@link #processLine(String)}.  */
    public final void processLineByLineText() throws FileNotFoundException {
        //Note that FileReader is used, not File, since File is not Closeable
        Scanner scanner = new Scanner(new FileReader(fFile));
        try {
            //first use a Scanner to get each line
            while (scanner.hasNextLine()) {
                processLineText(scanner.nextLine());
            }
        } finally {
            //ensure the underlying stream is always closed
            //this only has any effect if the item passed to the Scanner
            //constructor implements Closeable (which it does in this case).
            scanner.close();
        }
    }

    /** Template method that calls {@link #processLine(String)}.  */
    public final void processLineByLine() throws FileNotFoundException {
        //Note that FileReader is used, not File, since File is not Closeable
        Scanner scanner = new Scanner(new FileReader(fFile));
        try {
            //first use a Scanner to get each line
            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine());
            }
        } finally {
            //ensure the underlying stream is always closed
            //this only has any effect if the item passed to the Scanner
            //constructor implements Closeable (which it does in this case).
            scanner.close();
        }
    }

    /** 
    Overridable method for processing lines in different ways.
    
    <P>This simple default implementation expects simple name-value pairs, separated by an 
    '=' sign. Examples of valid input : 
    <tt>height = 167cm</tt>
    <tt>mass =  65kg</tt>
    <tt>disposition =  "grumpy"</tt>
    <tt>this is the name = this is the value</tt>
     */
    protected void processLine(String aLine) {
        //use a second Scanner to parse the content of each line 
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter("=");
        if (scanner.hasNext()) {
            String name = scanner.next();
            String value = scanner.next();
            log("Name is : " + quote(name.trim()) + ", and Value is : " + quote(value.trim()));
        } else {
            log("Empty or invalid line. Unable to process.");
        }
        //no need to call scanner.close(), since the source is a String
    }

    /** 
    Overridable method for processing lines in different ways.
    
     */
    protected void processLineText(String aLine) {
        //use a second Scanner to parse the content of each line 
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter("_");
        if (scanner.hasNext()) {
            String numberPacket = scanner.next();
            if (numberPacket.equalsIgnoreCase("publisher") || numberPacket.equalsIgnoreCase("subscriber")) {
                String topicID = scanner.next();
                scanner.next();
                String timeData = scanner.next();
                log(numberPacket + " " + topicID + " " + timeData);
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

                log("" + numberPacket + " " + receivedOrSendPacket + " " + typePacket + " " + addressIP + " " + portIP + " " + timeReceived + " " + timeEmision + " " + delayMsgReceived);
                //log("Name is : " + quote(name.trim()) + ", and Value is : " + quote(value.trim()) );
            }
        } else {
            log("Empty or invalid line. Unable to process.");
        }
        //no need to call scanner.close(), since the source is a String
    }
    // PRIVATE 
    private final File fFile;

    private static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

    private String quote(String aText) {
        String QUOTE = "'";
        return QUOTE + aText + QUOTE;
    }
}
