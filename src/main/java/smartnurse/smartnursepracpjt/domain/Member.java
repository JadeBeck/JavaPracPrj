package smartnurse.smartnursepracpjt.domain;

import javax.persistence.*;

@Entity  //적고 javax.persistence.Entity 임포트하면 이 클래스는 이제부터 jpa가 관리하는 entity가 됨
public class Member {  //Member 객체 생성 위해서 만드는 Member class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 생성해주는게 IDENTITY
    private Long id;  //식별자는 ID 와 name 두 개(사용자가 정하는거 말고.. 시스템이 부여하는 id)

    //@Column(name = "username")  //컬럼명이 username이면 이렇게 적으면 됨
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}