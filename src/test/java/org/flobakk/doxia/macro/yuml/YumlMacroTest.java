package org.flobakk.doxia.macro.yuml;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.doxia.macro.MacroExecutionException;
import org.apache.maven.doxia.macro.MacroRequest;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkEventAttributes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class YumlMacroTest {

    @Mock private Sink sink;

    private MacroRequest request;

    private Map<String, String> parameters;

    @Before
    public void setupRequest() {
        parameters = new HashMap<String, String>();
        request = new MacroRequest(parameters, new File("."));
    }

    @Test(expected=IllegalArgumentException.class)
    public void modelParameterIsRequired() throws MacroExecutionException {
        new YumlMacro().execute(sink, request);
    }

    @Test
    public void shouldCallFigureGraphics() throws MacroExecutionException {
        parameters.put("model", "[SomeClass]");
        new YumlMacro().execute(sink, request);
        verify(sink).figureGraphics(anyString(), any(SinkEventAttributes.class));
    }

}
