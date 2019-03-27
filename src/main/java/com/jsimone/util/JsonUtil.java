package com.jsimone.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsimone.error.ErrorResponse;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Other serialization/deserialization features include:
 * <p>
 * // Serialization features of Jackson (converting object to json)
 * objectMapper.enable(SerializationFeature.INDENT_OUTPUT)    // enable this for indented output
 * objectMapper.setSerializationInclusion(Include.NON_NULL)   // enable this to ignore null fields
 * objectMapper.setSerializationInclusion(Include.NON_EMPTY)  // enable this to ignore empty arrays
 * val fmt: SimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy")   // custom definition for date format
 * objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
 * <p>
 * // deserialization features of Jackson (converting json to object)
 * objectMapper.setDateFormat(fmt)
 * objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
 */
public class JsonUtil {

    private JsonUtil() {}
    //implicit val formats = DefaultFormats

    private static final String W3C_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // configure the object mapper
    static {
        SimpleDateFormat W3C_UTC_DATE_FORMAT = new SimpleDateFormat(W3C_UTC_FORMAT);
        W3C_UTC_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(W3C_UTC_DATE_FORMAT);// set a custom date format, or just take the default
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJsonString(Object obj) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        objectMapper.writeValue(out, obj);
        return out.toString();
    }

    public static JsonNode toJson(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    public static <T> JsonNode fromJson(Class<T> clazz, String json) {
        JsonNode node = null;
        try {
            node = objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructParametricType(JsonNode.class, clazz));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return node;
    }

    public static ErrorResponse fromJson(String json) {
        ErrorResponse errorResponse = null;
        try {
            errorResponse = objectMapper.readValue(json, ErrorResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return errorResponse;
    }

}




