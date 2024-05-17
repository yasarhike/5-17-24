package org.insta.wrapper.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.insta.wrapper.exception.JsonWrapperException;

import java.io.IOException;

/**
 * <p>
 * Utility class for handling JSON mapping operations using Jackson ObjectMapper.
 * </p>
 *
 * <p>
 * This class provides methods for creating JSON nodes, reading JSON trees,
 * and serializing Java objects to JSON strings or byte arrays.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ObjectMapper
 * @see JsonNode
 * @see NodeArray
 * @see NodeObject
 */
public final class MapperObject {

    private final ObjectMapper objectMapper;

    /**
     * <p>
     * Constructs a MapperObject instance with a new ObjectMapper.
     * </p>
     */
    public MapperObject() {
        objectMapper = new ObjectMapper();
    }

    /**
     * <p>
     * Returns the ObjectMapper instance.
     * </p>
     *
     * @return The ObjectMapper instance.
     */
    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    /**
     * <p>
     * Creates a new JSON array node.
     * </p>
     *
     * @return A new NodeArray instance.
     */
    public NodeArray getArrayNode() {
        return new NodeArray(objectMapper.createArrayNode());
    }

    /**
     * <p>
     * Creates a new JSON object node.
     * </p>
     *
     * @return A new NodeObject instance.
     */
    public NodeObject getObjectNode() {
        return new NodeObject(objectMapper.createObjectNode());
    }

    /**
     * <p>
     * Reads a JSON tree from the specified byte array.
     * </p>
     *
     * @param violations The byte array containing the JSON data.
     * @return The root node of the JSON tree.
     * @throws JsonWrapperException {@link JsonWrapperException} If an error occurs during serialization.
     */
    public JsonNode readTree(final byte[] violations) {
        try {
            return this.objectMapper.readTree(violations);
        } catch (IOException exception) {
            throw new JsonWrapperException("Read tree operation failed");
        }
    }

    /**
     * <p>
     * Serializes the specified object to a JSON byte array.
     * </p>
     *
     * @param object The object to serialize.
     * @return The JSON byte array representation of the object.
     * @throws JsonWrapperException {@link JsonWrapperException} If an error occurs during serialization.
     */
    public String writeValueAsString(final Object object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (IOException exception) {
            throw new JsonWrapperException("Write value as string operation failed");
        }
    }
}

