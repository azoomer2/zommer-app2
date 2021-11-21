package baseline;



public class item {
    private String serialNum;
    private String name;
    private String value;
    private String temp;

    public item(String serialNum, String name, String value)
    {
        this.serialNum = serialNum;
        this.name = name;
        this.value = value;
    }


    public String getTempString(){return temp;}

    public void setTempString(String temp){this.temp= temp;}

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
