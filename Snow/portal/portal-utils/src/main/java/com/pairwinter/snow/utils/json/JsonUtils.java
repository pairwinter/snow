package com.pairwinter.snow.utils.json;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.*;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ser.std.SerializerBase;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

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
        mapper.getJsonFactory().setCharacterEscapes(new CustomerCharacterEscapes());
        mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"));
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

    /**
     * Json array to java List
     *
     * @param jsonString String
     * @param tr         TypeReference<T>
     *                   \\TypeReference,ex.: new TypeReference< List<Album> >(){}
     * @return T
     */
    public static <T> T jsonToObjectList(String jsonString, TypeReference<T> tr) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }
        try {
            ObjectMapper mapper = getDefaultObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonParser parser = mapper.getJsonFactory().createJsonParser(jsonString);
//            JsonParser parser = mappingJsonFactory.createJsonParser(jsonString);
            return parser.readValueAs(tr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObjectList(String jsonString, TypeReference<T> tr, DateFormat dateFormat) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }
        try {
            ObjectMapper mapper = getDefaultObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (dateFormat != null) {
                mapper.setDateFormat(dateFormat);
            }
            JsonParser parser = mapper.getJsonFactory().createJsonParser(jsonString);
//            JsonParser parser = mappingJsonFactory.createJsonParser(jsonString);
            return parser.readValueAs(tr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (dateFormat != null) {
                mapper.setDateFormat(dateFormat);
            }
            result = mapper.readValue(jsonString, t);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> String toJsonWithSerializer(Object obj, String moduleName, Class<T> classType,
                                                  CustomerEntityToJSON<T> cEntityToJSON) {
        final CustomerJSONSerializer<T> cJsonSerializer = new CustomerJSONSerializer<T>(classType, cEntityToJSON);

        SimpleModule testModule = new SimpleModule(moduleName, new Version(1, 0, 0, null));
        testModule.addSerializer(cJsonSerializer);
        ObjectMapper mapper = getDefaultObjectMapper();
        mapper.registerModule(testModule);
        return getStringResult(obj, mapper);
    }

    public static <T> String toJsonArrayWithSerializer(Object obj, String moduleName, Class<T> classType,
                                                       CustomerEntityToJSONArray<T> cEntityToJSON) {
        final CustomerJSONSerializer<T> cJsonSerializer = new CustomerJSONSerializer<T>(classType, cEntityToJSON);

        SimpleModule testModule = new SimpleModule(moduleName, new Version(1, 0, 0, null));
        testModule.addSerializer(cJsonSerializer);
        ObjectMapper mapper = getDefaultObjectMapper();
        mapper.registerModule(testModule);
        return getStringResult(obj, mapper);
    }

    public static <T> String toJsonWithSerializer(Object obj, String moduleName, Class<T> classType,
                                                  CustomerEntityToJSON<T> cEntityToJSON, boolean escaped) {
        final CustomerJSONSerializer<T> cJsonSerializer = new CustomerJSONSerializer<T>(classType, cEntityToJSON);

        SimpleModule testModule = new SimpleModule(moduleName, new Version(1, 0, 0, null));
        testModule.addSerializer(cJsonSerializer);
        ObjectMapper mapper = getDefaultObjectMapper();
        mapper.registerModule(testModule);
        return getStringResult(obj, mapper, escaped);
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

interface CustomerEntityToJSON<T> {
    public ObjectNode getObjectNode(T t);
}

interface CustomerEntityToJSONArray<T> {
    public List<ObjectNode> getObjectNode(T t);
}

class CustomerJSONSerializer<T> extends SerializerBase<T> {
    private CustomerEntityToJSON<T> cetj;
    private CustomerEntityToJSONArray<T> cetja;
    private boolean isArray = false;

    public CustomerJSONSerializer(Class<T> t, CustomerEntityToJSON<T> cetj) {
        super(t);
        this.cetj = cetj;
    }

    public CustomerJSONSerializer(Class<T> t, CustomerEntityToJSONArray<T> cetja) {
        super(t);
        this.cetja = cetja;
        this.isArray = true;
    }

    @Override
    public void serialize(T value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {
        if (isArray) {
            List<ObjectNode> nodes = cetja.getObjectNode(value);
            for (ObjectNode objectNode : nodes) {
                jgen.writeTree(objectNode);
            }
        } else {

            jgen.writeTree(cetj.getObjectNode(value));
        }
    }

    public CustomerJSONSerializer(Class<T> t, boolean dummy) {
        super(t, dummy);
    }

    public CustomerJSONSerializer(Class<T> t) {
        super(t);
    }

    public CustomerJSONSerializer(JavaType type) {
        super(type);
    }

}

class CustomerCharacterEscapes extends CharacterEscapes {
    //    Logger logger = LogWrapper.getLogger(CustomerCharacterEscapes.class).getWrappedLogger();
    private final int[] _asciiEscapes;

    public CustomerCharacterEscapes() {
        _asciiEscapes = standardAsciiEscapesForJSON();
        _asciiEscapes['\''] = CharacterEscapes.ESCAPE_STANDARD;
        _asciiEscapes['\"'] = CharacterEscapes.ESCAPE_STANDARD;
        _asciiEscapes['/'] = CharacterEscapes.ESCAPE_STANDARD;
        _asciiEscapes['<'] = CharacterEscapes.ESCAPE_STANDARD;
        _asciiEscapes['>'] = CharacterEscapes.ESCAPE_STANDARD;
        _asciiEscapes['\n'] = CharacterEscapes.ESCAPE_STANDARD;
        _asciiEscapes['\r'] = CharacterEscapes.ESCAPE_STANDARD;
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return _asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(final int ch) {
        return new SerializableString() {
            @Override
            public String getValue() {
                return "\\" + _asciiEscapes[ch];
            }

            @Override
            public int charLength() {
                return 0;
            }

            @Override
            public char[] asQuotedChars() {
                return new char[0];
            }

            @Override
            public byte[] asUnquotedUTF8() {
                return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public byte[] asQuotedUTF8() {
                return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }
}

