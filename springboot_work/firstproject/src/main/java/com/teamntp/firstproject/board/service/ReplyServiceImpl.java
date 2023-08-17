package com.teamntp.firstproject.board.service;

import com.teamntp.firstproject.board.dto.ReplyDTO;
import com.teamntp.firstproject.board.entity.Board;
import com.teamntp.firstproject.board.entity.Reply;
import com.teamntp.firstproject.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(long bno) {
        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(Board.builder()
                .bno(bno)
                .build());
        return result.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);

    }

    @Override
    public void remove(long rno) {
        replyRepository.deleteById(rno);

    }
}
