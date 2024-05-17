package org.insta.wrapper.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * <p>
 * Record representing a JSON object node wrapper.
 * </p>
 *
 * <p>
 * This record encapsulates an {@link ObjectNode} and provides methods for manipulating
 * the object node by adding or setting values for specific keys.
 * </p>
 *
 * @param objectNode The underlying ObjectNode.
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ObjectNode
 * @see JsonNode
 */
public record NodeObject(ObjectNode objectNode) {

    /**
     * <p>
     * Puts an integer value into the object node with the specified key.
     * </p>
     *
     * @param key   The key for the value.
     * @param value The integer value to put.
     */
    public void putNode(final String key, final int value) {
        this.objectNode.put(key, value);
    }

    /**
     * <p>
     * Puts a string value into the object node with the specified key.
     * </p>
     *
     * @param key   The key for the value.
     * @param value The string value to put.
     */
    public void putNode(final String key, final String value) {
        this.objectNode.put(key, value);
    }

    /**
     * <p>
     * Sets an object node as the value for the specified key.
     * </p>
     *
     * @param key        The key for the value.
     * @param objectNode The object node value to set.
     */
    public void setValue(final String key, final ObjectNode objectNode) {
        this.objectNode.set(key, objectNode);
    }

    /**
     * <p>
     * Sets a JSON node as the value for the specified key.
     * </p>
     *
     * @param key      The key for the value.
     * @param jsonNode The JSON node value to set.
     */
    public void setValue(final String key, final JsonNode jsonNode) {
        this.objectNode.set(key, jsonNode);
    }

    /**
     * <p>
     * Puts a boolean value into the object node with the specified key.
     * </p>
     *
     * @param key    The key for the value.
     * @param result The boolean value to put.
     */
    public void putValue(final String key, final Boolean result) {
        this.objectNode.put(key, result);
    }

    /**
     * <p>
     * Puts a string value into the object node with the specified key.
     * </p>
     *
     * @param key   The key for the value.
     * @param value The string value to put.
     */
    public void put(final String key, final String value) {
        this.objectNode.put(key, value);
    }


    /**
     * <p>
     * Puts an Long value into the object node with the specified key.
     * </p>
     *
     * @param key   The key for the value.
     * @param value The integer value to put.
     */
    public void putNode(final String key, final Long value) {
        this.objectNode.put(key, value);
    }
}
