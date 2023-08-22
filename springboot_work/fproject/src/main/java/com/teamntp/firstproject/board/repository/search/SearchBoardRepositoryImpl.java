package com.teamntp.firstproject.board.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.teamntp.firstproject.board.entity.Board;
import com.teamntp.firstproject.board.entity.QBoard;
import com.teamntp.firstproject.member.entity.QMember;
import com.teamntp.firstproject.board.entity.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        // 조인 처리
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        // 집계함수 처리, Tuple 반환
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());
        tuple.groupBy(board);

        log.info("==========================================");
        log.info(tuple);
        log.info("==========================================");

        // List<Board> result = jpqlQuery.fetch();
        List<Tuple> result = tuple.fetch();

        log.info(result);
        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("searchPage...................................");

        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        // 조인 처리
        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        // Tuple 타입으로 select 해옴
        // select b, w, count(r) from Board b
        // left join b.writer w left join Reply r on r.board = b
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());

        // where 절에 들어갈 조건문 작성
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);
        booleanBuilder.and(expression);

        if(type !=null) {
            String[] typeArr = type.split("");

            // 검색 조건 작성하기
            BooleanBuilder conditionalBuilder = new BooleanBuilder();

            for (String t:typeArr) {
                switch (t){
                    case "t" :
                        conditionalBuilder.or(board.title.contains(keyword));
                        break;
                    case "w" :
                        conditionalBuilder.or(member.email.contains(keyword));
                        break;
                    case "c" :
                        conditionalBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionalBuilder);
        }

        tuple.where(booleanBuilder);
        // group by
        tuple.groupBy(board);

        // 정렬과 페이징 처리
        this.getQuerydsl().applyPagination(pageable, tuple);

        // 결과
        List<Tuple> result = tuple.fetch();
        log.info(result);

        // 결과(result)의 tuple 수
        // 검색 조건에 맞는 튜플의 개수 처리
        long count = tuple.fetchCount();
        log.info("Count: "+ count);

        // PageImpl 객체 리턴
        return new PageImpl<Object[]>(
                result.stream().map(Tuple::toArray).collect(Collectors.toList())
                , pageable
                , count);
    }
}
