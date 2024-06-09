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

    // Tests adding a post with invalid tags
    @Test
    public void shouldNotAddPostWithInvalidTags() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"T", "sample2"}, "Challenging", "Crucial");
        assertFalse(post.addPost());
    }

    // Tests adding a post with an invalid type body
    @Test
    public void shouldNotAddPostWithInvalidTypeBody() {
        post = new Post(1, "SampleTitle", "Short body for challenging post",
                new String[]{"sample1", "sample2"}, "Extremely Challenging", "Crucial");
        assertFalse(post.addPost());
    }

    // Tests adding a post with invalid type tags
    @Test
    public void shouldNotAddPostWithInvalidTypeTags() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"sample1", "sample2", "sample3", "sample4"}, "Simple", "Ordinary");
    assertFalse(post.addPost());
    }

    // Tests adding a valid comment
    @Test
    public void shouldAddCommentWithValidData() {
        assertTrue(post.addComment("This is a valid comment."));
    }

    // Tests adding a comment with invalid text length
    @Test
    public void shouldNotAddCommentWithInvalidTextLength() {
        assertFalse(post.addComment("Too short."));
    }

    // Tests adding a comment with an invalid first character
    @Test
    public void shouldNotAddCommentWithInvalidFirstCharacter() {
        assertFalse(post.addComment("this is invalid."));
    }

    // Tests adding more comments than the allowed maximum
    @Test
    public void shouldNotAddMoreThanAllowedComments() {
        for (int i = 0; i < 5; i++) {
            assertTrue(post.addComment("Valid comment number " + i));
        }
        assertFalse(post.addComment("One more comment."));
    }

    // Tests adding more comments than the allowed maximum for an "Easy" post
    @Test
    public void shouldNotAddCommentsBeyondLimitForEasyPost() {
        post = new Post(1, "SampleTitle", "This is a sample post body with more than 250 characters. ".repeat(10),
                new String[]{"sample1", "sample2"}, "Simple", "Ordinary");
        for (int i = 0; i < 3; i++) {
            assertTrue(post.addComment("Valid comment number " + i));
        }
        assertFalse(post.addComment("One more comment."));
    }
}