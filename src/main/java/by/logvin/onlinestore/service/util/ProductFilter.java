package by.logvin.onlinestore.service.util;

import by.logvin.onlinestore.bean.Criteria;
import by.logvin.onlinestore.bean.Product;

import java.util.List;

public interface ProductFilter {
    List<Product> filter(List<Product> products, Criteria criteria);
}
