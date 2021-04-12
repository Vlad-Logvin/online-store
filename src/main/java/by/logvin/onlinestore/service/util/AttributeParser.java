package by.logvin.onlinestore.service.util;

import by.logvin.onlinestore.bean.Attribute;

import java.util.List;
import java.util.Map;

public interface AttributeParser {
    Map<String, String> parse(String input);
    String unparse(List<Attribute> attributes);
}
