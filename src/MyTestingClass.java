public class MyTestingClass {
    private String name;
    private int id;

    public MyTestingClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int prime = 31;

        for (int i = 0; i < name.length(); i++) {
            hash = prime * hash + name.charAt(i);
        }

        hash = prime * hash + id;

        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id && this.name.equals(other.name);
    }
}
