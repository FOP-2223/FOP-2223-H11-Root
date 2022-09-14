package h11.parse;

/**
 * Instances of this class are used
 * to represent the projections parsed by an {@link LSystemParser}.
 * For example the projection <code>A -> AB</code>
 * can be represented by
 * <pre>
 *     new Projection('A', "AB")
 * </pre>
 *
 * @param source The source of the projection
 * @param destination The destination of the projection
 */
public record Projection(char source, String destination) {

    /**
     * @param source The source of the projection
     * @param destination The destination of the projection (may not be empty)
     */
    public Projection {
        checkDestinationNotEmpty(destination);
    }

    /**
     * @param source The source of the projection (must be of length 1)
     * @param destination The destination of the projection (may not be empty)
     */
    public Projection(String source, String destination) {
        this(getFirstChar(source), destination);
    }

    private static char getFirstChar(String source) {
        if (source.length() != 1) {
            throw new IllegalArgumentException("Source String must be exactly 1 character long: " + source);
        }
        return source.charAt(0);
    }

    private void checkDestinationNotEmpty(String destination) {
        if (destination.isEmpty()) {
            throw new IllegalArgumentException("Destination String cannot be empty");
        }
    }
}
