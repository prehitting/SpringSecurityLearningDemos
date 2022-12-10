package com.prehitting.db.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName YamlUtil
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/7 9:51
 * @Version 1.0
 */
public class YamlUtil {

    private YamlUtil(){}

    public static Object getValFromYaml(String key,String classpath) {
        Map<String, Object> resource = getResource(classpath);
        return resource.get(key);
    }
    //读取文件
    private static Map<String, Object> getResource(String classPath) {
        ClassPathResource resource = new ClassPathResource(classPath);
        InputStream inputStream;
        Yaml yaml = new Yaml();
        Map<String, Object> res = new LinkedHashMap<>();
        try {
            inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Map<String, Object> map = yaml.load(reader);
            buildFlattenedMap(res, asMap(map), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    //转为map
    private static Map<String, Object> asMap(Object object) {
        // YAML can have numbers as keys
        Map<String, Object> result = new LinkedHashMap<>();
        if (!(object instanceof Map)) {
            // A document can be a text literal
            result.put("document", object);
            return result;
        }

        Map<Object, Object> map = (Map<Object, Object>) object;
        map.forEach((key, value) -> {
            if (value instanceof Map) {
                value = asMap(value);
            }
            if (key instanceof CharSequence) {
                result.put(key.toString(), value);
            } else {
                // It has to be a map key in this case
                result.put("[" + key.toString() + "]", value);
            }
        });
        return result;
    }


    private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String path) {
        source.forEach((key, value) -> {
            if (org.springframework.util.StringUtils.hasText(path)) {
                if (key.startsWith("[")) {
                    key = path + key;
                } else {
                    key = path + '.' + key;
                }
            }
            if (value instanceof String) {
                result.put(key, value);
            } else if (value instanceof Map) {
                // Need a compound key
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) value;
                buildFlattenedMap(result, map, key);
            } else if (value instanceof Collection) {
                // Need a compound key
                @SuppressWarnings("unchecked")
                Collection<Object> collection = (Collection<Object>) value;
                if (collection.isEmpty()) {
                    result.put(key, "");
                } else {
                    int count = 0;
                    for (Object object : collection) {
                        buildFlattenedMap(result, Collections.singletonMap(
                                "[" + (count++) + "]", object), key);
                    }
                }
            } else {
                result.put(key, (value != null ? value : ""));
            }
        });
    }
}
