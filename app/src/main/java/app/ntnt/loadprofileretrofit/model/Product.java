package app.ntnt.loadprofileretrofit.model;

public class Product {
    private int id,idMeal,idcategory;
    private String strMeal,strMealThumb;

    public Product(int id, int idMeal, int idcategory, String strMeal, String strMealThumb) {
        this.id = id;
        this.idMeal = idMeal;
        this.idcategory = idcategory;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}
