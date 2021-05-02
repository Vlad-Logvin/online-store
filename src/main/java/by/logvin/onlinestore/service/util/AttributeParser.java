package by.logvin.onlinestore.service.util;

import by.logvin.onlinestore.bean.Attribute;

import java.util.List;
import java.util.Map;

/**
 * The AttributeParser interface is used for attribute parsing
 *
 * @author bylogvin
 */
public interface AttributeParser {

    /**
     * The method parse provides attribute parsing
     *
     * @param input {@link String} parsed string of attribute
     * @return {@link Map} of attributes, where key: attributes name, value: attribute value
     */
    Map<String, String> parse(String input);

    /**
     * The method unparse provides attribute unparsing
     *
     * @param attributes {@link Map} of attributes, where key: attributes name, value: attribute value
     * @return {@link String} parsed string of attribute
     */
    String unparse(List<Attribute> attributes);
}
