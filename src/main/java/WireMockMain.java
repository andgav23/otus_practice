import org.example.wiremock.Stubs;

public class WireMockMain {
  public static Stubs stubs = new Stubs();
  public static void main(String[] args) {
    stubs.wireMockStart()
        .getAllUsers("users.json")
        .getUser("user.json")
        .pingPong();}

}
