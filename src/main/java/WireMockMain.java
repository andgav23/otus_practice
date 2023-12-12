import org.example.wiremock.Stubs;

public class WireMockMain {
  public static Stubs stubs = new Stubs();
  public static void main(String[] args) {
    stubs.wireMockStart()
        .getAllUsers("users.json")
        .getUserOne("user-id1.json")
        .getUserTwo("user-id2.json")
        .pingPong();}

}
