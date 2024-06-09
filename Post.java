package demoapp;


import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The Post class represents a post in the CodeQA platform.
 * It includes methods to add a post and add a comment to a post.
 */
public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private ArrayList<String> postComments = new ArrayList<>();

    // Constructor to initialize a post with necessary details
    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

   
    public boolean addPost() {
        // Debugging statement
        System.out.println("Debug: Attempting to add post with title: " + postTitle);

        // Validate the post title
        if (!isValidTitle(postTitle)) {
            System.out.println("Debug: Invalid title");
            return false;
        }
        // Validate the post body
        if (!isValidBody(postBody, postType)) {
            System.out.println("Debug: Invalid body");
            return false;
        }
        // Validate the post tags
        if (!isValidTags(postTags, postType)) {
            System.out.println("Debug: Invalid tags");
            return false;
        }
        // Validate the post emergency status
        if (!isValidEmergency(postEmergency, postType)) {
            System.out.println("Debug: Invalid emergency status");
            return false;
        }

        // Attempt to write the post to a file
        try (FileWriter writer = new FileWriter("post.txt", true)) {
            writer.write(this.toString() + "\n");
            System.out.println("Debug: Post added successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Validates the post title
    private boolean isValidTitle(String title) {
        return title.length() >= 10 && title.length() <= 250 && Pattern.matches("[a-zA-Z]{5}.*", title);
    }

    // Validates the post body
    private boolean isValidBody(String body, String type) {
        int minLength = type.equals("Very Difficult") || type.equals("Difficult") ? 300 : 250;
        return body.length() >= minLength;
    }

    // Validates the post tags
    private boolean isValidTags(String[] tags, String type) {
        if (tags.length < 2 || tags.length > 5) return false;
        if (type.equals("Easy") && tags.length > 3) return false;

        for (String tag : tags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    // Validates the post emergency status
    private boolean isValidEmergency(String emergency, String type) {
        if (type.equals("Very Difficult") || type.equals("Difficult")) {
            return !emergency.equals("Ordinary");
        } else if (type.equals("Easy")) {
            return !(emergency.equals("Immediately Needed") || emergency.equals("Highly Needed"));
        }
        return true;
    }

    /**
     * Adds a comment to the post if it meets all validation criteria.
     * 
     * @param comment The comment to be added
     * @return true if the comment was added successfully, false otherwise
     */
    public boolean addComment(String comment) {
        // Debugging statement
        System.out.println("Debug: Attempting to add comment: " + comment);

        // Validate the comment
        if (!isValidComment(comment)) {
            System.out.println("Debug: Invalid comment");
            return false;
        }
        // Check if the maximum number of comments is reached
        if (!isValidCommentCount()) {
            System.out.println("Debug: Too many comments");
            return false;
        }

        // Add the comment to the list of comments
        postComments.add(comment);

        // Attempt to write the comment to a file
        try (FileWriter writer = new FileWriter("comment.txt", true)) {
            writer.write(comment + "\n");
            System.out.println("Debug: Comment added successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Validates the comment
    private boolean isValidComment(String comment) {
        String[] words = comment.split(" ");
        return words.length >= 4 && words.length <= 10 && Character.isUpperCase(words[0].charAt(0));
    }

    // Validates the number of comments
    private boolean isValidCommentCount() {
        int maxComments = postType.equals("Easy") || postType.equals("Ordinary") ? 3 : 5;
        return postComments.size() < maxComments;
    }

    // Overrides the toString method to format the post information
    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postTitle='" + postTitle + '\'' +
                ", postBody='" + postBody + '\'' +
                ", postTags='" + String.join(", ", postTags) + '\'' +
                ", postType='" + postType + '\'' +
                ", postEmergency='" + postEmergency + '\'' +
                ", postComments=" + postComments +
                 '}';
}
}