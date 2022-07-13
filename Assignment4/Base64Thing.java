import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Base64Thing {
    public static String hashPassword ( String password ) {
        byte [ ] bytesOfPassword = password.getBytes ( StandardCharsets.UTF_8) ;
        byte [ ] md5Digest = new byte [ 0 ] ;
        try {
            md5Digest = MessageDigest. getInstance ( "MD5" ) . digest ( bytesOfPassword ) ;
        } catch ( NoSuchAlgorithmException e ) {
            return null ;
        }return Base64. getEncoder ( ) . encodeToString (md5Digest ) ;
    }
}
