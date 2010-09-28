package no.rune.doxia.macro.yuml;

import static org.apache.commons.lang.StringUtils.defaultString;

import org.apache.maven.doxia.macro.AbstractMacro;
import org.apache.maven.doxia.macro.MacroExecutionException;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.sink.Sink;
/**
 * A macro to draw UML diagrams using the <a href="http://yuml.me">yuml.me</a> website.
 *
 * @plexus.component role="org.apache.maven.doxia.macro.Macro" role-hint="yuml"
 */
public class YumlMacro extends AbstractMacro {

    private static final String BASE_URL = "http://yuml.me/diagram/";



    @Override
    public void execute(Sink sink, MacroRequest request) throws MacroExecutionException {
        String model = (String)request.getParameter("model");
        String type = defaultString((String)request.getParameter("type"), "class");
        String style = defaultString((String)request.getParameter("style"), "scruffy");
        String size = defaultString((String)request.getParameter("size"), "100");
        String direction = defaultString((String)request.getParameter("direction"), "lr");

        required("model", model);

        sink.figure(null);
        sink.figureGraphics(BASE_URL + style + "/" + type + "/" + model, null);
        sink.figure_();

    }

}
