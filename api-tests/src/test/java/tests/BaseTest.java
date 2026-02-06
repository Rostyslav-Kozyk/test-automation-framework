package tests;

import clients.ClientFactory;
import clients.UsersClient;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected UsersClient usersClient;
    protected UsersClient usersClientWithSwagger;

    @BeforeClass(alwaysRun = true)
    void setUp() {
        usersClient = ClientFactory.getUsersClient();
        usersClientWithSwagger = ClientFactory.getUsersClientWithSwagger();
    }
}
