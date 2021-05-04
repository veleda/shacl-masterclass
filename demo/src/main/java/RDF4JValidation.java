import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.vocabulary.RDF4J;
import org.eclipse.rdf4j.model.vocabulary.SHACL;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.sail.shacl.ShaclSail;
import org.eclipse.rdf4j.sail.shacl.ShaclSailValidationException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class RDF4JValidation {

    public static Model validation(String dataGraph, String shapes) throws IOException {

        // init shacl repository
        ShaclSail shaclSail = new ShaclSail(new MemoryStore());
        Repository repository = new SailRepository(shaclSail);
        repository.init();

        // init model for validation report
        Model validationReport = new LinkedHashModel();
        validationReport.setNamespace(SHACL.NS);


        try (RepositoryConnection connection = repository.getConnection()) {

            FileOutputStream out = new FileOutputStream(Utils.VALIDATION_REPORT);
            Reader shaclRules = new FileReader(shapes);

            // adding shapes
            connection.begin();
            connection.add(shaclRules, "", RDFFormat.TURTLE, RDF4J.SHACL_SHAPE_GRAPH);
            connection.commit();

            // adding data
            connection.begin();
            Reader data = new FileReader(dataGraph);
            connection.add(data, "", RDFFormat.TURTLE);

            try {
                connection.commit();

            // if validation conforms false, gather validation report
            } catch (RepositoryException e) {
                Throwable cause = e.getCause();
                if (cause instanceof ShaclSailValidationException) {
                    validationReport = ((ShaclSailValidationException) cause).validationReportAsModel();

                    Rio.write(validationReport, out, RDFFormat.TURTLE);
                    System.out.println("[INFO] Validation report conforming false produced");
                    return validationReport;
                }
            }
            System.out.println("[INFO] Validation report conforming true produced");
            validationReport.add(Utils.vf.createBNode(), SHACL.CONFORMS, Utils.vf.createLiteral(true));
            Rio.write(validationReport, out, RDFFormat.TURTLE);
        }
        return validationReport;
    }
}
