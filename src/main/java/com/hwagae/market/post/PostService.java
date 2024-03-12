package com.hwagae.market.post;

import com.hwagae.market.file.FileEntity;
import com.hwagae.market.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileRepository fileRepository;

    public void save(PostDTO postDTO) throws IOException {
        if (postDTO.getPost_upLoadFile().isEmpty()) {
            PostEntity postEntity = PostEntity.toSaveEntity(postDTO);
            postRepository.save(postEntity);
        } else {
            PostEntity postEntity = PostEntity.toSaveFileEntity(postDTO);
            Integer postNum = postRepository.save(postEntity).getPost_num();
            PostEntity post = postRepository.findById(postNum).get();


            for (MultipartFile postFile : postDTO.getPost_upLoadFile()) {
                String originalFilename = postFile.getOriginalFilename();

                if (originalFilename != null && !originalFilename.isEmpty()) {
                    String file_url = System.currentTimeMillis() + "_" + originalFilename;
                    String savePath = "C:/image/" + file_url;
                    postFile.transferTo(new File(savePath));
                    FileEntity fileEntity = FileEntity.toFileEntity(postEntity, file_url);
                    fileRepository.save(fileEntity);
                } else {
                    System.out.println("파일이 입력되지않았습니다~" + originalFilename);
                }
            }
        }
    }

//    public List<PostDTO> findAll() {
//        List<PostEntity> postEntityList = postRepository.findAll();
//        List<PostDTO> postDTOList = new ArrayList<>();
//        for (PostEntity postEntity : postEntityList) {
//            postDTOList.add(PostDTO.toPostDTO(postEntity));
//        }
//        return postDTOList;
//    }

/*    public PostDTO findByNum(Integer post_num) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(post_num);
        if (optionalPostEntity.isPresent()) {
            PostEntity postEntity = optionalPostEntity.get();
            PostDTO postDTO = PostDTO.toPostDTO(postEntity);
            return postDTO;
        }
        return null;
    }*/

//    @Transactional
//    public PostDTO update(PostDTO postDTO) throws Exception {
//        if (postDTO.getPost_num() != null) {
//            PostEntity existingPost = postRepository.findById(postDTO.getPost_num()).orElse(null);
//
//            if (existingPost != null) {
//                deleteOldFiles(existingPost);
//                existingPost.setPost_title(postDTO.getPost_title());
//                existingPost.setPost_content(postDTO.getPost_content());
//
//                existingPost.getPostFiles().clear();
//
//                if (!postDTO.getPost_upLoadFile().isEmpty()) {
//                    for (MultipartFile postFile : postDTO.getPost_upLoadFile()) {
//                        String originalFilename = postFile.getOriginalFilename();
//
//                        if (originalFilename != null && !originalFilename.isEmpty()) {
//                            String file_url = System.currentTimeMillis() + "_" + originalFilename;
//                            String savePath = "C:/image/" + file_url;
//                            postFile.transferTo(new File(savePath));
//
//                            PostFileEntity fileEntity = PostFileEntity.toPostFileEntity(existingPost, file_url);
//                            fileRepository.save(fileEntity);
//                        } else {
//                            System.out.println("파일이 입력되지 않았어요!" + originalFilename);
//                        }
//                    }
//                }
//
//                postRepository.save(existingPost);
//                return findByNum(postDTO.getPost_num());
//            } else {
//                throw new Exception("해당 번호로 된 게시물이 존재하지 않습니다: " + postDTO.getPost_num());
//            }
//        } else {
//            throw new IllegalArgumentException("게시물 번호가 필요합니다.");
//        }
//    }

   /* public void delete(Integer notice_num) {
        postRepository.deleteById(notice_num);
    }*/


    public List<PostDTO> findAll() {
        List<PostEntity> postEntityList = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(PostEntity postEntity: postEntityList){
            postDTOList.add(PostDTO.toPostDTO(postEntity));
        }
        System.out.println("postDTOList = " + postDTOList);
        return postDTOList;
    }


}
