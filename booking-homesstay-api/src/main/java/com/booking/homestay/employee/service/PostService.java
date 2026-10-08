package com.booking.homestay.employee.service;

import com.booking.homestay.employee.dto.PostRequest;
import com.booking.homestay.employee.dto.PostResponse;
import com.booking.homestay.employee.mapper.PostMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.Post;
import com.booking.homestay.repository.PostRepository;

import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;


    public void save(PostRequest postRequest) {
        Optional<Post> posts = postRepository.findByTitle(postRequest.getTitle());
        if (posts.isPresent()) {
            throw new SpringException("Tiêu đề bài viết đã tồn tại");
        }
        postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostByType(Long typepostId) {
        if (authService.getCurrentUser().getHomeStay()==null) {
            return postRepository.findByTypePost_Id(typepostId)
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(toList());
        } else {
            return postRepository.findByTypePost_IdAndHomeStayOrTypePost_IdAndHomeStayNull(typepostId,authService.getCurrentUser().getHomeStay(), typepostId)
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(toList());
        }
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostByTypeMember(Long typepostId) {
        return postRepository.findByTypePost_Id(typepostId)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> countPost(Long id) {
        return postRepository.findByTypePost_Id( id)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void editPost(PostRequest postRequest) {
        Post post = postRepository.findById(postRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại bài viết ID - " + postRequest.getId()));
        Optional<Post> postName = postRepository.findByTitle(postRequest.getTitle());
        if (postName.isEmpty()) {
            postRepository.save(postMapper.mapEditToDtoById(postRequest, post));
        } else if (postName.get().getTitle().equals(postRequest.getTitle()) && postName.get().getId().equals(postRequest.getId())) {
            postRepository.save(postMapper.mapEditToDtoById(postRequest, post));
        } else {
            throw new SpringException("Tiêu đề bài viết đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại bài viết ID - " + id));
        return postMapper.mapToDto(post);
    }

}
