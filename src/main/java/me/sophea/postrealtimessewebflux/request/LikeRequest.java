package me.sophea.postrealtimessewebflux.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.sophea.postrealtimessewebflux.model.Post;
import me.sophea.postrealtimessewebflux.model.User;

@Data
@AllArgsConstructor
public class LikeRequest {

    private Post post;
    private User user;


}
