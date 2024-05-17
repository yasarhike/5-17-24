package org.insta.content.controller.reel.share;

import org.insta.content.controller.reel.like.ReelLikeControllerRest;
import org.insta.content.service.reel.share.ReelShareService;
import org.insta.content.service.reel.share.ReelShareServiceImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * <p>
 * RESTful controller class for managing sharing operations for reels within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for sharing and removing shares on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelShareServiceImpl
 */
@Path("/reelshare")
public final class ReelShareControllerRest {

    private final ReelShareService reelShareService;

    /**
     * <p>
     * Restrict object creation outside of the class.
     * </p>
     */
    private ReelShareControllerRest() {
        reelShareService = ReelShareServiceImpl.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelShareControllerRest reelShareControllerRest = new ReelShareControllerRest();
    }

    /**
     * <p>
     * Returns the singleton instance of the ReelShareControllerRest class.
     * </p>
     *
     * @return The singleton instance of the ReelShareControllerRest class.
     */
    public static ReelShareControllerRest getInstance() {
        return InstanceHolder.reelShareControllerRest;
    }

    /**
     * <p>
     * Endpoint for sharing a reel with a user.
     * </p>
     *
     * @param reelId the ID of the reel to share
     * @param userId the ID of the user to share with
     * @return the response in JSON format
     */
    @Path("/add/{reelId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] reelShare(@PathParam("reelId") final Long userId,
                            @PathParam("userId") final Long reelId) {
        return reelShareService.reelShare(userId, reelId);
    }

    /**
     * <p>
     * Endpoint for removing a shared reel.
     * </p>
     *
     * @param id the ID of the shared reel to remove
     * @return the response in JSON format
     */
    @Path("/remove/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeShare(@PathParam("id") final Long id) {
        return reelShareService.removeShare(id);
    }
}
