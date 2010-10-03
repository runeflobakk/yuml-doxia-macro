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

    public String[] getRequiredParameters() {
        return new String[] {"model"};
    }

    public String buildFrom(Map<String, String> parameters) throws IllegalArgumentException {
        ConcurrentMap<String, String> params =
            new ConcurrentHashMap<String, String>(parameters);

        for (String param : getRequiredParameters()) {
            if (!params.containsKey(param)) {
                throw new IllegalArgumentException("Parameter " + param + " is required!");
            }
        }
        params.putIfAbsent("type", "class");
        params.putIfAbsent("style", "scruffy");
        params.putIfAbsent("size", "100");
        params.putIfAbsent("direction", "lr");

        return baseUrl + replace("${style}/${type}/${model}", params);
    }
}
