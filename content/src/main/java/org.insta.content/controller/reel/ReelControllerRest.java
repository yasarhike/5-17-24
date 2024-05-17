package org.insta.content.controller.reel;

import org.insta.content.controller.reel.share.ReelShareControllerRest;
import org.insta.content.model.reel.Reel;
import org.insta.content.service.reel.ReelService;
import org.insta.content.service.reel.ReelServiceImpl;

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
 * Implementation of the ReelControllerRest class for managing reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelService
 */
@Path("/reel")
public final class ReelControllerRest {

    private final ReelService reelService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelControllerRest() {
        reelService = ReelServiceImpl.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelControllerRest reelControllerRest = new ReelControllerRest();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelControllerRest class.
     * </p>
     *
     * @return The singleton instance of ReelControllerRest class.
     */
    public static ReelControllerRest getInstance() {
        return InstanceHolder.reelControllerRest;
    }

    /**
     * <p>
     * Endpoint for removing a reel.
     * </p>
     *
     * @param reelId the ID of the reel to remove
     * @return the response in JSON format
     */
    @DELETE
    @Path("/remove/{reelid}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeReel(@PathParam("reelid") final Long reelId) {
        return reelService.removeReel(reelId);
    }

    /**
     * <p>
     * Endpoint for adding a reel for the specified user.
     * </p>
     *
     * @param reel the Reel object to add
     * @return the response in JSON format
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] addReel(final Reel reel) {
        return reelService.addReel(reel);
    }

    /**
     * <p>
     * Endpoint for retrieving a reel.
     * </p>
     *
     * @param reelId the ID of the reel to retrieve
     * @return the response in JSON format
     */
    @GET
    @Path("/get/{reelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] getReel(@PathParam("reelId") final Long reelId) {
        return reelService.getReel(reelId);
    }
}
