import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Zmap {

	static String ipToString(String ip){
		String[] bytes = ip.split("\\.");
		return (""+	(char) Integer.parseInt(bytes[0]) + 
					(char) Integer.parseInt(bytes[1]) + 
					(char) Integer.parseInt(bytes[2]) + 
					(char) Integer.parseInt(bytes[3])
		);
	}

	static String stringToIp(String s){
		return (""+
			(int) s.charAt(0) + '.' +
			(int) s.charAt(1) + '.' + 
			(int) s.charAt(2) + '.' + 
			(int) s.charAt(3)
			);
	}
	
	public static void main(String[] args) {
        Zmap obj = new Zmap();

//		for(int i=0;i<256;i++){
//			for(int j=0;j<256;j++){
//				for(int k=0;k<256;k++){
//					for(int l=1;l<224;l++){ 				//0.0.0.0/8           # RFC1122: "This host on this network"
//															//240.0.0.0/4         # RFC1112: Reserved
//															//224.0.0.0/4         # RFC5771: Multicast/Reserved
//						if(
//							l!=10 &&						//10.0.0.0/8          # RFC1918: Private-Use
//							l!=127 &&						//127.0.0.0/8         # RFC1122: Loopback
//							!(l==100 && k>=64 && k<128) &&	//100.64.0.0/10       # RFC6598: Shared Address Space
//							!(l==192 && k==168) &&			//192.168.0.0/16      # RFC1918: Private-Use
//							!(l==169 && k==254) &&			//169.254.0.0/16      # RFC3927: Link Local
//							!(l==172 && k>=16 && k<32) &&	//172.16.0.0/12       # RFC1918: Private-Use
//							!(l==198 && (k==18 || k==19))	//198.18.0.0/15       # RFC2544: Benchmarking
//							/*
//								192.0.0.0/24        # RFC6890: IETF Protocol Assignments
//								192.0.2.0/24        # RFC5737: Documentation (TEST-NET-1)
//								192.88.99.0/24      # RFC3068: 6to4 Relay Anycast
//								198.51.100.0/24     # RFC5737: Documentation (TEST-NET-2)
//								203.0.113.0/24      # RFC5737: Documentation (TEST-NET-3)
//								255.255.255.255/32  # RFC0919: Limited Broadcast
//							 */
//						){
//							System.out.println(l+"."+k+"."+j+"."+i);
//						}
//					}
//				}
//			}
//		}

        String ipAddress = "192.168.178.30";
        String outputFile = "/mnt/nfs_share/res.txt";
        String command = "zmap -n 1 -N 1 -p 80 " + ipAddress + " -o "+outputFile;
        String output = obj.executeCommand(command);
        System.out.println("Command executed. Output:");
        System.out.println(output);
	}

    private String executeCommand(String command) {
        System.out.println("Executing: "+command);
        StringBuffer output = new StringBuffer();
        Process p;

        StringBuilder cmdReturn = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            try (InputStream inputStream = process.getInputStream()) {
                int c;
                while ((c = inputStream.read()) != -1) {
                    cmdReturn.append((char) c);
                }
            }
            System.out.println(cmdReturn.toString());

        } catch (IOException ex) {
            Logger.getLogger(Zmap.class.getName())
                    .log(Level.SEVERE, null, ex);
        }


        return output.toString();

    }

}
