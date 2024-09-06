package space.jdbc.basic;

public class Product {

    private int id;
    private String name;
    private int price;
    private String kind;


    public Product(){}

    public Product(int id, String name, int price, String kind) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.kind = kind;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", kind='" + kind + '\'' +
                '}';
    }
}
