package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.TypePostRequest;
import com.booking.homestay.admin.dto.TypePostResponse;
import com.booking.homestay.admin.mapper.TypePostMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.Post;
import com.booking.homestay.model.TypePost;
import com.booking.homestay.repository.PostRepository;
import com.booking.homestay.repository.TypePostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class TypePostService {

    private final TypePostRepository typePostRepository;
    private final PostRepository postRepository;
    private final TypePostMapper TypePostMapper;

    public void save(TypePostRequest typePostRequest) {
        Optional<TypePost> TypePost = typePostRepository.findByTypeName(typePostRequest.getTypeName());
        if (TypePost.isPresent()) {
            throw new SpringException("Loại bài viết đã tồn tại");
        } else {
            typePostRepository.save(TypePostMapper.map(typePostRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<TypePostResponse> getAllTypePost() {
        return typePostRepository.findAll()
                .stream()
                .map(TypePostMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<TypePostResponse> getAllTypePostMember() {
        return typePostRepository.findAll()
                .stream()
                .map(TypePostMapper::mapToDto2)
                .collect(toList());
    }

    public void deleteTypePost(Long id) {
        List<Post> posts = postRepository.findByTypePost_Id(id);
        if (posts.size() != 0 ) {
            throw new SpringException("Loại bài viết đã tồn tại bài viết");
        } else {
            typePostRepository.deleteById(id);
        }
    }


    public void editTypePost(TypePostRequest typePostRequest) {
        TypePost TypePost = typePostRepository.findById(typePostRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại loại bài viết có ID - " + typePostRequest.getId()));
        Optional<TypePost> TypePostName = typePostRepository.findByTypeName(typePostRequest.getTypeName());
        if (!TypePostName.isPresent()) {
            typePostRepository.save(TypePostMapper.mapEditToDtoById(typePostRequest,TypePost));
        } else if(TypePostName.isPresent() && TypePostName.get().getTypeName().equals(typePostRequest.getTypeName()) && TypePostName.get().getId().equals(typePostRequest.getId())){
            typePostRepository.save(TypePostMapper.mapEditToDtoById(typePostRequest, TypePost));
        } else {
            throw new SpringException("Loại bài viết đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public TypePostResponse getTypePostById(Long id) {
        TypePost typePost = typePostRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại loại bài viết có ID - " + id));
        return TypePostMapper.mapToDto(typePost);
    }

}
