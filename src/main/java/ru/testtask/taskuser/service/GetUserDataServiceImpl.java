package ru.testtask.taskuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.taskuser.dao.repositories.UsersRepository;
import ru.testtask.taskuser.model.Phones;
import ru.testtask.taskuser.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetUserDataServiceImpl implements GetUserDataService {

    final UsersRepository usersRepository;
    final CommonUtilService commonUtilService;
    final EntityManager entityManager;

    @Override
    public UserDataResponse getUserById(Long userId) {
        Users user = commonUtilService.getUserById(userId);
        return UserDataResponse.builder()
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .cash(user.getProfiles().getCash().toString())
                .phones(user.getPhones().stream().map(Phones::getPhone).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<UserDataResponse> filterUsers(FilteredRequest request) {
        List<Users> users = filteredUsersByParams(request);
        return users.stream().map(u -> UserDataResponse.builder()
                .id(u.getId())
                .name(u.getName())
                .age(u.getAge())
                .email(u.getEmail())
                .cash(u.getProfiles().getCash().toString())
                .phones(u.getPhones().stream().map(Phones::getPhone).collect(Collectors.toSet()))
                .build()).collect(Collectors.toList());
    }

    public List<Users> filteredUsersByParams(FilteredRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize(), Sort.by("name").ascending());
        Page<Users> page = usersRepository.findAll(new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> usersRoot, CriteriaQuery<?> usersCriteria,
                                         CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (request.getNameLike() != null) {
                    predicates.add(cb.like(cb.lower(usersRoot.get("name")),
                            request.getNameLike().toLowerCase() + "%"));
                }
                if (request.getAge() != null) {
                    predicates.add(cb.equal(usersRoot.get("age"), request.getAge()));
                }
                if (request.getEmail() != null) {
                    predicates.add(cb.equal(usersRoot.get("email"), request.getEmail()));
                }
                if (request.getPhone() != null) {
                    Join<Users, Phones> userPhonesJoin = usersRoot.join("phones");
                    predicates.add(
                            cb.equal(userPhonesJoin.get("phone"), request.getPhone())
                    );
                }
                Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
                return finalPredicate;
            }
        }, pageable);

        return page.toList();
    }
}
