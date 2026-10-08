package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.TypePostRequest;
import com.booking.homestay.admin.dto.TypePostResponse;
import com.booking.homestay.model.TypePost;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class TypePostMapperImpl extends TypePostMapper {

    @Override
    public TypePost map(TypePostRequest typePostRequest) {
        if ( typePostRequest == null ) {
            return null;
        }

        TypePost typePost = new TypePost();

        typePost.setTypeName( typePostRequest.getTypeName() );

        return typePost;
    }

    @Override
    public TypePostResponse mapToDto(TypePost typePost) {
        if ( typePost == null ) {
            return null;
        }

        TypePostResponse typePostResponse = new TypePostResponse();

        typePostResponse.setId( typePost.getId() );
        typePostResponse.setTypeName( typePost.getTypeName() );

        typePostResponse.setCountPost( getCountPost(typePost) );

        return typePostResponse;
    }

    @Override
    public TypePostResponse mapToDto2(TypePost typePost) {
        if ( typePost == null ) {
            return null;
        }

        TypePostResponse typePostResponse = new TypePostResponse();

        typePostResponse.setId( typePost.getId() );
        typePostResponse.setTypeName( typePost.getTypeName() );

        typePostResponse.setCountPost( getCountPost2(typePost) );

        return typePostResponse;
    }

    @Override
    public TypePost mapEditToDtoById(TypePostRequest typePostRequest, TypePost typePost) {
        if ( typePostRequest == null && typePost == null ) {
            return null;
        }

        TypePost typePost1 = new TypePost();

        if ( typePostRequest != null ) {
            typePost1.setId( typePostRequest.getId() );
            typePost1.setTypeName( typePostRequest.getTypeName() );
        }

        return typePost1;
    }
}
