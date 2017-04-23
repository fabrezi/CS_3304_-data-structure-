import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * Created by farid on 4/22/2017.
 */
public class rmi_server {

    public static void main(String[] args){
        try{
            implement_interface obj = new implement_interface();

            Registry registry = LocateRegistry.createRegistry(7777);

            registry.rebind("hi", obj);

            System.out.println("server starts");

        } catch (RemoteException re){
            re.printStackTrace();
        }
    }
}
