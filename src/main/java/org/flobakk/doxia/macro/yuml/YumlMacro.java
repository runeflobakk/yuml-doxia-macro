package org.flobakk.doxia.macro.yuml;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.text.StrSubstitutor;
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
        sink.figure(null);
        sink.figureGraphics(buildYumlMeRequestUrl(request), null);
        sink.figure_();
    }


    private String buildYumlMeRequestUrl(MacroRequest request) {
        @SuppressWarnings("unchecked")
        ConcurrentMap<String, String> params =
            new ConcurrentHashMap<String, String>(request.getParameters());

        required("model", params.get("model"));
        params.putIfAbsent("type", "class");
        params.putIfAbsent("style", "scruffy");
        params.putIfAbsent("size", "100");
        params.putIfAbsent("direction", "lr");

        return new StrSubstitutor(params).replace("http://yuml.me/diagram/${style}/${type}/${model}");
    }

}
