package com.coderhouse.ProyectoFinal_PrimeraEntrega.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Field;
import java.util.List;

public class Utils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> ObjectNode excludeFields(T obj, List<String> fieldsToExclude) {
            ObjectNode node = mapper.valueToTree(obj);
            for (String fieldName : fieldsToExclude) {
                node.remove(fieldName);
            }
        return node; }

    public static <T> T toDTO(ObjectNode node, Class<T> clazz) {
        return mapper.convertValue(node, clazz);
    }
}

