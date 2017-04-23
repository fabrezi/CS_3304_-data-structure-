import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * Created by farid on 4/22/2017.
 */
public class rmi_client {

    public static void main(String[] args){
        try{
            int i=0;
            int transmit=10000;
            for(int num=0; num<5; num++) {
                i = i +5;
                for (int count = 0; count < 5; count++) {
                    Registry registry = LocateRegistry.getRegistry("farid-PC", 7777);
                    rmi_interface obj = (rmi_interface) registry.lookup("hi");

                    System.out.println("i=" + i + "count=" + count);
                    byte[] message = new byte[1024 * i];
                    System.out.println("lets send the numbers:");
                    long start_time = System.nanoTime();
                    for (int n = 0; n < transmit; n++) {
                        obj.catch_message(message);
                    }
                    obj.catch_message("quit".getBytes());
                    long stop_time = System.nanoTime();

                    System.out.println("duration:" + (stop_time - start_time) / 1000000);

                    if ( i == 5 && count == 1){
                        System.out.println("--------------------------------------------------------");
                        System.out.println("1st iteration:" + (stop_time - start_time)/1000000);
                    }

                    if( i==25 && count==1){
                        System.out.println("-------------------------------------------------");
                        System.out.println("100th iteration:" + (stop_time - start_time)/1000000);
                    }

                    if(i == 25 && count == 4){
                        System.out.println("---------------------------------------------");
                        System.out.println("1000th iteration:" + (stop_time - start_time)/1000000);
                    }
                }
            }
        }catch(NotBoundException nbe){
            nbe.printStackTrace();
    } catch(RemoteException re){
            re.printStackTrace();
    }
    }
}
