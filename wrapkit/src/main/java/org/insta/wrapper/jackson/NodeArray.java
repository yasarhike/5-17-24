package org.insta.wrapper.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * <p>
 * Record representing a JSON array node wrapper.
 * </p>
 *
 * <p>
 * This record encapsulates an {@link ArrayNode} and provides methods for adding JSON nodes
 * to the array and accessing the underlying ArrayNode.
 * </p>
 *
 * @param arrayNode The underlying ArrayNode.
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ArrayNode
 * @see JsonNode
 */
public record NodeArray(ArrayNode arrayNode) {

    /**
     * <p>
     * Adds a JSON node to the array.
     * </p>
     *
     * @param jsonNode The JSON node to add.
     * @return The modified ArrayNode after adding the JSON node.
     */
    public ArrayNode add(final JsonNode jsonNode) {
        return this.arrayNode.add(jsonNode);
    }

    /**
     * <p>
     * Returns the underlying ArrayNode.
     * </p>
     *
     * @return The underlying ArrayNode.
     */
    public ArrayNode getArrayNode() {
        return this.arrayNode;
    }
}
