package org.insta.content.controller.reel.comment;

import org.insta.content.model.Comment;
import org.insta.content.service.reel.comment.ReelCommentService;
import org.insta.content.service.reel.comment.ReelCommentServiceImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * RESTful controller class for managing comments on reels within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for adding and removing comments on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelCommentServiceImpl
 */
@Path("/reelcomment")
public final class ReelCommentControllerRest {

    private final ReelCommentService reelCommentService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelCommentControllerRest() {
        reelCommentService = ReelCommentServiceImpl.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelCommentControllerRest reelCommentControllerRest = new ReelCommentControllerRest();
    }

    /**
     * <p>
     * Creates a singleton instance of the ReelCommentControllerRest class if it is not already created.
     * </p>
     *
     * @return Singleton instance of ReelCommentControllerRest class.
     */
    public static ReelCommentControllerRest getInstance() {
        return InstanceHolder.reelCommentControllerRest;
    }

    /**
     * <p>
     * Endpoint for adding a comment to a reel.
     * </p>
     *
     * @param comment {@link Comment}The comment object to be added
     * @return the response in JSON format
     */
    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] add(final Comment comment) {
        return reelCommentService.addComment(comment);
    }

    /**
     * <p>
     * Endpoint for removing a comment from a reel.
     * </p>
     *
     * @param commentId ID of the comment to be removed
     * @return the response in JSON format
     */
    @Path("/remove/{commentId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] deleteComment(@PathParam("commentId") final Long commentId) {
        return reelCommentService.deleteComment(commentId);
    }
}
