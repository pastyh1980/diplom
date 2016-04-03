package com.github.vkpeb.model;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "bosses")
public class Boss {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "boss_id", length = 6, nullable = false)
    private long id;

    @Column(name = "family")
    private String family;

    @Column(name = "name")
    private String name;

    @Column(name = "otchestvo")
    private String otchestve;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL})
    @JoinColumn(name = "auth_id")
    private Auth auth;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtchestve() {
        return otchestve;
    }

    public void setOtchestve(String otchestve) {
        this.otchestve = otchestve;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
