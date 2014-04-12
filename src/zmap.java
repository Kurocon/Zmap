
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
		for(int i=0;i<256;i++){
			for(int j=0;j<256;j++){
				for(int k=0;k<256;k++){
					for(int l=1;l<224;l++){ 				//0.0.0.0/8           # RFC1122: "This host on this network"
															//240.0.0.0/4         # RFC1112: Reserved
															//224.0.0.0/4         # RFC5771: Multicast/Reserved
						if(
							l!=10 &&						//10.0.0.0/8          # RFC1918: Private-Use
							l!=127 &&						//127.0.0.0/8         # RFC1122: Loopback
							!(l==100 && k>=64 && k<128) &&	//100.64.0.0/10       # RFC6598: Shared Address Space
							!(l==192 && k==168) &&			//192.168.0.0/16      # RFC1918: Private-Use
							!(l==169 && k==254) &&			//169.254.0.0/16      # RFC3927: Link Local
							!(l==172 && k>=16 && k<32) &&	//172.16.0.0/12       # RFC1918: Private-Use
							!(l==198 && (k==18 || k==19))	//198.18.0.0/15       # RFC2544: Benchmarking
							/*
								192.0.0.0/24        # RFC6890: IETF Protocol Assignments
								192.0.2.0/24        # RFC5737: Documentation (TEST-NET-1)
								192.88.99.0/24      # RFC3068: 6to4 Relay Anycast
								198.51.100.0/24     # RFC5737: Documentation (TEST-NET-2)
								203.0.113.0/24      # RFC5737: Documentation (TEST-NET-3)
								255.255.255.255/32  # RFC0919: Limited Broadcast
							 */
						){
							System.out.println(l+"."+k+"."+j+"."+i);
						}
					}
				}
			}
		}
		System.out.println(ipToString("65.66.67.68"));

		System.out.println(stringToIp(ipToString("65.66.67.68")));
	}

}
