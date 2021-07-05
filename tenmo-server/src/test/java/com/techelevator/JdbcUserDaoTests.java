package com.techelevator;

import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class JdbcUserDaoTests extends TenmoDaoTests {

    private static final User USER_1 =
            new User(1091L, "testuser1", "password1", "USER");
    private static final User USER_2 =
            new User(1092L, "testuser2", "password1", "USER");
    private static final User USER_3 =
            new User(1093L, "testuser3", "password1", "USER");
    private static final User USER_4 =
            new User(1094L, "testuser4", "password1", "USER");
    private static final User USER_5 =
            new User(1095L, "testuser5", "password1", "USER");
    private static final User USER_6 =
            new User(1096L, "testuser6", "password1", "USER");

    private User testUser;
    private JdbcUserDao sut;

    @Before
    public void setup() {
        sut = new JdbcUserDao(dataSource);
        testUser = new User(1097L, "testuser7", "password1", "USER");
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
        User user = sut.findByUsername("testuser1");
        assertUsersMatch(USER_1, user);

        user = sut.findByUsername("testuser4");
        assertUsersMatch(USER_4, user);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByUsername_returns_exception_when_username_not_found()  {
        sut.findByUsername("George Washington");
    }

    //int findIdByUsername(String username);
    @Test
    public void findIdByUsername_returns_correct_id() {
        int userId = sut.findIdByUsername("testuser1");
        Assert.assertEquals(1091, userId);

        int userId1 = sut.findIdByUsername("testuser4");
        Assert.assertEquals(1094, userId1);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findIdByUsername_returns_null_when_username_not_found() {
        sut.findIdByUsername("Boots");
    }

    @Test
    public void createUser_creates_new_user() {
        boolean createdUser = sut.create("testuser8", "password1" );
        Assert.assertTrue(createdUser);
    }

    //helper method
    private void assertUsersMatch(User expected, User actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
    }
}
