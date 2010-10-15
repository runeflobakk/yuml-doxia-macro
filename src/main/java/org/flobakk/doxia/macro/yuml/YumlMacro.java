package org.flobakk.doxia.macro.yuml;

import java.util.Map;

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

    @Override
    public void execute(Sink sink, MacroRequest request) throws MacroExecutionException {
        @SuppressWarnings("unchecked")
        Map<String, String> parameters = request.getParameters();
        String url = new YumlMeRequestUrl("http://yuml.me/diagram/").buildFrom(parameters);

        sink.figure(null);
        sink.figureGraphics(url, null);
        sink.figure_();
    }
}
