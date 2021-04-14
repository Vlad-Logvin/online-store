package by.logvin.onlinestore.service.util.impl;

import by.logvin.onlinestore.bean.Attribute;
import by.logvin.onlinestore.service.util.AttributeParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeParserImpl implements AttributeParser {
    private final static Pattern ATTRIBUTE_REGEX = Pattern.compile("'(.+?)'\\s*=\\s*'(.+?)'");

    @Override
    public Map<String, String> parse(String input) {
        Map<String, String> attributes = new HashMap<>();
        Matcher matcher = ATTRIBUTE_REGEX.matcher(input);
        while (matcher.find()) {
            attributes.put(matcher.group(1), matcher.group(2));
        }
        return attributes;
    }

    @Override
    public String unparse(List<Attribute> attributes) {
        if (attributes == null) {
            return null;
        }
        StringBuilder attributeBuilder = new StringBuilder();
        for (Attribute a : attributes) {
            attributeBuilder.append("\'" + a.getName()  + "\'" +" = " + "\'" +a.getValue() + "\'" + " ");

        }
        return attributeBuilder.toString();
    }
}
