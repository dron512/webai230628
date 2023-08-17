package com.teamntp.firstproject.board.repository;

import com.teamntp.firstproject.board.entity.Board;
import com.teamntp.firstproject.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // @Modifying: JPQL을 이용해서 update, delete를 실행하기 위해서 필요함
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno ")
    void deleteByBno(@Param("bno") Long bno);
    // error: Named parameter not bound
    // @Param 설정 안함
    // 그거 말고도 JPQL 작성할 때 띄어쓰기도 중요한 듯

    // 게시물로 댓글 가져오기(쿼리메소드로 처리)
    // 특정 게시글 번호로 댓글을 가져와야 한다.
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
