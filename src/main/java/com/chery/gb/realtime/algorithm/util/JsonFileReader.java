package com.chery.gb.realtime.algorithm.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JSON文件读取工具类
 */
public class JsonFileReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将JSON文件读取为指定类型的对象
     * @param filePath JSON文件路径
     * @param valueType 目标类型
     * @return 解析后的Java对象
     * @throws IOException 如果文件读取或解析失败
     */
    public static <T> T readJsonFile(String filePath, Class<T> valueType) throws IOException {
        return objectMapper.readValue(new File(filePath), valueType);
    }

    /**
     * 将JSON文件读取为JsonNode（树模型）
     * @param filePath JSON文件路径
     * @return JsonNode对象
     * @throws IOException 如果文件读取或解析失败
     */
    public static JsonNode readJsonFileToNode(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }

    /**
     * 将JSON文件读取为Map
     * @param filePath JSON文件路径
     * @return Map对象
     * @throws IOException 如果文件读取或解析失败
     */
    public static Map<String, Object> readJsonFileToMap(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), 
            new TypeReference<Map<String, Object>>() {});
    }

    /**
     * 将JSON文件读取为List
     * @param filePath JSON文件路径
     * @return List对象
     * @throws IOException 如果文件读取或解析失败
     */
    public static List<Object> readJsonFileToList(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), 
            new TypeReference<List<Object>>() {});
    }

    /**
     * 将JSON文件转换为格式化字符串
     * @param filePath JSON文件路径
     * @return 格式化后的JSON字符串
     * @throws IOException 如果文件读取或解析失败
     */
    public static String readJsonFileAsString(String filePath) throws IOException {
        JsonNode node = readJsonFileToNode(filePath);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }
}