package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.UserRepository;
import com.ccstudy.qna.service.converter.UserUpdateConverter;
import com.ccstudy.qna.service.dto.user.UserDetailInfo;
import com.ccstudy.qna.service.dto.user.UserRequestDto;
import com.ccstudy.qna.service.dto.user.UserSimpleInfo;
import com.ccstudy.qna.service.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserRequestDto requestDto) {
        userRepository.save(requestDto.toEntity());
    }

    public UserDetailInfo findById(Long userId) {
        User user = findUser(userId);
        return new UserDetailInfo(user);
    }

    @Transactional
    public void update(UserUpdateDto updateDto) {
        User user = findUser(updateDto.getId());
        UserUpdateConverter.update(user, updateDto);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);
    }

    public List<UserSimpleInfo> findAll() {
        return userRepository.findAll().stream()
                .map(UserSimpleInfo::new)
                .collect(Collectors.toList());
    }
}
