package com.example.alstarboard.entity;

import lombok.*;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment, key 생성 전략
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;   // not null, 길이 100, varchar(100)

    @Column(nullable = false, length = 300)
    private String text;

    @Builder.Default
    @Column(name = "`like`")    // like는 예약어이므로 ``로 감싸줌, name으로 컬럼명을 `like`로 지정
    private Long like = 0L;     // default 0

    @Builder.Default
    @Column(name = "`unlike`")
    private Long unlike = 0L;

    @ManyToOne
    @JoinColumn(name = "`user`")    // board테이블의 컬럼명 user, 외래키
    @ToString.Exclude
    private User user; // User 테이블의 외래 키

    @ManyToOne
    @JoinColumn(name = "category")
    @ToString.Exclude
    private Category category;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp updatedAt;


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Image> images;
}
