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
        assertYumlMeUrlMatches(BASE_URL + "scruffy.*/class/\\[SomeClass\\]");
    }

    @Test
    public void shouldPutDiagramTypeIntoRequestUrl() {
        parameters.put("model", "(start)->(Show Dashboard)->(end)");
        parameters.put("type", "activity");
        assertYumlMeUrlMatches(BASE_URL + "scruffy.*/activity/\\(start\\)->\\(Show Dashboard\\)->\\(end\\)");
    }

    @Test
    public void shouldHandleStyleAndSizeAndDirection() {
        parameters.put("model", "[SomeClass]");
        parameters.put("style", "plain");
        parameters.put("scale", "75");
        parameters.put("direction", "td");
        assertYumlMeUrlMatches(BASE_URL + "plain;scale:75;dir:td/class/\\[SomeClass\\]");
    }


    private void assertYumlMeUrlMatches(String expectedRegex) {
        assertThat(requestUrl.buildFrom(parameters), matches(expectedRegex));
    }


    private static Matcher<Object> matches(String string) {
        return new Matches(string);
    }

}
