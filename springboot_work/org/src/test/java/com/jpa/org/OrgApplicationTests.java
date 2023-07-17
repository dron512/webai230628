package com.jpa.org;

import com.jpa.org.entity.FreeBoard;
import com.jpa.org.entity.QFreeBoard;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrgApplicationTests {

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	@Test
	void contextLoads() {
		JPAQuery<FreeBoard> query =
				jpaQueryFactory.selectFrom(QFreeBoard.freeBoard);
		System.out.println(query);

		List<FreeBoard> list = query.fetch();
		System.out.println(list);
	}

}
