package demoapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Post class.
 */
public class PostTest {
    private Post post;

    // Sets up a valid post object before each test
    @Before
    public void init() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"sample1", "sample2"}, "Challenging", "Crucial");
    }

    // Tests adding a post with valid data
    @Test
    public void shouldAddPostWithValidData() {
        assertTrue(post.addPost());
    }

    // Tests adding a post with an invalid title
    @Test
    public void shouldNotAddPostWithInvalidTitle() {
        post = new Post(1, "Short", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"sample1", "sample2"}, "Challenging", "Crucial");
        assertFalse(post.addPost());
    }

    // Tests adding a post with an invalid body
    @Test
    public void shouldNotAddPostWithInvalidBody() {
        post = new Post(1, "SampleTitle", "Too short",
                new String[]{"sample1", "sample2"}, "Challenging", "Crucial");
        assertFalse(post.addPost());
    }

    // Tests adding a post with an invalid tag
    @Test
    public void shouldNotAddPostWithInvalidTagLength() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"T", "sample2"}, "Challenging", "Crucial");
        assertFalse(post.addPost());
    }

    // Tests adding a post with invalid type tags
    @Test
    public void shouldNotAddPostWithInvalidTypeTags() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"sample1", "sample2", "sample3", "sample4"}, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    // Tests adding a post with invalid type body
    @Test
    public void shouldNotAddPostWithInvalidTypeBody() {
        post = new Post(1, "SampleTitle", "Short body for challenging post",
                new String[]{"sample1", "sample2"}, "Very Difficult", "Crucial");
        assertFalse(post.addPost());
    }

    // Tests adding a post with an invalid emergency status
    @Test
    public void shouldNotAddPostWithInvalidEmergency() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"sample1", "sample2"}, "Easy", "Immediately Needed");
        assertFalse(post.addPost());
    }

    // Tests adding a valid comment
    @Test
    public void shouldAddValidComment() {
        assertTrue(post.addComment("This is a valid comment."));
    }

    // Tests adding an invalid comment
    @Test
    public void shouldNotAddInvalidComment() {
        assertFalse(post.addComment("invalid comment."));
    }

    // Tests adding more than the allowed number of comments
    @Test
    public void shouldNotAddMoreThanAllowedComments() {
        post.addComment("This is the first comment.");
        post.addComment("This is the second comment.");
        post.addComment("This is the third comment.");
        post.addComment("This is the fourth comment.");
        assertFalse(post.addComment("This is the fifth comment."));
    }

    // Tests adding more than the allowed number of comments for an "Easy" post type
    @Test
    public void shouldNotAddMoreThanAllowedCommentsForEasyPost() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"sample1", "sample2"}, "Easy", "Ordinary");
        post.addComment("This is the first comment.");
        post.addComment("This is the second comment.");
        post.addComment("This is the third comment.");
        assertFalse(post.addComment("This is the fourth comment."));
    }

    // Tests adding a post with an uppercase tag
    @Test
    public void shouldNotAddPostWithUppercaseTags() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"Sample1", "sample2"}, "Challenging", "Crucial");
        assertFalse(post.addPost());
    }
}