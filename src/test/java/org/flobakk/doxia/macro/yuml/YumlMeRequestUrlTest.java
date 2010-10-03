package org.flobakk.doxia.macro.yuml;

import static org.junit.Assert.assertThat;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Matches;


public class YumlMeRequestUrlTest {

    private static final String BASE_URL = "http://yuml.me/diagram/";

    private Map<String, String> parameters;

    private YumlMeRequestUrl requestUrl;

    @Before
    public void setupParameters() {
        parameters = new HashMap<String, String>();
        requestUrl = new YumlMeRequestUrl(BASE_URL);
    }

    @Test(expected=IllegalArgumentException.class)
    public void modelParameterIsRequired() {
        requestUrl.buildFrom(parameters);
    }

    @Test
    public void shouldConstructAScruffyClassDiagramUrlWhenOnlySpecifyingModel() {
        parameters.put("model", "[SomeClass]");
        assertThat(requestUrl.buildFrom(parameters), matches(BASE_URL + "scruffy/class/\\[SomeClass\\]"));
    }

    @Test
    public void shouldPutDiagramTypeIntoRequestUrl() {
        parameters.put("model", "[SomeClass]");
        parameters.put("type", "activity");
        assertThat(requestUrl.buildFrom(parameters), matches(BASE_URL + "scruffy.*/activity/\\[SomeClass\\]"));
    }


    private static Matcher<Object> matches(String string) {
        return new Matches(string);
    }

}
