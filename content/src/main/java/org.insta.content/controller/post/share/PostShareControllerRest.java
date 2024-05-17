package org.insta.content.controller.post.share;

import org.insta.content.dao.post.share.PostShareDAOImpl;
import org.insta.content.service.post.share.PostShareService;
import org.insta.content.service.post.share.PostShareServiceImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Manages post share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostShareDAOImpl
 */
@Path("/postshare")
public class PostShareControllerRest {

    private final PostShareService postShareDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostShareControllerRest() {
        postShareDAOImpl = PostShareServiceImpl.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostShareControllerRest postShareControllerRest = new PostShareControllerRest();
    }
    /**
     * <p>
     * Returns the singleton instance of PostShareControllerRest class.
     * </p>
     *
     * @return The singleton instance of PostShareControllerRest class.
     */
    public static PostShareControllerRest getInstance() {
        return InstanceHolder.postShareControllerRest;
    }

    /**
     * <p>
     * Shares the specified post by the specified user.
     * </p>
     *
     * @param userId The ID of the user who shares the post.
     * @param postId The ID of the post to be shared.
     * @return The ID of the shared post.
     */
    @Path("/add/{postId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] postShare(@PathParam("userId") final Long userId,
                            @PathParam("postId") final Long postId) {
        return postShareDAOImpl.postShare(postId, userId);
    }

    /**
     * <p>
     * Removes the share with the specified ID.
     * </p>
     *
     * @param shareId The ID of the share to be removed.
     * @return True if the share is removed successfully, otherwise false.
     */
    @Path("/remove/{shareId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] postUnShare(@PathParam("shareId") final Long shareId) {
        return postShareDAOImpl.removeShare(shareId);
    }
}
