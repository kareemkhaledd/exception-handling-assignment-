import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try{
            String fileName=args[0];
            if(!fileName.endsWith(".arxml")){
                throw new NotVaildAutosarFileException("Invalid file extension");
            }
            File file=new File(fileName);
            FileInputStream inputStream =new FileInputStream(file);
            int R;
            StringBuilder stringBuilder=new StringBuilder();
            while ((R= inputStream.read())!=-1){
                stringBuilder.append((char) R);
            }
            String data=stringBuilder.toString();
            Scanner scanner=new Scanner(data);
            ArrayList<Autosar> autosars=new ArrayList<>();
            while (scanner.hasNext()){
                String line= scanner.nextLine();
                if (line.contains("<CONTAINER UUID")){
                    String containerID=line.substring(line.indexOf("UUID="),line.indexOf(">"));
                    String s=scanner.nextLine();
                    String shortName=s.substring(s.indexOf("r")+1,s.indexOf("</"));
                    String l=scanner.nextLine();
                    String longName=l.substring(l.indexOf(">")+1,l.indexOf("</"));
                    Autosar auto=new Autosar();
                    auto.setContainerID(containerID);
                    auto.setShortName(shortName);
                    auto.setLongName(longName);
                    autosars.add(auto);
                }
            }
            if (file.length()==0){
                throw new EmptyAutosarFileException("Empty file");
            }
            Collections.sort(autosars);
            String outName=fileName.substring(0,fileName.indexOf(".")) + "_mod.arxml";
            FileOutputStream outputStream =new FileOutputStream(outName);
            outputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            outputStream.write("<AUTOSAR>\n".getBytes());
            for(int i=0;i< autosars.size();i++){
                outputStream.write(autosars.get(i).toString().getBytes());
            }
            outputStream.write("</AUTOSAR>".getBytes());

        }catch (NotVaildAutosarFileException e){
            e=new NotVaildAutosarFileException("file not vali!d");
        }catch (FileNotFoundException e){
            e=new FileNotFoundException("file not found!");
        }catch (IOException e){
            e=new IOException("IO exception!");
        }catch (EmptyAutosarFileException e){
            e=new EmptyAutosarFileException("Empty file!");
        }
    }
}
