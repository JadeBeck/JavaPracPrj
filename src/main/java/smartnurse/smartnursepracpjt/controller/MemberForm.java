package smartnurse.smartnursepracpjt.controller;

public class MemberForm {
    private String name;  //이러면.. 이 name이랑 createMemberForm.html의 name = "name"의 "name"과 매칭이 되어서 값이 들우옴

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
