package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
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
        user.setName(changeUserRequest.getUserData().getName());
        user.setAge(changeUserRequest.getUserData().getAge());
        user.setEmail(changeUserRequest.getUserData().getEmail());
        user.setCash(changeUserRequest.getUserData().getCash());
        user.replacePhones(changeUserRequest.getUserData().getPhones());
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
}
