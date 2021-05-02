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
    private final static String QUOTE = "'";
    private static final String SPACE = " ";
    private static final String EQUAL_BETWEEN_SPACES = " = ";

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
            attributeBuilder.append(QUOTE).append(a.getName()).append(QUOTE).append(EQUAL_BETWEEN_SPACES).append(QUOTE).append(a.getValue()).append(QUOTE).append(SPACE);
        }
        return attributeBuilder.toString();
    }
}
