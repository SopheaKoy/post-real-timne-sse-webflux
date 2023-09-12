package me.sophea.postrealtimessewebflux.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.sophea.postrealtimessewebflux.model.Comment;
import me.sophea.postrealtimessewebflux.model.Post;

@Data
@AllArgsConstructor
public class CommentRequest {

    private Post post;
    private Comment comment;

}
