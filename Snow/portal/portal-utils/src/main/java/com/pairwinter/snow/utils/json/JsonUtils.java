package com.pairwinter.snow.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by damon on 14-7-11.
 */
public class JsonUtils {
    private static final MappingJsonFactory mappingJsonFactory = new MappingJsonFactory();

    private JsonUtils() {
        throw new UnsupportedOperationException();
    }

    public static <T> String toJson(T t, boolean escaped) {
        ObjectMapper mapper = getDefaultObjectMapper();
        return getStringResult(t, mapper, escaped);
    }

    public static <T> String toJson(T t) {
        ObjectMapper mapper = getDefaultObjectMapper();
        return getStringResult(t, mapper);
    }

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"));
        mapper.registerModule(BigIntegerSerializer.getModule());
        return mapper;
    }

    public static <T> String toJson(T t, DateFormat dateFormat, boolean escaped) {
        ObjectMapper mapper = getDefaultObjectMapper();
        if (dateFormat != null) {
            mapper.setDateFormat(dateFormat);
        }
        return getStringResult(t, mapper, escaped);
    }

    public static <T> String toJson(T t, DateFormat dateFormat) {
        ObjectMapper mapper = getDefaultObjectMapper();
        if (dateFormat != null) {
            mapper.setDateFormat(dateFormat);
        }
        return getStringResult(t, mapper);
    }

    public static <T> String toJson(T t, DateFormat dateFormat, String timezoneID) {
        ObjectMapper mapper = getDefaultObjectMapper();
        if (dateFormat != null) {
            if (StringUtils.isNotBlank(timezoneID)) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timezoneID));
            }
            mapper.setDateFormat(dateFormat);
        }
        return getStringResult(t, mapper);
    }

    /**
     * convert the date to default dateformat: yyyy-MM-dd HH:mm:ss z with the specific timezoneId
     *
     * @param t
     * @param timezoneID
     * @return
     */
    public static <T> String toJson(T t, String timezoneID) {
        ObjectMapper mapper = getDefaultObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        if (dateFormat != null) {
            if (StringUtils.isNotBlank(timezoneID)) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timezoneID));
            }
            mapper.setDateFormat(dateFormat);
        }
        return getStringResult(t, mapper);
    }

    public static <T> T convertToBean(Class<T> t, String jsonString) {
        return convertToBean(t, jsonString, null);
    }

    public static <T> T convertToBean(Class<T> t, String jsonString, DateFormat dateFormat) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }
        T result = null;
        try {
            ObjectMapper mapper = getDefaultObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (dateFormat != null) {
                mapper.setDateFormat(dateFormat);
            }
            result = mapper.readValue(jsonString, t);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getStringResult(Object obj, ObjectMapper mapper, boolean escaped) {
        StringWriter w = new StringWriter();
        try {
            mapper.writeValue(w, obj);
            if (escaped) {
                return StringEscapeUtils.escapeJavaScript(w.toString());
            }
            return w.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String getStringResult(Object obj, ObjectMapper mapper) {
        return getStringResult(obj, mapper, false);
    }

    public static List<Map<String, Object>> toListMap(String json) {
        ObjectMapper objectMapper = getDefaultObjectMapper();
        try {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> list = objectMapper.readValue(json, List.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

