package br.com.ltst.springwebmvcbasicodigitalinnovationone.config;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.enums.Race;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Jackson extends SimpleModule {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Propriedades não mapeadas não quebram
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // FALHA SE ALGUMA PROPRIEDADE ESTIVER VAZIA
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // Serve para compatibilidade de arrays, quando tem um array com um item, caso não tenha essa configuração ele se perde
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        // serialize datas
        objectMapper.registerModule(new JavaTimeModule());
        // serialize ENUM RACE
        objectMapper.registerModule(raceModuleMapper());
        return objectMapper;
    }


    public SimpleModule raceModuleMapper() {
        SimpleModule raceModule = new SimpleModule("JSONRaceModule", PackageVersion.VERSION);
        raceModule.addSerializer(Race.class, new RaceCerialize());
        raceModule.addDeserializer(Race.class, new RaceDecerialize());
        return raceModule;
    }

    class RaceCerialize extends StdSerializer<Race> {

        public RaceCerialize() {
            super(Race.class);
        }

        @Override
        public void serialize(Race value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.getValue());
        }
    }

    class RaceDecerialize extends StdDeserializer<Race> {

        public RaceDecerialize() {
            super(Race.class);
        }

        @Override
        public Race deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return Race.of(p.getText());
        }

    }
}
