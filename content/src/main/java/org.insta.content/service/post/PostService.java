package org.insta.content.service.post;

import org.insta.content.model.post.Post;

/**
 * <p>
 * Service interface for managing post operations.
 * </p>
 *
 * <p>
 * This interface provides methods for adding, removing, and retrieving posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostServiceImpl
 */
public interface PostService {

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post the post to be added
     * @return a byte array representing the result of the operation
     */
    byte[] addPost(final Post post);

    /**
     * <p>
     * Removes a post with the specified ID.
     * </p>
     *
     * @param postId the ID of the post to be removed
     * @return a byte array representing the result of the operation
     */
    byte[] removePost(final Long postId);

    /**
     * <p>
     * Retrieves a post with the specified ID.
     * </p>
     *
     * @param id the ID of the post to be retrieved
     * @return a byte array representing the retrieved post
     */
    byte[] getPost(final Long id);
}
