import java.util.regex.*;

public class Reg {
    public static void main(String[] args) {
        var ip = "192.168.1.1";
        byte[] ipArray = ipParser(ip);
        for(var a : ipArray) {
            System.out.println(Integer.toBinaryString(a));
        }
    }

    

    public static byte[] ipParser(String ipAddress) {
        var pattern  = Pattern.compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}");
        var matcher = pattern.matcher(ipAddress);
        // Define a byte array having 4 elements
        byte[] ipArray = new byte[4];
        if(matcher.matches()) {
          // Split the IP address into 4 portions
          String[] portions = matcher.group().split("\\.");
          for(int i = 0; i < 4; ++i) {
            int digit = Integer.parseInt(portions[i]);
            if(digit < 0 || digit > 255) {
              throw new IllegalArgumentException("This is not a valid ipv4");  
            }
            // Type cast the integer into a byte 
            ipArray[i] = (byte) digit;
          }
          } else {
              throw new IllegalArgumentException("ipv4 not found");
          }
        return ipArray;    
      }
}
