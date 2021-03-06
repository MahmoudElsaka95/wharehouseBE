/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wharehouse.wharehouseBE.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wharehouse.wharehouseBE.model.entities.BaseEntity;
import com.wharehouse.wharehouseBE.model.entities.Item;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "category")
public class Category extends BaseEntity implements Serializable{
 
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "category_code")
    private String categoryCode;
    @Size(max = 50)
    @Column(name = "category_namee")
    private String categoryNamee;
    @Size(max = 50)
    @Column(name = "category_namea")
    private String categoryNamea;
    
    @JsonIgnore
    @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
    private List<com.wharehouse.wharehouseBE.model.entities.Category> categoryList;
    @JsonIgnore
    @JoinColumn(name = "parent_id", referencedColumnName = "category_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private com.wharehouse.wharehouseBE.model.entities.Category parentId;
        

    public Category() {
    }

    public Category(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryNamee() {
        return categoryNamee;
    }

    public void setCategoryNamee(String categoryNamee) {
        this.categoryNamee = categoryNamee;
    }

    public String getCategoryNamea() {
        return categoryNamea;
    }

    public void setCategoryNamea(String categoryNamea) {
        this.categoryNamea = categoryNamea;
    }

    @XmlTransient
    public List<com.wharehouse.wharehouseBE.model.entities.Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<com.wharehouse.wharehouseBE.model.entities.Category> categoryList) {
        this.categoryList = categoryList;
    }

    public com.wharehouse.wharehouseBE.model.entities.Category getParentId() {
        return parentId;
    }

    public void setParentId(com.wharehouse.wharehouseBE.model.entities.Category parentId) {
        this.parentId = parentId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryCode != null ? categoryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof com.wharehouse.wharehouseBE.model.entities.Category)) {
            return false;
        }
        com.wharehouse.wharehouseBE.model.entities.Category other = (com.wharehouse.wharehouseBE.model.entities.Category) object;
        if ((this.categoryCode == null && other.categoryCode != null) || (this.categoryCode != null && !this.categoryCode.equals(other.categoryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wharehouse.wharehouseBE.model.entities.Category[ categoryCode=" + categoryCode + " ]";
    }
    
}
