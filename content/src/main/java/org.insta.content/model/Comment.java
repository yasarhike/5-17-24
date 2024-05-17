package org.insta.content.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.insta.content.groups.CommentValidator;
import org.insta.content.model.common.Common;

/**
 * <p>
 * Represents a comment.
 * </p>
 *
 * <p>
 * This class defines properties for a comment, including the comment ID, user ID,
 * content ID, and the comment content itself.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Common
 */
public final class Comment extends Common {

    @NotNull(message = "User id must not be blank", groups = CommentValidator.class)
    private Long userId;
    @NotNull(message = "Content id must not be blank", groups = CommentValidator.class)
    private Long contentId;
    @NotBlank(message = "Comment id must not be blank", groups = CommentValidator.class)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }
}
