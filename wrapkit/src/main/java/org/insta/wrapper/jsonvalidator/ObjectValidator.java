package org.insta.wrapper.jsonvalidator;

import org.insta.wrapper.exception.JsonWrapperException;
import org.insta.wrapper.hibernate.Validate;
import org.insta.wrapper.jackson.MapperObject;
import org.insta.wrapper.jackson.NodeArray;
import org.insta.wrapper.jackson.NodeObject;

import java.util.Objects;

/**
 * <p>
 * Utility class for validating objects and generating response payloads.
 * </p>
 *
 * <p>
 * This class provides methods for validating objects, generating success and failure
 * response payloads, and manual response payloads for various scenarios.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Validate
 * @see MapperObject
 * @see NodeObject
 * @see NodeArray
 */
public final class ObjectValidator {

    private static ObjectValidator objectValidator;
    private final Validate validate;

    /**
     * <p>
     * Constructs an ObjectValidator instance and initializes the Validate instance.
     * </p>
     */
    private ObjectValidator() {
        validate = Validate.getInstance();
    }


    /**
     * <p>
     * Returns the singleton instance of ObjectValidator.
     * </p>
     *
     * @return The singleton instance of ObjectValidator.
     */
    public static ObjectValidator getInstance() {
        return Objects.isNull(objectValidator) ? new ObjectValidator() : objectValidator;
    }


    /**
     * <p>
     * Validates the specified object against the specified validation groups and
     * returns the validation result as a byte array.
     * </p>
     *
     * @param <T>    The type of the object to validate.
     * @param object The object to validate.
     * @param groups The validation groups to apply.
     * @return The validation result as a byte array.
     */
    public <T> byte[] validate(final T object, final Class<?> groups) {
        try {
            final MapperObject mapperObject = new MapperObject();
            final NodeObject nodeObject = mapperObject.getObjectNode();

            validate.validate(object, groups).forEach(violation -> nodeObject.objectNode().put(violation.getPropertyPath().toString(), violation.getMessage()));

            return !nodeObject.objectNode().isEmpty() ? mapperObject.getObjectMapper().writeValueAsString(nodeObject.objectNode()).getBytes() : new byte[]{};
        } catch (Exception exception) {
            throw new JsonWrapperException("Parse Operation failed");
        }
    }

    /**
     * <p>
     * Generates a success response payload with the specified table ID and violations data.
     * </p>
     *
     * @param id         The ID of the table.
     * @param violations The violations data.
     * @return The success response payload as a byte array.
     */
    public byte[] responseWithID(final Long id, final byte[] violations) {
        try {
            final MapperObject mapperObject = new MapperObject();
            final NodeObject response = mapperObject.getObjectNode();
            final NodeObject tableData = mapperObject.getObjectNode();
            final NodeArray nodeArray = mapperObject.getArrayNode();

            if (Objects.nonNull(id)) {
                tableData.putNode("id", id);
            } else {
                tableData.putNode("status", "invalid credentials");
            }

            response.setValue("data", tableData.objectNode());
            response.setValue("Violations ", mapperObject.readTree(violations));
            nodeArray.add(response.objectNode());

            return mapperObject.writeValueAsString(nodeArray.getArrayNode()).getBytes();
        } catch (Exception ignored) {
            throw new JsonWrapperException("Parse operation failed");
        }
    }

    /**
     * <p>
     * Generates a response payload for the specified object.
     * </p>
     *
     * @param <T>    The type of the object.
     * @param object The object.
     * @return The response payload as a byte array.
     */
    public <T> byte[] objectResponse(final T object) {
        if (Objects.isNull(object)) return stringManualResponse("User not found");
        try {
            final MapperObject mapperObject = new MapperObject();

            return mapperObject.writeValueAsString(object).getBytes();
        } catch (Exception ignored) {
            throw new JsonWrapperException("Parse operation failed");
        }
    }

    /**
     * <p>
     * Generates a manual response payload with the specified result.
     * </p>
     *
     * @param result The result of the operation.
     * @return The manual response payload as a byte array.
     */
    public byte[] manualResponse(final boolean result) {
        try {
            final MapperObject mapperObject = new MapperObject();
            final NodeArray nodeArray = mapperObject.getArrayNode();
            final NodeObject nodeObject = mapperObject.getObjectNode();
            if (result) {
                nodeObject.put("status", "successful");
            } else {
                nodeObject.put("status", "Failed");
            }
            nodeArray.add(nodeObject.objectNode());

            return mapperObject.writeValueAsString(nodeArray.getArrayNode()).getBytes();
        } catch (Exception ignored) {
            throw new JsonWrapperException("Parse operation failed");
        }
    }

    /**
     * <p>
     * Generates a manual response payload with the specified result as a string.
     * </p>
     *
     * @param result The result string.
     * @return The manual response payload as a byte array.
     */
    public byte[] stringManualResponse(final String result) {
        try {
            final MapperObject mapperObject = new MapperObject();
            final NodeArray nodeArray = mapperObject.getArrayNode();
            final NodeObject nodeObject = mapperObject.getObjectNode();

            nodeObject.put("status", result);
            nodeArray.add(nodeObject.objectNode());

            return mapperObject.writeValueAsString(nodeArray.getArrayNode()).getBytes();
        } catch (Exception exception) {
            throw new JsonWrapperException("Parse failed");
        }
    }
}
