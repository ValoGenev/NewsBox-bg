package scam.dto;

import scam.model.Category;
import scam.model.SubCategory;

import java.util.Set;

public class CategoryWithSubCategoryDto {

    private Category mainCategory;

    private Set<SubCategory> subCategories;

    public CategoryWithSubCategoryDto(Category mainCategory, Set<SubCategory> subCategories) {
        this.mainCategory = mainCategory;
        this.subCategories = subCategories;
    }

    public Category getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(Category mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
