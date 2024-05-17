package org.insta.content.dao.post;

import org.insta.content.model.post.Post;

/**
 * <p>
 * Data Access Object interface for managing user posts.
 * </p>
 *
 * <p>
 * This interface provides methods for adding, removing, updating, and fetching posts for users.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostServiceDAOImpl
 */
public interface PostServiceDAO {

    /**
     * <p>
     * Posts a video or image for the user account.
     * </p>
     *
     * @param post the post to be added
     * @return the ID of the added post, or 0 if unsuccessful
     */
    Long addPost(final Post post);

    /**
     * <p>
     * Deletes a post for the user account.
     * </p>
     *
     * @param postId the ID of the post to be removed
     * @return true if the post is removed successfully, otherwise false
     */
    boolean removePost(final Long postId);

    /**
     * <p>
     * Retrieves a post with the specified ID.
     * </p>
     *
     * @param id the ID of the post to be retrieved
     * @return the retrieved post, or null if not found
     */
    Post getPost(final Long id);
}
