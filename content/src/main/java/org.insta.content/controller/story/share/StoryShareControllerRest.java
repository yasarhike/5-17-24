package org.insta.content.controller.story.share;

import org.insta.content.controller.story.like.StoryLikeControllerRest;
import org.insta.content.service.story.share.StoryShareService;
import org.insta.content.service.story.share.StoryShareServiceImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * <p>
 * RESTful controller class for managing story sharing operations within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for sharing and unsharing stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryShareService
 */
@Path("/storyshare")
public final class StoryShareControllerRest {

    private final StoryShareService storyShareService;

    /**
     * <p>
     * Private constructor to restrict object creation outside of the class.
     * </p>
     */
    private StoryShareControllerRest() {
        storyShareService = StoryShareServiceImpl.getInstance();
    }


    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryShareControllerRest storyShareControllerRest = new StoryShareControllerRest();
    }

    /**
     * <p>
     * Returns the singleton instance of the StoryShareControllerRest class.
     * </p>
     *
     * @return The singleton instance of StoryShareControllerRest class.
     */
    public static StoryShareControllerRest getInstance() {
        return InstanceHolder.storyShareControllerRest;
    }

    /**
     * <p>
     * Shares a story with the specified user.
     * </p>
     *
     * @param storyId the ID of the story to share
     * @param userId  the ID of the user to share with
     * @return the ID of the shared story if successful, otherwise -1
     */
    @Path("/add/{userId}/{storyId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyShare(@PathParam("storyId") final Long storyId,
                             @PathParam("userId") final Long userId) {
        return storyShareService.storyShare(storyId, userId);
    }

    /**
     * <p>
     * Unshares a story.
     * </p>
     *
     * @param storyId the ID of the story to unshare
     * @return A byte contains the response of the request.
     */
    @DELETE
    @Path("/remove/{storyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyUnShare(@PathParam("storyId") final Long storyId) {
        return storyShareService.storyUnShare(storyId);
    }
}
