package com.teamntp.firstproject.board.repository;

import com.teamntp.firstproject.board.entity.Board;
import com.teamntp.firstproject.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>
        , SearchBoardRepository
{

    // 연관관계가 있는 엔티티 조회(on 생략 가능)
    // 한개의 로우(Object) 내에 Object[ ] 로 나옴
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno ")
    Object getBoardWithWriter(@Param("bno") Long bno);

    // 연관관계가 없는 엔티티 조회(on 생략 불가능)
    @Query("select b, r from Board b left join Reply r on r.board = b where b.bno = :bno ")
    List<Object[]> getBoardListWithReply(@Param("bno") Long bno);

    // 목록 화면에 필요한 데이터
    @Query(value = "select b, w, count(r) " +
            " from Board b " +
            " left join b.writer w " +
            " left join Reply r on r.board = b " +
            " group by b ",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);


    /* todo 게시글 조회할 때 댓글을 같이 뿌릴 것인가?
     * view 에서 ajax 처리해서 필요할 때 동적으로 가져올 것인가?
     * 책에선 후자의 방법을 택함, 전자를 택할 경우 어떻게 처리하면 좋을지 생각해 볼 것 */
    @Query("select b, w, count(r) " +
            "from Board b left join b.writer w " +
            "left join Reply r on r.board = b " +
            "where b.bno = :bno ")
    Object getBoardByBno(@Param("bno") Long bno);
}
