import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        Model model = RDF4JValidation.validation(
                "src/main/resources/2024/data.ttl",
                "src/main/resources/2024/shapes.ttl",
                "src/main/resources/2024/report.ttl"
        );

        System.out.println(Utils.modelToString(model, RDFFormat.TURTLE));
    }
}
