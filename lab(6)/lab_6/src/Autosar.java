public class Autosar implements Comparable<Autosar>{
    private String containerID;
    private String shortName;
    private String longName;
    public String getContainerID(){
        return containerID;
    }
    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getLongName() {
        return longName;
    }
    public void setLongName(String longName) {
        this.longName = longName;
    }
    public Autosar(){}
    @Override
    public String toString(){
        return "    <CONTAINER UUID=" + this.getContainerID() + ">\n"
                + "     <SHORT-NAME>Container" + this.getShortName() + "</SHORT-NAME>\n"
                +"      <LONG-NAME>" + this.getLongName() + "</LONG-NAME>\n"
                +"  </CONTAINER>\n";
    }
    @Override
    public int compareTo(Autosar o){
        if(this.getShortName().charAt(0)>o.getShortName().charAt(0)){
            return 1;
        } else if (this.getShortName().charAt(0)<o.getShortName().charAt(0)) {
            return -1;
        }else {
            return 0;
        }
    }
}
