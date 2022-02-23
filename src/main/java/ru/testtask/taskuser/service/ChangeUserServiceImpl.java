package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.excpetions.UserAlreadyExistException;
import ru.testtask.taskuser.model.Phones;
import ru.testtask.taskuser.model.Users;

import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ChangeUserServiceImpl implements ChangeUserService {

    final UsersRepository usersRepository;
    final CommonUtilService commonUtilService;

    @Override
    public UserDataResponse createUser(CreateUserRequest createUserRequest) {
        usersRepository.findByEmail(createUserRequest.getEmail())
                .ifPresent(u -> {throw new UserAlreadyExistException(createUserRequest.getEmail());});

        Users users = new Users();
        for (String phoneVal : createUserRequest.getPhones()) {
            users.addPhone(phoneVal);
        }
        users.setName(createUserRequest.getName());
        users.setEmail(createUserRequest.getEmail());
        users.setAge(createUserRequest.getAge());
        users.setCash(createUserRequest.getCash());
        Users createdUser = usersRepository.save(users);
        return UserDataResponse.builder()
                .id(createdUser.getId())
                .name(createdUser.getName())
                .age(createdUser.getAge())
                .email(createdUser.getEmail())
                .cash(createdUser.getProfiles().getCash().toString())
                .phones(createdUser.getPhones().stream().map(Phones::getPhone).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public UserDataResponse changeUser(ChangeUserRequest changeUserRequest) {
        Users user = commonUtilService.getUserById(changeUserRequest.getUserId());
        if (changeUserRequest.getName() != null) {
            user.setName(changeUserRequest.getName());
        }
        if (changeUserRequest.getAge() != null) {
            user.setAge(changeUserRequest.getAge());
        }
        if (changeUserRequest.getEmail() != null) {
            user.setEmail(changeUserRequest.getEmail());
        }
        if (changeUserRequest.getCash() != null) {
            user.setCash(changeUserRequest.getCash());
        }
        if (changeUserRequest.getPhones() != null &&
                !changeUserRequest.getPhones().isEmpty()) {
            user.replacePhones(changeUserRequest.getPhones());
        }

        Users changedUser = usersRepository.save(user);
        return UserDataResponse.builder()
                .id(changedUser.getId())
                .name(changedUser.getName())
                .age(changedUser.getAge())
                .email(changedUser.getEmail())
                .cash(changedUser.getProfiles().getCash().toString())
                .phones(changedUser.getPhones().stream().map(Phones::getPhone).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void deleteUser(Long userId) {
        Users user = commonUtilService.getUserById(userId);
        usersRepository.delete(user);
    }
}
