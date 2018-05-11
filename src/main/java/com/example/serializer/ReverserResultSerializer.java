package com.example.serializer;

import com.example.model.ReverserResult;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReverserResultSerializer extends StdSerializer<ReverserResult> {
    protected ReverserResultSerializer() {
        super(ReverserResult.class);
    }

    @Override
    public void serialize(ReverserResult value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("result");
        gen.writeString(value.getOriginalString() + "|" + value.getReversedString());
        gen.writeEndObject();
    }
}
