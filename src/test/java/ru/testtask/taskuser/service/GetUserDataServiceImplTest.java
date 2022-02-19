package ru.testtask.taskuser.service;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.testtask.taskuser.AbstractLocalTest;
import ru.testtask.taskuser.model.Phones;
import ru.testtask.taskuser.model.Users;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GetUserDataServiceImplTest extends AbstractLocalTest {

    @Autowired
    GetUserDataService getUserDataService;

    @Test
    void getUserById_test() {
        Users user = createUser();
        GetUserDataService.UserDataResponse userResponse = getUserDataService.getUserById(user.getId());
        Assertions.assertThat(userResponse)
                .extracting(GetUserDataService.UserDataResponse::getName,
                        GetUserDataService.UserDataResponse::getAge,
                        GetUserDataService.UserDataResponse::getEmail)
                .containsExactly(user.getName(), user.getAge(), user.getEmail());
        Assertions.assertThat(userResponse.getPhones())
                .containsExactlyInAnyOrderElementsOf(user.getPhones().stream()
                        .map(Phones::getPhone).collect(Collectors.toSet()));
        Assertions.assertThat(user.getProfiles().getCash())
                .isCloseTo(new BigDecimal(userResponse.getCash()), Percentage.withPercentage(0.001));
    }

    @Test
    void filterUsers_test_checkAge() {
        List<Users> users = prepareUsersGroup();

        Collection<GetUserDataService.UserDataResponse> response =
                getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                .age(35)
                .page(0)
                .pageSize(4)
                .build());
        Assertions.assertThat(response.stream().map(GetUserDataService.UserDataResponse::getId))
                .hasSize(2)
                .containsExactlyInAnyOrder(users.get(0).getId(), users.get(3).getId());
    }

    @Test
    void filterUsers_test_checkEmailAge() {
        List<Users> users = prepareUsersGroup();

        Collection<GetUserDataService.UserDataResponse> response =
                getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                        .age(35)
                        .email("stepanIM@mail.com")
                        .page(0)
                        .pageSize(4)
                        .build());
        Assertions.assertThat(response.stream().map(GetUserDataService.UserDataResponse::getId))
                .hasSize(1)
                .containsExactlyInAnyOrder(users.get(0).getId());
    }

    @Test
    void filterUsers_test_checkName() {
        List<Users> users = prepareUsersGroup();

        Collection<GetUserDataService.UserDataResponse> response =
                getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                        .nameLike("ale")
                        .page(0)
                        .pageSize(4)
                        .build());
        Assertions.assertThat(response.stream().map(GetUserDataService.UserDataResponse::getId))
                .hasSize(2)
                .containsExactlyInAnyOrder(users.get(2).getId(), users.get(3).getId());
    }

    @Test
    void filterUsers_test_checkPhone() {
        List<Users> users = prepareUsersGroup();

        Collection<GetUserDataService.UserDataResponse> response =
                getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                        .phone("89211112234")
                        .page(0)
                        .pageSize(4)
                        .build());
        Assertions.assertThat(response.stream().map(GetUserDataService.UserDataResponse::getId))
                .hasSize(3)
                .containsExactlyInAnyOrder(users.get(0).getId(),
                        users.get(2).getId(), users.get(3).getId());
    }

    @Test
    void filterUsers_test_allConds() {
        List<Users> users = prepareUsersGroup();

        Collection<GetUserDataService.UserDataResponse> response =
                getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                        .nameLike("ale")
                        .age(35)
                        .email("alekseyMN2@mail.com")
                        .phone("89211112234")
                        .page(0)
                        .pageSize(4)
                        .build());
        Assertions.assertThat(response.stream().map(GetUserDataService.UserDataResponse::getId))
                .hasSize(1)
                .containsExactlyInAnyOrder(users.get(3).getId());
    }

    @Test
    void filterUsers_allUsers() {
        List<Users> users = prepareUsersGroup();

        List<GetUserDataService.UserDataResponse> response =
                getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                       .page(0)
                        .pageSize(4)
                        .build());
        Assertions.assertThat(response.stream().map(GetUserDataService.UserDataResponse::getId))
                .hasSize(4)
                .containsSequence(users.get(2).getId(),
                        users.get(3).getId(), users.get(4).getId(),
                        users.get(1).getId());

        response = getUserDataService.filterUsers(GetUserDataService.FilteredRequest.builder()
                        .page(1)
                        .pageSize(4)
                        .build());
        Assertions.assertThat(response.stream().map(GetUserDataService.UserDataResponse::getId))
                .hasSize(1)
                .containsSequence(users.get(0).getId());
    }

    private List<Users> prepareUsersGroup() {
        Users user1 = createUsers("Stepan", 35, "stepanIM@mail.com",
                "234567.12", Set.of("89211112233", "89211112234", "89211112235"));
        Users user2 = createUsers("Ivan", 36, "stepanTF@mail.com",
                "350000.12", Set.of("89211112243", "89211112244", "89211112245"));
        Users user3 = createUsers("Aleksandr", 37, "aleksKL2@mail.com",
                "350000.12", Set.of("89211112233", "89211112234", "89211112285"));
        Users user4 = createUsers("Aleksey", 35, "alekseyMN2@mail.com",
                "360000.12", Set.of("89211112234", "89211112264", "89211112265"));
        Users user5 = createUsers("Fedor", 39, "fedorKl@mail.com",
                "360000.12", Set.of("89211112235", "89211112274", "89211112275"));
        flushAndClearSession();

        return List.of(user1, user2, user3, user4, user5);
    }
}
