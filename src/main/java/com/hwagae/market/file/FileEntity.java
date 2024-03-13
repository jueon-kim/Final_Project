package com.hwagae.market.file;

import com.hwagae.market.post.PostEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileNum;

    @Column
    private String fileUrl;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_num")
    private PostEntity postEntity;


    // Update 로직 추가
    public void updateFileUrl(String newFileUrl){
        this.fileUrl = newFileUrl;
    }

    public static FileEntity toFileEntity(PostEntity postEntity,String fileUrl){
        FileEntity fileEntity=new FileEntity();
        fileEntity.setFileUrl(fileUrl);
        fileEntity.setPostEntity(postEntity);
        return fileEntity;
    }

}