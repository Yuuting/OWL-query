package jena;
import java.util.Iterator;
import java.util.List;
import java.io.*;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;

public class jena3 {
	public static void main(String[] args) {
    	final String inputFileName = "~Uploaded_DomainOntology-NMS.owl";
    	 // create an empty model
    	 OntModel model = ModelFactory.createOntologyModel();

    	 // use the RDFDataMgr to find the input file
    	 InputStream in = RDFDataMgr.open(inputFileName);
    	if (in == null) {
    	    throw new IllegalArgumentException(
    	                                 "File: " + inputFileName + " not found");
    	}

    	// read the RDF/XML file
    	model.read(in, null);

    	// write it to standard out
    	model.write(System.out);
    	// list the statements in the graph
        StmtIterator iter = model.listStatements();
        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject(); // get the subject
            Property predicate = stmt.getPredicate(); // get the predicate
            RDFNode object = stmt.getObject(); // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");
            
        }
    	OntClass SubnetB = model.getOntClass("http://www.owl-ontologies.com/Ontology1179610702.owl#Subnet-B");
    	for (Iterator i = SubnetB.listSubClasses(); i.hasNext(); ) {
    	  OntClass c = (OntClass) i.next();
    	  System.out.print( c.getLocalName() + " " );
    	}
    	System.out.print("\n");
    	for (Iterator i = model.listClasses(); i.hasNext(); ) {
      	  OntClass c = (OntClass) i.next();
      	  System.out.print( c.getLocalName() + " " );
      	}
    	System.out.print("\n");
    	
}
}
