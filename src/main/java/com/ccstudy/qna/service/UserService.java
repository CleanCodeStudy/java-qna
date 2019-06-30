package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.UserRepository;
import com.ccstudy.qna.domain.support.AccessUser;
import com.ccstudy.qna.domain.support.AccessUserStore;
import com.ccstudy.qna.exception.UnAuthorizedException;
import com.ccstudy.qna.service.converter.UserUpdateConverter;
import com.ccstudy.qna.service.dto.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        User user = findUser(AccessUserStore.getUserId());
        UserUpdateConverter.update(user, updateDto);
        AccessUserStore.setAccessUser(new AccessUser(user));
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

    @Transactional(readOnly = true)
    public void login(LoginDto loginDto) {
        User user = getUser(loginDto);
        user.matchPassword(loginDto.getPassword());

        AccessUserStore.setAccessUser(new AccessUser(user));
    }

    private User getUser(LoginDto loginDto) {
        return userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(UnAuthorizedException::new);
    }
}
