
import org.json.*;
import java.util.*;
import java.security.*;
import java.io.*;
import java.net.*;

public class Test
{
    public static void main(String[] args) throws Exception 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Is the device on(yes/no)?: ");
        String dev_st=sc.nextLine();
        boolean dev=dev_st.toLowerCase().contains("yes");
        System.out.println("Is the encryption on(yes/no)?: ");
        String ecr_st=sc.nextLine();
        boolean ecr=ecr_st.toLowerCase().contains("yes");
        String key= "";
        if(ecr){
            System.out.println("Generating a new encryption key .....");
            //String key=sc.nextLine();
            byte[] bytes = new byte[16];
            SecureRandom.getInstanceStrong().nextBytes(bytes);
            /* String hex="";
            for(byte i:bytes)
            hex+=String.format("%02X",i);
            System.out.print(hex);
            System.out.println();
             */
            byte[] encoded = Base64.getEncoder().encode(bytes);
            key=new String(encoded);

        }
        JSONObject jsonobj=new JSONObject();
        jsonobj.put("device_status",dev);
        jsonobj.put("encryption_status",ecr);
        jsonobj.put("key",key);
        String fln=jsonobj.toString();
        System.out.println(fln);
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("objects.json"));
            bw.write(fln);
            bw.close();

        }
        catch (Exception E)
        {

        }
        System.out.println("Updating the file, pls wait");
        try{

            Process process=Runtime.getRuntime().exec("git add .");
            process.waitFor();
            Process process1=Runtime.getRuntime().exec("git commit -m \"New commit\"");
            process1.waitFor();
            Process process2=Runtime.getRuntime().exec("git push origin main");
            process2.waitFor();

        }
        catch (Exception E)
        {
            System.out.println("Internet Issue");
        }

        System.out.println("Upload Complete!");
        System.out.println("Verifying Upload....");
        while(true)
        {
            try{String url="https://Anisha100.github.io/testing/objects.json";
                URL urlget=new URL(url);
                String read="";
                HttpURLConnection connection = (HttpURLConnection) urlget.openConnection();  
                connection.setRequestMethod("GET"); 
                InputStreamReader isrObj = new InputStreamReader(connection.getInputStream());  
                BufferedReader bf = new BufferedReader(isrObj);
                StringBuffer curr=new StringBuffer();
                while ((read = bf .readLine()) != null)  
                {  
                    curr.append(read);  
                }   
                // closing the BufferedReader  
                bf.close();
                connection.disconnect();
                if(curr.toString().equals(fln))
                    break;
            }
            catch (Exception e){
                System.out.println("Upload Failed :-(");
            }
        }
        System.out.println("Upload Successful");
    }

}