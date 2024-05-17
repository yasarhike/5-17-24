package org.insta.content.controller.story.like;

import org.insta.content.service.story.like.StoryLikeService;
import org.insta.content.service.story.like.StoryLikeServiceImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Implemented class for managing story like operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryLikeService
 */
@Path("/storylike")
public final class StoryLikeControllerRest {

    private final StoryLikeService storyLikeService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryLikeControllerRest() {
        storyLikeService = StoryLikeServiceImpl.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryLikeControllerRest storyLikeControllerRest = new StoryLikeControllerRest();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeControllerRest class.
     * </p>
     *
     * @return The singleton instance of StoryLikeControllerRest class.
     */
    public static StoryLikeControllerRest getInstance() {
        return InstanceHolder.storyLikeControllerRest;
    }

    /**
     * <p>
     * Endpoint for adding a like to a specific story.
     * </p>
     *
     * @param userId  the ID of the user
     * @param storyId the ID of the story
     * @return the response in JSON format
     */
    @Path("/add/{userId}/{storyId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyLike(@PathParam("userId") final Long userId,
                            @PathParam("storyId") final Long storyId) {
        return storyLikeService.storyLike(userId, storyId);
    }

    /**
     * <p>
     * Endpoint for removing a like from a story.
     * </p>
     *
     * @param id the ID of the like to be removed
     * @return the response in JSON format
     */
    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyUnLike(@PathParam("id") final Long id) {
        return storyLikeService.storyUnlike(id);
    }
}
