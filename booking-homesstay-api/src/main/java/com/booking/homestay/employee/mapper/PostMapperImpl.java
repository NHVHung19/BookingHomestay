package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.PostRequest;
import com.booking.homestay.employee.dto.PostResponse;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.Post;
import com.booking.homestay.model.TypePost;
import com.booking.homestay.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequest postRequest, User user) {
        if ( postRequest == null && user == null ) {
            return null;
        }

        Post post = new Post();

        if ( postRequest != null ) {
            post.setTypePost( postRequestToTypePost( postRequest ) );
            post.setTitle( postRequest.getTitle() );
            post.setDescription( postRequest.getDescription() );
        }
        if ( user != null ) {
            post.setHomeStay( user.getHomeStay() );
            post.setUser( user );
        }
        post.setCreateDate( java.time.Instant.now() );

        return post;
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( post.getId() );
        postResponse.setTitle( post.getTitle() );
        postResponse.setDescription( post.getDescription() );
        if ( post.getCreateDate() != null ) {
            postResponse.setCreateDate( post.getCreateDate().toString() );
        }
        postResponse.setTypePostName( postTypePostTypeName( post ) );
        postResponse.setId_typePost( postTypePostId( post ) );
        postResponse.setId_homeStay( postHomeStayId( post ) );
        postResponse.setUserName( postUserUserName( post ) );

        return postResponse;
    }

    @Override
    public Post mapEditToDtoById(PostRequest postRequest, Post post) {
        if ( postRequest == null && post == null ) {
            return null;
        }

        Post post1 = new Post();

        if ( postRequest != null ) {
            post1.setTypePost( postRequestToTypePost1( postRequest ) );
            post1.setId( postRequest.getId() );
            post1.setTitle( postRequest.getTitle() );
            post1.setDescription( postRequest.getDescription() );
        }
        if ( post != null ) {
            post1.setCreateDate( post.getCreateDate() );
            post1.setHomeStay( post.getHomeStay() );
            post1.setUser( post.getUser() );
        }

        return post1;
    }

    protected TypePost postRequestToTypePost(PostRequest postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        TypePost typePost = new TypePost();

        typePost.setId( postRequest.getId_typePost() );

        return typePost;
    }

    private String postTypePostTypeName(Post post) {
        if ( post == null ) {
            return null;
        }
        TypePost typePost = post.getTypePost();
        if ( typePost == null ) {
            return null;
        }
        String typeName = typePost.getTypeName();
        if ( typeName == null ) {
            return null;
        }
        return typeName;
    }

    private Long postTypePostId(Post post) {
        if ( post == null ) {
            return null;
        }
        TypePost typePost = post.getTypePost();
        if ( typePost == null ) {
            return null;
        }
        Long id = typePost.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long postHomeStayId(Post post) {
        if ( post == null ) {
            return null;
        }
        HomeStay homeStay = post.getHomeStay();
        if ( homeStay == null ) {
            return null;
        }
        Long id = homeStay.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String postUserUserName(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String userName = user.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }

    protected TypePost postRequestToTypePost1(PostRequest postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        TypePost typePost = new TypePost();

        typePost.setId( postRequest.getId_typePost() );

        return typePost;
    }
}
