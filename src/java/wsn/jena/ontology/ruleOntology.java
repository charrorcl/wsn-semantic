/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wsn.jena.ontology;

/**
 *
 * @author reols
 */
public class ruleOntology {

    public static void main(String[] args) {
        try {
            System.out.println(" -> Run Read Ontology");
            new readOntology().read();
        } catch (Exception e) {
            System.out.println("\nError Exception: \n" + e);
        }
    }
}
