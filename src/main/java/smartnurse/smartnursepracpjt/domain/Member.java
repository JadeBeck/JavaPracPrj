package smartnurse.smartnursepracpjt.domain;

public class Member {  //Member 객체 생성 위해서 만드는 Member class

    private Long id;  //식별자는 ID 와 name 두 개(사용자가 정하는거 말고.. 시스템이 부여하는 id)

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