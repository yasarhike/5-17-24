package org.insta.content.model.home;

/**
 * <p>
 * Enum representing media types with constants for image and video.
 * </p>
 *
 * <p>
 * This enum provides constants for different types of media such as image and video.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 */
public enum Media {
    IMAGE(1), VIDEO(2);

    private final int id;

    /**
     * <p>
     * Constructs a Media enum with the specified ID.
     * </p>
     *
     * @param id the ID of the media type
     */
    Media(int id) {
        this.id = id;
    }

    /**
     * <p>
     * Retrieves the Media enum corresponding to the given ID.
     * </p>
     *
     * @param id the ID of the media type
     * @return the Media enum corresponding to the given ID, or null if not found
     */
    public static Media getMedia(final int id) {
        return switch (id) {
            case 1 -> IMAGE;
            case 2 -> VIDEO;
            default -> null;
        };
    }

    /**
     * <p>
     * Retrieves the ID of the media type.
     * </p>
     *
     * @return the ID of the media type
     */
    public int getId() {
        return id;
    }
}

