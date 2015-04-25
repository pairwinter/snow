package com.pairwinter.snow.utils.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by damon on 9/4/14.
 */
public class BigIntegerSerializer extends JsonSerializer<BigInteger> {
    @Override
    public void serialize(BigInteger bigInteger, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeString(bigInteger.toString(10));
    }

    public static SimpleModule getModule(){
        SimpleModule module = new SimpleModule("BigInteger",new Version(1, 0, 0, null,null,null));
        module.addSerializer(BigInteger.class, new BigIntegerSerializer());
        return module;
    }
}
