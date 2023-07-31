package restapi.objects;

public class MockInfo {

    public MockInfo(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public MockInfo(){

    }

    private String name;
    private String surname;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "MockInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
