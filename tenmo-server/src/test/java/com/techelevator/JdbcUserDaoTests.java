package com.techelevator;

import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcUserDaoTests extends TenmoDaoTests {

    private static final User USER_1 =
            new User(1001L, "testuser", "password1", "USER");
    private static final User USER_2 =
            new User(1002L, "testuser1", "password1", "USER");
    private static final User USER_3 =
            new User(1003L, "testuser2", "password1", "USER");
    private static final User USER_4 =
            new User(1004L, "testuser3", "password1", "USER");
    private static final User USER_5 =
            new User(1005L, "testuser4", "password1", "USER");
    private static final User USER_6 =
            new User(1006L, "testuser5", "password1", "USER");

    private User testUser;

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
        testUser = new User(1007L, "testuser6", "password1", "USER");
    }

    //List<User> findAll();

    @Test
    public void findAll_returns_valid_list() {
        List<User> users = sut.findAll();
        Assert.assertEquals(6, users.size());
        assertUsersMatch(USER_1, users.get(0));
        assertUsersMatch(USER_4, users.get(3));
    }

    //User findByUsername(String username);
    @Test
    public void findByUsername_returns_correct_user() {
        User user = sut.findByUsername("testuser");
        assertUsersMatch(USER_1, user);

        User user1 = sut.findByUsername("testuser5");
        assertUsersMatch(USER_4, user1);
    }

    @Test
    public void findByUsername_returns_null_when_username_not_found() {
        User user = sut.findByUsername("George Washington");
        Assert.assertNull(user);
    }

    //int findIdByUsername(String username);
    @Test
    public void findIdByUsername_returns_correct_id() {
        int userId = sut.findIdByUsername("testuser");
        Assert.assertEquals(1001, userId);

        int userId1 = sut.findIdByUsername("testuser3");
        Assert.assertEquals(1004, userId1);
    }

    public void findIdByUsername_returns_null_when_username_not_found() {
        int userId = sut.findIdByUsername("Boots");
        Assert.assertEquals(0, userId);
    }


    //helper method
    private void assertUsersMatch(User expected, User actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());

    }
}
