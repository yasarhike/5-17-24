package org.insta.content.model.reel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.insta.content.groups.ReelValidator;
import org.insta.content.model.common.Common;

import java.sql.Timestamp;

/**
 * <p>
 * Represents a Reel.
 * </p>
 *
 * <p>
 * This class defines properties for a reel, including the reel ID, user ID,
 * content ID, and the reel itself.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Common
 */
public final class Reel extends Common {

    @Positive(message = "User id must be positive", groups = ReelValidator.class)
    private Long userId;
    @NotNull(message = "Caption must not be null", groups = ReelValidator.class)
    private String caption;
    @NotNull(message = "Is private must not be null", groups = ReelValidator.class)
    private boolean isPrivate;
    @Positive(message = "Reel id must  be positive", groups = ReelValidator.class)
    private Long reelId;
    @Positive(message = "Duration must be positive", groups = ReelValidator.class)
    private String duration;
    @NotNull(message = "User name must not be null", groups = ReelValidator.class)
    private String userName;
    private Timestamp timestamp;

    public Long getReelId() {
        return reelId;
    }

    public void setReelId(final Long reelId) {
        this.reelId = reelId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(final Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
