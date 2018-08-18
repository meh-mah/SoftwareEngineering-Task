package com.sainsburys.serialiser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sainsburys.model.IModel;

/**
 * Interface for formatters used in this module. Classes inheriting from this
 * interface will be used to change objects from one format to another.
 *
 * @author Mehran
 */
public interface ISerialiser {

    /**
     * Serialise the provided object to string.
     *
     * @param obj Object to serialise
     * @return String value of serialised object
     * @throws JsonProcessingException when the object cannot be processed
     */
    public String serialise(Object obj) throws JsonProcessingException;

    /**
     * Serialise the provided object to a file.
     *
     * @param file File to serialise to
     * @param obj Object to serialise
     * @throws IOException when the File cannot be written
     */
    public void serialiseToFile(File file, Object obj) throws IOException;

    /**
     * Deserialise the provided string to a java object.
     *
     * @param json Json to deserialise
     * @param type Type of object to serialise to
     * @return Serialised object
     * @throws Exception when the object cannot be deserialised
     */
    public IModel deserialise(String json, Class<IModel> type) throws Exception;
}
