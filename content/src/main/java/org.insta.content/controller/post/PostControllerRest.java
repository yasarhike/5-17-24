package org.insta.content.controller.post;

import org.insta.content.controller.post.share.PostShareControllerRest;
import org.insta.content.model.post.Post;
import org.insta.content.service.post.PostService;
import org.insta.content.service.post.PostServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * <p>
 * Manages posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostService
 */
@Path("/post")
public final class PostControllerRest {

    private final PostService postService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostControllerRest() {
        postService = PostServiceImpl.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostControllerRest postControllerRest = new PostControllerRest();
    }

    /**
     * <p>
     * Returns the singleton instance of PostControllerRest class.
     * </p>
     *
     * @return The singleton instance of PostControllerRest class.
     */
    public static PostControllerRest getInstance() {
        return InstanceHolder.postControllerRest;
    }

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post Refer to the {@link Post} of the user.
     * @return True if the post is added successfully, otherwise false.
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] addPost(final Post post) {
        return postService.addPost(post);
    }

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param postId Refer to postId of the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] deletePost(@PathParam("id") final Long postId) {
        return postService.removePost(postId);
    }


    /**
     * <p>
     * Retrieve the post with the specified ID.
     * </P>
     *
     * @param postId refers to the postId of the user.
     * @return True if the post is updated successfully, otherwise false.
     */
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public byte[] getPost(@PathParam("id") final Long postId) {
        return postService.getPost(postId);
    }
}
