import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;

import java.io.StringWriter;

public class Utils {

    public static final String VALIDATION_REPORT = "src\\main\\resources\\report.ttl";

    public static final ValueFactory vf = SimpleValueFactory.getInstance();

    public static String modelToString(Model model, RDFFormat syntax) {
        StringWriter writer = new StringWriter();
        RDFWriter rdfWriter = Rio.createWriter(syntax, writer);
        Rio.write(model, rdfWriter);
        return writer.toString();
    }
}
