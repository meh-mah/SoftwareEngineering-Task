package com.sainsburys.serialiser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sainsburys.model.IModel;

/**
 * Serialize any JAVA value to represent JSON. It provides functionality for
 * writing JSON, from basic POJOs (Plain Old Java Objects). Created as a
 * singleton.
 *
 * @author Mehran
 */
public class JsonSerialiser implements ISerialiser {

    ObjectMapper mapper = new ObjectMapper();

    private static JsonSerialiser instance;

    private JsonSerialiser() {
    }

    /**
     * Get an instance of JsonSerialiser.
     *
     * @return Existing instance if one exists, create and return new instance
     * otherwise
     */
    public static JsonSerialiser getInstance() {
        if (instance == null) {
            instance = new JsonSerialiser();
        }
        return instance;
    }

    /**
     * Method that can be used to serialise any Java object as a String.
     *
     * @param obj the object to serialize and represent as JSON
     * @return JSON format of the given object
     * @throws JsonProcessingException when object cannot be processed for
     * serialisation
     */
    @Override
    public String serialise(Object obj) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    /**
     * Method that can be used to serialize any Java object as JSON output,
     * written to File provided.
     *
     * @param obj the object to serialize and represent as JSON
     * @param file the file to write JSON output
     * @throws IOException when it is not possible to write to file
     */
    @Override
    public void serialiseToFile(File file, Object obj) throws IOException {
        mapper.writeValue(file, obj);
    }

    /**
     * Deserialises provided string into the requested object type.
     *
     * @param json String to deserialise
     * @param type Type of class to serialise to
     * @return Deserialised object
     * @throws Exception when type of object cannot be deserialised
     */
    @Override
    public IModel deserialise(final String json, final Class<IModel> type) throws Exception {
        if (mapper.canDeserialize(mapper.constructType(type))) {
            return (IModel) mapper.readValue(json, type);
        } else {
            throw new Exception("Object type can not be deserealised");
        }
    }

}
