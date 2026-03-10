package com.bodhisatta.automation.core.api.utils.testdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonDataReader {

    public static List<Map<String, Object>> getTestData(String fileName)
    {
        ObjectMapper mapper=new ObjectMapper();

        try{
            InputStream inputStream=JsonDataReader.class.getClassLoader().getResourceAsStream(fileName);

            return mapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {
            });
        }catch (Exception e)
        {
            throw new RuntimeException("Failed to read test data file: " + fileName);
        }
    }
}
