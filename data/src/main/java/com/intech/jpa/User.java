package com.intech.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.intech.views.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({View.UserDataTable.class,View.MessageDataTable.class})
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    @NotEmpty
    @JsonView({View.UserDataTable.class,View.MessageDataTable.class})
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @JsonView({View.UserDataTable.class,View.MessageDataTable.class})
    private String lastName;

    @Column
    @NotEmpty
    @Email
    @JsonView({View.UserDataTable.class,View.MessageDataTable.class})
    private String email;

    @Column(name = "create_date")
    @JsonView({View.UserDataTable.class})
    private Date createAt;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "system_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<SystemRole> userRole;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contacts",
            joinColumns = {@JoinColumn(name = "owner_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "contact_id", referencedColumnName = "id")}
    )
    private List<User> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SystemRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<SystemRole> userRole) {
        this.userRole = userRole;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public String[] getRoleNames() {
        if (this.userRole != null) {
            String[] result = new String[this.userRole.size()];
            int i = 0;
            for (SystemRole role : this.userRole) {
                result[i] = role.getName();
                i++;
            }
            return result;
        }
        return new String[0];
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if(o !=null && o instanceof User){
           return (this.id.equals(((User) o).getId())) &&
                   (this.firstName.equals(((User) o).getFirstName())) &&
                   (this.lastName.equals(((User) o).getLastName())) &&
                   (this.password.equals(((User) o).getPassword())) &&
                   (this.email.equals(((User) o).getEmail())) &&
                   (this.createAt.equals(((User) o).getCreateAt()));
        }
        return false;
    }
}
