package org.flobakk.doxia.macro.yuml;

import static org.apache.commons.lang.text.StrSubstitutor.replace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class YumlMeRequestUrl {

    private final String baseUrl;


    public YumlMeRequestUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public String buildFrom(Map<String, String> inputParameters) throws IllegalArgumentException {
        requireIn(inputParameters, "model");
        ConcurrentMap<String, String> completeParameters = populateDefaults(new ConcurrentHashMap<String, String>(inputParameters));
        return baseUrl + replace("${style};scale:${scale};dir:${direction}/${type}/${model}", completeParameters);
    }


    private <M extends ConcurrentMap<String, String>> M populateDefaults(final M parameters) {
        parameters.putIfAbsent("type", "class");
        parameters.putIfAbsent("style", "scruffy");
        parameters.putIfAbsent("scale", "100");
        parameters.putIfAbsent("direction", "lr");
        return parameters;
    }


    private static void requireIn(Map<String, String> parameters, String key) {
        if (!parameters.containsKey(key)) {
            throw new IllegalArgumentException("Parameter '" + key + "' is required!");
        }
    }
}
