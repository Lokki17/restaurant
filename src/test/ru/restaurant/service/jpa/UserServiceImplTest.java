package ru.restaurant.service.jpa;

import org.hibernate.engine.internal.Collections;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.restaurant.UserTestData;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.service.AbstractServiceTest;
import ru.restaurant.service.UserService;
import ru.restaurant.to.UserToClient;
import ru.restaurant.util.UserUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static ru.restaurant.UserTestData.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    UserService service;

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(USER, service.get(USER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_USER, ADMIN), service.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        Boolean deleted = service.delete(USER_ID - 1);
        Assert.isTrue(!deleted);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(USER, ADMIN_USER, ADMIN), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = getUpdated();
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        User saved = getCreated();
        service.save(saved);
        MATCHER.assertEquals(saved, service.get(saved.getId()));
    }

    @Test
    public void testSetRole() throws Exception {
        USER.getRoles().add(Role.ROLE_ADMIN);
        service.setRole(USER_ID, Role.ROLE_ADMIN);
        MATCHER.assertEquals(USER, service.get(USER_ID));
    }

    @Test
    public void testDeleteRole() throws Exception {
        ADMIN_USER.getRoles().remove(Role.ROLE_ADMIN);
        service.deleteRole(ADMIN_USER_ID, Role.ROLE_ADMIN);
        MATCHER.assertEquals(ADMIN_USER, service.get(ADMIN_USER_ID));
    }

}